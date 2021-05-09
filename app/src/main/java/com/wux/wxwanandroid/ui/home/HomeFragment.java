package com.wux.wxwanandroid.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wux.wxwanandroid.databinding.FragmentHomeBinding;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    FragmentHomeBinding mHomeBinding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        mHomeBinding = FragmentHomeBinding.inflate(inflater,container,false);

        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);


        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                mHomeBinding.textHome.setText(s);
            }
        });
        return mHomeBinding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mHomeBinding = null;
    }

}