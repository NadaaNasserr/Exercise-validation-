package com.example.eventvaildation.Controller;


import com.example.eventvaildation.Model.Event;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/event")
public class EventController {
    ArrayList<Event> events = new ArrayList<>();



    @GetMapping("/get")

    public ResponseEntity getEvents() {

        return ResponseEntity.status(HttpStatus.OK).body(events);

    }


    @PostMapping("/add")
    public ResponseEntity addEvent(@Valid @RequestBody Event event , Errors errors) {
        if(errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);

        }
        events.add(event);
        return ResponseEntity.status(HttpStatus.OK).body("user added");
    }


    @PutMapping("/update/{index}")
    public ResponseEntity updateEvent(@PathVariable int index,@Valid @RequestBody Event event, Errors errors) {
        if(errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        events.set(index, event);
        return ResponseEntity.status(HttpStatus.OK).body("user update");


    }

    @DeleteMapping("/delete/{index}")
    public ResponseEntity deleteEvent(@PathVariable int index) {
        events.remove(index);
        return ResponseEntity.status(HttpStatus.OK).body("Event deleted");

    }



    @PutMapping("change/{index}/{capacity}")
    public ResponseEntity changeCapacity(@PathVariable int index, @PathVariable int capacity ) {

        if (events.size() > index) {

            events.get(index).setCapacity(capacity);
            return ResponseEntity.status(HttpStatus.OK).body(events.get(index));

        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("array Index Out Of Bounds");

    }


    @GetMapping("search/{id}")
    public ResponseEntity search(@PathVariable String id) {
        for (Event e : events)
            if (e.getId().equals(id)) {
                return ResponseEntity.status(HttpStatus.OK).body(e);
            }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id does not found");
    }
}

