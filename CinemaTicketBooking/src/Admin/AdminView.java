package Admin;

import Login.LoginToAdmin;
import Login.LoginView;
import dto.Parkerdetails;

import java.util.List;
import java.util.Scanner;

public class AdminView extends AdminViewCallBack implements LoginToAdmin {
    private AdminControllerCallBack adminControllerCallBack;
    private AdminToLogin adminToLogin;
    Scanner scan=new Scanner(System.in);
    public AdminView(){
        adminToLogin=new LoginView();
        adminControllerCallBack=new AdminController(this);
    }
    public void adminMenu(){
        System.out.println();
        System.out.println("<----****----> Welcome Back Super User <----****---->");
        System.out.println("1.view specific parkings");
        System.out.println("2.Search a specific Number");
        System.out.println("3.Delete A lot");
        System.out.println("4.Exit");
        switch(scan.nextInt()){
            case 1:viewParks();
            break;
            case 2:Search();
            break;
            case 3:deleteLot();
            break;
            case 4:adminToLogin.Login();
        }
    }

    public void deleteLot() {
        System.out.println("Enter userid Password");
        adminControllerCallBack.deleteLot(scan.next()+" "+scan.next());
    }

    public void Search() {
        System.out.println("Enter the Vehicle number :");
        adminControllerCallBack.search(scan.next());
    }

    public void viewParks() {
        System.out.println("Enter Specific Location :");
        scan.nextLine();
        adminControllerCallBack.viewParks(scan.nextLine());
    }

    @Override
    public void displayLot(String[][][] quickBook) {
        for(int i=0;i<quickBook.length;i++) {
            System.out.printf("%-60s", "floor "+(i+1));
        }
        System.out.println();
        for(int i=0;i<5;i++){
            for(int j=0;j<quickBook.length;j++){
                for(int k=0;k<5;k++){
                    if(quickBook[j][i][k]==null){
                        System.out.printf("%-11s","Empty");
                    }
                    else
                        System.out.printf("%-11s",quickBook[j][i][k]);
                }
                System.out.print("     ");
            }
            System.out.println();
        }
        System.out.println();
        adminMenu();
    }

    @Override
    public void displayFailed() {
        System.out.println("Entered Location Not Found!!!");
        System.out.println("ReEnter Location or Back To Menu (y/n)");
        if(scan.next().charAt(0)=='y'){
         viewParks();
        }
        else{
            adminMenu();
        }
    }

    @Override
    public void searched(List<Parkerdetails> search) {
            System.out.printf("%-15s","Name");
        System.out.printf("%-15s","Location");
        System.out.printf("%-11s","Date");
        System.out.printf("%-11s","Phnumber");
        System.out.printf("%-11s\n","vehicleNumber");
        for(int i=0;i<search.size();i++){
            System.out.printf("%-15s",search.get(i).getName());
            System.out.printf("%-15s",search.get(i).getLocation());
            System.out.printf("%-11s",search.get(i).getDate());
            System.out.printf("%-11s",search.get(i).getNumber());
            System.out.printf("%-11s\n",search.get(i).getCar());
        }
        System.out.println();
        adminMenu();
    }

    @Override
    public void deleted(int deleteLot) {
        if(deleteLot==0)
        System.out.println("---> successfully Deleted <---");
        else{
            System.out.println("Enter Id Is not In List ???");
        }
        System.out.println();
        adminMenu();
    }
}
