package com.harlaus.common.quartz;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.harlaus.entity.PunchRecord;
import com.harlaus.entity.User;
import com.harlaus.listener.QQMessagePushListener;
import com.harlaus.service.AutoPunchService;
import com.harlaus.service.PunchRecordService;
import com.harlaus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class AutoPunchTask {

    @Autowired
    private AutoPunchService autoPunchService;

    @Autowired
    private UserService userService;

    @Autowired
    private PunchRecordService punchRecordService;

    @Autowired
    private QQMessagePushListener pushListener;

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    private static final ThreadLocal<SimpleDateFormat> FORMAT_THREAD_LOCAL = ThreadLocal.withInitial(()->new SimpleDateFormat("yyyy年M月d日"));

    @Scheduled(cron = "0 0 1 * * ? ")
    public void punch(){
        List<User> users = userService.getAll();
        ExecutorService executor = Executors.newFixedThreadPool(16);
        AtomicInteger atomicInteger = new AtomicInteger(0);
        for(int i = 0;i < 16;i++) {
            CompletableFuture.supplyAsync(() -> {
                List<PunchRecord> records = new ArrayList<>();
                while (atomicInteger.get() < users.size()) {
                    try {
                        int index = atomicInteger.getAndIncrement();
                        User user = users.get(index);
                        String loginToken = autoPunchService.getLoginToken(user);
                        String realLocation = autoPunchService.getRealLocation(loginToken);
                        if(StringUtils.isNotBlank(realLocation)){
                            Map<String, String> realCookie = autoPunchService.getRealCookie(realLocation,loginToken);
                            Integer integer = autoPunchService.RealPunch(realCookie,user);
                            PunchRecord record = punchRecordService.punchResult(realCookie);
                            records.add(record);
                            if(record.getState()){
                                String format = FORMAT_THREAD_LOCAL.get().format(record.getPunchTime());
                                String name = record.getName();
                                pushListener.sendToQQPunch(user,"今天是" + format + "," + name + "同学的打卡非常成功 ヾ(≧▽≦*)o ");
                            }else {
                                pushListener.sendToQQPunch(user,"打卡失败,请反馈给我的菜鸡主人 ╥﹏╥ ");
                            }
                        }else {
                            userService.delOne(user);
                            pushListener.sendToQQPunch(user,"账号密码被修改了,请重新注册系统 ╥﹏╥ ");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                return records;
            }, executor).whenComplete((v, e) -> {
                punchRecordService.saveBatch(v);
            });
        }
        executor.shutdown();
    }
}
