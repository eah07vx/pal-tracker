package io.pivotal.pal.tracker.bean;

import io.pivotal.pal.tracker.bean.TimeEntry;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public interface TimeEntryRepository {
    TimeEntry find(long id);

    TimeEntry create(TimeEntry timeEntry);

    List<TimeEntry> list();

    TimeEntry update(long id, TimeEntry timeEntry);

    Long delete(long id);
}
