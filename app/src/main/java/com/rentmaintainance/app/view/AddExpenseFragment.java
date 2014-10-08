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
import com.rentmaintainance.app.model.Expense;
import com.rentmaintainance.app.service.ExpenseService;
import com.rentmaintainance.app.utils.DateUtil;

public class AddExpenseFragment extends Fragment implements View.OnClickListener {

    private Context context;
    private EditText houseNumberEditText;
    private EditText dateEditText;
    private EditText detailsEditText;
    private EditText amountEditText;
    private Button addExpenseButton;
    private ExpenseService expenseService;
    private EditText categoryEditText;

    public AddExpenseFragment(Activity activity) {
        context = Context.getInstance();
        Context.getInstance().updateApplicationContext(activity);
        expenseService = context.getExpenseService();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_expense, container, false);

        initializeView(rootView);

        return rootView;
    }

    private Expense getExpenseDetails() {
        Expense expense = new Expense();
        expense.withPropertyId(houseNumberEditText.getText().toString())
                .withAmount(Float.parseFloat(amountEditText.getText().toString()))
                .withDate(DateUtil.getDateTime(dateEditText.getText().toString()))
                .withDetails(detailsEditText.getText().toString())
                .withCategory(categoryEditText.getText().toString());
        return expense;
    }

    private void initializeView(View rootView) {
        houseNumberEditText = (EditText) rootView.findViewById(R.id.houseNumberEditText);
        dateEditText = (EditText) rootView.findViewById(R.id.dateEditText);
        detailsEditText = (EditText) rootView.findViewById(R.id.detailsEditText);
        amountEditText = (EditText) rootView.findViewById(R.id.amountEditText);
        categoryEditText = (EditText) rootView.findViewById(R.id.categoryEditText);
        addExpenseButton = (Button) rootView.findViewById(R.id.addExpenseButton);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.addExpenseButton:
                expenseService.addExpense(getExpenseDetails());
        }
    }
}
