package com.example.navio.ui.lets_start;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.navio.MainActivity;
import com.example.navio.R;

public class LetsStartActivity extends AppCompatActivity {
    Animation welcomeGoOutAnimation;
    Animation welcomeComeInAnimation;
    Animation ifYouAreGoOutAnimation;
    Animation ifYouAreComeInAnimation;
    Animation buttonGoOutAnimation;
    Animation buttonComeInAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lets_start_main);

        final Button letsStartButton = findViewById(R.id.lets_start_button);
        final TextView helloWelcoTextView = findViewById(R.id.hello_welco);
        final TextView ifYouAreTextView = findViewById(R.id.if_you_are_);

        Animation welcomeComeInAnimation = new TranslateAnimation(0.0f, 0f, 0.0f, 800.0f);
        Animation welcomeGoOutAnimation = new TranslateAnimation(0.0f, 0f, 0.0f, -1400.0f);
        Animation ifYouAreComeInAnimation = new TranslateAnimation(0.0f, 0f, 0.0f, -300.0f);
        Animation ifYouAreGoOutAnimation = new TranslateAnimation(0.0f, 0f, 0.0f, 600.0f);
        Animation buttonComeInAnimation = new TranslateAnimation(0.0f, 0f, 0.0f, -300.0f);
        Animation buttonGoOutAnimation = new TranslateAnimation(0.0f, 0f, 0.0f, 600.0f);
        welcomeComeInAnimation.setDuration(800);
        ifYouAreComeInAnimation.setDuration(800);
        buttonComeInAnimation.setDuration(800);

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
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    final Intent intent = new Intent(LetsStartActivity.this, MainActivity.class);
                    startActivity(intent);
                    overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                    finish();
                }
            }, 400);

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
                helloWelcoTextView.setY(helloWelcoTextView.getY() + 800);
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