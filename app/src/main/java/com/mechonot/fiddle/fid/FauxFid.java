package com.mechonot.fiddle.fid;

import java.time.LocalDate;
import java.util.Random;

public class FauxFid extends Fid {
    private static final Random r = new Random();
    private static final String[] RANDOM_DESCRIPTIONS = {
            "Call Grandma",
            "Do Yoga",
            "https://www.example.com/"
    };

    public FauxFid() {
        super(LocalDate.now(), r.nextInt(3) + 1,
                r.nextInt(100) + 1,
                FidType.values()[r.nextInt(FidType.values().length)],
                BodyType.values()[r.nextInt(BodyType.values().length)],
                RANDOM_DESCRIPTIONS[r.nextInt(RANDOM_DESCRIPTIONS.length)],
                null, 0, 1);
    }
}