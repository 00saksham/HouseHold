package com.plumbum.aapu.household.Interface;

import java.sql.Blob;

/**
 * Created by Dawn on 6/18/2016.
 */
public interface CategoryInterface
{
    public abstract boolean addCategory(String name, Blob icon);
    public abstract boolean deleteCategory(int id);
    public abstract boolean updateCategory(String name, Blob icon);
}
