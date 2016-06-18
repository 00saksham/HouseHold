package com.plumbum.aapu.household.Interface;

import java.sql.Date;

/**
 * Created by Dawn on 6/18/2016.
 */
public interface LoanInterface //Loan -> Sum taken from someone (Payable)
{
    public abstract boolean addLoan(float sum, String lender, Date from, Date to, String remarks, Boolean exclude);
    //Exclude means if the amount needs to be excluded from total

    public abstract boolean deleteLoan(int id);
    public abstract boolean updateLoan(float sum, String lender, Date from,Date to,String remarks,Boolean exclude);
}
