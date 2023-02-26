package Admin;

import dto.Parkerdetails;

import java.util.List;

public interface AdminModelControllerCallBack {

    void displayFailed();

    void displayLot(String[][][] lots);

    void searched(List<Parkerdetails> search);

    void deleted(int deleteLot);
}
