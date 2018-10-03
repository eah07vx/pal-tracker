package io.pivotal.pal.tracker;

import io.pivotal.pal.tracker.TimeEntry;
import io.pivotal.pal.tracker.TimeEntryRepository;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TimeEntryController {
    private TimeEntryRepository ter;
    private final CounterService counter;
    private final GaugeService gauge;

    public TimeEntryController(TimeEntryRepository ter,
                               CounterService counter,
                               GaugeService gauge) {
         this.ter = ter;
         this.counter = counter;
         this.gauge = gauge;
    }

    @PostMapping("/time-entries")
    public ResponseEntity create(@RequestBody TimeEntry timeEntry) {
        ResponseEntity entity;
        try {
            TimeEntry newEntry = ter.create(timeEntry);
            entity = new ResponseEntity(newEntry,HttpStatus.CREATED);
            counter.increment("TimeEntry.created");
            gauge.submit("timeEntries.count", ter.list().size());


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
                counter.increment("TimeEntry.read");
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
                counter.increment("TimeEntry.listed");

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
                counter.increment("TimeEntry.updated");
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
                counter.increment("TimeEntry.deleted");
                gauge.submit("timeEntries.count", ter.list().size());
            }


        } catch (Exception ex) {
            entity = new ResponseEntity(HttpStatus.EXPECTATION_FAILED);
            System.out.println(ex.getLocalizedMessage());
        }



        return entity;
    }

}
