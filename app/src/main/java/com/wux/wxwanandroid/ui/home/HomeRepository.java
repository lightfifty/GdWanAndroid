package com.wux.wxwanandroid.ui.home;

import retrofit2.Retrofit;

/**
 * Description:
 *
 * @author:
 * @email:838640006@qq.com
 * @time: 2021/5/16 23:02.
 * version: 1.0
 */
public class HomeRepository {
    Retrofit mRetrofit;
    HomeApi mHomeApi;
    HomeRepository(){

        //OkHttpClient okHttpClient = new OkHttpClient().newBuilder().addInterceptor().build();

        mRetrofit = new Retrofit.Builder().baseUrl("https://www.wanandroid.com").build();
        mHomeApi = mRetrofit.create(HomeApi.class);
    }

//    List<Banner> getBanner(){
//
//
//    }
}

