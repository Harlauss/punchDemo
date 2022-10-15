package com.harlaus.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.harlaus.common.enums.YiBanResultHeader;
import com.harlaus.entity.CommonResult;
import com.harlaus.entity.PunchRecord;
import com.harlaus.entity.vo.BackRecordVo;
import com.harlaus.entity.vo.SearchRecordVo;
import com.harlaus.service.PunchRecordService;
import com.harlaus.mapper.PunchRecordMapper;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
* @author 23548
* @description 针对表【punch_record】的数据库操作Service实现
* @createDate 2022-09-25 22:53:03
*/
@Service
public class PunchRecordServiceImpl extends ServiceImpl<PunchRecordMapper, PunchRecord>
    implements PunchRecordService{

    public PunchRecord punchResult(Map<String,String> map) throws IOException, ParseException {
        String url = "http://gaut.shzu.edu.cn/gxqtxiaoyingyong/zyq/viewZyqJianKangDaKaForApp.do?client=gxqtapp";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setInstanceFollowRedirects(false);

        con.setRequestMethod("POST");
        con.setRequestProperty(YiBanResultHeader.Host.getKey(), YiBanResultHeader.Host.getValue());
        con.setRequestProperty(YiBanResultHeader.ContentLength.getKey(), YiBanResultHeader.ContentLength.getValue());
        con.setRequestProperty(YiBanResultHeader.Accept.getKey(), YiBanResultHeader.Accept.getValue());
        con.setRequestProperty(YiBanResultHeader.UserAgent.getKey(), YiBanResultHeader.UserAgent.getValue());
        con.setRequestProperty(YiBanResultHeader.ContentType.getKey(), YiBanResultHeader.ContentType.getValue());
        con.setRequestProperty(YiBanResultHeader.Origin.getKey(), YiBanResultHeader.Origin.getValue());
        con.setRequestProperty(YiBanResultHeader.XRequestedWith.getKey(), YiBanResultHeader.XRequestedWith.getValue());
        con.setRequestProperty(YiBanResultHeader.Referer.getKey(), YiBanResultHeader.Referer.getValue());
        con.setRequestProperty(YiBanResultHeader.AcceptEncoding.getKey(), YiBanResultHeader.AcceptEncoding.getValue());
        con.setRequestProperty(YiBanResultHeader.AcceptLanguage.getKey(), YiBanResultHeader.AcceptLanguage.getValue());
        con.setRequestProperty(YiBanResultHeader.Cookie.getKey(),
                "gautappin=" + map.get("gautappin") + ";"
                        + "gautappid=" + map.get("gautappid") + ";"
                        + "gautuserbindid=" + map.get("gautuserbindid") + ";"
                        + "gxqt_sso_sessionid=" + map.get("gxqt_sso_sessionid") + ";");
        con.setRequestProperty(YiBanResultHeader.Pragma.getKey(), YiBanResultHeader.Pragma.getValue());
        con.setRequestProperty(YiBanResultHeader.CacheControl.getKey(), YiBanResultHeader.CacheControl.getValue());
        con.setRequestProperty(YiBanResultHeader.Connection.getKey(), YiBanResultHeader.Connection.getValue());
        con.connect();


        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8));

        StringBuffer response = new StringBuffer();
        String inputLine;

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();



        HashMap<String, String> jsonMap = JSONObject.parseObject(response.toString(), new TypeReference<HashMap<String, String>>() {});
        PunchRecord record = new PunchRecord();
        if(jsonMap.get("code").equals("0")){
            record.setState(true);
        }else {
            record.setState(false);
        }
        HashMap<String, String> object = JSONObject.parseObject(JSONObject.parseObject(jsonMap.get("data"), new TypeReference<HashMap<String, String>>() {}).get("jkdk"), new TypeReference<HashMap<String, String>>() {});
        record.setStudentId(object.get("xhorgh")).setSex(object.get("leixing")).setName(object.get("xm")).setTiwen(object.get("tiwen")).setTiwen2(object.get("tiwen2")).setTiwen3(object.get("tiwen3")).setPunchTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(object.get("addtime"))).setDwaddress(object.get("dwaddress")).setWxprovince(object.get("wxprovince")).setWxcity(object.get("wxcity"));
        return record;
    }

    public CommonResult doDefaultPunchRecord(String id){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String endTime = simpleDateFormat.format(calendar.getTime());
        calendar.add(Calendar.DAY_OF_YEAR,-7);
        String startTime = simpleDateFormat.format(calendar.getTime());
        SearchRecordVo searchRecordVo = new SearchRecordVo(id,startTime,endTime,0L,3L);
        return doPunchRecord(searchRecordVo);
    }

    public CommonResult doPunchRecord(SearchRecordVo searchRecordVo){
        if(searchRecordVo.getCurrIndex() == null || searchRecordVo.getCurrIndex() < 0){
            searchRecordVo.setCurrIndex(0L);
        }
        if(searchRecordVo.getSize() == null || searchRecordVo.getSize() < 0){
            searchRecordVo.setSize(7L);
        }
        Page<BackRecordVo> page = new Page<BackRecordVo>(searchRecordVo.getCurrIndex(),searchRecordVo.getSize());
        IPage<BackRecordVo> recordPage = this.getBaseMapper().queryPunchRecordByStuId(page, searchRecordVo);
        if(recordPage.getRecords().size() > 0){
            return new CommonResult(200,"查询成功",recordPage);
        }else {
            return new CommonResult(444,"查询失败",null);
        }
    }
}




