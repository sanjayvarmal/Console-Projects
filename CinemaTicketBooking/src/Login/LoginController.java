package Login;

import dto.Parkerdetails;
import dto.subadmin;

import java.util.List;

public class LoginController extends LoginControllerCallBack implements LoginModelControllerCallBack{
    private LoginModelCallBack loginModel;
    private LoginViewCallBack loginView;
    public LoginController(LoginView loginView){
        this.loginView=loginView;
        this.loginModel=new LoginModel(this);
    }
    public void checkUser(String name,String password){
        loginModel.checkUser(name,password);
    }
    public void Logged(int n){
        loginView.logged(n);
    }
    public void checkLocation(String location){
        loginModel.checkLocation(location);
    }

    @Override
    public void quickBook(Parkerdetails userdetails) {
        loginModel.quickBook(userdetails);
    }
    public void loggedfailed(){
        loginView.loggedfailed();
    }
    public void addUser(subadmin admin){
        loginModel.addUser(admin);
    }
    public void userAdded(){
        loginView.userAdded();
    }

    @Override
    public void sortedDetails(List<String> loc) {
        loginView.sortedDetails(loc);
    }

    @Override
    public void noDetails() {
        loginView.noDetails();
    }

    @Override
    public void parkings(String[][][] quickBook) {
        loginView.parkings(quickBook);
    }

    public void addFailed(){
        loginView.addFailed();
    }
}
