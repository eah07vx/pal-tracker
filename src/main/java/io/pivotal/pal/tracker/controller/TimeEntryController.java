package io.pivotal.pal.tracker.controller;

import io.pivotal.pal.tracker.bean.TimeEntry;
import io.pivotal.pal.tracker.bean.TimeEntryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TimeEntryController {
    private TimeEntryRepository ter;

    public TimeEntryController(TimeEntryRepository ter) {
     this.ter = ter;
    }

    @PostMapping("/time-entries")
    public ResponseEntity create(@RequestBody TimeEntry timeEntry) {
        ResponseEntity entity;
        try {
            TimeEntry newEntry = ter.create(timeEntry);
            entity = new ResponseEntity(newEntry,HttpStatus.CREATED);

        } catch (Exception ex) {
            entity = new ResponseEntity(HttpStatus.EXPECTATION_FAILED);
            System.out.println("Error Creating Time Entry");
            System.out.println(ex.getLocalizedMessage());
        }
        return entity;
    }

    @GetMapping("/time-entries/{id}")
    public ResponseEntity read(@PathVariable Long id) {
        ResponseEntity entity;
        try {
            TimeEntry timeEntry = ter.find(id.longValue());
            if (timeEntry != null) {
                entity = new ResponseEntity(timeEntry, HttpStatus.OK);
            } else {
                entity = new ResponseEntity(null, HttpStatus.NOT_FOUND);
            }


        } catch (Exception ex) {
            entity = new ResponseEntity(HttpStatus.EXPECTATION_FAILED);
            System.out.println("Error Creating Time Entry");
            System.out.println(ex.getLocalizedMessage());
        }
        return entity;
    }

    @GetMapping("/time-entries")
    public ResponseEntity<List<TimeEntry>> list() {
        ResponseEntity<List<TimeEntry>> entity;
        try {
            List terList = ter.list();
            if (terList != null) {
                entity = new ResponseEntity(terList, HttpStatus.OK);

            } else {
                throw new Exception ("No List Returned");
            }

        } catch (Exception ex) {
            entity = new ResponseEntity(HttpStatus.EXPECTATION_FAILED);
            System.out.println(ex.getLocalizedMessage());
        }
        return entity;
    }

    @PutMapping("/time-entries/{id}")
    public ResponseEntity update(@PathVariable long id, @RequestBody TimeEntry timeEntry) {
        ResponseEntity entity;
        try {
            TimeEntry updatedEntry = ter.update(id, timeEntry);
            if (updatedEntry != null && timeEntry.equals(updatedEntry)) {
                entity = new ResponseEntity(updatedEntry, HttpStatus.OK);
            } else {
                entity = new ResponseEntity(timeEntry, HttpStatus.NOT_FOUND);
            }

        } catch (Exception ex) {
            entity = new ResponseEntity(HttpStatus.EXPECTATION_FAILED);
            System.out.println(ex.getLocalizedMessage());
        }



        return entity;
    }

    @DeleteMapping("/time-entries/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        ResponseEntity entity;
        try {
            Long iD = ter.delete(id.longValue());
            if ( iD.equals(new Long(-1)) ) {
                entity = new ResponseEntity(HttpStatus.EXPECTATION_FAILED);
            } else {
                entity = new ResponseEntity(HttpStatus.NO_CONTENT);
            }


        } catch (Exception ex) {
            entity = new ResponseEntity(HttpStatus.EXPECTATION_FAILED);
            System.out.println(ex.getLocalizedMessage());
        }



        return entity;
    }

}
