package Client;

import Login.LoginToClient;
import Login.LoginView;
import dto.Parkerdetails;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class ClientView extends ClientViewCallBack implements LoginToClient {
    private ClientControllerCallBack adminControllerCallBack;
    private clientToLogin clientToLogin;
    Scanner scan=new Scanner(System.in);
    public ClientView(){
        clientToLogin=new LoginView();
        adminControllerCallBack=new ClientController(this);
    }
    public void clientMenu(){
        System.out.println();
        System.out.println("<----****----> Welcome Back User <----****---->");
        System.out.println("1.view Lot");
        System.out.println("2.Modify Lot");
        System.out.println("3.Payment");
        System.out.println("4.view details");
        System.out.println("5.Exit");
        switch(scan.nextInt()){
            case 1:viewLots();
            break;
            case 2:modify();
            break;
            case 3:payment();
            break;
            case 4:viewdetails();
            case 5:clientToLogin.Login();
        }
    }

    private void viewdetails() {
        System.out.println("Enter the Date(yyyy/mm/dd) You Want to See ??");
        adminControllerCallBack.viewDetails(scan.next());
    }

    private void payment() {
        System.out.println("Enter Your VehicleNumber :");
        adminControllerCallBack.payment(scan.next());
    }

    private void modify() {
        String userName="",password="";
        int lots=0;
        System.out.println("Modify UserId :(y/n)");
        if(scan.next().charAt(0)=='y'){
            userName=scan.next();
        }
        System.out.println("Modify password:(y/n)");
        if(scan.next().charAt(0)=='y'){
            password=scan.next();
        }
        System.out.println("Modify floorsize :(y/n)");
        if(scan.next().charAt(0)=='y'){
            lots=scan.nextInt();
        }
        adminControllerCallBack.modify(userName,password,lots);
    }

    private void viewLots() {
        adminControllerCallBack.viewLots();
    }

    @Override
    public void displayLots(String[][][] quickBook) {
        if(quickBook.length!=0) {
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
            System.out.println("OOPS Currently No Users Available??");
        }
        clientMenu();
    }

    @Override
    public void modified() {
        System.out.println("*****> Successfully modified Details <*****");
        clientMenu();
    }

    @Override
    public void details(List<Parkerdetails> search) {
        DateTimeFormatter time=DateTimeFormatter.ofPattern("HH:mm");
        System.out.printf("%-15s","Name");
        System.out.printf("%-20s","Location");
        System.out.printf("%-11s","Date");
        System.out.printf("%-11s","Phnumber");
        System.out.printf("%-15s","vehicleNumber");
        System.out.printf("%-13s","ParkedTime");
        System.out.printf("%-13s\n","ExitTime");
        for(int i=0;i<search.size();i++){
            System.out.printf("%-15s",search.get(i).getName());
            System.out.printf("%-20s",search.get(i).getLocation());
            System.out.printf("%-11s",search.get(i).getDate());
            System.out.printf("%-11s",search.get(i).getNumber());
            System.out.printf("%-15s",search.get(i).getCar());
            System.out.print("    "+search.get(i).getParkTime().format(time));
            System.out.println("       "+(search.get(i).getEndTime()!=null?search.get(i).getEndTime().format(time):"00:00"));
        }
        System.out.println();
        clientMenu();
    }

    @Override
    public void pay(int payment) {
        if(payment>=0){
            System.out.println("Pay the Amount RS."+payment);
            while(scan.nextInt()!=payment){
                System.out.println("!!!!!!!! Pay correct Amount !!!!!!!!");
            }
            System.out.println("Successfully Paid");
            clientMenu();
        }
        else{
            System.out.println("===> Entered vehicle number Is Not In base <===");
            System.out.println("Are You Want To ReEnter number (y/n)");
            if(scan.next().charAt(0)=='y')
                payment();
            else
                clientMenu();
        }
    }
}
