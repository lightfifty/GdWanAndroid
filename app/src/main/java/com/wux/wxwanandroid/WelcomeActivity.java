package com.wux.wxwanandroid;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.wux.wxwanandroid.databinding.ActivityWelcomeBinding;

import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {

    private ActivityWelcomeBinding mBinding;
    private View mRoot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityWelcomeBinding.inflate(getLayoutInflater());
        mRoot = mBinding.getRoot();
        setContentView(mRoot);
        mBinding.lavAnimCenter.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationEnd(Animator animation) {
                Intent intent = new Intent(WelcomeActivity.this,HomeActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        mBinding.lavAnimCenter.playAnimation();
    }

    @Override
    protected void onDestroy() {
        mBinding.lavAnimCenter.cancelAnimation();
        super.onDestroy();
    }
}

