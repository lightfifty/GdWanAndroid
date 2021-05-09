package com.wux.wxwanandroid.ui.square;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wux.wxwanandroid.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class SquareFragment extends Fragment {

    private SquareViewModel mSquareViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mSquareViewModel =
                new ViewModelProvider(this).get(SquareViewModel.class);
        View root = inflater.inflate(R.layout.fragment_square, container, false);
        final TextView textView = root.findViewById(R.id.text_notifications);
        mSquareViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}