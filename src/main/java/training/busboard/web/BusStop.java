package training.busboard.web;

import training.busboard.json.BusStopCollectionJSON;
import training.busboard.json.BusStopJSON;

import java.util.ArrayList;
import java.util.List;

public class BusStop {
    public String naptanId;
    public ArrayList<ArrivalEvent> arrivalEvents;
    public String commonName;

    BusStop(BusStopJSON busStopJSON){
        this.naptanId = busStopJSON.naptanId;
        this.commonName = busStopJSON.commonName;
    }

    public static ArrayList<BusStop> nearestBusStops(BusStopCollectionJSON busStopCollectionJSON, int num) {
        ArrayList<BusStop> busStopList = new ArrayList<BusStop>();
        int count = 0;
        for(BusStopJSON busStopJSON : busStopCollectionJSON.stopPoints){
            busStopList.add(new BusStop(busStopJSON));
            count++;
            if(count >= num) break;
        }
        return busStopList;
    }

    public String getNaptanId() {
        return this.naptanId;
    }

    public ArrayList<ArrivalEvent> getArrivalEvents() {
        return this.arrivalEvents;
    }
}
