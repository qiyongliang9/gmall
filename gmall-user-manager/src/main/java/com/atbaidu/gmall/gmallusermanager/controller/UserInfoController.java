package com.atbaidu.gmall.gmallusermanager.controller;

import com.atbaidu.gmall.bean.UserInfo;
import com.atbaidu.gmall.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping("findAll")
    @ResponseBody
    public List<UserInfo> findAll(){
         List<UserInfo> userInfoListAll = userInfoService.getUserInfoListAll();
        return userInfoListAll;
    }

    @RequestMapping("update")
    @ResponseBody
    public void updateUserInfoById(UserInfo userInfo){
        userInfoService.updateUser(userInfo);
    }
    @RequestMapping("insert")
    @ResponseBody
    public void addUser(UserInfo userInfo){
        userInfoService.addUser(userInfo);
    }
}
