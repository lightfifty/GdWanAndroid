package com.wux.wxwanandroid.base;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 向url中添加通用参数的拦截器
 */
public class QueryParameterInterceptor implements Interceptor {
    private Map<String, String> queryParameterMap = new HashMap<>();

    public void setQueryParameterMap(Map<String, String> queryParameterMap) {
        this.queryParameterMap = queryParameterMap;
    }

    public QueryParameterInterceptor() {
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        HttpUrl.Builder urlBuilder = request.url().newBuilder();
        if (queryParameterMap != null && !queryParameterMap.isEmpty()) {
            for (String key : queryParameterMap.keySet()) {
                urlBuilder.addQueryParameter(key, queryParameterMap.get(key));
            }
        }
        Request newRequest = request.newBuilder()
                .url(urlBuilder.build())
                .build();
        return chain.proceed(newRequest);
    }
}
