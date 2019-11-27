package training.busboard;

import org.glassfish.jersey.jackson.JacksonFeature;
import training.busboard.json.ArrivalEventJSON;
import training.busboard.json.BusStopCollectionJSON;
import training.busboard.json.PostcodeCoordsJSON;

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

//        //User input:
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("Please enter your postcode:");
//        String postcode = scanner.nextLine(); //TO-DO: Validate later.
//
//        //Get the lon and lat.
//        Client client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();
//        PostcodeCoordsJSON postcodeCoordsJSON = client.target(String.format("https://api.postcodes.io/postcodes/%s", postcode))
//                .request(MediaType.APPLICATION_JSON_TYPE)
//                .get(PostcodeCoordsJSON.class);
//
//        //Find the nearest two bus stops.
//        BusStopCollectionJSON busStopList = client.target(String.format("https://api.tfl.gov.uk/StopPoint?stopTypes=NaptanPublicBusCoachTram&modes=bus&lat=%s&lon=%s",
//                postcodeCoordsJSON.result.latitude, postcodeCoordsJSON.result.longitude))
//                .request(MediaType.APPLICATION_JSON_TYPE)
//                .get(BusStopCollectionJSON.class);
//        //SORT ERROR HERE:
//        System.out.println(busStopList.busStops.get(0).naptanId);
//
//        //Obtain data from TfL - Bus Stop with given ID:
//        ArrayList<ArrivalEventJSON> arrivalEventJSONList = client.target("https://api.tfl.gov.uk/StopPoint/490008660N/Arrivals")
//                .request(MediaType.APPLICATION_JSON_TYPE)
//                .get(new GenericType<ArrayList<ArrivalEventJSON>>() {});
//        for(ArrivalEventJSON stop: arrivalEventJSONList){
//            stop.convertToTime();
//        }
//
//        //Sort for 5 earliest times:
//       List<ArrivalEventJSON> sortedArrivalEventJSONList = arrivalEventJSONList.stream().
//               sorted((p1, p2)->p1.isBefore(p2)).limit(5).collect(Collectors.toList());
//
//        for(ArrivalEventJSON stop: sortedArrivalEventJSONList){
//            System.out.printf( "Bus %s towards %s is arriving at %s\n", stop.lineId, stop.destinationName, stop.expectedArrivalTime.toString());
//        }
 }



}