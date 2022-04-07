package com.mechonot.fiddle.fid;

import java.time.LocalDate;
import java.util.Random;

public class FauxFid extends Fid {
    private static final Random r = new Random();
    private static final String[] RANDOM_BODIES = {
            "Call Grandma",
            "Do Yoga",
            "https://www.example.com/"
    };

    public FauxFid() {
        super(0L, LocalDate.now(), LocalDate.now(), r.nextInt(3) + 1,
                r.nextInt(100) + 1,
                FidType.values()[r.nextInt(FidType.values().length)],
                BodyType.values()[r.nextInt(BodyType.values().length)],
                RANDOM_BODIES[r.nextInt(RANDOM_BODIES.length)], 0, 1);
    }
}
