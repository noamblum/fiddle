package com.mechonot.fiddle.fid;

import com.mechonot.fiddle.utils.HelperFunctions;

import java.time.LocalDate;


public class FidFactory {

    public static Fid createFidFromDB(String id, String creationDate, String priority,
                                      String duration, String fidType, String bodyType, String description,
                                      String body, String interval, String numOfRecurrences) {
        return new Fid(Integer.parseInt(id), LocalDate.parse(creationDate),
                Integer.parseInt(priority), Integer.parseInt(duration), FidType.valueOf(fidType),
                BodyType.valueOf(bodyType), description, body, Integer.parseInt(interval),
                Integer.parseInt(numOfRecurrences));
    }

    public static Fid createRecurringFid(LocalDate creationDate, int priority, int duration,
                                         FidType fidType, BodyType bodyType, String description,
                                         String body, int interval, int numOfRecurrences) {
        return new Fid(creationDate, priority, duration, fidType, bodyType, description, body, interval, numOfRecurrences);
    }

    public static Fid createFid(LocalDate creationDate, int priority, int duration, FidType fidType,
                                BodyType bodyType, String description, String body) {
        return new Fid(creationDate, priority, duration, fidType, bodyType, description, body, 0, 1);
    }

    public static Fid createEmptyFid() {
        return new Fid(LocalDate.now(), 1, 10, FidType.Article, BodyType.TEXT, "", "", 0, 1);
    }

    public static Fid generateFid() {
        return new Fid(LocalDate.now(), 1, 1, FidType.Task, BodyType.TEXT, HelperFunctions.generateTask(), "body", 0, 1);
    }

}
