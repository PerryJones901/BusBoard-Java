package training.busboard;

import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class BusStopList {
    public ArrayList<BusStop> stopPoints;
}