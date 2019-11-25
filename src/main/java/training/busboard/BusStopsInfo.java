package training.busboard;

import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class BusStopsInfo {
    public ArrayList<BusStop> busStops;
}