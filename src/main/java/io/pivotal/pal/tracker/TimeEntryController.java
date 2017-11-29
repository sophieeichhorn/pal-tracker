package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/time-entries")
public class TimeEntryController {

    TimeEntryRepository timeEntryRepository;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }

    @PostMapping
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntry) {

        return new ResponseEntity<>(timeEntryRepository.create(timeEntry), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable("id") Long id) {
        HttpStatus statusCode = HttpStatus.OK;

        if(timeEntryRepository.find(id) == null) {
            statusCode = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<>(timeEntryRepository.find(id), statusCode);
    }

    @PutMapping("{id}")
    public ResponseEntity<TimeEntry> update(@PathVariable("id") Long id, @RequestBody TimeEntry timeEntry) {
        HttpStatus statusCode = HttpStatus.OK;
        timeEntry = timeEntryRepository.update(id, timeEntry);

        if(timeEntry == null) {
            statusCode = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<>(timeEntry, statusCode);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<TimeEntry> delete(@PathVariable("id") Long id) {
        TimeEntry timeEntry = timeEntryRepository.find(id);
        timeEntryRepository.delete(id);

        return new ResponseEntity<>(timeEntry, HttpStatus.NO_CONTENT);
    }

    @GetMapping("")
    public ResponseEntity<List<TimeEntry>> list() {

        return new ResponseEntity<>(timeEntryRepository.list(), HttpStatus.OK);
    }
}
