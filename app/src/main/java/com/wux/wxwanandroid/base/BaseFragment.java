package com.wux.wxwanandroid.base;

import com.google.android.material.snackbar.Snackbar;

import androidx.fragment.app.Fragment;

/**
 * Description:
 *
 * @author:
 * @email:838640006@qq.com
 * @time: 2021/5/8 16:18.
 * version: 1.0
 */
public class BaseFragment extends Fragment {


    public void showSnackBarMsg(String msg) {
        Snackbar.make(getView(), msg, Snackbar.LENGTH_SHORT).show();
    }

}
