package training.busboard.json;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class BusStopCollectionJSON {
    public ArrayList<BusStopJSON> stopPoints; //JSON Name.
}