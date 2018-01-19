package com.joker.pager.adapter;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.joker.pager.BannerViewPager;
import com.joker.pager.holder.ViewHolder;
import com.joker.pager.holder.ViewHolderCreator;

import java.util.List;

/**
 * BannerViewPagerAdapter
 *
 * @author joker
 * @date 2018/1/10.
 */

public class BannerViewPagerAdapter<T> extends PagerAdapter {

    private static final String TAG = BannerViewPagerAdapter.class.getSimpleName();

    private final List<T> mData;
    private final ViewHolderCreator mCreator;
    private static final int MULTIPLE_COUNT = 360;
    private BannerViewPager mViewPager;
    private boolean mLoopEnable;

    public BannerViewPagerAdapter(@NonNull List<T> data, @NonNull ViewHolderCreator creator) {
        mData = data;
        mCreator = creator;
    }

    public List<T> getData() {
        return mData;
    }

    public void clearData() {
        mData.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mLoopEnable ? getRealCount() * MULTIPLE_COUNT : getRealCount();
    }

    public int getRealCount() {
        return mData.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return getItemView(container, position);
    }

    public void setViewPager(BannerViewPager bannerPager) {
        this.mViewPager = bannerPager;
    }

    @Override
    public void finishUpdate(ViewGroup container) {
        if (mLoopEnable) {
            int position = mViewPager.getCurrentItem();
            if (position == 0) {
                position = getRealCount() * MULTIPLE_COUNT >> 2;
            } else if (position == getCount() - 1) {
                position = (getRealCount() - 1) * MULTIPLE_COUNT >> 2;
            }
            try {
                mViewPager.setCurrentItem(position, false);
            } catch (IllegalStateException e) {
                Log.e(TAG, e.getMessage());
            }
        }
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @SuppressWarnings("unchecked")
    private View getItemView(ViewGroup container, int position) {

        final ViewHolder<T> holder = mCreator.createViewHolder();
        final View v = holder.getItemView();
        final int realPosition = toRealPosition(position);
        holder.onBindView(v, mData.get(realPosition), realPosition);

        container.addView(v);
        return v;
    }

    public final int toRealPosition(int position) {
        if (!mLoopEnable) {
            return position;
        }
        final int realCount = getRealCount();
        if (realCount == 0) {
            return 0;
        }
        return position % realCount;
    }

    public void setLoopEnable(boolean loop) {
        mLoopEnable = loop;
        notifyDataSetChanged();
    }
}
