package Dto;

public class Userlogin {
    private String UserName;
    private char[] password;
    public void SetName(String UserName){
        this.UserName=UserName;
    }
    public void SetPassword(char[] password){
        this.password=password;
    }
    public char[] GetPassword(){
        return password;
    }
    public String GetName(){
        return UserName;
    }
}
