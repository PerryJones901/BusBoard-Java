package training.busboard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class StopInfo {

    StopInfo(){

    }
    public String $type;
    public String lineId;
    public String destinationName;
    public String expectedArrival;
}
