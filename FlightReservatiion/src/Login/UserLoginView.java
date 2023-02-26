package Login;

import Admin.AdminView;
import Admin.login;
import FlightReservationSystem.FlightReservationSystemView;
import FlightReservationSystem.SystemLogin;

import java.util.Scanner;

public class UserLoginView extends UserLoginViewCallBack implements login, SystemLogin {
    private UserLoginControllerCallBack userLoginController;
    private mod1tomod2 bookView;
    private Scanner scan=new Scanner(System.in);
    private LogintoAdmin admin;

    public UserLoginView()
    {
        userLoginController=new UserLoginController(this);
        bookView=new FlightReservationSystemView(this);
        admin=new AdminView(this);
    }
    public static void main(String []args){
        UserLoginView userLoginView=new UserLoginView();
        System.out.println();
        System.out.println("_______________________WELCOME TO GHOSTFLAMES AIRLINES_______________________");
        userLoginView.Login();
    }
    public void Login(){
        System.out.println();
        System.out.println("Choose Any Option");
        System.out.println("1.Login");
        System.out.println("2.Exit");
        switch(scan.nextInt()){
            case 1:GetDetails();
            break;
            case 2:System.exit(0);
            default:
                Login();
        }
    }
    public void GetDetails(){
        System.out.println("Enter Your UserName ");
        String UserName=scan.next();
        System.out.println("Enter password");
        char []Password=scan.next().toCharArray();
        userLoginController.Login(UserName,Password);
    }
    public void successfullyChanged(){
        System.out.println("----------------Password Successfully Changed------------------");
        Login();
    }
    public void alreadyLogged(String UserName){
        System.out.println("____________________________________");
        System.out.println("        Welcome back "+UserName);
        System.out.println("____________________________________");
        System.out.println();
        System.out.println("1.Change Password");
        System.out.println("0.No");
        if(scan.nextInt()==1){
            System.out.println("Enter New Password : ");
            userLoginController.changepassword(scan.next().toCharArray());
        }
        bookView.System();
    }
    public void adminView(){
        admin.adminStart();
    }
    public void newlyLogged(String UserName){
        System.out.println("_________________________________________________________");
        System.out.println("Welcome "+UserName+" We Are Added You In Our System");
        System.out.println("_________________________________________________________");
        System.out.println("1.Change Password");
        System.out.println("0.No");
        if(scan.nextInt()==1){
            System.out.println("Enter New Password : ");
            userLoginController.changepassword(scan.next().toCharArray());
        }
        bookView.System();
    }
}
