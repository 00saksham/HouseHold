package com.plumbum.aapu.household;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 *  @desc  A fragment use to represent Payable Amount(LOAN) List In Debts/Loan Activity
 */
public class PayableFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_payble, container, false);
        return view;
    }

}
