package dto;

import java.time.LocalTime;

public class Parkerdetails {
    private String name;
    private String location;
    private String date;
    private String number;
    private String CarNumber;
    private int hours;
    private LocalTime parkTime;
    private LocalTime endTime;
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
    public void setParkTime(LocalTime parkTime){
        this.parkTime=parkTime;
    }
    public LocalTime getParkTime(){
        return parkTime;
    }
    public void setendTime(LocalTime endTime){
        this.endTime=endTime;
    }
    public LocalTime getEndTime(){
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
