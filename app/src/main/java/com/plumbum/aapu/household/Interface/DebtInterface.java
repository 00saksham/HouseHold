package com.plumbum.aapu.household.Interface;

import android.database.Cursor;

import java.sql.Date;

/**
 * Created by Dawn on 6/18/2016.
 */
public interface DebtInterface //Debt -> Given to someone (Receivable)
{
    //Exclude means if the amount needs to be excluded from total
    public abstract void addDebt(float sum, String borrower, Date from,Date to,String remarks,Boolean exclude);
    public abstract Cursor fetchCategory(String query);
    public abstract void deleteDebt(int id);
    public abstract void updateDebt(int id,float sum, String borrower, Date from,Date to,String remarks,Boolean exclude);
}
