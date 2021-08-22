package com.wux.wxwanandroid.base;

import android.content.Context;

import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.orhanobut.logger.Logger;
import com.wux.wxwanandroid.BuildConfig;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpService {
    private volatile static HttpService INSTANCE = null;
    private Retrofit mRetrofit;
    private Context mContext;

    /**
     * 懒汉 + 双重检查 + volatile防止指令重排序导致问题
     *
     * @return
     */
    public static HttpService getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (HttpService.class) {
                if (INSTANCE == null) {
                    INSTANCE = new HttpService(context);
                }
            }
        }
        return INSTANCE;
    }

    private HttpService(Context context) {
        this.mContext = context;
        mRetrofit = createRetrofit(WxWanAndroidApi.HOST);
    }

    public Retrofit getRetrofit() {
        return mRetrofit;
    }

    /**
     * 创建并初始化OkHttpClient，给Retrofit的初始化做准备
     * 从应用到底层看 OkHttpClient 可以配置的点有
     *
     * @return
     */
    private OkHttpClient getOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        // 设置应用层拦截器，用于对生成的Request 在发出前进行二次处理，或者对底层返回的Response进行二次处理。
        if (BuildConfig.DEBUG) {
            // HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            // 生成的日志默认是通过Platform的log来打印的，比如打印到终端中，
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(String message) {
                    // 使用orhanobut的日志打印工具，可读性更高
                    Logger.d(message);
                }
            });
            // 设置打印日志级别,NONE,HEADERS,BODY,BASIC
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
            // 日志可能会泄露头里面的敏感信息，重编辑敏感头字段可以让其打印为“ ”
            httpLoggingInterceptor.redactHeader("Authorization");
            httpLoggingInterceptor.redactHeader("Cookie");
            // 正式加入拦截器队列中
            builder.addInterceptor(httpLoggingInterceptor);
        }
        // 通过拦截器添加头部字段
        Map<String, String> optionalHeaders = new HashMap<>();
        optionalHeaders.put("userCode", "123456");
        optionalHeaders.put("token", "initToken");
        HeaderInterceptor headerInterceptor = new HeaderInterceptor(optionalHeaders);
        builder.addInterceptor(headerInterceptor);

        // 通过拦截器为url添加通用参数
        Map<String, String> urlParameter = new HashMap<>();
        urlParameter.put("commonParameter", "test");
        QueryParameterInterceptor queryParameterInterceptor = new QueryParameterInterceptor();
        queryParameterInterceptor.setQueryParameterMap(urlParameter);
        builder.addInterceptor(queryParameterInterceptor);

        // todo 这里需要完成缓存拦截器
        // builder.cache()

        // 接下来真的要发起网络请求了，需要设置网络请求参数
        builder.connectTimeout(5000, TimeUnit.MICROSECONDS);
        builder.readTimeout(5000, TimeUnit.MICROSECONDS);
        builder.writeTimeout(5000, TimeUnit.MICROSECONDS);

        // 发出网络请求后仍然可以使用网络层拦截器
        //builder.addNetworkInterceptor();

        // 设置Cookie
        ClearableCookieJar cookieJar =
                new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(mContext));
        builder.cookieJar(cookieJar);

        return builder.build();
    }

    private Retrofit createRetrofit(String baseUrl) {
        return new Retrofit.Builder()
                .client(getOkHttpClient())
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }


}
