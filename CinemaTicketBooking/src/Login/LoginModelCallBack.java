package Login;

import dto.Parkerdetails;
import dto.subadmin;

public abstract class LoginModelCallBack {
    public abstract void checkUser(String name, String password);

    public abstract void addUser(subadmin admin);

    public abstract void checkLocation(String location) ;

    public abstract void quickBook(Parkerdetails userdetails);
}
