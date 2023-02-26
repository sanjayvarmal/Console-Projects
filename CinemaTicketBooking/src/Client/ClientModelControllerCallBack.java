package Client;

import dto.Parkerdetails;

import java.util.List;

public interface ClientModelControllerCallBack {


    void displayLots(String[][][] viewlots);

    void modified();

    void details(List<Parkerdetails> viewDetails);

    void pay(int payment);
}
