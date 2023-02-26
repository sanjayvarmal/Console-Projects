package Login;

import dto.Parkerdetails;
import dto.subadmin;
import repository.Repository;

import java.util.*;

public class LoginModel extends LoginModelCallBack{
    private LoginModelControllerCallBack loginControllerCallBack;
    public LoginModel(LoginController loginController){
        this.loginControllerCallBack=loginController;
    }
    public void checkUser(String name,String password){
        int n=Repository.getInstance().checkuser(name,password);
        if(n==0 || n==1 ){
            loginControllerCallBack.Logged(n);
        }
        else{
            loginControllerCallBack.loggedfailed();
        }
    }
    public void addUser(subadmin admin){
        if(Repository.getInstance().addUser(admin)){
            loginControllerCallBack.userAdded();
        }
        else{
            loginControllerCallBack.addFailed();
        }
    }

    @Override
    public void checkLocation(String location) {
        List<String> loc=Repository.getInstance().checkLocation(location);
        if(loc.size()!=0){
            loginControllerCallBack.sortedDetails(loc);
        }
        else{
            loginControllerCallBack.noDetails();
        }
    }

    @Override
    public void quickBook(Parkerdetails userdetails) {
            loginControllerCallBack.parkings(Repository.getInstance().quickBook(userdetails));
    }
}
