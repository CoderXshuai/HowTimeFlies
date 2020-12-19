package com.example.howtimeflies.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class Time implements Serializable {
    private Integer tid;
    private Integer iconID;
    private String type;
    private String appName;
    private Integer progressNum;
    private Integer studyHour;
    private Integer studyMin;
    private String detailUrl;

    public Time(Integer iconID, String type, String appName, Integer progressNum, Integer studyHour, Integer studyMin) {
        this.iconID = iconID;
        this.type = type;
        this.appName = appName;
        this.progressNum = progressNum;
        this.studyHour = studyHour;
        this.studyMin = studyMin;
    }
}
