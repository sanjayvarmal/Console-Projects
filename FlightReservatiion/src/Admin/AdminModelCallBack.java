package Admin;

import Dto.Flights;

import java.util.List;

public abstract class AdminModelCallBack {
    public abstract void addFlights(List<Flights> flights);
    public abstract void showF();
    public abstract void modifyF(int id,int ticket,String Location,String landing,String date,String time);
}
