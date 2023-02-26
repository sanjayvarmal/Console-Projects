package Client;

import dto.Parkerdetails;
import repository.Repository;

import java.util.List;

public class ClientModel extends ClientModelCallBack {
    private ClientModelControllerCallBack clientModelControllerCallBack;
    public ClientModel(ClientController adminController){
        this.clientModelControllerCallBack =adminController;
    }

    @Override
    public void viewLots() {
        clientModelControllerCallBack.displayLots(Repository.getInstance().viewlots());
    }

    @Override
    public void modify(String name,String password,int lots) {
        Repository.getInstance().modify(name,password,lots);
        clientModelControllerCallBack.modified();
    }

    @Override
    public void viewDetails(String next) {
        clientModelControllerCallBack.details(Repository.getInstance().viewDetails(next));
    }

    @Override
    public void payment(String next) {
            clientModelControllerCallBack.pay(Repository.getInstance().payment(next));
    }
}
