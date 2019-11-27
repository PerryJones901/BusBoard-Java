package training.busboard.web;

import training.busboard.json.ArrivalEventJSON;
import training.busboard.json.BusStopCollectionJSON;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ArrivalEvent {

    public String lineId;
    public String destinationName;
    public LocalDateTime expectedArrival; //This is what we really output.

    ArrivalEvent(ArrivalEventJSON arrivalEventJSON){
        this.lineId = arrivalEventJSON.lineId;
        this.destinationName = arrivalEventJSON.destinationName;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
        this.expectedArrival = LocalDateTime.parse(arrivalEventJSON.expectedArrival, formatter);
    }

    public int isBefore(ArrivalEvent arrivalEvent2){
        if(this.expectedArrival.isBefore(arrivalEvent2.expectedArrival)) return -1;
        else if (this.expectedArrival.isAfter(arrivalEvent2.expectedArrival)) return 1;
        else return 0;
    }

    public static ArrayList<ArrivalEvent> nextArrivalEvents(ArrayList<ArrivalEventJSON> nextEvents, int num){
        ArrayList<ArrivalEvent> arrivalEventList = new ArrayList<ArrivalEvent>();
        for(ArrivalEventJSON event : nextEvents){
            arrivalEventList.add(new ArrivalEvent(event));
        }
        //Sort for first <num> earliest times:
        List<ArrivalEvent> sortedArrivalEventList = arrivalEventList.stream().
                sorted((p1, p2) -> p1.isBefore(p2)).limit(num).collect(Collectors.toList());
        return (ArrayList<ArrivalEvent>) sortedArrivalEventList;
    }

    public String getLineId(){
        return lineId;
    }

    public String getDestinationName(){
        return destinationName;
    }

    public LocalDateTime getExpectedArrival(){
        return expectedArrival;
    }
}