package com.wux.wxwanandroid.ui.officialaccounts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wux.wxwanandroid.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class OfficalAccountsFragment extends Fragment {

    private OfficalAccountsViewModel mViewModel;

    public static OfficalAccountsFragment newInstance() {
        return new OfficalAccountsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.offical_accounts_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(OfficalAccountsViewModel.class);
        // TODO: Use the ViewModel
    }

}