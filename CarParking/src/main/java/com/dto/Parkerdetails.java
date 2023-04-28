package com.dto;

import java.time.LocalDateTime;

public class Parkerdetails {
	private String name;
    private String location;
    private String date;
    private String number;
    private String CarNumber;
    private int hours;
    private LocalDateTime parkTime;
    private LocalDateTime endTime;
    private int cost;
    public void setDate(String date){
        this.date=date;
    }
    public void setLocation(String location){
        this.location=location;
    }
    public String getLocation(){
        return location;
    }
    public String getDate(){
        return date;
    }
    public void setParkTime(LocalDateTime parkTime){
        this.parkTime=parkTime;
    }
    public LocalDateTime getParkTime(){
        return parkTime;
    }
    public void setendTime(LocalDateTime endTime){
        this.endTime=endTime;
    }
    public LocalDateTime getEndTime(){
        return endTime;
    }
    public void nameSet(String name){
        this.name=name;
    }
    public void numberSet(String number){
        this.number=number;
    }
    public void carSet(String carNumber){
        this.CarNumber=carNumber;
    }
    public void hourSet(int hours){
        this.hours=hours;
    }
    public void costSet(int cost){
        this.cost=cost;
    }

    public String getName(){
        return name;
    }
    public String getNumber(){
        return number;
    }
    public String getCar(){
        return CarNumber;
    }
    public int getHours(){
        return hours;
    }
    public int getCost(){
        return cost;
    }
}
