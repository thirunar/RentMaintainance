package com.rentmaintainance.app.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.rentmaintainance.app.R;
import com.rentmaintainance.app.model.Property;

import java.util.List;

public class PropertiesListAdaptor extends BaseAdapter {

    private static final String TAG = "PropertiesListAdaptor";
    private Context context;
    private List<Property> properties;


    public PropertiesListAdaptor(Context context, List<Property> properties) {
        super();
        this.context = context;
        this.properties = properties;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.property_row, parent, false);
        }

        TextView houseNumberTextView = (TextView) convertView.findViewById(R.id.houseNumberTextView);
        houseNumberTextView.setText(properties.get(position).getHouseNumber());

        TextView houseNameTextView = (TextView) convertView.findViewById(R.id.houseNameTextView);
        houseNameTextView.setText(properties.get(position).getName());

        return convertView;
    }

    @Override
    public int getCount() {
        return properties.size();
    }

    @Override
    public Property getItem(int position) {
        return properties.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

}
