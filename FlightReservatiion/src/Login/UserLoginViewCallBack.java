package Login;

public abstract class UserLoginViewCallBack {

    public abstract void alreadyLogged(String name);

    public abstract  void newlyLogged(String name);
    public abstract void successfullyChanged();
    public abstract void adminView();
}
