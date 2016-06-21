package com.plumbum.aapu.household.DAO;

import android.content.Context;
import android.database.Cursor;

import com.plumbum.aapu.household.Database.DBAdapter;
import com.plumbum.aapu.household.VO.LoanVO;

import java.sql.Date;

/**
 * Created by Dawn on 6/18/2016.
 */
public class LoanDAO
{
    private DBAdapter dbAdapter = null;
    private static Context context;
    private static LoanDAO instance = null;
    private static String LOAN_TABLE_NAME="LOAN";
    private static String QUERY = null;
    private static final String INSERT = "INSERT INTO ";

    private static int LOAN_ID=0;
    private static float SUM=0;
    private static Date DATE_FROM=null;
    private static Date DATE_TO=null;
    private static String LENDER=null;
    private static String REMARKS=null;
    private static boolean EXCLUDE=false;


    /*
     *@desc - A private constructor
     */
    private LoanDAO()
    {

    }

    /**
     * @desc -  Use to create only a single Instance of LoanDAO class
     *          Use the application context, which will ensure that you
     *          don't accidentally leak an Activity's context.
     *          See this article for more information: http://bit.ly/6LRzfx
     */
    public synchronized static LoanDAO getInstance(Context context)
    {
        if(instance == null)
        {
            instance = new LoanDAO();
        }
        return instance;
    }


    /**
     *@desc - Use to get Application Context
     *@return -  Application Context
     */
    public static Context getAppContext() {
        return LoanDAO.context;
    }

    /**
     * @desc    Use to take Single instance of Database and perform the add Request using SQL query
     * @param   loanVO  - Contains all the attributes of Loan
     */

    public void addQuery(LoanVO loanVO)
    {
        SUM = loanVO.getSum();
        LENDER = loanVO.getLender();
        DATE_FROM = loanVO.getFrom();
        DATE_TO = loanVO.getTo();
        REMARKS = loanVO.getRemarks();
        EXCLUDE = loanVO.getExclude();

        QUERY = INSERT + LOAN_TABLE_NAME + "(sum,lender,date_from,date_to,remarks,exclude) VALUES ('"+SUM+"','"+LENDER+"','"+
                DATE_FROM+"','"+DATE_TO+"','"+REMARKS+"','"+EXCLUDE+"');";

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
     * @param   loanVO - Contains ID for the Row
     */

    public void deleteQuery(LoanVO loanVO)
    {
        LOAN_ID = loanVO.getId();
        QUERY = "DELETE FROM "+ LOAN_TABLE_NAME +" WHERE _ID="+LOAN_ID+";";

        dbAdapter.getInstance(getAppContext()).anyQuery(QUERY);
    }

    /**
     * @desc    Update the Row in Table
     * @param   loanVO - Contains all the Updated attributes of Loan
     */

    public void updateQuery(LoanVO loanVO)
    {
        SUM = loanVO.getSum();
        LENDER = loanVO.getLender();
        DATE_FROM = loanVO.getFrom();
        DATE_TO = loanVO.getTo();
        REMARKS = loanVO.getRemarks();
        EXCLUDE = loanVO.getExclude();

        QUERY = "UPDATE "+LOAN_TABLE_NAME+" SET sum='"+SUM+"',lender='"+LENDER+"',date_from='"+DATE_FROM+
                "',date_to='"+DATE_TO+"',remarks='"+REMARKS+"',exclude='"+EXCLUDE+"' WHERE _id="+LOAN_ID+";";

        dbAdapter.getInstance(getAppContext()).anyQuery(QUERY);
    }

}
