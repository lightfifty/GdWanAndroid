package com.wux.wxwanandroid.base;

import android.text.TextUtils;

import java.io.IOException;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 对请求头做处理的的拦截器
 */
public class HeaderInterceptor implements Interceptor {
    Map<String, String> optionalHeaders;

    public HeaderInterceptor(Map<String, String> headers) {
        this.optionalHeaders = headers;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        /**********************拿到上层拦截器传递下来的请求**************************/
        Request beforeRequest = chain.request();
        // 拿到请求的建造者
        Request.Builder builder = beforeRequest.newBuilder();
        // 有参数时为请求添加新字段
        if (optionalHeaders != null && !optionalHeaders.isEmpty()) {
            for (String key : optionalHeaders.keySet()) {
                builder.addHeader(key, optionalHeaders.get(key));
            }
        }
        // 生成经过处理的请求
        Request dealedRequest = builder.build();
        /**********************收到下层拦截器传回的响应**********************/
        // 通过chain向下传递请求并得到底层响应
        Response afterResponse = chain.proceed(dealedRequest);
        // 将响应中最新的值更新到本地
        if (optionalHeaders !=null && !optionalHeaders.isEmpty()){
            for (String key:optionalHeaders.keySet()){
                String value = afterResponse.headers().get(key);
                if (!TextUtils.isEmpty(value)){
                    optionalHeaders.put(key,value);
                }
            }
        }
        // 这里只取响应中的数据，不对响应做修改，直接返回。
        return afterResponse;
    }
}
