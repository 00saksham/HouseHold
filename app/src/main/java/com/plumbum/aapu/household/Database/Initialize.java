package com.plumbum.aapu.household.Database;

import com.plumbum.aapu.household.Implementaion.CategoryImplementation;

/**
 * Created by Dawn on 6/26/2016.
 */
public class Initialize
{
    CategoryImplementation categoryImplementation;

    public void initializeSavingCategory(String category[],byte[][] image)
    {
        for(int i=18;i<24;i++)
        {
            categoryImplementation
                    .getInstance().addCategory(category[i],image[i],"saving");
        }

    }

    public void initializeExpenseCategory(String category[],byte[][] image)
    {
        for(int i=0;i<10;i++)
        {
            categoryImplementation
                    .getInstance().addCategory(category[i],image[i],"expense");
        }
    }

    public void initializeExtra(String category[],byte[][] image)
    {
        for(int i=10;i<18;i++)
        {
            categoryImplementation
                    .getInstance().addCategory(category[i],image[i],"extra");
        }
    }
}
