package com.wux.wxwanandroid.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import com.wux.wxwanandroid.databinding.FragmentHomeBinding;

import org.jetbrains.annotations.NotNull;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    FragmentHomeBinding mHomeBinding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        mHomeBinding = FragmentHomeBinding.inflate(inflater, container, false);
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        initView();
//
//        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                mHomeBinding.textHome.setText(s);
//            }
//        });
        return mHomeBinding.getRoot();
    }

    private void initView() {
       mHomeBinding.srlHome.setOnRefreshListener(new OnRefreshListener() {
           @Override
           public void onRefresh(@NonNull @NotNull RefreshLayout refreshLayout) {

           }
       });

       mHomeBinding.srlHome.setOnLoadMoreListener(new OnLoadMoreListener() {
           @Override
           public void onLoadMore(@NonNull @NotNull RefreshLayout refreshLayout) {

           }
       });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mHomeBinding = null;
    }

}