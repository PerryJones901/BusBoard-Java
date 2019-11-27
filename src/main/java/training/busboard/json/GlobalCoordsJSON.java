package training.busboard.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class GlobalCoordsJSON {
    public String longitude;    //JSON
    public String latitude;     //JSON
}
