package com.rentmaintainance.app.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

public class ObservableScrollView extends ScrollView {
    private ScrollCallbacks mScrollCallbacks;

    public ObservableScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (mScrollCallbacks != null) {
            mScrollCallbacks.onScrollChanged();
        }
    }

    @Override
    public int computeVerticalScrollRange() {
        return super.computeVerticalScrollRange();
    }

    public void setCallbacks(ScrollCallbacks listener) {
        mScrollCallbacks = listener;
    }

    public static interface ScrollCallbacks {
        public void onScrollChanged();
    }
}
