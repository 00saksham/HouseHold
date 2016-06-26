package com.plumbum.aapu.household.DAO;

import android.content.Context;
import android.database.Cursor;

import com.plumbum.aapu.household.Database.DBAdapter;
import com.plumbum.aapu.household.VO.UserVO;

/**
 * Created by Dawn on 6/26/2016.
 */
public class UserDAO
{
    private static Context context;
    private DBAdapter dbAdapter = null;
    private static UserDAO instance = null;
    private static String QUERY = null;
    private static String USER_TABLE_NAME="USER";
    private static final String INSERT = "INSERT INTO ";

    private String USER_NAME = null;
    private double USER_BALANCE = 0;
    private double USER_SAVING_AMOUNT = 0;
    private double USER_EXPENSE_AMOUNT = 0;
    private int USER_ID=1;
    /*
  *@desc - A private constructor
  */
    private UserDAO()
    {

    }

    /**
     * @desc -  Use to create only a single Instance of CategoryDAO class
     *          Use the application context, which will ensure that you
     *          don't accidentally leak an Activity's context.
     *          See this article for more information: http://bit.ly/6LRzfx
     */
    public synchronized static UserDAO getInstance(Context context)
    {
        if(instance == null)
        {
            instance = new UserDAO();
        }
        return instance;
    }


    /**
     *@desc - Use to get Application Context
     *@return -  Application Context
     */
    public static Context getAppContext() {
        return UserDAO.context;
    }

    /**
     * @desc    Use to take Single instance of Database and perform the add Request using SQL query
     * @param   userVO  - Contains all the attributes of Category
     */

    public void addUser(UserVO userVO)
    {
        USER_NAME = userVO.getUserName();
        USER_BALANCE = userVO.getBalance();
        USER_SAVING_AMOUNT = userVO.getSaving_amount();

        QUERY = INSERT + USER_TABLE_NAME + "(username,user_balance,user_saving_amount) VALUES ('"+USER_NAME+"','"+USER_BALANCE+"','"+
                                             USER_SAVING_AMOUNT+"');";

        dbAdapter.getInstance(getAppContext()).anyQuery(QUERY);
    }

    public double getBalance()
    {
        QUERY = "select user_balance from USER";

        Cursor cursor = dbAdapter.getInstance(getAppContext()).fetchQuery(QUERY);

        cursor.moveToFirst();
        double balance = cursor.getDouble(0);

        return balance;
    }

    public double getSavingAmount()
    {
        QUERY = "select user_saving_amount from USER";

        Cursor cursor = dbAdapter.getInstance(getAppContext()).fetchQuery(QUERY);

        cursor.moveToFirst();
        double saving_amount = cursor.getDouble(0);

        return saving_amount;
    }

    public double getExpenseAmount()
    {
        QUERY = "select user_expense_amount from USER";

        Cursor cursor = dbAdapter.getInstance(getAppContext()).fetchQuery(QUERY);

        cursor.moveToFirst();
        double expense_amount = cursor.getDouble(0);

        return expense_amount;
    }


    public void setBalance(UserVO userVO)
    {
        USER_BALANCE = userVO.getBalance();

        QUERY = "UPDATE "+USER_TABLE_NAME+" SET user_balance='"+USER_BALANCE+"' WHERE _id="+USER_ID+";";
        dbAdapter.getInstance(getAppContext()).anyQuery(QUERY);
    }

    public void setSavingAmount(UserVO userVO)
    {

        USER_SAVING_AMOUNT = userVO.getSaving_amount();

        QUERY = "UPDATE "+USER_TABLE_NAME+" SET user_saving_amount='"+USER_SAVING_AMOUNT+"' WHERE _id="+USER_ID+";";
        dbAdapter.getInstance(getAppContext()).anyQuery(QUERY);
    }

    public void setExpenseAmount(UserVO userVO)
    {

        USER_EXPENSE_AMOUNT = userVO.getExpense_amount();

        QUERY = "UPDATE "+USER_TABLE_NAME+" SET user_expense_amount='"+USER_EXPENSE_AMOUNT+"' WHERE _id="+USER_ID+";";
        dbAdapter.getInstance(getAppContext()).anyQuery(QUERY);
    }
}
