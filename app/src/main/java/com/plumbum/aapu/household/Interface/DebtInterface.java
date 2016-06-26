package com.plumbum.aapu.household.Interface;

import android.database.Cursor;

/**
 * Created by Dawn on 6/18/2016.
 */
public interface DebtInterface //Debt -> Given to someone (Receivable)
{
    //Exclude means if the amount needs to be excluded from total
    public abstract void addDebt(double sum, String borrower, String from,String to,String remarks,Boolean exclude);
    public abstract Cursor fetchDebt(String query);
    public abstract void deleteDebt(int id);
    public abstract void updateDebt(int id,double sum, String borrower, String from,String to,String remarks,Boolean exclude);
}
