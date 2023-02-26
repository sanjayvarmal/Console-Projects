package Client;

import dto.Parkerdetails;

import java.util.List;

public class ClientController extends ClientControllerCallBack implements ClientModelControllerCallBack {
    private ClientViewCallBack clientViewCallBack;
    private ClientModelCallBack clientModelCallBack;
    public ClientController(ClientView clientView){
        this.clientViewCallBack = clientView;
        this.clientModelCallBack =new ClientModel(this);
    }

    @Override
    public void viewLots() {
        clientModelCallBack.viewLots();
    }

    @Override
    public void modify(String name,String password,int lots) {
        clientModelCallBack.modify(name,password,lots);
    }

    @Override
    public void viewDetails(String next) {
        clientModelCallBack.viewDetails(next);
    }

    @Override
    public void payment(String next) {
        clientModelCallBack.payment(next);
    }

    @Override
    public void displayLots(String[][][] viewlots) {
        clientViewCallBack.displayLots(viewlots);
    }

    @Override
    public void modified() {
        clientViewCallBack.modified();
    }

    @Override
    public void details(List<Parkerdetails> viewDetails) {
        clientViewCallBack.details(viewDetails);
    }

    @Override
    public void pay(int payment) {
        clientViewCallBack.pay(payment);
    }
}
