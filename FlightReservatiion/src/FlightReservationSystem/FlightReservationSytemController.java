package FlightReservationSystem;

import Dto.Flights;
import Dto.users;

import java.util.*;

public class FlightReservationSytemController extends FlightSystemControllerCallBack implements FlightSystemModelControllerCallBack{
    private FlightSystemViewCallBack flightViewCallBack;
    private FligthSystemModelCallBack fligthModelCallBack;

    public FlightReservationSytemController(FlightSystemViewCallBack fligthViewCallBack){
        this.flightViewCallBack=fligthViewCallBack;
        this.fligthModelCallBack=new FlightReservationSystemModel(this);
    }
    public void checkFlights(String From,String Location,String date,int ticket){
        fligthModelCallBack.checkFlights(From,Location,date,ticket);
    }
    public void passengerDetails(List<users> bulkdetails, int ticket){
        fligthModelCallBack.passengerDetails(bulkdetails,ticket);
    }
    public void cancel(int id){
        fligthModelCallBack.cancel(id);
    }
    public void cancelSuccess(int id){
        flightViewCallBack.cancelSuccess(id);
    }
    public void viewBookedTickets(){
        fligthModelCallBack.viewBookedTickets();
    }
    public void books(List<List<String>> books){
        flightViewCallBack.books(books);
    }
    public void Success(List<Flights> locations){
        flightViewCallBack.Success(locations);
    }
    public void bookingSuccess(List<List<String>> BookList){
        flightViewCallBack.bookingSuccess(BookList);
    }
    public void failure(){
        flightViewCallBack.failure();
    }
}
