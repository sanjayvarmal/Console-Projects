package Login;

import Admin.AdminToLogin;
import Admin.AdminView;
import Client.ClientView;
import Client.clientToLogin;
import dto.Parkerdetails;
import dto.subadmin;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;


public class LoginView extends LoginViewCallBack implements clientToLogin, AdminToLogin {
    private LoginControllerCallBack loginControllerCallBack;
    private LoginToClient loginToClient;
    private LoginToAdmin loginToAdmin;
    private subadmin client;
    private Parkerdetails userdetails;
     Scanner scan=new Scanner(System.in);
    public LoginView(){
        loginControllerCallBack=new LoginController(this);
    }
    public static void main(String []args){
        LoginView loginView=new LoginView();
        loginView.Login();
    }
    public void Login(){
        System.out.println("___________________________________Welcome to DemoGod CarParking___________________________________");
        System.out.println("Choose One Option");
        System.out.println("1.Login");
        System.out.println("2.Adduser");
        System.out.println("3.quickBook");
        System.out.println("4.Exit");
        switch(scan.nextInt()){
            case 1:login();
            break;
            case 2:Adduser();
            break;
            case 3:book();
            break;
            case 4:System.exit(0);
        }
    }
    public void book(){
        System.out.println("Enter Your Current location :");
        String location=scan.next();
        loginControllerCallBack.checkLocation(location);
    }
    public void quickBook(String loc) {
        userdetails=new Parkerdetails();
        System.out.println("Enter Your Name :");
        userdetails.nameSet(scan.next());
        System.out.println("Enter Your Number :");
        userdetails.numberSet(scan.next());
        System.out.println("Enter Your Car Number :");
        userdetails.carSet(scan.next());
        System.out.println("Enter Your Hours :");
        userdetails.hourSet(scan.nextInt());
        userdetails.setDate(String.valueOf(LocalDate.now()));
        userdetails.setParkTime(LocalTime.now());
        userdetails.setLocation(loc.split("@")[0]);
        loginControllerCallBack.quickBook(userdetails);
    }
    public void login(){
        System.out.println("Enter Your Name :");
        String name=scan.next();
        System.out.println("Enter Your PassWord :");
        String password=scan.next();
        loginControllerCallBack.checkUser(name,password);
    }
    public void Adduser(){
        client=new subadmin();
        System.out.println("Enter Your Parking Lot Location :");
        scan.nextLine();
        client.setLocation(scan.nextLine());
        System.out.println("Enter No of floors :");
        client.setSlots(scan.nextInt());
        System.out.println("Enter an Hour Price :");
        client.setPrice(scan.nextInt());
        System.out.println("Enter Your Cutomized UserId :");
        client.setUserid(scan.next());
        System.out.println("Enter The Passeword :");
        client.setPassword(scan.next());
        System.out.println("ReEnter Password");
        while(!scan.next().equals(client.getPassword())){
            System.out.println("~~~Wrong Password ReEnter~~~");
        }
        System.out.println("---> Pay RS.10000 for Yearly Access <---");
        int a=scan.nextInt();
        while(a<10000){
            System.out.println("You have not that Sufficient Amount!!!");
            System.out.println("Pay balance Again");
            a+=scan.nextInt();
        }
        loginControllerCallBack.addUser(client);
    }
    public void logged(int n){
        if(n==0){
            loginToClient=new ClientView();
            loginToClient.clientMenu();
        }
        else{
         loginToAdmin=new AdminView();
         loginToAdmin.adminMenu();
        }
    }
    public void userAdded(){
        System.out.println("(******> You are Added <******)");
        Login();
    }
    public void addFailed(){
        System.out.println("!!!Id or Password Are already added!!!");
        Login();
    }

    @Override
    public void noDetails() {
        System.out.println("Entered Location Has no Parking Lots");
        book();
    }

    @Override
    public void sortedDetails(List<String> loc) {
        System.out.printf("%-20s","Location");
        System.out.printf("%-5s","slot");
        System.out.printf("%-6s\n","Prices");
        for(int i=0;i<loc.size();i++){
            System.out.printf("%-20s",loc.get(i).split("@")[0]);
            System.out.printf("%-5s",loc.get(i).split("@")[1]);
            System.out.printf("%-6s\n",loc.get(i).split("@")[2]);
        }
        System.out.println();
        System.out.println("Book or Exit?");
        if(scan.next().charAt(0)=='b') {
            System.out.println("Your user Lot by Number :");
            int n=scan.nextInt() - 1;
            quickBook(loc.get(n));
        }
        else{
            Login();
        }
    }

    @Override
    public void parkings(String[][][] quickBook) {
        if(quickBook.length!=0) {
            System.out.println("----> Successfully booked <----");
            for (int i = 0; i < quickBook.length; i++) {
                System.out.printf("%-60s", "floor " + (i + 1));
            }
            System.out.println();
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < quickBook.length; j++) {
                    for (int k = 0; k < 5; k++) {
                        if (quickBook[j][i][k] == null) {
                            System.out.printf("%-11s", "Empty");
                        } else
                            System.out.printf("%-11s", quickBook[j][i][k]);
                    }
                    System.out.print("     ");
                }
                System.out.println();
            }
            System.out.println();
        }
        else{
            System.out.println("Entered Vehicle Number Already In Parking ??");
            System.out.println("!!! Choose Right Number !!!");
            System.out.println();
        }
        Login();
    }

    public void loggedfailed(){
        System.out.println("***** Enter username is Not Registered *****");
        System.out.println("***** Enter valid Userid or Password *****");
        System.out.println();
        Login();
    }
}
