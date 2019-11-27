package training.busboard.web;

import org.glassfish.jersey.jackson.JacksonFeature;
import training.busboard.json.BusStopCollectionJSON;
import training.busboard.json.BusStopJSON;
import training.busboard.json.PostcodeCoordsJSON;
import training.busboard.json.ArrivalEventJSON;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class APIBusBoard {
    public final String postcode;
    public ArrayList<BusStop> busStops; //List of 2 BusStop's

    public APIBusBoard(String postcode) {
        this.postcode = postcode;

        //Get the lon and lat.
        Client client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();
        PostcodeCoordsJSON postcodeCoordsJSON = client.target(String.format("https://api.postcodes.io/postcodes/%s", this.postcode))
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(PostcodeCoordsJSON.class);

        //Find the nearest two bus stops.
        BusStopCollectionJSON busStopCollectionJSON = client.target(String.format("https://api.tfl.gov.uk/StopPoint?stopTypes=NaptanPublicBusCoachTram&modes=bus&radius=500&lat=%s&lon=%s",
                postcodeCoordsJSON.result.latitude, postcodeCoordsJSON.result.longitude))
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(BusStopCollectionJSON.class);

        busStops = BusStop.nearestBusStops(busStopCollectionJSON, 2);

        for (BusStop busStop : busStops) {

            //Obtain data from TfL - Bus Stop with given ID:
            ArrayList<ArrivalEventJSON> arrivalEventJSONList = client.target(String.format("https://api.tfl.gov.uk/StopPoint/%s/Arrivals", busStop.naptanId))
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get(new GenericType<ArrayList<ArrivalEventJSON>>() {
                    });
            busStop.arrivalEvents = ArrivalEvent.nextArrivalEvents(arrivalEventJSONList,5);
        }
    }

    public String getPostcode() {
        return postcode;
    }

    public ArrayList<BusStop> getBusStops() {
        return busStops;
    }
}
