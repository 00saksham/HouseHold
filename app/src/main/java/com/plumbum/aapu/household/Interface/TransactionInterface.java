package com.plumbum.aapu.household.Interface;

import android.database.Cursor;

/**
 * Created by Dawn on 6/18/2016.
 */
public interface TransactionInterface
{
    public abstract void addTransaction(double sum, String date, String remarks, byte[] category_icon,String category_name,String category_type);
    public abstract Cursor fetchTransaction(String query);
    public abstract void deleteTransaction(int id);
    public abstract void updateTransaction(int id,double sum, String date, String remarks, byte[] category_icon,String category_name,String category_type);
}
