package Admin;

import Dto.Flights;

import java.util.List;

public class AdminController extends AdminControllerCallBack implements AdminModelControllerCallBack{
    private AdminViewCallBack adminViewCallBack;
    private AdminModelCallBack adminModelCallBack;
    public AdminController(AdminViewCallBack adminviewCallBack){
        this.adminViewCallBack=adminviewCallBack;
        this.adminModelCallBack=new AdminModel(this);
    }
    public void addFlight(List<Flights> flights){
        adminModelCallBack.addFlights(flights);
    }
    public void AddedFlights(){
        adminViewCallBack.AddedFlights();
    }
    public void showFlights(){
        adminModelCallBack.showF();
    }
    public void modifysuccess(){
        adminViewCallBack.success();
    }
    public void modifyfailure(){
        adminViewCallBack.failure();
    }
    public void showFlights(List<Flights> flightsList){
        adminViewCallBack.showFlights(flightsList);
    }
    public void modifyF(int id,int ticket,String Location,String landing,String date,String time){
        adminModelCallBack.modifyF(id,ticket,Location,landing,date,time);
    }
}
