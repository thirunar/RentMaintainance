package com.rentmaintainance.app.view;

import android.app.Activity;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.*;
import com.emilsjolander.components.StickyScrollViewItems.StickyScrollView;
import com.rentmaintainance.app.Context;
import com.rentmaintainance.app.R;
import com.rentmaintainance.app.customviews.KenBurnsView;
import com.rentmaintainance.app.model.Expense;
import com.rentmaintainance.app.utils.DateUtil;
import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardArrayAdapter;
import it.gmariotti.cardslib.library.internal.CardHeader;
import it.gmariotti.cardslib.library.view.CardListView;

import java.util.ArrayList;
import java.util.List;

public class ViewExpensesFragment extends Fragment {

    private static final String TAG = "ViewExpensesFragment";
    private CardListView expenseListView;
    private float mActionBarHeight;
    private BottomMenuFragment bottomMenuFragment;

    private static View view;

    public ViewExpensesFragment(Activity activity) {
        Context.getInstance().updateApplicationContext(activity.getApplicationContext());
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null)
                parent.removeView(view);
        }
        try {
            view = inflater.inflate(R.layout.fragment_view_expenses, container, false);
            initializeView(view);
        } catch (InflateException e) {

        }
        return view;
    }

    private void initializeView(final View rootView) {
        setupKenBurnsView(rootView);
        setupStickyScrollView(rootView);
        setupExpenseListView(rootView);
        setupBottomMenuFragment();
    }

    private void setupBottomMenuFragment() {
        bottomMenuFragment = (BottomMenuFragment) getFragmentManager().findFragmentById(R.id.fragment_bottom_menu);
        bottomMenuFragment.plusButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getActivity().getActionBar() != null) {
                    getActivity().getActionBar().show();
                }
                android.support.v4.app.FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.container, new AddExpenseFragment(getActivity()))
                        .commit();

            }
        });
    }

    private void setupExpenseListView(View rootView) {
        expenseListView = (CardListView) rootView.findViewById(R.id.expenseListView);

        List<Expense> expenses = Context.getInstance().allExpense().getAllExpense();
        CardArrayAdapter expenseArrayAdapter = new CardArrayAdapter(getActivity(), getCardsFor(expenses));

        expenseListView.setAdapter(expenseArrayAdapter);
    }

    private void setupStickyScrollView(final View rootView) {
        StickyScrollView stickyScroll = (StickyScrollView) rootView.findViewById(R.id.sticky_scroll);
        stickyScroll.setShadowHeight(50);

        final TypedArray styledAttributes = getActivity().getTheme().obtainStyledAttributes(
                new int[]{android.R.attr.actionBarSize});
        mActionBarHeight = styledAttributes.getDimension(0, 0);

        stickyScroll.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                float y = rootView.findViewById(R.id.sticky_scroll).getScrollY();
                if (getActivity() != null && y >= mActionBarHeight && getActivity().getActionBar().isShowing()) {
                    getActivity().getActionBar().hide();
                } else if (getActivity() != null && y == 0 && !getActivity().getActionBar().isShowing()) {
                    getActivity().getActionBar().show();
                }
            }
        });
    }

    private void setupKenBurnsView(View rootView) {
        KenBurnsView headerPicture = (KenBurnsView) rootView.findViewById(R.id.header_picture);
        headerPicture.setResourceIds(R.drawable.picture0, R.drawable.picture1);
    }

    private List<Card> getCardsFor(List<Expense> expenses) {
        List<Card> cards = new ArrayList<Card>();
        for (Expense expense : expenses) {
            CardHeader cardheader = new CardHeader(getActivity());
            cardheader.setTitle(expense.category());

            Card card = new Card(getActivity().getApplicationContext());
            card.setTitle(Float.toString(expense.amount()) + " \nDate: " + DateUtil.formatDateTime(expense.date()));
            card.addCardHeader(cardheader);

            cards.add(card);
        }
        return cards;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (bottomMenuFragment != null)
            getFragmentManager().beginTransaction().remove(bottomMenuFragment).commit();
    }

}
