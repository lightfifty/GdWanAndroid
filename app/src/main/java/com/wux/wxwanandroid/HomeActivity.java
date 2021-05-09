package com.wux.wxwanandroid;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;
import com.wux.wxwanandroid.databinding.ActivityHomeBinding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class HomeActivity extends AppCompatActivity {
    ActivityHomeBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        initView();
    }

    private void initView() {
        // 侧滑栏初始化

        //主界面初始化
        setSupportActionBar(mBinding.content.toolbar);
        mBinding.content.fabGoToTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, R.string.go_to_top, Snackbar.LENGTH_SHORT).setAction("取消", null).show();
            }
        });

        AppBarConfiguration bottomBarConfiguration = new AppBarConfiguration.Builder(R.id.navigation_home, R.id.navigation_knowledge, R.id.navigation_square).build();
        NavController bottomBarNavController = Navigation.findNavController(this,R.id.home_fragment);
        NavigationUI.setupActionBarWithNavController(this, bottomBarNavController, bottomBarConfiguration);
        NavigationUI.setupWithNavController(mBinding.content.bnvHomeNavBottom,bottomBarNavController);
    }

}