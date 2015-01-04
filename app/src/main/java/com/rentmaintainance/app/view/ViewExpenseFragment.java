package com.rentmaintainance.app.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.rentmaintainance.app.Context;
import com.rentmaintainance.app.R;
import com.rentmaintainance.app.model.Expense;
import com.rentmaintainance.app.model.Tenant;
import com.rentmaintainance.app.utils.DateUtil;

import static com.rentmaintainance.app.AllConstants.EXPENSE_STRING;
import static com.rentmaintainance.app.AllConstants.TENANT_STRING;

public class ViewExpenseFragment extends Fragment {

    private TextView propertyIdValueTextView;
    private TextView amountValueTextView;
    private TextView detailsValueTextView;
    private TextView categoryValueTextView;
    private TextView dateValueTextView;
    private Button editExpenseButton;
    private Context context;

    public ViewExpenseFragment(Activity activity) {
        Context.getInstance().updateApplicationContext(activity);
    }

    public static ViewExpenseFragment newInstance(Tenant tenant) {
        ViewExpenseFragment fragment = new ViewExpenseFragment();

        Bundle bundle = new Bundle();
        bundle.putParcelable(TENANT_STRING, tenant);
        fragment.setArguments(bundle);

        return fragment;
    }

    public ViewExpenseFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final Expense expense = (Expense) getArguments().get(EXPENSE_STRING);
        View rootView = inflater.inflate(R.layout.fragment_view_expense, container, false);

        initializeView(rootView);

        propertyIdValueTextView.setText(expense.propertyId());
        amountValueTextView.setText(String.valueOf(expense.amount()));
        detailsValueTextView.setText(expense.details());
        categoryValueTextView.setText(expense.category());
        dateValueTextView.setText(DateUtil.formatDateTime(expense.date()));

        return rootView;

    }

    private void initializeView(View rootView) {
        propertyIdValueTextView = (TextView) rootView.findViewById(R.id.houseNumberValueTextView);
        amountValueTextView = (TextView) rootView.findViewById(R.id.amountValueTextView);
        detailsValueTextView = (TextView) rootView.findViewById(R.id.detailsValueTextView);
        categoryValueTextView = (TextView) rootView.findViewById(R.id.categoryValueTextView);
        dateValueTextView = (TextView) rootView.findViewById(R.id.dateValueTextView);
        editExpenseButton = (Button) rootView.findViewById(R.id.editExpenseButton);
        context = Context.getInstance().updateApplicationContext(getActivity().getApplicationContext());
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
