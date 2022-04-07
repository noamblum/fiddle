package com.mechonot.fiddle.Fid;

import java.time.LocalDate;

public class FidFactory {

    static Fid recreateFid(Long id, LocalDate creationDate, LocalDate deadline, int priority,
                         int duration, FidType fidType, String body, int interval,
                         int numOfRecurrences) {
        return new Fid(id, creationDate, deadline, priority, duration, fidType, body, interval, numOfRecurrences);
    }

    static Fid createRecurringFid(LocalDate creationDate, LocalDate deadline, int priority,
                         int duration, FidType fidType, String body, int interval,
                         int numOfRecurrences) {
        return new Fid(creationDate, deadline, priority, duration, fidType, body, interval, numOfRecurrences);
    }

    static Fid createFid(LocalDate creationDate, LocalDate deadline, int priority,
                         int duration, FidType fidType, String body) {
        return new Fid(creationDate, deadline, priority, duration, fidType, body, 0, 1);
    }
}
