package training.busboard.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class ArrivalEventJSON {

    public String lineId;
    public String destinationName;
    public String expectedArrival; //Here just so JSON works.

//    public LocalDateTime expectedArrivalTime; //This is what we really output.
//
//    public void convertToTime() {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
//        expectedArrivalTime = LocalDateTime.parse(expectedArrival, formatter);
//    }
//
//    public int isBefore(ArrivalEvent arrivalEvent2){
//        if(this.expectedArrivalTime.isBefore(arrivalEvent2.expectedArrivalTime)) return -1;
//        else if (this.expectedArrivalTime.isAfter(arrivalEvent2.expectedArrivalTime)) return 1;
//        else return 0;
//    }
}
