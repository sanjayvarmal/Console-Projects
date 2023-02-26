package Login;

import repository.FlightRepository;

public class UserLoginModel extends UserLoginModelCallBack{
    private UserLoginModelControllerCallBack userLoginControllerCallBack;
    public UserLoginModel(UserLoginModelControllerCallBack userLoginControllerCallBack){
        this.userLoginControllerCallBack=userLoginControllerCallBack;
    }
    public void changepassword(char []NewPassWord){
        if(FlightRepository.getInstance().changepassword(NewPassWord))
            userLoginControllerCallBack.passwordchanged();
    }
    public void check(String UserName,char[] password){
        int id=FlightRepository.getInstance().check(UserName,password);
        if(id==0){
            admin();
        }
        else{
            result(UserName,id);
        }
    }
    public void admin(){
        userLoginControllerCallBack.admin();
    }
    public void result(String UserName,int id){
        if(id==2)
            userLoginControllerCallBack.alreadyLogged(UserName);
        else
            userLoginControllerCallBack.newlyLogged(UserName);
    }
}
