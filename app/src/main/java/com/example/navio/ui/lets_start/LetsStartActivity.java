package com.example.navio.ui.lets_start;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.navio.R;
import com.example.navio.ui.login.LoginActivity;

public class LetsStartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lets_start_main);

        final Button letsStartButton = findViewById(R.id.lets_start_button);
        final TextView helloWelcoTextView = findViewById(R.id.hello_welco);
        final TextView ifYouAreTextView = findViewById(R.id.if_you_are_);

        Animation welcomeComeInAnimation = new TranslateAnimation(0.0f, 0f, -500.0f, 500.0f);
        Animation welcomeGoOutAnimation = new TranslateAnimation(0.0f, 0f, 0.0f, -1400.0f);

        Animation ifYouAreComeInAnimation = new TranslateAnimation(0.0f, 0f, 1600.0f, -300.0f);
        Animation ifYouAreGoOutAnimation = new TranslateAnimation(0.0f, 0f, 0.0f, 1400.0f);

        Animation buttonComeInAnimation = new TranslateAnimation(0.0f, 0f, 1600.0f, -300.0f);
        Animation buttonGoOutAnimation = new TranslateAnimation(0.0f, 0f, 0.0f, 1400.0f);

        welcomeComeInAnimation.setDuration(1700);
        ifYouAreComeInAnimation.setDuration(2600);
        buttonComeInAnimation.setDuration(2600);

        // To read our color
        letsStartButton.setBackgroundTintList(null);
        letsStartButton.setOnClickListener(view -> {
            welcomeGoOutAnimation.setDuration(800);
            ifYouAreGoOutAnimation.setDuration(800);
            buttonGoOutAnimation.setDuration(800);
            welcomeGoOutAnimation.setFillAfter(true);
            ifYouAreGoOutAnimation.setFillAfter(true);
            buttonGoOutAnimation.setFillAfter(true);
            helloWelcoTextView.startAnimation(welcomeGoOutAnimation);
            ifYouAreTextView.startAnimation(ifYouAreGoOutAnimation);
            letsStartButton.startAnimation(buttonGoOutAnimation);
            Handler handler = new Handler();
            handler.postDelayed(() -> {
                final Intent intent = new Intent(LetsStartActivity.this, LoginActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
            }, 500);

        });
        helloWelcoTextView.startAnimation(welcomeComeInAnimation);
        ifYouAreTextView.startAnimation(ifYouAreComeInAnimation);
        letsStartButton.startAnimation(buttonComeInAnimation);


        welcomeComeInAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                welcomeComeInAnimation.setFillAfter(true);
                helloWelcoTextView.setY(helloWelcoTextView.getY() + 500);
                helloWelcoTextView.clearAnimation();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });

        ifYouAreComeInAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                ifYouAreComeInAnimation.setFillAfter(true);
                ifYouAreTextView.setY(ifYouAreTextView.getY() - 300);
                ifYouAreTextView.clearAnimation();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });

        buttonComeInAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                buttonComeInAnimation.setFillAfter(true);
                letsStartButton.setY(letsStartButton.getY() - 300);
                letsStartButton.clearAnimation();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        buttonGoOutAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {


            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }

}