package com.joker.pagerbanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

/**
 * MainActivity
 *
 * @author joker
 * @date 2018/1/19
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void buttonOne(View view) {
        startActivity(new Intent(this, CommonBannerActivity.class));
    }

    public void buttonTwo(View view) {
        startActivity(new Intent(this, ScaleBannerActivity.class));
    }

    public void buttonThree(View view) {
        startActivity(new Intent(this, DepthCardBannerActivity.class));
    }

    public void buttonFour(View view) {
        startActivity(new Intent(this, AccordionBannerActivity.class));
    }

    public void buttonFive(View view) {
        startActivity(new Intent(this, CubeOutBannerActivity.class));
    }
}
