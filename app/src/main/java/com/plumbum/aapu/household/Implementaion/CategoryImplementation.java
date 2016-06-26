package com.plumbum.aapu.household.Implementaion;

import android.content.Context;
import android.database.Cursor;

import com.plumbum.aapu.household.DAO.CategoryDAO;
import com.plumbum.aapu.household.Interface.CategoryInterface;
import com.plumbum.aapu.household.VO.CategoryVO;

/**
 * Created by Dawn on 6/18/2016.
 */
public class CategoryImplementation implements CategoryInterface
{
    private CategoryVO categoryVO = new CategoryVO();
    private CategoryDAO categoryDAO = null;

    private static CategoryImplementation instance ;
    private static Context context;


    /*
     *@desc - A private constructor
     */
    private CategoryImplementation()
    {

    }

    /**
     * @desc -  Use to create only a single Instance of CategoryImplementation class
     *          Use the application context, which will ensure that you
     *          don't accidentally leak an Activity's context.
     *          See this article for more information: http://bit.ly/6LRzfx
     */
    public synchronized static CategoryImplementation getInstance()
    {
        if(instance == null)
        {
            instance = new CategoryImplementation();
        }
        return instance;
    }

    /**
     *@desc - Use to get Application Context
     *@return -  Application Context
     */
    public static Context getAppContext() {
        return CategoryImplementation.context;
    }

    /**
     * @description   To add a new Category
     * @param         category_name - Category Name
     * @param         category_icon - Icon
     * @return        boolean true or false for Category added or not
     */

    @Override
    public void addCategory(String category_name, byte[] category_icon,String category_type)
    {
        categoryVO.setCategory_name(category_name);
        categoryVO.setCategory_icon(category_icon);
        categoryVO.setCategory_type(category_type);

        categoryDAO.getInstance(getAppContext()).addQuery(categoryVO);
    }

    @Override
    public Cursor fetchCategory(String query)
    {
        return categoryDAO.getInstance(getAppContext()).fetchQuery(query);
    }

    /**
     * @description   To delete a Category
     * @param         id to identify the category
     */
    @Override
    public void deleteCategory(int id)
    {
        categoryVO.setId(id);

        categoryDAO.getInstance(getAppContext()).deleteQuery(categoryVO);

    }

    /**
     * @description   To update a Category
     * @param         id            - ID of the category
     * @param         category_name - Category name
     * @param         category_icon - Category icon
     * @return        boolean true or false for Category updated or not
     */

    @Override
    public void updateCategory(int id,String category_name, byte[] category_icon,String category_type)
    {
        categoryVO.setId(id);
        categoryVO.setCategory_icon(category_icon);
        categoryVO.setCategory_name(category_name);
        categoryVO.setCategory_type(category_type);

        categoryDAO.getInstance(getAppContext()).updateQuery(categoryVO);

    }
}
