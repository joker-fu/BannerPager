package com.joker.pager.holder;

/**
 * ViewHolderCreator
 *
 * @author joker
 * @date 2018/1/10.
 */

public interface ViewHolderCreator<VH extends ViewHolder> {

    /**
     * 创建 ViewHolder
     *
     * @return ViewHolder
     */
    VH createViewHolder();
}
