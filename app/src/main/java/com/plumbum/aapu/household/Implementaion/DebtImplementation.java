package com.plumbum.aapu.household.Implementaion;

import android.content.Context;
import android.database.Cursor;

import com.plumbum.aapu.household.DAO.DebtDAO;
import com.plumbum.aapu.household.Interface.DebtInterface;
import com.plumbum.aapu.household.VO.DebtVO;

import java.sql.Date;

/**
 * Created by Dawn on 6/18/2016.
 */
public class DebtImplementation implements DebtInterface
{
    private DebtDAO debtDAO = null;
    private DebtVO debtVO = new DebtVO();

    private static Context context;
    private static DebtImplementation instance;


    /*
     *@desc - A private constructor
     */

    private DebtImplementation()
    {

    }

    /**
     * @desc -  Use to create only a single Instance of DebtImplementation class
     *          Use the application context, which will ensure that you
     *          don't accidentally leak an Activity's context.
     *          See this article for more information: http://bit.ly/6LRzfx
     */

    public synchronized static DebtImplementation getInstance()
    {
        if(instance == null)
        {
            instance = new DebtImplementation();
        }
        return instance;
    }

    /**
     *@desc - Use to get Application Context
     *@return -  Application Context
     */

    public static Context getAppContext() {
        return DebtImplementation.context;
    }


    /**
     * @desc    To add a Debt Transaction in Database
     * @param   sum
     * @param   borrower
     * @param   from - From Date
     * @param   to  - To Date
     * @param   remarks
     * @param   exclude
     */

    @Override
    public void addDebt(float sum, String borrower, Date from, Date to, String remarks, Boolean exclude)
    {
        debtVO.setSum(sum);
        debtVO.setBorrower(borrower);
        debtVO.setFrom(from);
        debtVO.setTo(to);
        debtVO.setRemarks(remarks);
        debtVO.setExclude(exclude);


        debtDAO.getInstance(getAppContext()).addQuery(debtVO);

    }

    /**
     * @desc    To fetch a ResultSet from Database
     * @param   query - SQL query
     * @return  Result Set
     */

    @Override
    public Cursor fetchDebt(String query)
    {
        return debtDAO.getInstance(getAppContext()).fetchQuery(query);
    }

    /**
     * @desc    Delete a row from Debt Table
     * @param   id - Use to identify the row
     */

    @Override
    public void deleteDebt(int id)
    {
        debtVO.setId(id);

        debtDAO.getInstance(getAppContext()).deleteQuery(debtVO);
    }

    /**
     * @desc    Update a row in Debt Table
     * @param   id
     * @param   sum
     * @param   borrower
     * @param   from
     * @param   to
     * @param   remarks
     * @param   exclude
     */
    @Override
    public void updateDebt(int id, float sum, String borrower, Date from, Date to, String remarks, Boolean exclude)
    {
        debtVO.setSum(sum);
        debtVO.setBorrower(borrower);
        debtVO.setFrom(from);
        debtVO.setTo(to);
        debtVO.setRemarks(remarks);
        debtVO.setExclude(exclude);


        debtDAO.getInstance(getAppContext()).updateQuery(debtVO);

    }
}
