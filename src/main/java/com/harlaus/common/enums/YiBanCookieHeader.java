package com.harlaus.common.enums;

import lombok.Getter;

@Getter
public enum YiBanCookieHeader {
    Host("Host","gaut.shzu.edu.cn"),
    UpgradeInsecureRequests("Upgrade-Insecure-Requests","1"),
    UserAgent("User-Agent","Mozilla/5.0 (Linux; Android 10; vivo 1907 Build/QKQ1.190716.003; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/92.0.4515.131 Mobile Safari/537.36 yiban_android/5.0.11"),
    Accept("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9"),
    Authorization("authorization","Bearer "),
    Appversion("appversion","5.0.13"),
    Logintoken("logintoken",""),
    Signature("signature","kfyTkwLyrbehb7GhM+Cu9ayOq6i7+5UupIezDjHP0/oimgx7+clSeqDKMm6bOUGzdM63A+6rMeLihqA9+JBnaQ"),
    XRequestedWith("X-Requested-With","com.yiban.app"),
    AcceptEncoding("Accept-Encoding","gzip, deflate"),
    AcceptLanguage("Accept-Language","zh-CN,zh;q=0.9,en-US;q=0.8,en;q=0.7"),
    Pragma("Pragma","no-cache"),
    CacheControl("Cache-Control","no-cache"),
    Connection("Connection","no-cache");

    private final String key;
    private final String value;
    YiBanCookieHeader(String key,String value){
        this.key = key;
        this.value = value;
    }

}
