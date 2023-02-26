package Expense;

import java.util.List;

public class ExpenseController extends ExpenseControllerCallBack implements ExpenseModelControllerCallBack {
    private ExpenseViewCallBack expenseViewCallBack;
    private ExpenseModelCallBack expenseModelCallBack;
    public ExpenseController(ExpenseView expenseView) {
        this.expenseViewCallBack=expenseView;
        this.expenseModelCallBack=new ExpenseModel(this);
    }

    @Override
    public void login(String name) {
        expenseModelCallBack.login(name);
    }

    @Override
    public void insert(String[] data) {
        expenseModelCallBack.insert(data);
    }

    @Override
    public void search(String next) {
        expenseModelCallBack.search(next);
    }

    @Override
    public void exit() {
        expenseModelCallBack.exit();
    }

    @Override
    public void successfully() {
        expenseViewCallBack.successfully();
    }

    @Override
    public void insertted() {
        expenseViewCallBack.insertted();
    }

    @Override
    public void result(List<String> search) {
        expenseViewCallBack.result(search);
    }

    @Override
    public void exited() {
        expenseViewCallBack.exitted();
    }
}
