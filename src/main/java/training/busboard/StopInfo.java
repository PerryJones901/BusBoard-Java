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
    public String expectedArrival; //Here just so JSON works.
    public LocalDateTime expectedArrivalTime; //This is what we really output.

    public void convertToTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
        expectedArrivalTime = LocalDateTime.parse(expectedArrival, formatter);
    }

    public int isBefore(StopInfo stopInfo2){
        if(this.expectedArrivalTime.isBefore(stopInfo2.expectedArrivalTime)) return -1;
        else if (this.expectedArrivalTime.isAfter(stopInfo2.expectedArrivalTime)) return 1;
        else return 0;
    }
}
