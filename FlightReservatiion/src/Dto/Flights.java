package Dto;

public class Flights {
    private int Id;
    private int price;
    private String FlightName;
    private String From;
    private String To;
    private String Date;
    private String Time;
    private int Amount;
    private int AllotedSeats;
    public void SetPrice(int price){
        this.price=price;
    }
    public int GetPrice(){
        return price;
    }
    public void SetAmount(int amount){
        this.Amount=amount;
    }
    public int GetAmount(){
        return this.Amount;
    }
    public void SetAllotedSeats(int size){
        this.AllotedSeats=size;
    }
    public int getAllotedSeats() {
        return this.AllotedSeats;
    }
    public void SetFrom(String From){
        this.From=From;
    }
    public String GetFrom(){
        return this.From;
    }
    public void SetId(int Id){
        this.Id=Id;
    }
    public void SetFlightName(String FlightName){
        this.FlightName=FlightName;
    }
    public void SetTo(String To){
        this.To=To;
    }
    public void SetDate(String Date){
        this.Date=Date;
    }
    public void SetTime(String Time){
        this.Time=Time;
    }

    public int GetId(){
        return this.Id;
    }
    public String GetFlightName(){
        return this.FlightName;
    }
    public String GetTo(){
        return this.To;
    }
    public String GetDate(){
        return this.Date;
    }
    public String GetTime(){
        return this.Time;
    }
}