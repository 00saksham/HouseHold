package com.plumbum.aapu.household.DAO;

import android.content.Context;
import android.database.Cursor;

import com.plumbum.aapu.household.Database.DBAdapter;
import com.plumbum.aapu.household.VO.DebtVO;

/**
 * Created by Dawn on 6/18/2016.
 */
public class DebtDAO
{
    private DBAdapter dbAdapter;
    private static Context context;
    private static DebtDAO instance = null;
    private static String DEBT_TABLE_NAME="DEBT";
    private static String QUERY = null;
    private static final String INSERT = "INSERT INTO ";

    private static int DEBT_ID=0;
    private static double SUM=0;
    private static String DATE_FROM=null;
    private static String DATE_TO=null;
    private static String BORROWER=null;
    private static String REMARKS=null;
    private static boolean EXCLUDE=false;


    /*
     *@desc - A private constructor
     */
    private DebtDAO()
    {

    }

    /**
     * @desc -  Use to create only a single Instance of DebtDAO class
     *          Use the application context, which will ensure that you
     *          don't accidentally leak an Activity's context.
     *          See this article for more information: http://bit.ly/6LRzfx
     */
    public synchronized static DebtDAO getInstance(Context context)
    {
        if(instance == null)
        {
            instance = new DebtDAO();
        }
        return instance;
    }


    /**
     *@desc - Use to get Application Context
     *@return -  Application Context
     */
    public static Context getAppContext() {
        return DebtDAO.context;
    }

    /**
     * @desc    Use to take Single instance of Database and perform the add Request using SQL query
     * @param   debtVO  - Contains all the attributes of Debt
     */

    public void addQuery(DebtVO debtVO)
    {
        SUM = debtVO.getSum();
        BORROWER = debtVO.getBorrower();
        DATE_FROM = debtVO.getFromDate();
        DATE_TO = debtVO.getToDate();
        REMARKS = debtVO.getRemarks();
        EXCLUDE = debtVO.getExclude();

        QUERY = INSERT + DEBT_TABLE_NAME + "(sum,borrower,date_from,date_to,remarks,exclude) VALUES ('"+SUM+"','"+BORROWER+"','"+
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
     * @param   debtVO - Contains ID for the Row
     */

    public void deleteQuery(DebtVO debtVO)
    {
        DEBT_ID = debtVO.getId();
        QUERY = "DELETE FROM "+ DEBT_TABLE_NAME +" WHERE _ID="+DEBT_ID+";";

        dbAdapter.getInstance(getAppContext()).anyQuery(QUERY);
    }

    /**
     * @desc    Update the Row in Table
     * @param   debtVO - Contains all the Updated attributes of Debt
     */

    public void updateQuery(DebtVO debtVO)
    {
        SUM = debtVO.getSum();
        BORROWER = debtVO.getBorrower();
        DATE_FROM = debtVO.getFromDate();
        DATE_TO = debtVO.getToDate();
        REMARKS = debtVO.getRemarks();
        EXCLUDE = debtVO.getExclude();

        QUERY = "UPDATE "+DEBT_TABLE_NAME+" SET sum='"+SUM+"',borrower='"+BORROWER+"',date_from='"+DATE_FROM+
                "',date_to='"+DATE_TO+"',remarks='"+REMARKS+"',exclude='"+EXCLUDE+"' WHERE _id="+DEBT_ID+";";

        dbAdapter.getInstance(getAppContext()).anyQuery(QUERY);
    }

}
