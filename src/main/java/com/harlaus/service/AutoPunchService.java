package com.harlaus.service;

import com.harlaus.entity.PunchRecord;
import com.harlaus.entity.User;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.Map;

public interface AutoPunchService {
    public String getLoginToken(User user) throws IOException;
    public String getRealLocation(String loginToken) throws IOException;
    public Map<String,String> getRealCookie(String realLocation,String loginToken) throws IOException;
    public Integer RealPunch(Map<String,String> cookieMap,User user) throws IOException;
}
