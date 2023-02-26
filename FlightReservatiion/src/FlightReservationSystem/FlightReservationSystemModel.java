package FlightReservationSystem;

import Dto.Flights;
import Dto.users;
import repository.FlightRepository;

import java.util.*;

public class FlightReservationSystemModel extends FligthSystemModelCallBack{
    private FlightSystemModelControllerCallBack flightSystemModelControllerCallBack;

    public FlightReservationSystemModel(FlightSystemModelControllerCallBack flightSystemModelControllerCallBack){
        this.flightSystemModelControllerCallBack=flightSystemModelControllerCallBack;
    }
    public void checkFlights(String From,String Location,String date,int ticket){
        List<Flights> Locations=FlightRepository.getInstance().checkFlights(From,Location,date,ticket);
        if(Locations!=null){
            flightSystemModelControllerCallBack.Success(Locations);
        }
        else{
            flightSystemModelControllerCallBack.failure();
        }
    }
    public void books(List<List<String>> books){
        for(int i=0;i<books.size();i++){

        }
    }
    public void viewBookedTickets(){
        List<List<String>> Books=FlightRepository.getInstance().viewBooked();
        flightSystemModelControllerCallBack.books(Books);
    }
    public void cancel(int id){
            flightSystemModelControllerCallBack.cancelSuccess(FlightRepository.getInstance().cancel(id));
    }
    public void passengerDetails(List<users> bulkdetails, int ticket){
        List<List<String>> BookList=FlightRepository.getInstance().addDetails(bulkdetails,ticket);
        if(BookList.size()!=0){
            flightSystemModelControllerCallBack.bookingSuccess(BookList);
        }
    }
}