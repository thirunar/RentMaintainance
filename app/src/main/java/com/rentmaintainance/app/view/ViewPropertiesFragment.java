package com.rentmaintainance.app.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import com.rentmaintainance.app.Context;
import com.rentmaintainance.app.R;
import com.rentmaintainance.app.model.Property;

import java.util.List;

public class ViewPropertiesFragment extends Fragment {

    private static final String TAG = "ViewPropertiesFragment";
    private ListView propertiesListView;
    private Context context;

    public ViewPropertiesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_view_properties, container, false);

        initializeView(rootView);

        propertiesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                getFragmentManager().beginTransaction()
                        .replace(R.id.container,
                                ViewPropertyFragment.newInstance((Property) parent.getItemAtPosition(position)))
                        .addToBackStack(null)
                        .commit();
            }
        });
        return rootView;
    }

    private void initializeView(View rootView) {
        propertiesListView = (ListView) rootView.findViewById(R.id.propertiesListView);

        List<Property> properties = Context.getInstance().allProperties().getAllProperties();

        PropertiesListAdaptor propertiesListAdaptor = new PropertiesListAdaptor(getActivity().getApplicationContext(), properties);
        propertiesListView.setAdapter(propertiesListAdaptor);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }
}
