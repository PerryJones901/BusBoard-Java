package training.busboard;

import org.glassfish.jersey.jackson.JacksonFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

public class Main {
    public static void main(String args[]) {

        Client client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();
        String request = client.target("https://api.tfl.gov.uk/StopPoint/490008660N")
                .request(MediaType.APPLICATION_JSON)
                .get(String.class);

        System.out.println(request);
    }
}	
