package Login;

public interface UserLoginModelControllerCallBack {
    void alreadyLogged(String name);
    void newlyLogged(String name);
    void passwordchanged();
    void admin();
}
