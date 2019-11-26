package training.busboard;

import org.glassfish.jersey.jackson.JacksonFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String args[]) {

        //User input:
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter your postcode:");
        String postcode = scanner.nextLine(); //TO-DO: Validate later.

        //Get the lon and lat.
        Client client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();
        PostcodeInfo postcodeInfo = client.target(String.format("https://api.postcodes.io/postcodes/%s", postcode))
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(PostcodeInfo.class);

        //Find the nearest two bus stops.
        BusStopList busStopList = client.target(String.format("https://api.tfl.gov.uk/StopPoint?stopTypes=NaptanPublicBusCoachTram&modes=bus&radius=1000&lat=%s&lon=%s",
                postcodeInfo.result.latitude, postcodeInfo.result.longitude))
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(BusStopList.class);
        //SORT ERROR HERE:
        System.out.println(busStopList.stopPoints.get(0).naptanId);

        //Obtain data from TfL - Bus Stop with given ID:
        ArrayList<StopInfo> stopInfoList = client.target("https://api.tfl.gov.uk/StopPoint/490008660N/Arrivals")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(new GenericType<ArrayList<StopInfo>>() {});
        for(StopInfo stop: stopInfoList){
            stop.convertToTime();
        }

        //Sort for 5 earliest times:
       List<StopInfo> sortedStopInfoList = stopInfoList.stream().
               sorted((p1, p2)->p1.isBefore(p2)).limit(5).collect(Collectors.toList());

        for(StopInfo stop: sortedStopInfoList){
            System.out.printf( "Bus %s towards %s is arriving at %s\n", stop.lineId, stop.destinationName, stop.expectedArrivalTime.toString());
        }
    }



}