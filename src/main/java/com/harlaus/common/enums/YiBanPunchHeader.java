package com.harlaus.common.enums;

import lombok.Getter;

@Getter
public enum YiBanPunchHeader {
    Host("Host","gaut.shzu.edu.cn"),
    ContentLength("Content-Length","551"),
    Accept("Accept","application/json, text/plain, */*"),
    UserAgent("User-Agent","Mozilla/5.0 (Linux; Android 10; vivo 1907 Build/QKQ1.190716.003; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/92.0.4515.131 Mobile Safari/537.36 yiban_android/5.0.11"),
    ContentType("Content-Type","application/x-www-form-urlencoded"),
    Origin("Origin","http://gaut.shzu.edu.cn"),
    XRequestedWith("X-Requested-With","com.yiban.app"),
    Referer("Referer","http://gaut.shzu.edu.cn/"),
    AcceptEncoding("Accept-Encoding","gzip, deflate"),
    AcceptLanguage("Accept-Language","zh-CN,zh;q=0.9,en-US;q=0.8,en;q=0.7"),

    /**
     * 需要自己拼接
     */
    Cookie("Cookie",""),

    Pragma("Pragma","no-cache"),
    CacheControl("Cache-Control","no-cache"),
    Connection("Connection","keep-alive");

    private final String key;
    private final String value;

    YiBanPunchHeader(String key, String value) {
        this.key = key;
        this.value = value;
    }

}
