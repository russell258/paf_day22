package sg.edu.nus.iss.paf_d22workshop.RestTemplate;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import sg.edu.nus.iss.paf_d22workshop.model.RSVP;

public class RsvpRestTemplate {
    
    private static final String RSVP_ENDPOINT_URL = "http://localhost:8080/api/rsvps";

    private static RestTemplate restTemplate = new RestTemplate();

    public List<RSVP> getRSVPS(){

        HttpEntity<String> entity = new HttpEntity<String>("parameters");
        ResponseEntity<List<RSVP>> results = restTemplate.exchange(RSVP_ENDPOINT_URL, HttpMethod.GET, entity, new ParameterizedTypeReference<List<RSVP>>() {
            });
        return results.getBody();
    }


    public Boolean createRSVP(RSVP newRSVP){
        ResponseEntity<Boolean> result = restTemplate.postForEntity(RSVP_ENDPOINT_URL, newRSVP, Boolean.class);
        return result.getBody();
    }

    public Boolean updateRSVP(RSVP rsvp){
        HttpEntity<String> entity = new HttpEntity<String>("parameters");
        ResponseEntity<Boolean> result = restTemplate.exchange(RSVP_ENDPOINT_URL,HttpMethod.PUT,entity,Boolean.class);

        return result.getBody();
    }

    public RSVP findById(int id){
        ResponseEntity<RSVP> result = restTemplate.getForEntity(RSVP_ENDPOINT_URL + "/" + id, RSVP.class);
        return result.getBody();
    }

}
