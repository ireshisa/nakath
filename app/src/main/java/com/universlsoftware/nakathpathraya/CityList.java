package com.universlsoftware.nakathpathraya;

public class CityList {

    private double lat;
    private double lon;
    private String city;

    private String name;
    private String  number;
    private String   time;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public CityList(double lat, double lon, String city,String name,String number,String time) {
        this.lat = lat;
        this.lon = lon;
        this.city = city;

        this.name = name;
        this.number = number;
        this.time = time;

    }


    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
