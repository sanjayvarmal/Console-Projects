package Admin;

import Dto.Flights;

import java.util.List;

public abstract class AdminViewCallBack {
    public abstract void AddedFlights();
    public abstract void showFlights(List<Flights> flightsList);
    public abstract void success();
    public abstract void failure();
}
