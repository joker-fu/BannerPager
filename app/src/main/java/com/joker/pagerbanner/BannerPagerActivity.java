package com.joker.pagerbanner;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.joker.pager.BannerPager;
import com.joker.pager.PagerOptions;
import com.joker.pager.holder.ViewHolder;
import com.joker.pager.holder.ViewHolderCreator;
import com.joker.pager.listener.OnItemClickListener;
import com.joker.pager.transformer.AccordionTransformer;
import com.joker.pager.transformer.CubeOutTransformer;
import com.joker.pager.transformer.DepthCardTransformer;
import com.joker.pager.transformer.ScaleTransformer;

import java.util.ArrayList;
import java.util.List;

/**
 * BannerPagerActivity
 *
 * @author joker
 * @date 2018/1/18
 */
public class BannerPagerActivity extends AppCompatActivity {

    private BannerPager<String> bannerPager0;
    private BannerPager<String> bannerPager1;
    private BannerPager<String> bannerPager2;
    private BannerPager<String> bannerPager3;
    private BannerPager<String> bannerPager4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner_pager);
        initView();
        initData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        bannerPager0.startTurning();
        bannerPager1.startTurning();
        bannerPager2.startTurning();
        bannerPager3.startTurning();
        bannerPager4.startTurning();
    }

    @Override
    protected void onPause() {
        super.onPause();
        bannerPager0.stopTurning();
        bannerPager1.stopTurning();
        bannerPager2.stopTurning();
        bannerPager3.stopTurning();
        bannerPager4.stopTurning();
    }

    private void initView() {
        bannerPager0 = findViewById(R.id.banner_pager0);
        bannerPager1 = findViewById(R.id.banner_pager1);
        bannerPager2 = findViewById(R.id.banner_pager2);
        bannerPager3 = findViewById(R.id.banner_pager3);
        bannerPager4 = findViewById(R.id.banner_pager4);
    }

    private void initData() {
        final List<String> data = new ArrayList<>();

        data.add("http://7xi8d6.com1.z0.glb.clouddn.com/20180109085038_4A7atU_rakukoo_9_1_2018_8_50_25_276.jpeg");
        data.add("http://7xi8d6.com1.z0.glb.clouddn.com/20180102083655_3t4ytm_Screenshot.jpeg");
        data.add("http://7xi8d6.com1.z0.glb.clouddn.com/20171228085004_5yEHju_Screenshot.jpeg");


        final PagerOptions pagerOptions0 = new PagerOptions.Builder(this)
                .setTurnDuration(2000)
                .setLoopEnable(false)
                .setIndicatorColor(Color.RED, Color.BLUE)
                .setIndicatorSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics()))
                .setIndicatorAlign(RelativeLayout.CENTER_IN_PARENT)
                .setIndicatorMarginBottom(300)
                .build();

        final PagerOptions pagerOptions1 = new PagerOptions.Builder(this)
                .setTurnDuration(2000)
                .setPagePadding(20)
                .setPrePagerWidth(80)
                .setIndicatorSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics()))
                .setLoopEnable(false)
                .setIndicatorAlign(RelativeLayout.CENTER_IN_PARENT)
                .setPageTransformer(new ScaleTransformer())
                .build();

        final PagerOptions pagerOptions2 = new PagerOptions.Builder(this)
                .setTurnDuration(2000)
                .setPrePagerWidth(160)
                .setIndicatorAlign(RelativeLayout.ALIGN_PARENT_RIGHT)
                .setPageTransformer(new DepthCardTransformer())
                .build();

        final PagerOptions pagerOptions3 = new PagerOptions.Builder(this)
                .setTurnDuration(2000)
                .setIndicatorAlign(RelativeLayout.ALIGN_PARENT_LEFT)
                .setPageTransformer(new AccordionTransformer())
                .build();

        final PagerOptions pagerOptions4 = new PagerOptions.Builder(this)
                .setTurnDuration(2000)
                .setPageTransformer(new CubeOutTransformer())
                .setIndicatorAlign(RelativeLayout.ALIGN_PARENT_LEFT)
                .build();


        bannerPager0
                .setPagerOptions(pagerOptions0)
                .setPages(data, new ViewHolderCreator<BannerPagerHolder>() {
                    @Override
                    public BannerPagerHolder createViewHolder() {
                        final View view = LayoutInflater.from(BannerPagerActivity.this).inflate(R.layout.item_image_banner, null);
                        return new BannerPagerHolder(view);
                    }
                });

        bannerPager1
                .setPagerOptions(pagerOptions1)
                .setPages(data, new ViewHolderCreator<BannerPagerHolder>() {
                    @Override
                    public BannerPagerHolder createViewHolder() {
                        final View view = LayoutInflater.from(BannerPagerActivity.this).inflate(R.layout.item_image_banner, null);
                        return new BannerPagerHolder(view);
                    }
                });
        bannerPager2
                .setPagerOptions(pagerOptions2)
                .setPages(data, new ViewHolderCreator<BannerPagerHolder>() {
                    @Override
                    public BannerPagerHolder createViewHolder() {
                        final View view = LayoutInflater.from(BannerPagerActivity.this).inflate(R.layout.item_image_banner, null);
                        return new BannerPagerHolder(view);
                    }
                });
        bannerPager1.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(int location, int position) {
                Toast.makeText(BannerPagerActivity.this, location + ": 点击：" + position, Toast.LENGTH_SHORT).show();
            }
        });
        bannerPager2.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(int location, int position) {
                Toast.makeText(BannerPagerActivity.this, location + ": 点击：" + position, Toast.LENGTH_SHORT).show();
            }
        });
        bannerPager3
                .setPagerOptions(pagerOptions3)
                .setPages(data, new ViewHolderCreator<BannerPagerHolder>() {
                    @Override
                    public BannerPagerHolder createViewHolder() {
                        final View view = LayoutInflater.from(BannerPagerActivity.this).inflate(R.layout.item_image_banner, null);
                        return new BannerPagerHolder(view);
                    }
                });
        bannerPager4
                .setPagerOptions(pagerOptions4)
                .setPages(data, new ViewHolderCreator<BannerPagerHolder>() {
                    @Override
                    public BannerPagerHolder createViewHolder() {
                        final View view = LayoutInflater.from(BannerPagerActivity.this).inflate(R.layout.item_image_banner, null);
                        return new BannerPagerHolder(view);
                    }
                });
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
