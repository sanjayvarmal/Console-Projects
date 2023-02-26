package Login;

import java.util.List;

public interface LoginModelControllerCallBack {
    void Logged(int n);

    void loggedfailed();

    void addFailed();

    void userAdded();

    void sortedDetails(List<String> loc);

    void noDetails();

    void parkings(String[][][] quickBook);
}
