package com.universlsoftware.nakathpathraya.ui.nwadudina;

public class Event {
    private String name;
    private String type;
    private String to;
    private String except;
    private String month;
    private int day;
    private boolean holiday;

    public Event(String name, String type, String to, String details, String month, String day, boolean holiday) {
        this.name = name;
        this.type = type;
        this.to = to;
        this.except = details;
        this.month = month;
        this.day = Integer.parseInt(day);
        this.holiday = holiday;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getTo() {
        return to;
    }

    public String getExcept() {
        return except;
    }

    public String getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public boolean isHoliday() {
        return this.holiday;
    }
}