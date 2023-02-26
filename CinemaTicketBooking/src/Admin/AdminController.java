package Admin;

import dto.Parkerdetails;

import java.util.List;

public class AdminController extends AdminControllerCallBack implements AdminModelControllerCallBack{
    private AdminViewCallBack adminViewCallBack;
    private AdminModelCallBack adminModelCallBack;
    public AdminController(AdminView adminView){
        this.adminViewCallBack=adminView;
        this.adminModelCallBack=new AdminModel(this);
    }

    @Override
    public void viewParks(String Lot) {
        adminModelCallBack.viewParks(Lot);
    }

    @Override
    public void search(String next) {
        adminModelCallBack.search(next);
    }

    @Override
    public void deleteLot(String s) {
        adminModelCallBack.deleteLot(s);
    }

    @Override
    public void displayFailed() {
        adminViewCallBack.displayFailed();
    }

    @Override
    public void displayLot(String[][][] lots) {
adminViewCallBack.displayLot(lots);
    }

    @Override
    public void searched(List<Parkerdetails> search) {
        adminViewCallBack.searched(search);
    }

    @Override
    public void deleted(int deleteLot) {
        adminViewCallBack.deleted(deleteLot);
    }
}
