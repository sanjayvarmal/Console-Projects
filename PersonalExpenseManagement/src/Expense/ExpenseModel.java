package Expense;

import Repository.Repository;

public class ExpenseModel extends ExpenseModelCallBack {
    private ExpenseModelControllerCallBack expenseModelControllerCallBack;
    public ExpenseModel(ExpenseController expenseController) {
        this.expenseModelControllerCallBack=expenseController;
    }

    @Override
    public void login(String name) {
        Repository.getInstance().login(name);
        expenseModelControllerCallBack.successfully();
    }

    @Override
    public void insert(String[] data) {
        Repository.getInstance().insert(data);
        expenseModelControllerCallBack.insertted();
    }

    @Override
    public void search(String next) {
        expenseModelControllerCallBack.result(Repository.getInstance().search(next));
    }

    @Override
    public void exit() {
        expenseModelControllerCallBack.exited();
    }
}
