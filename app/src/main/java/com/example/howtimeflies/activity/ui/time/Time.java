package com.example.howtimeflies.activity.ui.time;

import lombok.Data;

@Data
public class Time {
    private Integer tid;
    private String type;
    private Integer progressNum;
    private Integer studyHour;
    private Integer studyMin;
    private String detailUrl;

    public Time(String type, Integer progressNum, Integer studyHour, Integer studyMin, String detailUrl) {
        this.type = type;
        this.progressNum = progressNum;
        this.studyHour = studyHour;
        this.studyMin = studyMin;
        this.detailUrl = detailUrl;
    }
}
