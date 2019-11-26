package training.busboard.web;

import org.glassfish.jersey.jackson.JacksonFeature;
import training.busboard.BusStop;
import training.busboard.BusStopList;
import training.busboard.PostcodeInfo;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

public class BusInfo {
    private final String postcode;

    public BusInfo(String postcode) {
        this.postcode = postcode;
    }

    public String getPostcode() {
        return postcode;
    }

    public String textOfNextBuses(){
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
        busStopList.stopPoints = new ArrayList<BusStop>(busStopList.stopPoints.subList(0,2));
    }

}
