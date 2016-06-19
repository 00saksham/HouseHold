package com.plumbum.aapu.household.Interface;

import android.database.Cursor;

import java.sql.Blob;
import java.sql.Date;

/**
 * Created by Dawn on 6/18/2016.
 */
public interface TransactionInterface
{
    public abstract void addTransaction(float sum, Date date, String remarks, Blob category_icon,String category_name);
    public abstract Cursor fetchTransaction(String query);
    public abstract void deleteTransaction(int id);
    public abstract void updateTransaction(int id,float sum, Date date, String remarks, Blob category_icon,String category_name);
}
