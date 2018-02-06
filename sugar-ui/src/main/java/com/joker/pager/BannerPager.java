package com.joker.pager;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.joker.pager.adapter.BannerViewPagerAdapter;
import com.joker.pager.holder.ViewHolderCreator;
import com.joker.pager.listener.OnItemClickListener;

import java.lang.reflect.Field;
import java.util.List;

/**
 * BannerPager
 *
 * @author joker
 * @date 2018/1/10.
 */

public class BannerPager<T> extends RelativeLayout implements ViewPager.OnPageChangeListener {

    private static final String TAG = BannerPager.class.getSimpleName();

    private BannerViewPager mViewPager;
    private BannerViewPagerAdapter<T> mAdapter;
    private LinearLayout mIndicatorLayout;
    private ImageView mCurrentIndicator;

    private PagerOptions mPagerOptions;
    private boolean isStartLoop;
    private boolean isDetached2StopLoop;

    private ViewPagerScroller mScroller;
    private OnPageChangeListener mPageChangeListener;
    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            int currentItem = mViewPager.getCurrentItem();
            if (currentItem < mAdapter.getCount() - 1) {
                currentItem++;
            } else {
                currentItem = 0;
            }
            mViewPager.setCurrentItem(currentItem);
            sendHandlerEmptyMessage();
            trace("切换轮播图");
            return true;
        }
    });
    private OnTouchListener mOnTouchListener = new OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            performClick();
            return mViewPager.onTouchEvent(event);
        }
    };

    public BannerPager(Context context) {
        this(context, null);
    }

    public BannerPager(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BannerPager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        LayoutInflater.from(context).inflate(R.layout.widget_banner_pager, this);
        mViewPager = (BannerViewPager) findViewById(R.id.widget_view_pager);
        mIndicatorLayout = (LinearLayout) findViewById(R.id.widget_indicator_container);
        mPagerOptions = new PagerOptions.Builder(context).build();
        initViewPagerScroll();
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.addOnPageChangeListener(this);
        setOnTouchListener(mOnTouchListener);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (isDetached2StopLoop && !isStartLoop) {
            startTurning();
            isDetached2StopLoop = false;
        }
        trace("onAttachedToWindow");
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (!isDetached2StopLoop && isStartLoop) {
            stopTurning();
            isDetached2StopLoop = true;
        }
        trace("onDetachedFromWindow");
    }

    /**
     * 设置ViewPager的滑动速度
     */
    private void initViewPagerScroll() {
        try {
            final Field scroller = ViewPager.class.getDeclaredField("mScroller");
            scroller.setAccessible(true);
            mScroller = new ViewPagerScroller(mViewPager.getContext());
            scroller.set(mViewPager, mScroller);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (mPageChangeListener != null) {
            mPageChangeListener.onPageScrolled(position, positionOffset, positionOffsetPixels);
        }
    }

    @Override
    public void onPageSelected(int position) {
        // 切换indicator
        final int realPosition = position % mIndicatorLayout.getChildCount();
        setIndicatorSelected(realPosition);
        if (mPageChangeListener != null) {
            mPageChangeListener.onPageSelected(realPosition);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if (mPageChangeListener != null) {
            mPageChangeListener.onPageScrollStateChanged(state);
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        handleEvent(ev);
        return super.dispatchTouchEvent(ev);
    }

    private void handleEvent(MotionEvent ev) {
        //没有使能loop 或者 没有调用start
        if (!mPagerOptions.mLoopEnable || !isStartLoop) {
            return;
        }
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_OUTSIDE:
                trace("dispatchTouchEvent: " + ev.getX());
                removeHandlerMessages();
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                trace("dispatchTouchEvent: " + ev.getX());
                removeHandlerMessages();
                sendHandlerEmptyMessage();
                break;
            default:
                trace("未知事件: " + ev.getAction());
                break;
        }
    }

    private void sendHandlerEmptyMessage() {
        if (mHandler != null && mPagerOptions.mLoopEnable) {
            mHandler.sendEmptyMessageDelayed(0, mPagerOptions.mDelayedTime);
        }
    }

    private void removeHandlerMessages() {
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
        }
    }

    private void setIndicatorSelected(int currentItem) {
        if (mCurrentIndicator != null) {
            mCurrentIndicator.setImageDrawable(mPagerOptions.mIndicatorDrawable[0]);
            mCurrentIndicator.setSelected(false);
        }
        final ImageView indicator = (ImageView) mIndicatorLayout.getChildAt(currentItem);
        indicator.setSelected(true);
        indicator.setImageDrawable(mPagerOptions.mIndicatorDrawable[1]);
        mCurrentIndicator = indicator;
    }

    /**
     * 设置 PagerOptions
     *
     * @param options options
     * @return BannerPager<T>
     */
    public BannerPager<T> setPagerOptions(PagerOptions options) {
        mPagerOptions = options;
        return this;
    }

    /**
     * 设置 page data
     *
     * @param data    List<T>
     * @param creator ViewHolderCreator
     */
    public void setPages(@NonNull List<T> data, @NonNull ViewHolderCreator creator) {
        mAdapter = new BannerViewPagerAdapter<>(data, creator);
        mViewPager.setAdapter(mAdapter);
        mAdapter.setLoopEnable(mPagerOptions.mLoopEnable);
        mAdapter.setViewPager(mViewPager);
        initIndicator();
        handlePagerOptions();
    }

    private void initIndicator() {
        final int count = mAdapter.getRealCount();
        mIndicatorLayout.removeAllViews();
        IndicatorView indicator;
        for (int i = 0; i < count; i++) {
            indicator = new IndicatorView(getContext());
            mIndicatorLayout.addView(indicator);
            indicator.setPagerOptions(mPagerOptions);
        }
        setIndicatorSelected(mViewPager.getCurrentItem());
        trace("indicator count : " + mIndicatorLayout.getChildCount());
    }

    private void handlePagerOptions() {
        //设置每页之间间距
        mViewPager.setPageMargin(mPagerOptions.mPagePadding);
        //设置预显示宽
        final ViewGroup.MarginLayoutParams mp = (MarginLayoutParams) mViewPager.getLayoutParams();
        mp.leftMargin = mp.rightMargin = mPagerOptions.mPrePagerWidth;
        mViewPager.setLayoutParams(mp);
        //设置指示器位置
        LayoutParams lp = (LayoutParams) mIndicatorLayout.getLayoutParams();
        lp.addRule(mPagerOptions.mIndicatorAlign);
        if (mPagerOptions.mIndicatorMarginBottom != -1) {
            lp.bottomMargin = mPagerOptions.mIndicatorMarginBottom;
        }
        mIndicatorLayout.setLayoutParams(lp);
        //设置切换效果
        mViewPager.setPageTransformer(true, mPagerOptions.mPageTransformer);
        //设置滚动速度
        mScroller.setScrollDuration(mPagerOptions.mScrollDuration);
        //设置指示器显示隐藏
        mIndicatorLayout.setVisibility(mPagerOptions.mIndicatorVisibility);
    }

    public void clearPages() {
        mAdapter.clearData();
        initIndicator();
    }

    public ViewPager getViewPager() {
        return mViewPager;
    }

    /**
     * 暂时不停供外部使用
     */
    private void setCurrentItem(int position) {
        if (mPagerOptions.mLoopEnable) {
            final int realCount = mAdapter.getRealCount();
            final int multiple = mViewPager.getCurrentItem() / realCount;
            position = position + multiple * realCount;
        }
        mViewPager.setCurrentItem(position, false);
        trace("new position: " + position);
    }

    public boolean isStartLoop() {
        return isStartLoop;
    }

    /**
     * 开始轮播
     */
    public void startTurning() {
        if (mAdapter == null) {
            return;
        }
        if (isStartLoop) {
            stopTurning();
        }
        mPagerOptions.mLoopEnable = true;
        mAdapter.setLoopEnable(true);
        mViewPager.addOnPageChangeListener(this);
        sendHandlerEmptyMessage();
        isStartLoop = true;
        isDetached2StopLoop = false;
        trace("startTurning ");
    }

    /**
     * 停止轮播
     */
    public void stopTurning() {
        removeHandlerMessages();
        mViewPager.removeOnPageChangeListener(this);
        isStartLoop = false;
        isDetached2StopLoop = true;
        trace("stopTurning ");
    }

    private void trace(String msg) {
        if (mPagerOptions.mDebug) {
            Log.d(TAG, "===> " + msg);
        }
    }

    /**
     * 获取ViewPager的滚动速度
     */
    public int getScrollDuration() {
        return mScroller.getScrollDuration();
    }

    /**
     * 设置 page 改变监听
     *
     * @param listener OnPageChangeListener
     */
    public BannerPager<T> setOnPageChangeListener(OnPageChangeListener listener) {
        mPageChangeListener = listener;
        return this;
    }

    /**
     * 设置 Item点击监听
     *
     * @param listener OnItemClickListener
     * @return BannerPager
     */
    public BannerPager<T> setOnItemClickListener(OnItemClickListener listener) {
        mViewPager.setOnItemClickListener(listener);
        return this;
    }
}
