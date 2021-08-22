package com.wux.wxwanandroid.page.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kennyc.view.MultiStateView;
import com.scwang.smart.refresh.footer.BallPulseFooter;
import com.scwang.smart.refresh.header.BezierRadarHeader;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import com.wux.wxwanandroid.R;
import com.wux.wxwanandroid.base.BaseFragment;
import com.wux.wxwanandroid.databinding.FragmentHomeBinding;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class HomeFragment extends BaseFragment {

    private HomeViewModel homeViewModel;
    FragmentHomeBinding mHomeBinding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mHomeBinding = FragmentHomeBinding.inflate(inflater, container, false);
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        initView();
        return mHomeBinding.getRoot();
    }

    private void initView() {
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });
        homeViewModel.getNetConnect().observe(getViewLifecycleOwner(),isConnect->{
            if (!isConnect){
                mHomeBinding.msvContent.setViewState(MultiStateView.ViewState.ERROR);
            }
        });
        mHomeBinding.toolbar.setTitle("首页");
        mHomeBinding.toolbar.inflateMenu(R.menu.home_titile_menu);
        mHomeBinding.toolbar.setOnMenuItemClickListener(
                item -> {
                    if (item.getItemId() == R.id.home_titie_time) {
                        showSnackBarMsg("时间流逝！");
                    } else if (item.getItemId() == R.id.home_titie_scan) {
                        showSnackBarMsg("扫描！");
                    }
                    return true;
                }
        );
        mHomeBinding.msvContent.setOnClickListener(view -> {
            MultiStateView.ViewState currentState = mHomeBinding.msvContent.getViewState();
            if (currentState.equals(MultiStateView.ViewState.EMPTY)) {
                View emptyView = mHomeBinding.msvContent.getView(MultiStateView.ViewState.EMPTY);
                if (emptyView != null) {
                    // TODO: 2021/8/22  这里使用统一的样式，后续增加外置的
                    // 触发网络请求

                }
            } else if (currentState.equals(MultiStateView.ViewState.ERROR)) {
                    // 触发重试机制

            } else {

            }
        });
        // 下拉刷新数据，上拉加载更多
        mHomeBinding.srlHome.setRefreshHeader(new BezierRadarHeader(getActivity()));
        mHomeBinding.srlHome.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                // 刷新数据后finish
                refreshLayout.finishRefresh();
            }
        });
        mHomeBinding.srlHome.setRefreshFooter(new BallPulseFooter(getActivity()));
        mHomeBinding.srlHome.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                // 加载更多后finish
                refreshLayout.autoLoadMore();
            }
        });




    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mHomeBinding = null;
    }

}