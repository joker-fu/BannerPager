# BannerPager

#### how to use：
Step 1. in your layout
```
    <com.joker.pager.BannerPager
        android:id="@+id/banner_pager0"
        android:layout_width="match_parent"
        android:layout_height="200dp"
        android:layout_marginBottom="10dp" />
```

Step 2. in your code，configuration the options you need
```        
    final PagerOptions pagerOptions0 = new PagerOptions.Builder(this)
            .setTurnDuration(2000)
            .setLoopEnable(false)
            .setIndicatorColor(Color.RED, Color.BLUE)
            .setPrePagerWidth(100)
            .setIndicatorAlign(RelativeLayout.CENTER_IN_PARENT)
            .setIndicatorMarginBottom(300)
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
```

Step 3. in your code，call startTurning or stopTurning
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
        compile 'com.github.joker-fu:BannerPager:0.0.1'
    }
```
