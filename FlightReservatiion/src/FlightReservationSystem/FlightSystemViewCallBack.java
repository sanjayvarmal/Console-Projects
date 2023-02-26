package FlightReservationSystem;

import Dto.Flights;
import Dto.users;

import java.util.*;

public abstract class FlightSystemViewCallBack {
    abstract void Success(List<Flights> locations);
    abstract void failure();
    abstract void bookingSuccess(List<List<String>> BookList);
    abstract void cancelSuccess(int id);
    abstract void books(List<List<String>> books);
}
