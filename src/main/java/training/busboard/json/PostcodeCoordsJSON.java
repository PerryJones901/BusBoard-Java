package training.busboard.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class PostcodeCoordsJSON {
    public GlobalCoordsJSON result; //JSON
}
