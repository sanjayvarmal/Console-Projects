package Expense;

public abstract class ExpenseControllerCallBack {
    public abstract void login(String name);

    public abstract void insert(String[] data) ;

    public abstract void search(String next);

    public abstract void exit();
}
