package com.eshc.backend.models;

import java.util.List;
import java.util.SortedSet;

public class WorkingGroup {

    private Long id;
    private String name;
    private String coConvener;
    private List<Long> actionPoints;
    private SortedSet<Long> priorityActions;
    private SortedSet<Long> nextMeetingActions;
}
