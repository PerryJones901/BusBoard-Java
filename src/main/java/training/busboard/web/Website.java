package training.busboard.web;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import training.busboard.PostcodeInfo;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

@Controller
@EnableAutoConfiguration
public class Website {

    @RequestMapping("/")
    ModelAndView home() {
        return new ModelAndView("index");
    }

    @RequestMapping("/busInfo")
    ModelAndView busInfo(@RequestParam("postcode") String postcode) {

        //Get the lon and lat.
        Client client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();
        PostcodeInfo postcodeInfo = client.target(String.format("https://api.postcodes.io/postcodes/%s", postcode))
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(PostcodeInfo.class);


        //Navigate to info.html page.
        return new ModelAndView("info", "busInfoW", new BusInfo(postcode)) ;
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Website.class, args);
    }

}