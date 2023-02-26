package Admin;

import Dto.Flights;
import repository.FlightRepository;

import java.util.ArrayList;
import java.util.List;

public class AdminModel extends AdminModelCallBack{
    private AdminModelControllerCallBack adminControllerCallBack;
    public AdminModel(AdminModelControllerCallBack adminControllerCallBack){
        this.adminControllerCallBack=adminControllerCallBack;
    }
    public void addFlights(List<Flights> flights){
        if(FlightRepository.getInstance().addFlights(flights))
            adminControllerCallBack.AddedFlights();
    }
    public void showF(){
        List<Flights> flightsList=new ArrayList<>();
        flightsList=FlightRepository.getInstance().showF();
        adminControllerCallBack.showFlights(flightsList);
    }
    public void modifyF(int id,int ticket,String Location,String landing,String date,String time){
        if(FlightRepository.getInstance().modifyF(id,ticket,Location,landing,date,time))
            adminControllerCallBack.modifysuccess();
        else
            adminControllerCallBack.modifyfailure();
    }
}
