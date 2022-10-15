package com.harlaus.common.enums;

import lombok.Getter;

@Getter
public enum YiBanLoginTokenHeader {
    Authorization("Authorization","Bearer"),
    loginToken("loginToken",""),
    AppVersion("AppVersion","5.0.13"),
    UserAgent("User-Agent","YiBan/5.0.13 Mozilla/5.0 (Linux; Android 10; vivo 1907 Build/QKQ1.190716.003; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/92.0.4515.131 Mobile Safari/537.36"),
    smdid("smdid","BGxEoObqizdDVnKXXweSONz+mT72Q6ic09Dunm3WCbldCwaT3E9wcEuZLRJzOwYbEYcOqLxFa5Xi+SFkt4vtaNQ=="),
    ContentType("Content-Type","application/x-www-form-urlencoded"),
    ContentLength("Content-Length","895"),
    Host("Host","m.yiban.cn"),
    AcceptEncoding("Accept-Encoding", "utf-8"),
    Pragma("Pragma","no-cache"),
    CacheControl("Cache-Control","no-cache"),
    Connection("Connection","keep-alive");

    private final String key;
    private final String value;

    YiBanLoginTokenHeader(String key, String value){
        this.key = key;
        this.value = value;
    }

}
