package com.joker.pagerbanner;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.joker.pager.BannerPager;
import com.joker.pager.PagerOptions;
import com.joker.pager.holder.ViewHolder;
import com.joker.pager.holder.ViewHolderCreator;

import java.util.ArrayList;
import java.util.List;

/**
 * CommonBannerActivity
 *
 * @author joker
 * @date 2018/1/19
 */
public class CommonBannerActivity extends AppCompatActivity {

    private BannerPager<String> bannerPager0;
    private BannerPager<String> bannerPager1;
    private BannerPager<String> bannerPager2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common);

        bannerPager0 = findViewById(R.id.banner_pager0);
        bannerPager1 = findViewById(R.id.banner_pager1);
        bannerPager2 = findViewById(R.id.banner_pager2);

        final List<String> data = new ArrayList<>();

        data.add("http://7xi8d6.com1.z0.glb.clouddn.com/20180109085038_4A7atU_rakukoo_9_1_2018_8_50_25_276.jpeg");
        data.add("http://7xi8d6.com1.z0.glb.clouddn.com/20180102083655_3t4ytm_Screenshot.jpeg");
        data.add("http://7xi8d6.com1.z0.glb.clouddn.com/20171228085004_5yEHju_Screenshot.jpeg");

        final PagerOptions pagerOptions0 = new PagerOptions.Builder(this)
                .setTurnDuration(2000)
                .setIndicatorColor(Color.RED, Color.BLUE)
                .setIndicatorSize(16)
                .setLoopEnable(false)
                .setIndicatorAlign(RelativeLayout.ALIGN_PARENT_LEFT)
                .setIndicatorMarginBottom(40)
                .build();

        bannerPager0
                .setPagerOptions(pagerOptions0)
                .setPages(data, new ViewHolderCreator<BannerPagerHolder>() {
                    @Override
                    public BannerPagerHolder createViewHolder() {
                        final View view = LayoutInflater.from(CommonBannerActivity.this).inflate(R.layout.item_image_banner, null);
                        return new BannerPagerHolder(view);
                    }
                });

        final PagerOptions pagerOptions1 = new PagerOptions.Builder(this)
                .setTurnDuration(2000)
                .setIndicatorColor(Color.RED, Color.BLUE)
                .setIndicatorSize(16)
                .setIndicatorAlign(RelativeLayout.ALIGN_PARENT_RIGHT)
                .setIndicatorMarginBottom(40)
                .build();

        bannerPager1
                .setPagerOptions(pagerOptions1)
                .setPages(data, new ViewHolderCreator<BannerPagerHolder>() {
                    @Override
                    public BannerPagerHolder createViewHolder() {
                        final View view = LayoutInflater.from(CommonBannerActivity.this).inflate(R.layout.item_image_banner, null);
                        return new BannerPagerHolder(view);
                    }
                });

        final PagerOptions pagerOptions2 = new PagerOptions.Builder(this)
                .setTurnDuration(2000)
                .setIndicatorColor(Color.RED, Color.BLUE)
                .setIndicatorSize(16)
                .setPagePadding(16)
                .setPrePagerWidth(60)
                .setIndicatorAlign(RelativeLayout.CENTER_IN_PARENT)
                .setIndicatorMarginBottom(40)
                .build();

        bannerPager2
                .setPagerOptions(pagerOptions2)
                .setPages(data, new ViewHolderCreator<BannerPagerHolder>() {
                    @Override
                    public BannerPagerHolder createViewHolder() {
                        final View view = LayoutInflater.from(CommonBannerActivity.this).inflate(R.layout.item_image_banner, null);
                        return new BannerPagerHolder(view);
                    }
                });
    }

    @Override
    protected void onResume() {
        super.onResume();
        bannerPager1.startTurning();
        bannerPager2.startTurning();
    }

    @Override
    protected void onStop() {
        super.onStop();
        bannerPager1.stopTurning();
        bannerPager2.stopTurning();
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
