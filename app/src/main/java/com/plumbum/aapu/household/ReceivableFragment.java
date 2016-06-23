package com.plumbum.aapu.household;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 *  @desc  A fragment use to represent Receivable Amount(DEBT) List In Debts/Loan Activity
 */
public class ReceivableFragment extends Fragment
{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_receivable, container, false);
        return view;
    }

}
