package com.rentmaintainance.app.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import com.rentmaintainance.app.Context;
import com.rentmaintainance.app.R;
import com.rentmaintainance.app.model.Income;
import com.rentmaintainance.app.service.IncomeService;
import com.rentmaintainance.app.utils.DateUtil;

public class AddIncomeFragment extends Fragment implements View.OnClickListener {

    private Context context;
    private EditText houseNumberEditText;
    private EditText dateEditText;
    private EditText detailsEditText;
    private EditText amountEditText;
    private Button addIncomeButton;
    private IncomeService incomeService;

    public AddIncomeFragment(Activity activity) {
        context = Context.getInstance();
        Context.getInstance().updateApplicationContext(activity);
        incomeService = context.getIncomeService();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_income, container, false);

        initializeView(rootView);
        addIncomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                incomeService.addIncome(getIncomeDetails());
            }
        });

        return rootView;
    }

    private Income getIncomeDetails() {
        Income income = new Income();
        income.withPropertyId(houseNumberEditText.getText().toString())
                .withAmount(Float.parseFloat(amountEditText.getText().toString()))
                .withDate(DateUtil.getDateTime(dateEditText.getText().toString()))
                .withDetails(detailsEditText.getText().toString());
        return income;
    }

    private void initializeView(View rootView) {
        houseNumberEditText = (EditText) rootView.findViewById(R.id.houseNumberEditText);
        dateEditText = (EditText) rootView.findViewById(R.id.dateEditText);
        detailsEditText = (EditText) rootView.findViewById(R.id.detailsEditText);
        amountEditText = (EditText) rootView.findViewById(R.id.amountEditText);
        addIncomeButton = (Button) rootView.findViewById(R.id.addIncomeButton);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.addIncomeButton:
                incomeService.addIncome(getIncomeDetails());
        }
    }
}
