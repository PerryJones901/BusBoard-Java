package training.busboard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@JsonIgnoreProperties(ignoreUnknown = true)

public class StopInfo {

    StopInfo(){

    }

    public String lineId;
    public String destinationName;
    public String expectedArrival;
    public LocalDateTime expectedArrivalTime;

    public void convertToTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
        expectedArrivalTime = LocalDateTime.parse(expectedArrival, formatter);
    }
}
