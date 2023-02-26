package Admin;

import repository.Repository;

public class AdminModel extends AdminModelCallBack{
    private AdminModelControllerCallBack adminModelControllerCallBack;
    public AdminModel(AdminController adminController){
        this.adminModelControllerCallBack=adminController;
    }

    @Override
    public void viewParks(String lot) {
        String Lots[][][]= Repository.getInstance().viewParks(lot);
        if(Lots.length>0){
            adminModelControllerCallBack.displayLot(Lots);
        }
        else{
            adminModelControllerCallBack.displayFailed();
        }
    }

    @Override
    public void search(String next) {
        adminModelControllerCallBack.searched(Repository.getInstance().search(next));
    }

    @Override
    public void deleteLot(String s) {
        adminModelControllerCallBack.deleted(Repository.getInstance().deleteLot(s));
    }
}
