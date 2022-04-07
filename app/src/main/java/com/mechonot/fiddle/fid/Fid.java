package com.mechonot.fiddle.fid;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;

public class Fid {
    private final LocalDate creationDate;
    private final int priority;//[1,3]
    private final int duration;
    private final FidType fidType;
    private final BodyType bodyType;
    private final String description;
    private final String body;
    private final int interval;
    private int intervalsLeft;
    protected int id;

    public Fid(int id, LocalDate creationDate, int priority, int duration,
               FidType fidType, BodyType bodyType, String description,String body, int interval, int numOfRecurrences) {
        this.id = id;
        this.creationDate = creationDate;
        this.priority = priority;
        this.duration = duration;
        this.fidType = fidType;
        this.bodyType = bodyType;
        this.body = body;
        this.description = description;
        this.interval = interval;
        this.intervalsLeft = numOfRecurrences;
    }

    public Fid(String id, String creationDate, String priority, String duration,
               String fidType, String bodyType, String description,String body, String interval, String numOfRecurrences) {
        this.id = Integer.parseInt(id);
        this.creationDate = LocalDate.parse(creationDate);
        this.priority = Integer.parseInt(priority);
        this.duration = Integer.parseInt(duration);
        this.fidType = FidType.valueOf(fidType);
        this.bodyType = BodyType.valueOf(bodyType);
        this.description = description;
        this.body = body;
        this.interval = Integer.parseInt(interval);
        this.intervalsLeft = Integer.parseInt(numOfRecurrences);
    }

    public Fid(LocalDate creationDate, int priority, int duration, FidType fidType,
               BodyType bodyType,String description, String body, int interval, int numOfRecurrences) {
        this.id = FidIdGenerator.getNextId();
        this.creationDate = creationDate;
        this.priority = priority;
        this.duration = duration;
        this.fidType = fidType;
        this.bodyType = bodyType;
        this.description = description;
        this.body = body;
        this.interval = interval;
        this.intervalsLeft = numOfRecurrences;
    }

    public int getId() {
        return id;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public int getPriority() {
        return priority;
    }

    public int getDuration() {
        return duration;
    }

    public int getInterval() {
        return interval;
    }

    public int getIntervalLeft() {
        return intervalsLeft;
    }

    public BodyType getBodyType() {
        return bodyType;
    }

    public FidType getFidType() {
        return fidType;
    }

    public String getDescription() {
        return description;
    }

    public String getBody() {
        return body;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Fid done() {
        if (--intervalsLeft == 0) return null;
        this.id = FidIdGenerator.getNextId();
        return this;
    }
}
