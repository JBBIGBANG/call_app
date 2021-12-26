package com.share.call_prediction;

import java.util.Date;

public class CallInfo {
    private String date;
    private String duration;

    public CallInfo(String date, String duration) {
        this.date = date;
        this.duration = duration;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
