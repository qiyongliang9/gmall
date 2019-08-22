package com.atbaidu.gmall.gmallorderweb.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atbaidu.gmall.bean.UserAddress;
import com.atbaidu.gmall.bean.UserInfo;
import com.atbaidu.gmall.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class OrderConreoller {

    @Reference
    UserInfoService userInfoService;

    @RequestMapping("trade")
    @ResponseBody
    public List<UserAddress> trade(String userId){
        // 返回一个视图名称叫index.html
        return userInfoService.getUserAddressList(userId);
    }
}
