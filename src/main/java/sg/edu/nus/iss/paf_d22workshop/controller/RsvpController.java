package sg.edu.nus.iss.paf_d22workshop.controller;

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
        return rsvpRepo.findAll();
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> getRSVPCount(){
        Integer countRsvp = rsvpRepo.count();

        return ResponseEntity.ok().body(countRsvp);
    }

    @GetMapping("/{rsvp-id}")
    public ResponseEntity<RSVP> getByName(@PathVariable("rsvp-id") int id){
        RSVP rsvp = rsvpRepo.findByName(id);

        if (rsvp!=null){
            return new ResponseEntity<RSVP> (rsvp,HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping
    public ResponseEntity<Boolean> saveRSVP (@RequestBody RSVP rsvp){
        Boolean saved = false;
        saved = rsvpRepo.save(rsvp);

        if (saved){
            return ResponseEntity.ok().body(saved);
        }else{
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping
    public ResponseEntity<Boolean> updateRSVP(@PathVariable("rsvp-id") int id, @RequestBody RSVP rsvp){
        RSVP r = rsvpRepo.findByName(id);
        
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
