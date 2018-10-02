package io.pivotal.pal.tracker;


import java.util.*;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    private HashMap<Long, TimeEntry> list;
    private long incrementCounter = 1;

    public InMemoryTimeEntryRepository(){
        list = new HashMap();
    }

    @Override
    public TimeEntry find(Long id) {

        return list.get(new Long(id));
    }

    @Override
    public TimeEntry create(TimeEntry timeEntry) {

        timeEntry.setId(new Long(incrementCounter));
        list.put(new Long(incrementCounter),timeEntry);
        incrementCounter++;
        return timeEntry;
    }

    @Override
    public List<TimeEntry> list() {
        return new ArrayList<TimeEntry> (list.values());
    }

    @Override
    public TimeEntry update(Long id, TimeEntry timeEntry) {

        timeEntry.setId(id);
        list.replace(new Long(id), timeEntry);

        return timeEntry;
    }

    @Override
    public Long delete(Long id) {
        Long Id = new Long(id);
        if (!list.isEmpty() && list.containsKey(Id)) {
            list.remove(Id);
        } else {
            Id = new Long(-1);
        }
        return Id;
    }

}
