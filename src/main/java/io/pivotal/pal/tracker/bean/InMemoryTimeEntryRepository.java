package io.pivotal.pal.tracker;


import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    private TimeEntry timeEntry;
    private HashMap<Long, TimeEntry> list;


    public InMemoryTimeEntryRepository(){

    }

    @Override
    public TimeEntry find(long id) {

        return list.get(new Long(id));
    }

    @Override
    public TimeEntry create(TimeEntry timeEntry) {

        this.timeEntry = timeEntry;
        this.list.put(timeEntry.getId(),timeEntry);
        return timeEntry;
    }

    @Override
    public LinkedList list() {
        return (LinkedList)list.values();
    }

    @Override
    public TimeEntry update(long id, TimeEntry timeEntry) {

        this.delete(id);
        list.put(new Long(id), timeEntry);

        return timeEntry;
    }

    @Override
    public void delete(long id) {
        Long Id = new Long(id);
        if (!list.isEmpty() && list.containsKey(Id)) {
            list.remove(Id);
        }
    }

}
