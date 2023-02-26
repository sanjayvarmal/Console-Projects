package Expense;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;


public class ExpenseView extends ExpenseViewCallBack{
    private ExpenseControllerCallBack expenseControllerCallBack;
    private Scanner scan=new Scanner(System.in);
    public ExpenseView(){
        this.expenseControllerCallBack=new ExpenseController(this);
    }
    public static void main(String []args){
        ExpenseView view=new ExpenseView();
        view.menu();
    }
    public void menu(){
        System.out.println("<~~~~~~~~~~~~~ Welcome To Your Diary ~~~~~~~~~~~~~>");
        System.out.println("------------ Manage Your Personal Expense Data Through Online ------------");
        System.out.println("1.If you are already in database Enter your database name or new Enter Your Unique For life time validation :");
        System.out.println("2.Exit");
        switch (scan.nextInt()){
            case 1:Login();
            break;
        }
    }
    private void Login() {
        System.out.println("Enter User Id :");
        String name=scan.next();
        expenseControllerCallBack.login(name);
    }

    @Override
    public void successfully() {
        System.out.println("<~~~~~~~~~~~~ successfully setted ~~~~~~~~~~~~>");
        System.out.println("1.Insert Data");
        System.out.println("2.search data");
        System.out.println("3.Exit");
        switch (scan.nextInt()){
            case 1:insert();
            break;
            case 2:
                System.out.println("Enter Date You Want To Search (yyyy-mm-dd):");
                expenseControllerCallBack.search(scan.next());
            break;
            case 3:expenseControllerCallBack.exit();
        }
    }

    @Override
    public void insertted() {
        System.out.println("<~~~~~~~~~~~~~~~~~~~~~~ Successfully Insertted ~~~~~~~~~~~~~~~~~~~~~~>");
        successfully();
    }

    @Override
    public void result(List<String> search) {
        System.out.println("<---------------- Searched Results ---------------->");
        System.out.println("+---------------------------------------------------------------------------------+");
        System.out.print("|");
        System.out.printf("%-16s"," Date");
        System.out.printf("%-59s","Reason");
        System.out.printf("%-5s","Amount");
        System.out.println("|");
        for(int i=0;i<search.size();i++){
            System.out.print("|");
            System.out.printf("%-16s",search.get(i).split("@")[0]);
            System.out.printf("%-60s",search.get(i).split("@")[1]);
            System.out.printf("%-5s",search.get(i).split("@")[2]);
            System.out.println("|");
        }
        System.out.println("+---------------------------------------------------------------------------------+");
        System.out.println();
        successfully();
    }

    @Override
    public void exitted() {
        System.out.println("************* Thank You *************");
    }

    public void insert(){
        System.out.println("How many data You Want To Insert :");
        String []data=new String[scan.nextInt()];
        for(int i=0;i<data.length;i++) {
            String s="";
            s= String.valueOf(LocalDate.now())+"@";
            System.out.println("Enter your Reason For the Expense :");
            scan.nextLine();
            s+=scan.nextLine()+"@";
            System.out.println("Enter The Amount For Your Expense :");
            s+=scan.nextInt();
            data[i]=s;
        }
        expenseControllerCallBack.insert(data);
    }
}
