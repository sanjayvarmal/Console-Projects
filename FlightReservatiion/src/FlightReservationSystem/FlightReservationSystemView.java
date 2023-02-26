package FlightReservationSystem;

import Dto.Flights;
import Login.UserLoginView;
import Login.mod1tomod2;
import Dto.users;

import java.util.*;

public class FlightReservationSystemView extends FlightSystemViewCallBack implements mod1tomod2 {
    private FlightSystemControllerCallBack flightControllerCallBack;
    private Scanner scan=new Scanner(System.in);
    private SystemLogin userLoginView;
    public FlightReservationSystemView(UserLoginView userLoginView)
    {
      this.flightControllerCallBack=new FlightReservationSytemController(this);
      this.userLoginView=userLoginView;
    }
    public void System(){
        reserveSystem();
    }
    public void reserveSystem(){
        System.out.println();
        System.out.println("Choose Your option :");
        System.out.println("1.BookTickets");
        System.out.println("2.CancelTickets");
        System.out.println("3.ViewBookedTickets");
        System.out.println("4.Exit");
        switch(scan.nextInt()){
            case 1:GetDetails();
            break;
            case 2:Cancel();
            break;
            case 3:
                flightControllerCallBack.viewBookedTickets();
                break;
            case 4:
                userLoginView.Login();
                break;
            default:
                System.out.println("Enter Correct Option :");
                reserveSystem();
        }
    }
    public void Cancel(){
        System.out.println();
        System.out.println("Enter Your Id :");
        int id=scan.nextInt();
        flightControllerCallBack.cancel(id);
    }
    public void cancelSuccess(int id){
        if(id==0) {
            System.out.println("There no Available id ");
            System.out.println("Enter Correct id :");
                Cancel();
        }
        else{
            System.out.println("SuccessFully Canceled");
            System.out.println("Your Refund will be credited to Your Acccount by half of Original");
        }
        reserveSystem();
    }
    public void books(List<List<String>> books){
        for(int i=0;i<books.size();i++){
            System.out.println();
            System.out.println("-----------------------------------------------------------------");
            System.out.println("| Mr."+books.get(i).get(2)+"                                         BooksId "+books.get(i).get(0)+" |");
            System.out.println("|                                                               |");
            System.out.println("| FlightName : "+books.get(i).get(3)+"                             From : "+books.get(i).get(4)+" |");
            System.out.println("|                                                               |");
            System.out.println("|                                                 To : "+books.get(i).get(5)+"  |");
            System.out.println("|                                                               |");
            System.out.println("| OnBoarding : "+books.get(i).get(6)+"                                  Date : "+books.get(i).get(7)+" |");
            System.out.println("-----------------------------------------------------------------");
            System.out.println();
        }
        reserveSystem();
    }
    public void GetDetails(){
        System.out.println("Enter The Date You Want To Be Our Customer :");
        String date=scan.next();
        System.out.println("Enter Your Locations :");
        String From=scan.next();
        System.out.println("Enter Location You To Want to Travel Like Flames :");
        String To=scan.next();
        System.out.println("Enter How Many Tickets You want To Book :");
        int ticket=scan.nextInt();
        flightControllerCallBack.checkFlights(From,To,date,ticket);
    }
    public void Success(List<Flights> locations){
        if(locations.size()==0) {
            System.out.println("There Are No flights For Your Location");
            System.out.println("If you want to book another Flight(y/n)");
            if(scan.next().charAt(0)=='y')
                GetDetails();
            else
                reserveSystem();
        }
        else {
            System.out.println("________________________________________________________________Sorted Flights________________________________________________________________");
            for (Flights Loc : locations) {
                System.out.println("Id--> " + Loc.GetId() + "       FlightName--> " + Loc.GetFlightName() + " From--> (" + Loc.GetFrom() + " <-> " + Loc.GetTo() + ")       Date--> " + Loc.GetDate() + "       Time--> " + Loc.GetTime() + "        AllotedSize--> " + Loc.getAllotedSeats() + "       Price -->" + Loc.GetPrice());
            }
            System.out.println();
            System.out.println("Book Ticket (Y) Or Exit(N) :");
            if (scan.next().charAt(0) == 'N')
                System.exit(0);
            System.out.println("Enter How many seats You Want to Book :");
            List<users> bulkDetails=new ArrayList<>();
            int ticket = scan.nextInt();
            for(int i=0;i<ticket;i++) {
                System.out.println("Enter "+(i+1)+" candidate details :");
                users userdet=new users();
                System.out.println("Enter Id Which You want to Book :");
                userdet.SetId(scan.nextInt());
                System.out.println("Enter Your Name :");
                userdet.SetName(scan.next());
                System.out.println("Enter Your Age :");
                userdet.SetAge(scan.nextInt());
                System.out.println("Enter Your gender :");
                userdet.SetGender(scan.next());
                System.out.println("Enter Your PhoneNumber :");
                userdet.SetPhone(scan.nextLong());
                for(Flights Loc:locations) {
                    if(Loc.GetId()==userdet.GetId()) {
                        System.out.println("Pay the Amoumt To Book tickets = " + Loc.GetPrice());
                        scan.next();
                        break;
                    }
                }
                System.out.println();
                bulkDetails.add(userdet);
            }
            flightControllerCallBack.passengerDetails(bulkDetails,ticket);
        }
    }
    public void bookingSuccess(List<List<String>> BookList){
        System.out.println("______________________You Tickets are Successfully Book______________________");
        for(int i=0;i<BookList.size();i++){
            System.out.println();
            System.out.println("BookId = "+BookList.get(i).get(0));
            System.out.println("FlightNumber = "+BookList.get(i).get(1));
            System.out.println("FlightName = "+BookList.get(i).get(2));
            System.out.println("From = "+BookList.get(i).get(3));
            System.out.println("To = "+BookList.get(i).get(4));
            System.out.println("Depature = "+BookList.get(i).get(5));
            System.out.println("Totalseats available = "+BookList.get(i).get(6));
            System.out.println();
        }
        System.out.println();
        System.out.println("Are You Need Few More Tickets(Y/N) :");
        if(scan.next().charAt(0)=='Y')
            GetDetails();
        else{
            System.out.println("We Are Happy For Travelling With Crazy Man ");
            System.out.println("Thanks For Travelling With Us");
            System.out.println();
            reserveSystem();
        }
    }
    public void failure(){
        System.out.println("Sorry Memeber There's No Available Flight For Your Location or tickets");
        System.out.println("You Have Any other Location To Travel Y/N");
        if(scan.next().charAt(0)=='y')
                GetDetails();
        System.out.println("We Are Happy For Travelling With Crazy Man");
        System.out.println("Thanks For Travelling With Us");
        reserveSystem();
    }
}
