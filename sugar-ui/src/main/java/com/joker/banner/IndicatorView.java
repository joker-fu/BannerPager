package com.joker.banner;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.ViewGroup;


/**
 * Indicator
 *
 * @author joker
 * @date 2018/1/18.
 */

final class IndicatorView extends AppCompatImageView {
    public IndicatorView(Context context) {
        super(context);
        init(context);
    }

    public IndicatorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public IndicatorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {

    }

    public void setPagerOptions(PagerOptions pagerOptions) {
        setImageDrawable(pagerOptions.mIndicatorDrawable[0]);
        setPadding(pagerOptions.mIndicatorDistance, 0, pagerOptions.mIndicatorDistance, 0);

        if (pagerOptions.mIndicatorSize != -1) {
            final ViewGroup.LayoutParams layoutParams = getLayoutParams();
            layoutParams.width = layoutParams.height = pagerOptions.mIndicatorSize;
            setLayoutParams(layoutParams);
        }
    }
}
