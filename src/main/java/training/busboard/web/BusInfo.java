package training.busboard.web;

import org.glassfish.jersey.jackson.JacksonFeature;
import training.busboard.BusStop;
import training.busboard.BusStopList;
import training.busboard.PostcodeInfo;
import training.busboard.StopInfo;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BusInfo {
    private final String postcode;
    private List<BusStop> busStops;

    public BusInfo(String postcode) {
        this.postcode = postcode;
    }

    public String getPostcode() {
        return postcode;
    }

    public ArrayList<String> getOutput() {
        return this.output;
    }

    public ArrayList<String> textOfNextBuses() {
        ArrayList<String> output = new ArrayList<String>();
        //Get the lon and lat.
        Client client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();
        PostcodeInfo postcodeInfo = client.target(String.format("https://api.postcodes.io/postcodes/%s", this.postcode))
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(PostcodeInfo.class);

        //Find the nearest two bus stops.
        BusStopList busStopList = client.target(String.format("https://api.tfl.gov.uk/StopPoint?stopTypes=NaptanPublicBusCoachTram&modes=bus&radius=500&lat=%s&lon=%s",
                postcodeInfo.result.latitude, postcodeInfo.result.longitude))
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(BusStopList.class);
        busStopList.stopPoints = new ArrayList<BusStop>(busStopList.stopPoints.subList(0, 2));
        for (BusStop busStop : busStopList.stopPoints) {
            System.out.println("We are now looking at busStop: " + busStop.naptanId);

            //Obtain data from TfL - Bus Stop with given ID:
            ArrayList<StopInfo> stopInfoList = client.target(String.format("https://api.tfl.gov.uk/StopPoint/%s/Arrivals", busStop.naptanId))
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get(new GenericType<ArrayList<StopInfo>>() {
                    });
            for (StopInfo stop : stopInfoList) {
                stop.convertToTime();
            }

            //Sort for 5 earliest times:
            List<StopInfo> sortedStopInfoList = stopInfoList.stream().
                    sorted((p1, p2) -> p1.isBefore(p2)).limit(5).collect(Collectors.toList());

            for (StopInfo stop : sortedStopInfoList) {
                output.add(String.format("Bus %s towards %s is arriving at %s\n", stop.lineId, stop.destinationName, stop.expectedArrivalTime.toString()));
            }
        }
        return output;
    }
}
