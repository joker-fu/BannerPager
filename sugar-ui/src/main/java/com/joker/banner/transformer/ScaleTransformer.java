package com.joker.banner.transformer;

import android.view.View;

/**
 * ScaleTransformer
 *
 * @author joker
 * @date 2018/1/11.
 */

public class ScaleTransformer extends ABaseTransformer {
    private static final float MIN_SCALE = 0.88F;
    private static final float MIN_ALPHA = 0.8F;

    @Override
    public void transformPage(View page, float position) {
        onTransform(page, position);
    }

    @Override
    protected void onTransform(View page, float position) {
        if (position < -1 || position > 1) {
            // [-Infinity,-1) || (1,+Infinity]
            page.setScaleY(MIN_SCALE);
            page.setAlpha(MIN_ALPHA);
        } else if (position <= 0) {
            // [-1,0]
            final float scaleFactor = MIN_SCALE + (1 - MIN_SCALE) * (1 - Math.abs(position));
            page.setScaleY(scaleFactor);
            page.setAlpha(scaleFactor);
        } else if (position <= 1) {
            // (0,1]
            final float scaleFactor = MIN_SCALE + (1 - MIN_SCALE) * (1 - Math.abs(position));
            page.setScaleY(scaleFactor);
            page.setAlpha(scaleFactor);
        }
    }
}