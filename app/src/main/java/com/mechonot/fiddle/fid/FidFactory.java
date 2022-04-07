package com.mechonot.fiddle.fid;

import java.time.LocalDate;

public class FidFactory {

    static Fid recreateFid(int id, LocalDate creationDate, LocalDate deadline, int priority,
                           int duration, FidType fidType, BodyType bodyType, String description, String body, int interval,
                           int numOfRecurrences) {
        return new Fid(id, creationDate, priority, duration, fidType, bodyType, description, body, interval, numOfRecurrences);
    }

    static Fid createRecurringFid(LocalDate creationDate, LocalDate deadline, int priority,
                                  int duration, FidType fidType, BodyType bodyType, String description, String body, int interval,
                                  int numOfRecurrences) {
        return new Fid(creationDate, priority, duration, fidType, bodyType, description, body, interval, numOfRecurrences);
    }

    static Fid createFid(LocalDate creationDate, LocalDate deadline, int priority,
                         int duration, FidType fidType, BodyType bodyType, String description, String body) {
        return new Fid(creationDate, priority, duration, fidType, bodyType, description, body, 0, 1);
    }
}
