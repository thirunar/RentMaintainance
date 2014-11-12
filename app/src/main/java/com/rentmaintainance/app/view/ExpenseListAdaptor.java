package com.rentmaintainance.app.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.rentmaintainance.app.R;
import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardArrayAdapter;
import it.gmariotti.cardslib.library.view.CardView;

import java.util.List;

public class ExpenseListAdaptor extends CardArrayAdapter {

    private static final String TAG = "ExpenseListAdaptor";
    private Context context;
    private ViewHolder mHolder;
    private List<Card> cards;


    public ExpenseListAdaptor(Context context, List<Card> cards) {
        super(context, cards);
        this.context = context;
        this.cards = cards;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        super.getView(position, convertView, parent);
//        if (convertView == null) {
//            convertView = LayoutInflater.from(context)
//                    .inflate(R.layout.expense_row, parent, false);
//        }
//        CustomCard card = new CustomCard(context, R.layout.card_thumbnail_layout);
//        card.setupInnerViewElements(parent, convertView);
//
//        Expense expense = expenses.get(position);
//        card.setHeader(expense.category());
//        card.setDetails(expense.details());
//        card.setDate(DateUtil.formatDateTime(expense.date()));
//        card.setAmount(Float.toString(expense.amount()));
//
//        CardView cardView = (CardView) convertView.findViewById(R.id.expense_card);
//        cardView.setCard(card);
//
//        return convertView;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.expense_row, parent, false);
            mHolder = new ViewHolder();
            mHolder.cardView = (CardView) convertView.findViewById(R.id.expense_card);

            convertView.setTag(mHolder);
        } else {
            mHolder = (ViewHolder) convertView.getTag();
        }
        mHolder.cardView.setCard(getItem(position));

        return convertView;
    }

    @Override
    public int getCount() {
        return cards.size();
    }

    @Override
    public Card getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public class ViewHolder {
        private CardView cardView;
    }
}
