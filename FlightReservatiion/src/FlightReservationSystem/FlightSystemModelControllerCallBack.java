package FlightReservationSystem;

import Dto.Flights;

import java.util.*;

public interface FlightSystemModelControllerCallBack {
    void Success(List<Flights> Locations);
    void failure();
    void bookingSuccess(List<List<String>> BookList);
    void cancelSuccess(int id);
    void books(List<List<String>> books);
}
