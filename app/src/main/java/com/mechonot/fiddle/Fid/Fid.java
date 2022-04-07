package com.mechonot.fiddle.Fid;
import java.time.LocalDate;

public class Fid {
    private Long id;
    private LocalDate creation_date;
    private LocalDate dead_line;
    private Integer priority;//[1,3]
    private Integer duration;
    private FidType fid_type;
    private String body;

    public Long get_id() {
        return id;
    }

    public LocalDate get_creation_date() {
        return creation_date;
    }

    public LocalDate get_dead_line() {
        return dead_line;
    }

    public Integer getPriority() {
        return priority;
    }


    public Integer getDuration() {
        return duration;
    }


    public String getBody() {
        return body;
    }

    public FidType get_fid_time(){
        return fid_type;
    }

}
