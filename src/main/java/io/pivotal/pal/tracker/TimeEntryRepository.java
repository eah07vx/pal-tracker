package io.pivotal.pal.tracker;

import io.pivotal.pal.tracker.TimeEntry;

import java.util.List;

public interface TimeEntryRepository {
    TimeEntry find(Long id);

    TimeEntry create(TimeEntry timeEntry);

    List<TimeEntry> list();

    TimeEntry update(Long id, TimeEntry timeEntry);

    Long delete(Long id);
}
