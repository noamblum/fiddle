package com.mechonot.fiddle.fid;

import java.time.LocalDate;

public class FidFactory {
    public static Fid createFidFromDb(String id, String creationDate, String priority, String duration,
               String fidType, String bodyType, String description,String body, String interval, String numOfRecurrences) {
        return new Fid(Integer.parseInt(id),
                LocalDate.parse(creationDate),
                Integer.parseInt(priority),
                Integer.parseInt(duration),
                FidType.valueOf(fidType),
                BodyType.valueOf(bodyType),
                description,
                body,
                Integer.parseInt(interval),
                Integer.parseInt(numOfRecurrences));
    }
}
