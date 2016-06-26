package com.plumbum.aapu.household.VO;

/**
 * Created by Dawn on 6/26/2016.
 */
public class UserVO
{
    String userName;
    double balance;
    double saving_amount;
    double expense_amount;
    int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getExpense_amount() {
        return expense_amount;
    }

    public void setExpense_amount(double expense_amount) {
        this.expense_amount = expense_amount;
    }

    public double getSaving_amount() {
        return saving_amount;
    }

    public void setSaving_amount(double saving_amount) {
        this.saving_amount = saving_amount;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
