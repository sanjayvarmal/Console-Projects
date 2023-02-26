package Login;

import java.util.List;

public abstract class LoginViewCallBack {

    public abstract void logged(int n);

    public abstract void loggedfailed();

    public abstract void userAdded();

    public abstract void addFailed();

    public abstract void noDetails();

    public abstract void sortedDetails(List<String> loc);

    public  abstract void parkings(String[][][] quickBook);
}
