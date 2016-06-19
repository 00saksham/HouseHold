package com.plumbum.aapu.household.Interface;

import android.database.Cursor;

import java.sql.Blob;

/**
 * Created by Dawn on 6/18/2016.
 */
public interface CategoryInterface
{
    public abstract void addCategory(String category_name, Blob category_icon);
    public abstract Cursor fetchCategory(String query);
    public abstract void deleteCategory(int id);
    public abstract void updateCategory(int id,String category_name, Blob category_icon);
}
