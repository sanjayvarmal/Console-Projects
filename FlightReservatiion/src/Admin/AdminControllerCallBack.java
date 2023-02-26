package Admin;

import Dto.Flights;

import java.util.*;

public abstract class AdminControllerCallBack {
    public abstract void addFlight(List<Flights> flights);
    public abstract void showFlights();
    public abstract void modifyF(int id,int ticket,String Location,String landing,String date,String time);
}
