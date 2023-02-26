package Expense;

import java.util.List;

public abstract class ExpenseViewCallBack {
    public abstract void successfully();

    public abstract void insertted();

    public abstract void result(List<String> search);

    public abstract void exitted();
}
