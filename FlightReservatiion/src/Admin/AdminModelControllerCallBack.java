package Admin;

import Dto.Flights;

import java.util.List;

public interface AdminModelControllerCallBack {
    void AddedFlights();
    void showFlights(List<Flights> flightsList);
    void modifysuccess();
    void modifyfailure();
}
