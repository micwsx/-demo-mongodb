package com.example.demomongodb.domain;

import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

/**
 * @author: Michael
 * @date: 9/21/2022 10:25 PM
 */
public class TimeSpan {

    @Field("start_ts")
    private Date startTime;

    @Field("end_ts")
    private Date endTime;

    public TimeSpan() {
    }

    public TimeSpan(Date startTime, Date endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
