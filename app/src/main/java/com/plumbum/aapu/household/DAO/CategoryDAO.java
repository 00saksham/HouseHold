package com.plumbum.aapu.household.DAO;

import android.content.Context;
import android.database.Cursor;

import com.plumbum.aapu.household.Database.DBAdapter;
import com.plumbum.aapu.household.VO.CategoryVO;

/**
 * Created by Dawn on 6/18/2016.
 */
public class CategoryDAO
{
    private DBAdapter dbAdapter = null;
    private static Context context;
    private static CategoryDAO instance = null;
    private static String QUERY = null;
    private static final String INSERT = "INSERT INTO ";
    private static final String CATEGORY_TABLE_NAME = "CATEGORY";

    private static String CATEGORY_NAME = null;
    private static byte[] CATEGORY_ICON = null;
    private static String CATEGORY_TYPE = null;
    private static int CATEGORY_ID = 0;

    /*
     *@desc - A private constructor
     */
    private CategoryDAO()
    {

    }

    /**
     * @desc -  Use to create only a single Instance of CategoryDAO class
     *          Use the application context, which will ensure that you
     *          don't accidentally leak an Activity's context.
     *          See this article for more information: http://bit.ly/6LRzfx
     */
    public synchronized static CategoryDAO getInstance(Context context)
    {
        if(instance == null)
        {
            instance = new CategoryDAO();
        }
        return instance;
    }


    /**
     *@desc - Use to get Application Context
     *@return -  Application Context
     */
    public static Context getAppContext() {
        return CategoryDAO.context;
    }

    /**
     * @desc    Use to take Single instance of Database and perform the add Request using SQL query
     * @param   categoryVO  - Contains all the attributes of Category
     */

    public void addQuery(CategoryVO categoryVO)
    {
        CATEGORY_NAME = categoryVO.getCategory_name();
        CATEGORY_ICON = categoryVO.getCategory_icon();
        CATEGORY_TYPE = categoryVO.getCategory_type();
        QUERY = INSERT + CATEGORY_TABLE_NAME + "(category_name,category_icon,category_type) VALUES ('"+CATEGORY_NAME+"','"+CATEGORY_ICON+
                "','"+CATEGORY_TYPE+"');" ;

        dbAdapter.getInstance(getAppContext()).insertCategory(categoryVO);
    }

    /**
     * @desc    Use for any fetch query
     * @param   query - SQL query
     * @return  Cursor - Result set
     */

    public Cursor fetchQuery(String query)
    {
       return dbAdapter.getInstance(getAppContext()).fetchQuery(query);
    }


    /**
     * @desc    Delete a Row from Table
     * @param   categoryVO - Contains ID for the Row
     */

    public void deleteQuery(CategoryVO categoryVO)
    {
        CATEGORY_ID = categoryVO.getId();
        QUERY = "DELETE FROM "+ CATEGORY_TABLE_NAME +" WHERE _ID="+CATEGORY_ID+";";

        dbAdapter.getInstance(getAppContext()).anyQuery(QUERY);
    }

    /**
     * @desc    Update the Row in Table
     * @param   categoryVO - Contains all the Updated attributes of Category
     */

    public void updateQuery(CategoryVO categoryVO)
    {
        CATEGORY_NAME = categoryVO.getCategory_name();
        CATEGORY_ICON = categoryVO.getCategory_icon();
        CATEGORY_ID   = categoryVO.getId();
        CATEGORY_TYPE = categoryVO.getCategory_type();
        QUERY = "UPDATE "+CATEGORY_TABLE_NAME+" SET category_name='"+CATEGORY_NAME+"',category_icon='"+CATEGORY_ICON+
                "',category_type='"+CATEGORY_TYPE+"' " +
                "WHERE _id="+CATEGORY_ID+";";

        dbAdapter.getInstance(getAppContext()).anyQuery(QUERY);
    }
}
