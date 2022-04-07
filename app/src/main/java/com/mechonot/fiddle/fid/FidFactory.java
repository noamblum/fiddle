package com.mechonot.fiddle.fid;

import com.mechonot.fiddle.utils.HelperFunctions;

import java.time.LocalDate;


public class FidFactory {

    public static Fid recreateFid(int id, LocalDate creationDate, LocalDate deadline, int priority,
                           int duration, FidType fidType, BodyType bodyType, String description, String body, int interval,
                           int numOfRecurrences) {
        return new Fid(id, creationDate, priority, duration, fidType, bodyType, description, body, interval, numOfRecurrences);
    }

    public static Fid createRecurringFid(LocalDate creationDate, LocalDate deadline, int priority,
                                  int duration, FidType fidType, BodyType bodyType, String description, String body, int interval,
                                  int numOfRecurrences) {
        return new Fid(creationDate, priority, duration, fidType, bodyType, description, body, interval, numOfRecurrences);
    }

    public static Fid createFid(LocalDate creationDate, LocalDate deadline, int priority,
                         int duration, FidType fidType, BodyType bodyType, String description, String body) {
        return new Fid(creationDate, priority, duration, fidType, bodyType, description, body, 0, 1);
    }
    public static Fid createEmptyFid(){
        return new Fid(LocalDate.now(),1,1, FidType.Article, BodyType.TEXT,"desc","body",0,1);
    }

    public static Fid generateFid(){
        return new Fid(LocalDate.now(),1,1, FidType.Task, BodyType.TEXT, HelperFunctions.generateTask(),"body",0,1);
    }

}
