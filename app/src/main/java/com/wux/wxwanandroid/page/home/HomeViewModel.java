package com.wux.wxwanandroid.page.home;

import android.app.Application;
import android.content.Context;

import com.wux.wxwanandroid.utils.CommonUtils;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * 考虑到要用到Application，这里继承自AndroidViewModel
 */
public class HomeViewModel extends AndroidViewModel {

    private MutableLiveData<String> mText;

    private MutableLiveData<Boolean> mNetConnect;

    public HomeViewModel(Application application) {
        super(application);
        mText = new MutableLiveData<>();
        mNetConnect = new MutableLiveData<>();
        checkNet();

        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<Boolean> getNetConnect() {
        return mNetConnect;
    }

    /**
     * 发起请求查询数据
     */
    public void initData() {



    }

    /**
     * 用户主动发起请求检测网络状态
     */
    public void checkNet() {
        // TODO: 2021/8/22 检查网络
        boolean isConnect = CommonUtils.isNetworkConnected(getApplication());
        mNetConnect.setValue(isConnect);
    }
}