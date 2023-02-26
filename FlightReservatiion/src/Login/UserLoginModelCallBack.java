package Login;

public abstract class UserLoginModelCallBack {
    abstract void check(String UserName,char[] password);
    abstract void changepassword(char []NewPassWord);
}
