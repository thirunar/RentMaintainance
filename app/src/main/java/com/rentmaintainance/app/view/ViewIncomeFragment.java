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
import com.rentmaintainance.app.model.Income;
import com.rentmaintainance.app.model.Tenant;
import com.rentmaintainance.app.utils.DateUtil;

import static com.rentmaintainance.app.AllConstants.INCOME_STRING;

public class ViewIncomeFragment extends Fragment {

    private TextView propertyIdValueTextView;
    private TextView amountValueTextView;
    private TextView detailsValueTextView;
    private TextView categoryValueTextView;
    private TextView dateValueTextView;
    private Button editIncomeButton;
    private Context context;

    public ViewIncomeFragment(Activity activity) {
        Context.getInstance().updateApplicationContext(activity);
    }

    public static ViewIncomeFragment newInstance(Tenant tenant) {
        ViewIncomeFragment fragment = new ViewIncomeFragment();

        Bundle bundle = new Bundle();
        bundle.putParcelable(INCOME_STRING, tenant);
        fragment.setArguments(bundle);

        return fragment;
    }

    public ViewIncomeFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final Income income = (Income) getArguments().get(INCOME_STRING);
        View rootView = inflater.inflate(R.layout.fragment_view_income, container, false);

        initializeView(rootView);

        propertyIdValueTextView.setText(income.propertyId());
        amountValueTextView.setText(String.valueOf(income.amount()));
        detailsValueTextView.setText(income.details());
        dateValueTextView.setText(DateUtil.formatDateTime(income.date()));

        return rootView;

    }

    private void initializeView(View rootView) {
        propertyIdValueTextView = (TextView) rootView.findViewById(R.id.houseNumberValueTextView);
        amountValueTextView = (TextView) rootView.findViewById(R.id.amountValueTextView);
        detailsValueTextView = (TextView) rootView.findViewById(R.id.detailsValueTextView);
        categoryValueTextView = (TextView) rootView.findViewById(R.id.categoryValueTextView);
        dateValueTextView = (TextView) rootView.findViewById(R.id.dateValueTextView);
        editIncomeButton = (Button) rootView.findViewById(R.id.editExpenseButton);
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
