package com.plumbum.aapu.household.Interface;

import java.sql.Date;

/**
 * Created by Dawn on 6/18/2016.
 */
public interface DebtInterface //Debt -> Given to someone (Receivable)
{
    public abstract boolean addDebt(float sum, String borrower, Date from,Date to,String remarks,Boolean exclude);
                                    //Exclude means if the amount needs to be excluded from total

    public abstract boolean deleteDebt(int id);
    public abstract boolean updateDebt(float sum, String borrower, Date from,Date to,String remarks,Boolean exclude);
}
