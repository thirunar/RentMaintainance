package com.rentmaintainance.app.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.emilsjolander.components.StickyScrollViewItems.StickyScrollView;
import com.melnykov.fab.FloatingActionButton;
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

import static com.rentmaintainance.app.AllConstants.EXPENSE_STRING;

public class ViewExpensesFragment extends Fragment {

    private static final String TAG = "ViewExpensesFragment";
    private CardListView expenseListView;

    private View view;
    private FloatingActionButton floatingActionButton;

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

        view = inflater.inflate(R.layout.fragment_view_expenses, container, false);
        initializeView(view);
        return view;
    }

    private void initializeView(final View rootView) {
        setupKenBurnsView(rootView);
        setupStickyScrollView(rootView);
        setupExpenseListView(rootView);
        setupActionButton(rootView);
    }

    private void setupActionButton(View rootView) {
        floatingActionButton = (FloatingActionButton) rootView.findViewById(R.id.add_expense_button);
        final FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
    }

    private void setupKenBurnsView(View rootView) {
        KenBurnsView headerPicture = (KenBurnsView) rootView.findViewById(R.id.header_picture);
        headerPicture.setResourceIds(R.drawable.picture0, R.drawable.picture1);
    }

    private List<Card> getCardsFor(List<Expense> expenses) {
        List<Card> cards = new ArrayList<Card>();
        for (final Expense expense : expenses) {
            CardHeader cardheader = new CardHeader(getActivity());
            cardheader.setTitle(expense.category());

            Card card = new Card(getActivity().getApplicationContext());
            card.setTitle(Float.toString(expense.amount()) + " \nDate: " + DateUtil.formatDateTime(expense.date()));
            card.addCardHeader(cardheader);

            final FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            card.setOnClickListener(new Card.OnCardClickListener() {
                @Override
                public void onClick(Card card, View view) {
                    ViewExpenseFragment fragment = new ViewExpenseFragment(getActivity());
                    Bundle bundle = new Bundle();
                    bundle.putParcelable(EXPENSE_STRING, expense);
                    fragment.setArguments(bundle);
                    fragmentManager.beginTransaction()
                            .replace(R.id.container, fragment)
                            .commit();

                }
            });
            cards.add(card);
        }
        return cards;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

}
