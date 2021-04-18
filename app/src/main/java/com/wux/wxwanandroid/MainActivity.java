package com.wux.wxwanandroid;

import android.os.Bundle;
import android.view.View;

import com.wux.wxwanandroid.databinding.ActivityMainBinding;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding mBinding;
    private View mRoot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        mRoot = mBinding.getRoot();
        setContentView(mRoot);


    }
}