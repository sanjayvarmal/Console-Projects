package Admin;

import Dto.Flights;
import Login.LogintoAdmin;
import Login.UserLoginView;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdminView extends AdminViewCallBack implements LogintoAdmin {
    private AdminControllerCallBack adminController;
    private Scanner scan=new Scanner(System.in);
    private login user;

    public AdminView(UserLoginView userLoginView)
    {
        adminController=new AdminController(this);
        this.user=userLoginView;
    }
    public void adminStart(){
        adminJobs();
    }
    public void adminJobs(){
        System.out.println("Choose Option : ");
        System.out.println("1.Add Flights");
        System.out.println("2.Show Flights");
        System.out.println("3.Modify Flights");
        System.out.println("4.Exit");
        switch(scan.nextInt()){
            case 1:addFlights();
            break;
            case 2:adminController.showFlights();
            break;
            case 3:ModifyF();
            break;
            case 4:
                user.Login();
            default:
                adminJobs();
        }
    }
    public void addFlights(){
        System.out.println("Enter How Many Flights To add :");
        int a=scan.nextInt();
        List<Flights> flightsList=new ArrayList<>();
        for(int i=0;i<a;i++){
            Flights flightobject=new Flights();
            System.out.println("Enter Flight Id :");
            flightobject.SetId(scan.nextInt());
            System.out.println("Enter Flight Ticket Price :");
            flightobject.SetPrice(scan.nextInt());
            System.out.println("Enter Flight Name");
            flightobject.SetFlightName(scan.next());
            System.out.println("Enter takeOff place :");
            flightobject.SetFrom(scan.next());
            System.out.println("Enter Landing Place :");
            flightobject.SetTo(scan.next());
            System.out.println("Enter That bording date :");
            flightobject.SetDate(scan.next());
            System.out.println("Enter the depature Time :");
            flightobject.SetTime(scan.next());
            System.out.println("Enter the Total Ticket Allotments :");
            flightobject.SetAllotedSeats(scan.nextInt());
            flightsList.add(flightobject);
        }
        adminController.addFlight(flightsList);
    }
    public void AddedFlights(){
        System.out.println();
        System.out.println("_______________________________Flights Added Successfully_______________________________");
        System.out.println();
        adminJobs();
    }
    public void showFlights(List<Flights> flightsList){
        for(Flights Loc:flightsList){
           System.out.println("Id--> " + Loc.GetId() + "       FlightName--> " + Loc.GetFlightName() + " From--> (" + Loc.GetFrom() + " <-> " + Loc.GetTo() + ")       Date--> " + Loc.GetDate() + "       Time--> " + Loc.GetTime() + "        AllotedSize--> " + Loc.getAllotedSeats() + "       Price -->" + Loc.GetPrice()+"    Received amount --> "+Loc.GetAmount());
        }
        adminJobs();
    }
    public void ModifyF(){
        System.out.println("Enter Id For Which Flight You Want to Modify");
        int id=scan.nextInt(),ticket=0;
        String Location="",landing="",date="",time="";
        System.out.println("Modify price (y/n) :");
        if(scan.next().charAt(0)=='y') {
            System.out.println("Enter Flight Ticket Price :");
            ticket=scan.nextInt();
        }
        System.out.println("Modify Takeoff (y/n) :");
        if(scan.next().charAt(0)=='y') {
            System.out.println("Enter takeOff place :");
            Location=scan.next();
        }
        System.out.println("Modify Landing (y/n) :");
        if(scan.next().charAt(0)=='y') {
            System.out.println("Enter Landing Place :");
            landing=scan.next();
        }
        System.out.println("Modify bordingdate (y/n) :");
        if(scan.next().charAt(0)=='y') {
            System.out.println("Enter That bording date :");
            date=scan.next();
        }
        System.out.println("Modify Depature time (y/n) :");
        if(scan.next().charAt(0)=='y') {
            System.out.println("Enter the depature Time :");
            time=scan.next();
        }
        adminController.modifyF(id,ticket,Location,landing,date,time);
    }
    public void success(){
        System.out.println("________________________Flight Modified successfully________________________");
        adminJobs();
    }
    public void failure(){
        System.out.println("---> Entered Id Not In The Database <---");
        adminJobs();
    }
}
