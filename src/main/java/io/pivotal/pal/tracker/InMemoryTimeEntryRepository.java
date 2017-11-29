package io.pivotal.pal.tracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    private HashMap<Long, TimeEntry> timeEntries = new HashMap<>();

    public TimeEntry create(TimeEntry timeEntry) {
        timeEntry.setId(getFreshId());
        this.timeEntries.put(timeEntry.getId(), timeEntry);

        return timeEntry;
    }

    public TimeEntry find(Long id) {

        return this.timeEntries.get(id);
    }

    public List<TimeEntry> list() {

        return new ArrayList<>(this.timeEntries.values());
    }

    public TimeEntry update(Long id, TimeEntry timeEntry) {
        timeEntry.setId(id);
        this.timeEntries.replace(id, timeEntry);

        return timeEntry;
    }

    public void delete(Long id) {
        this.timeEntries.remove(id);
    }

    private Long getFreshId() {
        return this.timeEntries.size() + 1L;
    }
}
