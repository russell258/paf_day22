package sg.edu.nus.iss.paf_d22workshop.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sg.edu.nus.iss.paf_d22workshop.model.RSVP;
import sg.edu.nus.iss.paf_d22workshop.repository.RsvpRepository;

@RestController
@RequestMapping("/api/rsvp")
public class RsvpController {
    
    @Autowired
    RsvpRepository rsvpRepo;

    @GetMapping
    public List<RSVP> getAllRSVP(){
        List<RSVP> rsvps = new ArrayList<>();
        rsvps = rsvpRepo.findAll();

        return rsvps;
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> getRSVPCount(){
        Integer countRsvp = rsvpRepo.count();

        return ResponseEntity.ok().body(countRsvp);
    }

    @GetMapping("/{rsvp-name}")
    public ResponseEntity<RSVP> getByName(@PathVariable("rsvp-name") String name){
        RSVP rsvp = rsvpRepo.findByName(name);

        if (rsvp!=null){
            return new ResponseEntity<RSVP>(rsvp,HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Boolean> saveRSVP (@RequestBody RSVP rsvp){
        Boolean saved = false;
        saved = rsvpRepo.save(rsvp);

        if (saved){
            return new ResponseEntity<>(saved, HttpStatus.CREATED);
        }else{
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping
    public ResponseEntity<Boolean> updateRSVP(@PathVariable("rsvp-name") String name, @RequestBody RSVP rsvp){
        RSVP r = rsvpRepo.findByName(name);
        
        Boolean updated = false;

        if (r!=null){
            updated = rsvpRepo.update(rsvp);
        }
        if (updated){
            return ResponseEntity.ok().body(updated);
        }else{
            return ResponseEntity.ofNullable(updated);
        }
    }

}
