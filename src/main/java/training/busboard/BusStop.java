package training.busboard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)

public class BusStop {

    public String naptanId;
    public ArrayList<StopInfo> nextFiveBuses;
    public float distance; //Potential deletion.
}