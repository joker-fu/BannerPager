# BannerPager

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
    final PagerOptions pagerOptions0 = new PagerOptions.Builder(this)
            .setTurnDuration(2000)
            .setLoopEnable(false)
            .setIndicatorColor(Color.RED, Color.BLUE)
            .setIndicatorSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics()))
            .setIndicatorAlign(RelativeLayout.CENTER_IN_PARENT)
            .setIndicatorMarginBottom(300)
            .build();
     
    //设置BannerPager
    bannerPager0
            .setPagerOptions(pagerOptions0)
            .setPages(data, new ViewHolderCreator<BannerPagerHolder>() {
                @Override
                public BannerPagerHolder createViewHolder() {
                    final View view = LayoutInflater.from(BannerPagerActivity.this).inflate(R.layout.item_image_banner, null);
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
        bannerPager0.startTurning();
    }

    @Override
    protected void onPause() {
        super.onPause();
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
        compile 'com.github.joker-fu:BannerPager:0.0.2'
    }
```
