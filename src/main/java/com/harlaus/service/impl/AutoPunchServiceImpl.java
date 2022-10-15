package com.harlaus.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.harlaus.common.enums.*;
import com.harlaus.common.utils.PasswordUtil;
import com.harlaus.entity.PunchRecord;
import com.harlaus.entity.User;
import com.harlaus.service.AutoPunchService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AutoPunchServiceImpl implements AutoPunchService {
    @Override
    public String getLoginToken(User user) throws IOException {
        String url = "https://m.yiban.cn/api/v4/passport/login";
        URL obj = new URL(url);
        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

        //添加请求头
        con.setRequestMethod("POST");
//        con.setRequestProperty(YiBanLoginTokenHeader.Authorization.getKey(), YiBanLoginTokenHeader.Authorization.getValue());
//        con.setRequestProperty(YiBanLoginTokenHeader.loginToken.getKey(), YiBanLoginTokenHeader.loginToken.getValue());
        con.setRequestProperty(YiBanLoginTokenHeader.AppVersion.getKey(), YiBanLoginTokenHeader.AppVersion.getValue());
        con.setRequestProperty(YiBanLoginTokenHeader.UserAgent.getKey(), YiBanLoginTokenHeader.UserAgent.getValue());
        con.setRequestProperty(YiBanLoginTokenHeader.smdid.getKey(), YiBanLoginTokenHeader.smdid.getValue());
        con.setRequestProperty(YiBanLoginTokenHeader.ContentType.getKey(), YiBanLoginTokenHeader.ContentType.getValue());
        con.setRequestProperty(YiBanLoginTokenHeader.ContentLength.getKey(), YiBanLoginTokenHeader.ContentLength.getValue());
        con.setRequestProperty(YiBanLoginTokenHeader.Host.getKey(), YiBanLoginTokenHeader.Host.getValue());
        con.setRequestProperty(YiBanLoginTokenHeader.AcceptEncoding.getKey(), YiBanLoginTokenHeader.AcceptEncoding.getValue());
//        con.setRequestProperty(YiBanLoginTokenHeader.Pragma.getKey(), YiBanLoginTokenHeader.Pragma.getValue());
//        con.setRequestProperty(YiBanLoginTokenHeader.CacheControl.getKey(), YiBanLoginTokenHeader.CacheControl.getValue());
        con.setRequestProperty(YiBanLoginTokenHeader.Connection.getKey(), YiBanLoginTokenHeader.Connection.getValue());

        String password = PasswordUtil.encryption(user.getPassword());
        String urlParameters = "app=1&" +
                "sig=98ff685be3c7724d&" +
                "ct=2&" +
                "password=" + password + "&" +
                "authCode=&" +
                "identify=3C75A83EE30CC76676797A06712FFEB0C746CDF3ACA80413CFEB98B4EA8F740B&" +
                "v=5.0.13&" +
                "mobile=" + user.getPhone() + "&" +
                "sversion=29&" +
                "device=OnePlus%3AGM1910&" +
                "apn=wifi&" +
                "token=";

        //发送Post请求
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();


        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8));

        StringBuffer response = new StringBuffer();
        String inputLine;

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

//        return response.toString();

        int i = response.lastIndexOf("access_token");
        if(i > 0){
            return response.substring(i+15,response.length()-3);
        }else {
            return null;
        }
    }

    @Override
    public String getRealLocation(String loginToken) throws IOException {
        String url = "https://f.yiban.cn/iapp377994";
        URL obj = new URL(url);
        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

        con.setRequestMethod("GET");
        con.setRequestProperty(YiBanWafCookieHeader.Host.getKey(), YiBanWafCookieHeader.Host.getValue());
        con.setRequestProperty(YiBanWafCookieHeader.Connection.getKey(), YiBanWafCookieHeader.Connection.getValue());
        con.setRequestProperty(YiBanWafCookieHeader.UpgradeInsecureRequests.getKey(), YiBanWafCookieHeader.UpgradeInsecureRequests.getValue());
        con.setRequestProperty(YiBanWafCookieHeader.UserAgent.getKey(), YiBanWafCookieHeader.UserAgent.getValue());
        con.setRequestProperty(YiBanWafCookieHeader.Accept.getKey(), YiBanWafCookieHeader.Accept.getValue());
        con.setRequestProperty(YiBanWafCookieHeader.authorization.getKey(), YiBanWafCookieHeader.authorization.getValue() + loginToken);
        con.setRequestProperty(YiBanWafCookieHeader.appversion.getKey(), YiBanWafCookieHeader.appversion.getValue());
        con.setRequestProperty(YiBanWafCookieHeader.logintoken.getKey(),loginToken);
        con.setRequestProperty(YiBanWafCookieHeader.signature.getKey(), YiBanWafCookieHeader.signature.getValue());
        con.setRequestProperty(YiBanWafCookieHeader.XRequestedWith.getKey(), YiBanWafCookieHeader.XRequestedWith.getValue());
        con.setRequestProperty(YiBanWafCookieHeader.SecFetchSite.getKey(), YiBanWafCookieHeader.SecFetchSite.getValue());
        con.setRequestProperty(YiBanWafCookieHeader.SecFetchMode.getKey(), YiBanWafCookieHeader.SecFetchMode.getValue());
        con.setRequestProperty(YiBanWafCookieHeader.SecFetchUser.getKey(), YiBanWafCookieHeader.SecFetchUser.getValue());
        con.setRequestProperty(YiBanWafCookieHeader.SecFetchDest.getKey(), YiBanWafCookieHeader.SecFetchDest.getValue());
        con.setRequestProperty(YiBanWafCookieHeader.AcceptEncoding.getKey(), YiBanWafCookieHeader.AcceptEncoding.getValue());
        con.setRequestProperty(YiBanWafCookieHeader.AcceptLanguage.getKey(), YiBanWafCookieHeader.AcceptLanguage.getValue());
        con.setRequestProperty(YiBanWafCookieHeader.Cookie.getKey(), YiBanWafCookieHeader.Cookie.getValue() + loginToken + "; client=android");
        con.setRequestProperty(YiBanWafCookieHeader.Pragma.getKey(), YiBanWafCookieHeader.Pragma.getValue());
        con.setRequestProperty(YiBanWafCookieHeader.CacheControl.getKey(), YiBanWafCookieHeader.CacheControl.getValue());
        con.connect();
        return con.getHeaderField("Location");
    }

    @Override
    public Map<String, String> getRealCookie(String realLocation,String loginToken) throws IOException {
        Map<String,String> map = new HashMap<>();
        URL obj = new URL(realLocation);
        HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
        connection.setRequestMethod("GET");
        connection.setInstanceFollowRedirects(false);
        connection.setRequestProperty(YiBanCookieHeader.Host.getKey(),YiBanCookieHeader.Host.getValue());
        connection.setRequestProperty(YiBanCookieHeader.UpgradeInsecureRequests.getKey(),YiBanCookieHeader.UpgradeInsecureRequests.getValue());
        connection.setRequestProperty(YiBanCookieHeader.UserAgent.getKey(),YiBanCookieHeader.UserAgent.getValue());
        connection.setRequestProperty(YiBanCookieHeader.Accept.getKey(),YiBanCookieHeader.Accept.getValue());
        connection.setRequestProperty(YiBanCookieHeader.Authorization.getKey(),YiBanCookieHeader.Authorization.getValue() + loginToken);
        connection.setRequestProperty(YiBanCookieHeader.Appversion.getKey(),YiBanCookieHeader.Appversion.getValue());
        connection.setRequestProperty(YiBanCookieHeader.Logintoken.getKey(),loginToken);
        connection.setRequestProperty(YiBanCookieHeader.XRequestedWith.getKey(),YiBanCookieHeader.XRequestedWith.getValue());
        connection.setRequestProperty(YiBanCookieHeader.AcceptEncoding.getKey(),YiBanCookieHeader.AcceptEncoding.getValue());
        connection.setRequestProperty(YiBanCookieHeader.AcceptLanguage.getKey(),YiBanCookieHeader.AcceptLanguage.getValue());
        connection.setRequestProperty(YiBanCookieHeader.Pragma.getKey(),YiBanCookieHeader.Pragma.getValue());
        connection.setRequestProperty(YiBanCookieHeader.CacheControl.getKey(),YiBanCookieHeader.CacheControl.getValue());
        connection.setRequestProperty(YiBanCookieHeader.Connection.getKey(),YiBanCookieHeader.Connection.getValue());

        connection.connect();
        List<String> cookieList = connection.getHeaderFields().get("Set-Cookie");
        cookieList.forEach(s->{
            String[] split = s.split("=");
            map.put(split[0],split[1]);
        });
        return map;
    }

    @Override
    public Integer RealPunch(Map<String, String> cookieMap,User user) throws IOException {
        String url = "http://gaut.shzu.edu.cn/gxqtxiaoyingyong/zyq/saveaddZyqJianKangDaKaForApp.do?client=gxqtapp";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //添加请求头
        con.setRequestMethod("POST");
        con.setRequestProperty(YiBanPunchHeader.Host.getKey(), YiBanPunchHeader.Host.getValue());
        con.setRequestProperty(YiBanPunchHeader.ContentLength.getKey(), YiBanPunchHeader.ContentLength.getValue());
        con.setRequestProperty(YiBanPunchHeader.Accept.getKey(), YiBanPunchHeader.Accept.getValue());
        con.setRequestProperty(YiBanPunchHeader.UserAgent.getKey(), YiBanPunchHeader.UserAgent.getValue());
        con.setRequestProperty(YiBanPunchHeader.ContentType.getKey(), YiBanPunchHeader.ContentType.getValue());
        con.setRequestProperty(YiBanPunchHeader.Origin.getKey(), YiBanPunchHeader.Origin.getValue());
        con.setRequestProperty(YiBanPunchHeader.XRequestedWith.getKey(), YiBanPunchHeader.XRequestedWith.getValue());
        con.setRequestProperty(YiBanPunchHeader.Referer.getKey(), YiBanPunchHeader.Referer.getValue());
        con.setRequestProperty(YiBanPunchHeader.AcceptEncoding.getKey(), YiBanPunchHeader.AcceptEncoding.getValue());
        con.setRequestProperty(YiBanPunchHeader.AcceptLanguage.getKey(), YiBanPunchHeader.AcceptLanguage.getValue());
        con.setRequestProperty(YiBanPunchHeader.Cookie.getKey(),
                "JSESSIONID=" + cookieMap.get("JSESSIONID") + ";"
                        + "gautappin=" + cookieMap.get("gautappin") + ";"
                        + "gautappid=" + cookieMap.get("gautappid") + ";"
                        + "gautuserbindid=" + cookieMap.get("gautuserbindid") + ";"
                        + "gxqt_sso_sessionid=" + cookieMap.get("gxqt_sso_sessionid") + ";");
        con.setRequestProperty(YiBanPunchHeader.Pragma.getKey(), YiBanPunchHeader.Pragma.getValue());
        con.setRequestProperty(YiBanPunchHeader.CacheControl.getKey(), YiBanPunchHeader.CacheControl.getValue());
        con.setRequestProperty(YiBanPunchHeader.Connection.getKey(), YiBanPunchHeader.Connection.getValue());

        String urlParameters = "dwaddress=" + user.getDwaddress() + "&" +
                "jkzk=1&" +
                "bszz=&" +
                "tiwen=" + user.getTiwen() + "&" +
                "tiwen2=" + user.getTiwen2() + "&" +
                "tiwen3=" + user.getTiwen3() + "&" +
                "bz=&" +
                "wxprovince=" + user.getWxprovince() + "&" +
                "wxcity="+ user.getWxcity() + "&" +
                "longitude=" + URLEncoder.encode("115.912776",StandardCharsets.UTF_8) + "&" +
                "latitude=" + URLEncoder.encode("29.611317",StandardCharsets.UTF_8) + "&" +
                "dwtime=" + URLEncoder.encode(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()),StandardCharsets.UTF_8) + "&" +
                "deviceid=f1ade933fae6090424d293eee6c1d609&" +
                "accuracy=c3a02326135756e2d462c2c36989dcf5";

        //发送Post请求
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();


        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8));

        StringBuffer response = new StringBuffer();
        String inputLine;

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return con.getResponseCode();
    }


}
