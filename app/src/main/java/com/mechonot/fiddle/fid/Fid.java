package com.mechonot.fiddle.fid;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;

public class Fid {
    private final LocalDate creationDate;
    private int priority;//[1,3]
    private int duration;
    private FidType fidType;
    private final BodyType bodyType;
    private String description;
    private String body;
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

    public Fid(LocalDate creationDate, int priority, int duration, FidType fidType,
               BodyType bodyType, String description, String body, int interval, int numOfRecurrences) {
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

    public void setPriority(int priority) {
        this.priority = priority;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }
    public void setFidType(FidType fidType) {
        this.fidType = fidType;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setBody(String body) {
        this.body = body;
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public Fid done() {
        if (--intervalsLeft == 0) return null;
        this.id = FidIdGenerator.getNextId();
        return this;
    }
}
