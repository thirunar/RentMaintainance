package com.rentmaintainance.app.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.emilsjolander.components.StickyScrollViewItems.StickyScrollView;
import com.rentmaintainance.app.Context;
import com.rentmaintainance.app.R;
import com.rentmaintainance.app.customviews.KenBurnsView;
import com.rentmaintainance.app.model.Income;

import java.util.List;

public class ViewIncomesFragment extends Fragment {

    private static final String TAG = "ViewIncomesFragment";
    private ListView incomesListView;
    private int defaultHeaderTopPos;
    private int contentFrameLayoutPos;
    private DrawerLayout drawerLayout;


    public ViewIncomesFragment(Activity activity) {
        Context.getInstance().updateApplicationContext(activity.getApplicationContext());
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_view_incomes, container, false);

        initializeView(rootView);

        return rootView;
    }

    private void initializeView(View rootView) {
        KenBurnsView headerPicture = (KenBurnsView) rootView.findViewById(R.id.header_picture);
        headerPicture.setResourceIds(R.drawable.picture0, R.drawable.picture1);

        StickyScrollView stickyScroll = (StickyScrollView) rootView.findViewById(R.id.sticky_scroll);

        stickyScroll.setShadowHeight(50);

        incomesListView = (ListView) rootView.findViewById(R.id.incomesListView);

        List<Income> incomes = Context.getInstance().allIncome().getAllIncome();

        IncomeListAdaptor incomeListAdaptor = new IncomeListAdaptor(getActivity().getApplicationContext(), incomes);
        incomesListView.setAdapter(incomeListAdaptor);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }
}
