package com.example.apurpura.lifenavhelperapi;

/**
 * Created by apurpura on 5/30/2017.
 */

public class EventModel {
    public String Id;
    public String CalendarId;
    public String Summary;
    public String Description;
    public String Location;
    public String StartTime;
    public String EndTime;
    public String Action;
    public Integer u_id;
    public String Notify;

    public EventModel(String id, String calendarId, String summary, String description,
                      String location, String startTime, String endTime, String action, Integer _id, String notify){
        this.Id = id;
        this.CalendarId = calendarId;
        this.Summary = summary;
        this.Description = description;
        this.Location = location;
        this.StartTime = startTime;
        this.EndTime = endTime;
        this.Action = action;
        this.u_id = _id;
        this.Notify = notify;
    }
}
