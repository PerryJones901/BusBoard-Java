package training.busboard.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class BusStopJSON {
    public String naptanId; //JSON
    public String commonName;
}