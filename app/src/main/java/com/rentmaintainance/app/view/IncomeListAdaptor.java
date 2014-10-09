package com.rentmaintainance.app.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.rentmaintainance.app.R;
import com.rentmaintainance.app.model.Income;

import java.util.List;

public class IncomeListAdaptor extends BaseAdapter {

    private static final String TAG = "IncomeListAdaptor";
    private Context context;
    private List<Income> incomes;


    public IncomeListAdaptor(Context context, List<Income> incomes) {
        super();
        this.context = context;
        this.incomes = incomes;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.income_row, parent, false);
        }

        TextView dateTextView = (TextView) convertView.findViewById(R.id.dateTextView);
        dateTextView.setText(incomes.get(position).date().toString());
        TextView incomeAmountTextView = (TextView) convertView.findViewById(R.id.incomeAmountTextView);
        incomeAmountTextView.setText(String.valueOf(incomes.get(position).amount()));

        return convertView;
    }

    @Override
    public int getCount() {
        return incomes.size();
    }

    @Override
    public Income getItem(int position) {
        return incomes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

}
