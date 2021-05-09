package com.wux.wxwanandroid.bean;

/**
 * Description:
 *
 * @author:
 * @email:838640006@qq.com
 * @time: 2021/5/8 23:22.
 * version: 1.0
 */
public class BaseResponse <T>{
    T data;
    int errorCode;
    String errorMsg;
}
