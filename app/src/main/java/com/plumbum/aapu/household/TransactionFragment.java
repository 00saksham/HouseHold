package com.plumbum.aapu.household;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.plumbum.aapu.household.Cursors.TransactionCursor;
import com.plumbum.aapu.household.Implementaion.TransactionImplementation;

/**
 * Created by Dawn on 6/23/2016.
 */
public class TransactionFragment extends Fragment
{
    String SQL="select _id as _id,CATEGORY_NAME as CATEGORY_NAME,SUM AS SUM,REMARKS AS REMARKS,CATEGORY_ICON AS CATEGORY_ICON" +
            " FROM TRANSACTIONS ";

    TransactionImplementation transactionImplementation=null;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_transaction, container, false);
        return view;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getTransactionList();
    }

    private void getTransactionList()
    {
        Cursor cursor = TransactionImplementation.getInstance().fetchTransaction(SQL);

        TransactionCursor transactionCursor = new TransactionCursor(this.getContext(),cursor,0);

        ListView listView = (ListView) getView().findViewById(R.id.fragment_transaction_list);
        listView.setAdapter(transactionCursor);
    }
}
