package com.cuyer.calitracker.View;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.cuyer.calitracker.Controller.ViewPagerAdapter;
import com.cuyer.calitracker.R;
import com.cuyer.calitracker.View.ViewPager.ViewPagerFirstSlideFragment;
import com.cuyer.calitracker.View.ViewPager.ViewPagerSecondSlideFragment;
import com.cuyer.calitracker.View.ViewPager.ViewPagerThirdSlideFragment;

import java.util.Objects;

public class LoginScreen extends AppCompatActivity {

    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);
    ViewPager2 viewPager2;
    ViewPagerAdapter viewPagerAdapter;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
        setContentView(R.layout.activity_login_screen);
        Objects.requireNonNull(getSupportActionBar()).hide();



        // sign up button listener
        Button sign_up_button = findViewById(R.id.sign_up_button);
        Button log_in_button = findViewById(R.id.log_in_button);

        sign_up_button.setOnClickListener(view -> {
            view.startAnimation(buttonClick);
            Intent intent = new Intent(LoginScreen.this, Sign_Up.class);
            startActivity(intent);
            overridePendingTransition(R.anim.push_up_in,R.anim.push_up_out);
        });

        log_in_button.setOnClickListener(view -> {
            view.startAnimation(buttonClick);
            Intent intent = new Intent(LoginScreen.this, LogIn.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in,R.anim.slide_in);
        });


        VideoView videoview = (VideoView) findViewById(R.id.videoView2);
        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.login_video2);

        videoview.setVideoURI(uri);
        videoview.start();

        videoview.setOnCompletionListener(mediaPlayer -> {
            videoview.start();
        });



        // ViewPager
        viewPager2 = findViewById(R.id.ViewPager);
        linearLayout = findViewById(R.id.SliderDots);
        int dotscount;
        ImageView[] dots;
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(),getLifecycle());



        viewPagerAdapter.addFragment(new ViewPagerFirstSlideFragment());
        viewPagerAdapter.addFragment(new ViewPagerSecondSlideFragment());
        viewPagerAdapter.addFragment(new ViewPagerThirdSlideFragment());

        viewPager2.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        viewPager2.setAdapter(viewPagerAdapter);
        viewPager2.bringToFront();

        dotscount = viewPagerAdapter.getItemCount();
        dots = new ImageView[dotscount];

        for(int i = 0; i < dotscount; i++){

            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.nonactive_dot));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(8,0,8,0);

            linearLayout.addView(dots[i], params);
        }

        dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.active_dot));

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                for (int i = 0; i < dotscount ; i++) {
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.nonactive_dot));
                }
                dots[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.active_dot));
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });





    }


    // after coming back to login, start video again
    protected void onResume() {
        super.onResume();
        VideoView videoview = (VideoView) findViewById(R.id.videoView2);
        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.login_video2);
        videoview.setVideoURI(uri);
        videoview.start();
        videoview.setOnCompletionListener(mediaPlayer -> videoview.start());

    }

    @Override
    public void onBackPressed() {

    }

}