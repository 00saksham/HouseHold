package com.plumbum.aapu.household.DAO;

import android.content.Context;
import android.database.Cursor;

import com.plumbum.aapu.household.Database.DBAdapter;
import com.plumbum.aapu.household.VO.TransactionVO;

import java.sql.Blob;

/**
 * Created by Dawn on 6/18/2016.
 */
public class TransactionDAO
{
    private DBAdapter dbAdapter = null;
    private static Context context;
    private static TransactionDAO instance = null;
    private static String TRANSACTION_TABLE_NAME="LOAN";
    private static String QUERY = null;
    private static final String INSERT = "INSERT INTO ";

    private static int TRANSACTION_ID=0;
    private static float SUM=0;
    private static String DATE=null;
    private static String REMARKS=null;
    private static String CATEGORY_NAME = null;
    private static Blob CATEGORY_ICON = null;


    /*
     *@desc - A private constructor
     */
    private TransactionDAO()
    {

    }

    /**
     * @desc -  Use to create only a single Instance of TransactionDAO class
     *          Use the application context, which will ensure that you
     *          don't accidentally leak an Activity's context.
     *          See this article for more information: http://bit.ly/6LRzfx
     */
    public synchronized static TransactionDAO getInstance(Context context)
    {
        if(instance == null)
        {
            instance = new TransactionDAO();
        }
        return instance;
    }


    /**
     *@desc - Use to get Application Context
     *@return -  Application Context
     */
    public static Context getAppContext() {
        return TransactionDAO.context;
    }

    /**
     * @desc    Use to take Single instance of Database and perform the add Request using SQL query
     * @param   transactionVO  - Contains all the attributes of Transaction
     */

    public void addQuery(TransactionVO transactionVO)
    {

        dbAdapter.getInstance(getAppContext()).anyQuery(QUERY);
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
     * @param   transactionVO - Contains ID for the Row
     */

    public void deleteQuery(TransactionVO transactionVO)
    {
        TRANSACTION_ID = transactionVO.getId();
        QUERY = "DELETE FROM "+ TRANSACTION_TABLE_NAME +" WHERE _ID="+TRANSACTION_ID+";";

        dbAdapter.getInstance(getAppContext()).anyQuery(QUERY);
    }

    /**
     * @desc    Update the Row in Table
     * @param   transactionVO - Contains all the Updated attributes of Transaction
     */

    public void updateQuery(TransactionVO transactionVO)
    {
        SUM = transactionVO.getSum();
        DATE = transactionVO.getDate();
        REMARKS = transactionVO.getRemarks();
        CATEGORY_NAME = transactionVO.getCategory_name();
        CATEGORY_ICON = transactionVO.getCategory_icon();

        QUERY = "UPDATE "+TRANSACTION_TABLE_NAME+" SET sum='"+SUM+"',date='"+DATE+"',remarks='"+REMARKS+
                "',category_name='"+CATEGORY_NAME+"',category_icon='"+CATEGORY_ICON+"' WHERE _id="+TRANSACTION_ID+";";

        dbAdapter.getInstance(getAppContext()).anyQuery(QUERY);
    }

}
