package com.plumbum.aapu.household.Implementaion;

import android.content.Context;
import android.database.Cursor;

import com.plumbum.aapu.household.DAO.LoanDAO;
import com.plumbum.aapu.household.Interface.LoanInterface;
import com.plumbum.aapu.household.VO.LoanVO;

import java.sql.Date;

/**
 * Created by Dawn on 6/18/2016.
 */
public class LoanImplementation implements LoanInterface
{
    private LoanDAO loanDAO = null;
    private LoanVO loanVO = null;

    private static Context context;
    private static LoanImplementation instance;


    /*
     *@desc - A private constructor
     */

    private LoanImplementation()
    {

    }

    /**
     * @desc -  Use to create only a single Instance of LoanImplementation class
     *          Use the application context, which will ensure that you
     *          don't accidentally leak an Activity's context.
     *          See this article for more information: http://bit.ly/6LRzfx
     */

    public synchronized static LoanImplementation getInstance()
    {
        if(instance == null)
        {
            instance = new LoanImplementation();
        }
        return instance;
    }

    /**
     *@desc - Use to get Application Context
     *@return -  Application Context
     */

    public static Context getAppContext() {
        return LoanImplementation.context;
    }


    /**
     * @desc    To add a Loan Transaction in Database
     * @param   sum
     * @param   lender
     * @param   from - From Date
     * @param   to  - To Date
     * @param   remarks
     * @param   exclude
     */

    @Override
    public void addLoan(float sum, String lender, Date from, Date to, String remarks, Boolean exclude)
    {
        loanVO.setSum(sum);
        loanVO.setLender(lender);
        loanVO.setFrom(from);
        loanVO.setTo(to);
        loanVO.setRemarks(remarks);
        loanVO.setExclude(exclude);


        loanDAO.getInstance(getAppContext()).addQuery(loanVO);

    }

    /**
     * @desc    To fetch a ResultSet from Database
     * @param   query - SQL query
     * @return  Result Set
     */

    @Override
    public Cursor fetchCategory(String query)
    {
        return loanDAO.getInstance(getAppContext()).fetchQuery(query);
    }

    /**
     * @desc    Delete a row from Loan Table
     * @param   id - Use to identify the row
     */

    @Override
    public void deleteLoan(int id)
    {
        loanVO.setId(id);

        loanDAO.getInstance(getAppContext()).deleteQuery(loanVO);
    }

    /**
     * @desc    Update a row in Loan Table
     * @param   id
     * @param   sum
     * @param   lender
     * @param   from
     * @param   to
     * @param   remarks
     * @param   exclude
     */
    @Override
    public void updateLoan(int id, float sum, String lender, Date from, Date to, String remarks, Boolean exclude)
    {
        loanVO.setSum(sum);
        loanVO.setLender(lender);
        loanVO.setFrom(from);
        loanVO.setTo(to);
        loanVO.setRemarks(remarks);
        loanVO.setExclude(exclude);


        loanDAO.getInstance(getAppContext()).updateQuery(loanVO);

    }
}
