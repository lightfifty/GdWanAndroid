package com.wux.wxwanandroid.ui.home;


import com.wux.wxwanandroid.bean.BaseResponse;
import com.wux.wxwanandroid.ui.home.bean.Banner;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Description:
 * @author:
 * @email:838640006@qq.com
 * @time: 2021/5/16 22:41.
 * version: 1.0
 */
public interface HomeApi {
    /**
     * 首页banner
     * https://www.wanandroid.com/banner/json
     *
     * 方法：GET
     * 参数：无
     * @return
     */
    @GET("/banner/json")
    Call<BaseResponse<List<Banner>>> getBanner();

}
