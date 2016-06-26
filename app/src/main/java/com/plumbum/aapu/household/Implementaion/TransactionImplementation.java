package com.plumbum.aapu.household.Implementaion;

import android.content.Context;
import android.database.Cursor;

import com.plumbum.aapu.household.DAO.TransactionDAO;
import com.plumbum.aapu.household.Interface.TransactionInterface;
import com.plumbum.aapu.household.VO.TransactionVO;

/**
 * Created by Dawn on 6/18/2016.
 */
public class TransactionImplementation implements TransactionInterface
{

    private TransactionDAO transactionDAO = null;
    private TransactionVO transactionVO = new TransactionVO();

    private static Context context;
    private static TransactionImplementation instance;


    /*
     *@desc - A private constructor
     */

    private TransactionImplementation()
    {

    }

    /**
     * @desc -  Use to create only a single Instance of TransactionImplementation class
     *          Use the application context, which will ensure that you
     *          don't accidentally leak an Activity's context.
     *          See this article for more information: http://bit.ly/6LRzfx
     */

    public synchronized static TransactionImplementation getInstance()
    {
        if(instance == null)
        {
            instance = new TransactionImplementation();
        }
        return instance;
    }

    /**
     *@desc - Use to get Application Context
     *@return -  Application Context
     */

    public static Context getAppContext() {
        return TransactionImplementation.context;
    }

    /**
     * @desc    Use to Add a new Transaction in Database
     * @param   sum
     * @param   date
     * @param   remarks
     * @param   category_icon
     * @param   category_name
     */

    @Override
    public void addTransaction(double sum, String date, String remarks, byte[] category_icon, String category_name,String category_type)
    {
        transactionVO.setSum(sum);
        transactionVO.setDate(date);
        transactionVO.setRemarks(remarks);
        transactionVO.setCategory_name(category_name);
        transactionVO.setCategory_icon(category_icon);
        transactionVO.setCategory_type(category_type);

        transactionDAO.getInstance(getAppContext()).addQuery(transactionVO);

    }


    /**
     * @desc    Use to fetch a Result Set
     * @param   query
     * @return
     */

    @Override
    public Cursor fetchTransaction(String query)
    {
        return transactionDAO.getInstance(getAppContext()).fetchQuery(query);
    }

    /**
     * @desc    Use to delete a Transaction from Database
     * @param   id
     */

    @Override
    public void deleteTransaction(int id)
    {
        transactionVO.setId(id);

        transactionDAO.getInstance(getAppContext()).deleteQuery(transactionVO);
    }

    /**
     * @desc    Use to update a Transaction in Database
     * @param   id
     * @param   sum
     * @param   date
     * @param   remarks
     * @param   category_icon
     * @param   category_name
     */

    @Override
    public void updateTransaction(int id, double sum, String date, String remarks, byte[] category_icon, String category_name,String category_type)
    {
        transactionVO.setSum(sum);
        transactionVO.setDate(date);
        transactionVO.setRemarks(remarks);
        transactionVO.setCategory_name(category_name);
        transactionVO.setCategory_icon(category_icon);
        transactionVO.setCategory_type(category_type);

        transactionDAO.getInstance(getAppContext()).updateQuery(transactionVO);
    }
}
