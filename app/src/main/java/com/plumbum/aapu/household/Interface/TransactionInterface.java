package com.plumbum.aapu.household.Interface;

import java.sql.Blob;
import java.sql.Date;

/**
 * Created by Dawn on 6/18/2016.
 */
public interface TransactionInterface
{
    public abstract boolean addTransaction(float sum, Date date, String remarks, Blob icon,String category);
    public abstract boolean deleteTransaction(int id);
    public abstract boolean updateTransaction(float sum, Date date, String remarks, Blob icon,String category);
}
