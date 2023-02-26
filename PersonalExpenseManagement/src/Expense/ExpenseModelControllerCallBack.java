package Expense;

import java.util.List;

public interface ExpenseModelControllerCallBack {
    void successfully();

    void insertted();

    void result(List<String> search);

    void exited();
}
