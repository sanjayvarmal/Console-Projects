package FlightReservationSystem;

import Dto.users;

import java.util.*;

public abstract class FlightSystemControllerCallBack {
    abstract void checkFlights(String From,String Location,String date,int ticket);
    abstract void passengerDetails(List<users> bulkdetails, int ticket);
    abstract void cancel(int id);
    abstract void viewBookedTickets();
}
