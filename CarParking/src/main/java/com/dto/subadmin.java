package com.dto;

public class subadmin {
    private String location;//
    private String userid;//
    private String password;//
    private int slots;//
    private int nooflots;
    private String floors[][][];
    private int amount;
    private int price;//
    public void setAmount(int amount){
        this.amount=amount;
    }
    private int getAmount(){
        return amount;
    }
    public void setLocation(String location){
        this.location=location;
    }
    public void setUserid(String userid){
        this.userid=userid;
    }
    public void setPassword(String password){
        this.password=password;
    }
    public void setSlots(int slots){
        this.slots=slots;
        floors=new String[slots][5][5];
        nooflots=slots*25;
    }
    public void setNooflots(int nooflots){
        this.nooflots=nooflots;
    }
    public int getNooflots(){
        return nooflots;
    }
    public void setFloors(String floors[][][]){
        this.floors=floors;
    }
    public String[][][] getFloor(){
        return floors;
    }
    public void setPrice(int price){
        this.price=price;
    }

    public String getLocation(){
        return location;
    }
    public String getUserid(){
        return userid;
    }
    public String getPassword(){
        return password;
    }
    public int getSlots(){
        return slots;
    }
    public int getPrice(){
        return price;
    }
}
