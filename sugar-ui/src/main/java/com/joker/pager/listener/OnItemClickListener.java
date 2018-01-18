package com.joker.pager.listener;

import android.support.annotation.IntRange;

/**
 * OnItemClickListener
 *
 * @author joker
 * @date 2018/1/12.
 */

public interface OnItemClickListener {
    /**
     * item 点击事件
     *
     * @param location location -1： 点击左边预显示  0： 当前显示 1： 右边预显示
     * @param position position
     */
    void onItemClick(@IntRange(from = -1, to = 1) int location, int position);
}
