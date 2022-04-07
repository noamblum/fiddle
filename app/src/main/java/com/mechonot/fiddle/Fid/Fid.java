package com.mechonot.fiddle.Fid;
import java.time.LocalDate;
import java.util.Calendar;

public class Fid {
    private final LocalDate creationDate;
    private final int priority;//[1,3]
    private final int duration;
    private final FidType fidType;
    private final String body;
    private final int interval;
    private int intervalsLeft;
    protected Long id;
    protected LocalDate deadline;

    public Fid(Long id,
               LocalDate creationDate,
               LocalDate deadline,
               int priority,
               int duration,
               FidType fidType,
               String body,
               int interval,
               int numOfRecurrences) {
        this.id = id;
        this.creationDate = creationDate;
        this.deadline = deadline;
        this.priority = priority;
        this.duration = duration;
        this.fidType = fidType;
        this.body = body;
        this.interval = interval;
        this.intervalsLeft = numOfRecurrences;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public LocalDate getDeadLine() {
        return deadline;
    }

    public Integer getPriority() {
        return priority;
    }

    public Integer getDuration() {
        return duration;
    }

    public String getBody() {
        return body;
    }

    public FidType getFidId() {
        return fidType;
    }

    public Fid done() {
        if (--intervalsLeft == 0) return null;
        deadline = deadline.plusDays(interval);
        this.id = FidIdGenerator.getNextId();
        return this;
    }
}
