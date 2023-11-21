package com.example.trackervalidation.Controller;
import com.example.trackervalidation.Model.Tracker;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/tracker")
public class TrackerController {

    ArrayList<Tracker> trackers = new ArrayList<>();


    @GetMapping("/get")
    public ResponseEntity getTracker() {
        return ResponseEntity.status(HttpStatus.OK).body(trackers);

    }


    @PostMapping("/add")
    public ResponseEntity addTracker(@Valid @RequestBody Tracker tracker, Errors errors) {
        if(errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        trackers.add(tracker);
        return ResponseEntity.status(200).body("user added");

    }


    @PutMapping("/update/{index}")
    public ResponseEntity updateTracker(@PathVariable int index,@Valid @RequestBody Tracker tracker, Errors errors) {
        if(errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        trackers.set(index,tracker);
        return ResponseEntity.status(HttpStatus.OK).body("tracker updated");

    }

    @DeleteMapping("/delete/{index}")
    public ResponseEntity deleteTracker(@PathVariable int index) {
        trackers.remove(index);
        return ResponseEntity.status(HttpStatus.OK).body("tracker deleted");

    }



    @PutMapping("/change/{index}")
    public ResponseEntity changeStatus(@PathVariable int index) {


        if (trackers.get(index).getStatus().equals("Started")) {
            trackers.get(index).setStatus("in Progress");
        } else if (trackers.get(index).getStatus().equals("in Progress")) {
            trackers.get(index).setStatus("Completed");
        }
        return ResponseEntity.status(HttpStatus.OK).body(trackers.get(index));
    }



    @GetMapping("/search/{title}")
    public ResponseEntity searchTitle(@PathVariable String title) {

        ArrayList<Tracker> t1 = new ArrayList<>();
        for (Tracker t : trackers) {
            if (t.getTitle().equals(title)) {
                t1.add(t);

            }
        }
                return ResponseEntity.status(HttpStatus.OK).body(t1);


        }



    @GetMapping("/display/{company}")
    public ResponseEntity displayAllProject(@PathVariable String company) {
        ArrayList<Tracker> t1 = new ArrayList<>();

        for (Tracker t : trackers) {
            if (t.getCompanyName().equals(company)) {
                t1.add(t);
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(t1);
    }


}



