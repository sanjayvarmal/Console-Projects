package Login;

import dto.Parkerdetails;
import dto.subadmin;

public abstract class LoginControllerCallBack {
    public abstract void checkUser(String name, String password);


    public abstract void addUser(subadmin client);

    public abstract void checkLocation(String location);

    public abstract void quickBook( Parkerdetails userdetails) ;
}
