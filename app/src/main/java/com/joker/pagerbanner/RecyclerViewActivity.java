package com.joker.pagerbanner;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.joker.pager.BannerPager;
import com.joker.pager.PagerOptions;
import com.joker.pager.holder.ViewHolder;
import com.joker.pager.holder.ViewHolderCreator;
import com.joker.pager.transformer.DepthCardTransformer;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private TestAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        mRecyclerView = findViewById(R.id.recyclerView);
        initData();
    }

    private void initData() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter = new TestAdapter());

        data.add("http://7xi8d6.com1.z0.glb.clouddn.com/20180109085038_4A7atU_rakukoo_9_1_2018_8_50_25_276.jpeg");
        data.add("http://7xi8d6.com1.z0.glb.clouddn.com/20180102083655_3t4ytm_Screenshot.jpeg");
        data.add("http://7xi8d6.com1.z0.glb.clouddn.com/20171228085004_5yEHju_Screenshot.jpeg");
    }

    final List<String> data = new ArrayList<>();


    private class TestAdapter extends RecyclerView.Adapter {

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (viewType == 0) {
                return new BannerHolder(new BannerPager<String>(parent.getContext()));
            } else {
                return new TestHolder(new TextView(parent.getContext()));
            }
        }

        @Override
        public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
            final int viewType = getItemViewType(position);
            if (viewType != 0) {
                ((TestHolder) holder).mTextView.setText("测试数据： " + position);
            } else {
                final BannerPager<String> bannerPager = ((BannerHolder) holder).mBannerPager;
                if (!bannerPager.isStartLoop()) {
                    bannerPager.startTurning();
                }
            }
        }

        @Override
        public int getItemViewType(int position) {
            if (position == 10 || position == 20) {
                return 0;
            } else {
                return 1;
            }
        }

        @Override
        public int getItemCount() {
            return 60;
        }
    }

    private class TestHolder extends RecyclerView.ViewHolder {

        private final TextView mTextView;

        private TestHolder(TextView itemView) {
            super(itemView);
            mTextView = itemView;
        }
    }

    private class BannerHolder extends RecyclerView.ViewHolder {

        private BannerPager<String> mBannerPager;

        private BannerHolder(BannerPager<String> itemView) {
            super(itemView);
            mBannerPager = itemView;
            init(itemView.getContext());
        }

        private void init(final Context context) {
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 300);
            mBannerPager.setLayoutParams(layoutParams);

            final PagerOptions pagerOptions0 = new PagerOptions.Builder(context)
                    .setTurnDuration(2000)
                    .openDebug(true)
                    .setIndicatorSize(20)
                    .setPageTransformer(new DepthCardTransformer())
                    .setIndicatorAlign(RelativeLayout.CENTER_IN_PARENT)
                    .setIndicatorMarginBottom(40)
                    .build();

            mBannerPager
                    .setPagerOptions(pagerOptions0)
                    .setPages(data, new ViewHolderCreator<BannerPagerHolder>() {
                        @Override
                        public BannerPagerHolder createViewHolder() {
                            final View view = LayoutInflater.from(context).inflate(R.layout.item_image_banner, null);
                            return new BannerPagerHolder(view);
                        }
                    });
        }
    }

    private class BannerPagerHolder extends ViewHolder<String> {

        private ImageView mImage;

        private BannerPagerHolder(View itemView) {
            super(itemView);
            mImage = itemView.findViewById(R.id.image);
        }

        @Override
        public void onBindView(View view, String data, int position) {
            Glide.with(mImage.getContext())
                    .load(data)
                    .into(mImage);
        }
    }
}
