package Login;

public abstract class UserLoginControllerCallBack {
    public abstract void Login(String UserName, char[] password);
    public abstract void changepassword(char []NewPassword);
}
