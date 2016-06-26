package com.plumbum.aapu.household.Interface;

import android.database.Cursor;

/**
 * Created by Dawn on 6/18/2016.
 */
public interface LoanInterface //Loan -> Sum taken from someone (Payable)
{

    //Exclude means if the amount needs to be excluded from total

    public abstract void addLoan(double sum, String lender, String from, String to, String remarks, Boolean exclude);
    public abstract Cursor fetchLoan(String query);
    public abstract void deleteLoan(int id);
    public abstract void updateLoan(int id,double sum, String lender, String from,String to,String remarks,Boolean exclude);
}
