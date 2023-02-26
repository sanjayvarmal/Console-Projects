package Client;

public abstract class ClientControllerCallBack {
    public abstract void viewLots();

    public abstract void modify(String name,String password,int lots);

    public abstract void viewDetails(String next);

    public abstract void payment(String next);
}
