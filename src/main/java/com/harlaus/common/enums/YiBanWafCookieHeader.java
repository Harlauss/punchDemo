package com.harlaus.common.enums;

import lombok.Getter;

@Getter
public enum YiBanWafCookieHeader {

    Host("Host","f.yiban.cn"),
    Connection("Connection","keep-alive"),
    UpgradeInsecureRequests("Upgrade-Insecure-Requests","1"),
    UserAgent("User-Agent","Mozilla/5.0 (Linux; Android 10; vivo 1907 Build/QKQ1.190716.003; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/92.0.4515.131 Mobile Safari/537.36 yiban_android/5.0.11"),
    Accept("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9"),

    /**
     * 加上登陆时获取的token
     */
    authorization("authorization","Bearer "),

    appversion("appversion","5.0.11"),

    /**
     * 加上登录获取的token
     */
    logintoken("logintoken",""),

    signature("signature","2owBO24xQDPEAFBYmdzXgbQ/Yst5V8ARnnd5+eyatseNw8ZX75GMM4elkAWDNDuiCVOPr7k+4O3fXArwcjzBeA"),
    XRequestedWith("X-Requested-With","com.yiban.app"),
    SecFetchSite("Sec-Fetch-Site","none"),
    SecFetchMode("Sec-Fetch-Mode","navigate"),
    SecFetchUser("Sec-Fetch-User","?1"),
    SecFetchDest("Sec-Fetch-Dest","document"),
    AcceptEncoding("Accept-Encoding", "gzip, deflate"),
    AcceptLanguage("Accept-Language","zh-CN,zh;q=0.9,en-US;q=0.8,en;q=0.7"),

    /**
     * 加上登录获取的token+; client=android
     */
    Cookie("Cookie","loginToken="),

    Pragma("Pragma","no-cache"),
    CacheControl("Cache-Control","no-cache");

    private final String key;

    private final String value;

    YiBanWafCookieHeader(String key, String value){
        this.key = key;
        this.value = value;
    }

}
