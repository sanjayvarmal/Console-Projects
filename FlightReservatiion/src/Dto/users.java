package Dto;

public class users {
    private int Id;
    private int FlightNumber;
    private String Name;
    private int Age;
    private String Gender;
    private Long PhoneNumber;
    private int ticket;

    public void SetFlightnumber(int id){
        this.FlightNumber=id;
    }
    public int GetFlightnumber(){
        return FlightNumber;
    }
    public void SetId(int Id){
        this.Id=Id;
    }
    public int GetId(){
        return this.Id;
    }
    public void SetTicket(int ticket){
        this.ticket=ticket;
    }
    public int GetTicket(){
        return this.ticket;
    }
    public void SetName(String name){
        this.Name=name;
    }
    public void SetAge(int Age){
        this.Age=Age;
    }
    public void SetGender(String Gender){
        this.Gender=Gender;
    }
    public void SetPhone(Long Number){
        this.PhoneNumber=Number;
    }

    public String GetName(){
        return this.Name;
    }
    public int GetAge(){
        return this.Age;
    }
    public String GetGender(){
        return this.Gender;
    }
    public Long GetPhone(){
        return this.PhoneNumber;
    }
}
