package com.joker.pager.transformer;

import android.view.View;

/**
 * AccordionTransformer
 *
 * @author joker
 * @date 2018/1/12.
 */

public class AccordionTransformer extends BaseTransformer {

    @Override
    protected void onTransform(View view, float position) {
        view.setPivotX(position < 0 ? 0 : view.getWidth());
        view.setScaleX(position < 0 ? 1f + position : 1f - position);
    }

}
