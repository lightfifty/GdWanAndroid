package com.wux.wxwanandroid.base;

import com.wux.wxwanandroid.bean.BaseResponse;
import com.wux.wxwanandroid.page.home.bean.Banner;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * 调用玩Android的API
 */
public interface WxWanAndroidApi {
    String HOST= "https://www.wanandroid.com/";

    /**
     * 查询首页banner
     * @return 这里先用Call 熟悉Retrofit 的接口
     */
    @GET("banner/json")
    Call<BaseResponse<Banner>> getBanner();




}
