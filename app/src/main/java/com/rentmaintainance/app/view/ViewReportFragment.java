package com.rentmaintainance.app.view;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import com.rentmaintainance.app.Context;
import com.rentmaintainance.app.R;

import java.io.File;
import java.io.IOException;

public class ViewReportFragment extends Fragment implements View.OnClickListener {

    private Context context;
    private Spinner spinner;

    public ViewReportFragment(Activity activity) {
        context = Context.getInstance();
        Context.getInstance().updateApplicationContext(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_view_report, container, false);

        initializeView(rootView);

        return rootView;
    }

    private void initializeView(View rootView) {
        spinner = (Spinner) rootView.findViewById(R.id.options);

        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.options, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

        Button generateReportButton = (Button) rootView.findViewById(R.id.generate_report);
        generateReportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String selectedItem = spinner.getSelectedItem().toString();
                    if (selectedItem.equals(getString(R.string.property))) {
                        context.getReportingService().getReportForAllProperties();
                    } else if (selectedItem.equals(getString(R.string.income))) {
                        context.getReportingService().getReportForAllIncomes();
                    } else if (selectedItem.equals(getString(R.string.expense))) {
                        context.getReportingService().getReportForAllExpenses();
                    } else if (selectedItem.equals(getString(R.string.tenant))) {
                        context.getReportingService().getReportForAllTenants();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onClick(View view) {

    }
}
