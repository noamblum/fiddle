package com.mechonot.fiddle.utils;

import java.util.Random;

public class HelperFunctions {
    public static String generateTask() {
        Random random = new Random();
        int rand = random.nextInt(3);
        switch (rand){
            case 0:
                return "Call Grandma";
            case 1:
                return "20 push-ups";
            case 2:
                return "update your PlayList";
            default:
                return  "Meditate";
        }
    }
}
