package Admin;

import dto.Parkerdetails;

import java.util.List;

public  abstract class AdminViewCallBack {
    public abstract void displayLot(String[][][] lots);

    public abstract void displayFailed();

    public abstract void searched(List<Parkerdetails> search);

    public abstract void deleted(int deleteLot);
}
