package io.pivotal.pal.tracker;

import java.util.LinkedList;

public interface TimeEntryRepository {
    TimeEntry find(long id);

    TimeEntry create(TimeEntry timeEntry);

    LinkedList list();

    TimeEntry update(long id, TimeEntry timeEntry);

    void delete(long id);
}
