package com.joker.pager.transformer;

import android.view.View;

/**
 * DepthCardTransformer
 *
 * @author joker
 * @date 1518/1/12.
 */

public class DepthCardTransformer extends BaseTransformer {

    @Override
    public void transformPage(View page, float position) {
        onTransform(page, position);
    }

    @Override
    protected void onTransform(View page, float position) {
        if (position < -1) {
            /* [-Infinity,-1)*/
            /*页面已经在屏幕左侧第一个*/
            page.setCameraDistance(10000);
            page.setPivotX(page.getWidth() / 2);
            page.setPivotY(page.getWidth());
            page.setRotationY(15);
        } else if (position <= 0) {
            /* [-1,0]*/
            /*页面从左侧进入或者向左侧滑出的状态*/
            page.setCameraDistance(10000);
            page.setPivotX(page.getWidth() / 2);
            page.setPivotY(page.getWidth());
            page.setRotationY(-15 + (1 - position) * 15);
        } else if (position <= 1) {
            /* (0,1]*/
            /*页面从右侧进入或者向右侧滑出的状态*/
            page.setCameraDistance(10000);
            page.setPivotX(page.getWidth() / 2);
            page.setPivotY(page.getWidth());
            page.setRotationY(-15 + (1 - position) * 15);
        } else if (position <= 2) {
            /*页面已经在屏幕右侧第一个*/
            page.setCameraDistance(10000);
            page.setPivotX(page.getWidth() / 2);
            page.setPivotY(page.getWidth());
            page.setRotationY(-15);
        }
    }
}