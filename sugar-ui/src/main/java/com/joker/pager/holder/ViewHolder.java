package com.joker.pager.holder;

import android.view.View;

/**
 * ViewHolder
 *
 * @author joker
 * @date 2018/1/10.
 */

public abstract class ViewHolder<T> {

    private final View mItemView;

    public ViewHolder(View itemView) {
        mItemView = itemView;
    }

    public View getItemView() {
        return mItemView;
    }

    /**
     * 绑定数据
     *
     * @param view     view
     * @param data     data
     * @param position position
     */
    public abstract void onBindView(View view, T data, int position);
}
