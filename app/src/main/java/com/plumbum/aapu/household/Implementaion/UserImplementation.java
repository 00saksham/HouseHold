package com.plumbum.aapu.household.Implementaion;

import android.content.Context;

import com.plumbum.aapu.household.DAO.UserDAO;
import com.plumbum.aapu.household.VO.UserVO;

/**
 * Created by Dawn on 6/26/2016.
 */
public class UserImplementation
{
    private static Context context;
    private static UserImplementation instance;

    UserDAO userDAO;
    UserVO userVO = new UserVO();


    /*
     *@desc - A private constructor
     */

    private UserImplementation()
    {

    }

    /**
     * @desc -  Use to create only a single Instance of DebtImplementation class
     *          Use the application context, which will ensure that you
     *          don't accidentally leak an Activity's context.
     *          See this article for more information: http://bit.ly/6LRzfx
     */

    public synchronized static UserImplementation getInstance()
    {
        if(instance == null)
        {
            instance = new UserImplementation();
        }
        return instance;
    }

    /**
     *@desc - Use to get Application Context
     *@return -  Application Context
     */

    public static Context getAppContext() {
        return UserImplementation.context;
    }


    public void addUser(String name,double balance)
    {
        userVO.setUserName(name);
        userVO.setBalance(balance);
        userVO.setSaving_amount(balance);

        userDAO.getInstance(getAppContext()).addUser(userVO);
    }

    public void setBalance(double balance)
    {
        userVO.setBalance(balance);

        userDAO.getInstance(getAppContext()).setBalance(userVO);
    }

    public void setUserSaving(double balance)
    {
        userVO.setSaving_amount(balance);

        userDAO.getInstance(getAppContext()).setSavingAmount(userVO);
    }

    public void setUserExpense(double balance)
    {
        userVO.setExpense_amount(balance);

        userDAO.getInstance(getAppContext()).setExpenseAmount(userVO);

    }

    public double getBalance()
    {
        return userDAO.getInstance(getAppContext()).getBalance();
    }

    public double getUserSaving()
    {
        return userDAO.getInstance(getAppContext()).getSavingAmount();
    }

    public double getUserExpense()
    {
        return userDAO.getInstance(getAppContext()).getExpenseAmount();
    }

}
