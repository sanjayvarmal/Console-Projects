package Login;

public class UserLoginController extends UserLoginControllerCallBack implements UserLoginModelControllerCallBack{
    private UserLoginViewCallBack userLoginViewCallBack;
    private UserLoginModelCallBack userLoginModelCallBack;
    public UserLoginController(UserLoginView userLoginview){
        this.userLoginViewCallBack=userLoginview;
        this.userLoginModelCallBack=new UserLoginModel(this);
    }
    public void changepassword(char []NewPassword){
        userLoginModelCallBack.changepassword(NewPassword);
    }
    public void admin(){
        userLoginViewCallBack.adminView();
    }
    public void passwordchanged(){
        userLoginViewCallBack.successfullyChanged();
    }
    public void Login(String UserName,char []password){
        userLoginModelCallBack.check(UserName,password);
    }
    public void alreadyLogged(String name){
        userLoginViewCallBack.alreadyLogged(name);
    }
    public void newlyLogged(String name){
        userLoginViewCallBack.newlyLogged(name);
    }
}
