package training.busboard;

import org.glassfish.jersey.jackson.JacksonFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

public class Main {
    public static void main(String args[]) {

        Client client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();
        ArrayList<StopInfo> stopInfoList = client.target("https://api.tfl.gov.uk/StopPoint/490008660N/Arrivals")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(new GenericType<ArrayList<StopInfo>>() {});

       List<StopInfo> sortedStopInfoList = stopInfoList.stream().
               sorted(Comparator.com)

        for(StopInfo stop: stopInfoList){
            stop.convertToTime();
            System.out.printf( "Bus %s towards %s is arriving at %s\n", stop.lineId, stop.destinationName, stop.expectedArrivalTime.toString());
        }
    }



}