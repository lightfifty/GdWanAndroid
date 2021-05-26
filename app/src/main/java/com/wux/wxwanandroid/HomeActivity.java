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
        // 设置顶部要关联的item，
        AppBarConfiguration bottomBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home,
                R.id.navigation_knowledge,
                R.id.navigation_square,
                R.id.navigation_faqs,
                R.id.navigation_offical_accounts).build();
        // 创建NavController
        NavController bottomBarNavController = Navigation.findNavController(this,R.id.home_fragment);
        // 为底部导航栏创建设置控制器
        NavigationUI.setupWithNavController(mBinding.content.bnvHomeNavBottom,bottomBarNavController);
        // 建立顶部toolbar和底部导航栏的关联
        NavigationUI.setupActionBarWithNavController(this, bottomBarNavController, bottomBarConfiguration);
    }

}