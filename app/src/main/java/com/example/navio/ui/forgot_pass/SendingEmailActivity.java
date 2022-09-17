package com.example.navio.ui.forgot_pass;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.navio.R;

public class SendingEmailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sending_email);

        final ImageView sendingIcon = findViewById(R.id.sending_icon);
        final TextView sendingCodeText = findViewById(R.id.sending_an_email_text);

        final Animation sendingAnimation = new TranslateAnimation(0.0f, 0f, 50.0f, 100.0f);
        sendingAnimation.setDuration(800);
        sendingIcon.startAnimation(sendingAnimation);

        final Animation sendingCodeTextAnimation = new TranslateAnimation(0.0f, 0f, 150.0f, 50.0f);
        sendingCodeTextAnimation.setDuration(800);
        sendingCodeText.startAnimation(sendingCodeTextAnimation);

        sendingAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                sendingAnimation.setFillAfter(true);
                sendingIcon.setY(sendingIcon.getY() + 100.0f);
                sendingIcon.clearAnimation();

                sendingCodeTextAnimation.setFillAfter(true);
                sendingCodeText.setY(sendingCodeText.getY() + 50);
                sendingCodeText.clearAnimation();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}