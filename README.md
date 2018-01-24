# BannerPager

![效果图](https://github.com/joker-fu/BannerPager/blob/master/app/src/main/assets/gif.gif)

#### how to use：
 1. 步骤一
```
    <com.joker.pager.BannerPager
        android:id="@+id/banner_pager0"
        android:layout_width="match_parent"
        android:layout_height="200dp"
        android:layout_marginBottom="10dp" />
```

 2. 步骤二
```        
    //配置你需要的轮播参数
    final PagerOptions pagerOptions2 = new PagerOptions.Builder(this)
            .setTurnDuration(2000)
            .setIndicatorColor(Color.RED, Color.BLUE)
            .setIndicatorSize(16)
            .setPagePadding(16)
            .setPrePagerWidth(60)
            .setIndicatorAlign(RelativeLayout.CENTER_IN_PARENT)
            .setIndicatorMarginBottom(40)
            .build();
     
    //设置BannerPager
    bannerPager2
            .setPagerOptions(pagerOptions2)
            .setPages(data, new ViewHolderCreator<BannerPagerHolder>() {
                @Override
                public BannerPagerHolder createViewHolder() {
                    final View view = LayoutInflater.from(CommonBannerActivity.this).inflate(R.layout.item_image_banner, null);
                    return new BannerPagerHolder(view);
                }
            });
            
    
    //当前轮播的ViewHolder
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
```

 3. 步骤三
```
    @Override
    protected void onResume() {
        super.onResume();
        //适当位置开启轮播
        bannerPager0.startTurning();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //适当位置停止轮播
        bannerPager0.stopTurning();
    }
```

#### Library projects：
[![](https://jitpack.io/v/joker-fu/BannerPager.svg)](https://jitpack.io/#joker-fu/BannerPager)

Step 1. Add the JitPack repository to your build file

Add it in your root build.gradle at the end of repositories:
```
    allprojects {
        repositories {
            ...
            maven { url 'https://jitpack.io' }
        }
    }
```

Step 2. Add the dependency
```
    dependencies {
        compile 'com.github.joker-fu:BannerPager:0.0.7'
    }
```
