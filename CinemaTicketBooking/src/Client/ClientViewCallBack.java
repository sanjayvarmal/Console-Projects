package Client;

import dto.Parkerdetails;

import java.util.List;

public  abstract class ClientViewCallBack {

    public abstract void displayLots(String[][][] viewlots);

    public abstract void modified();

    public abstract void details(List<Parkerdetails> viewDetails);

    public abstract void pay(int payment);
}
