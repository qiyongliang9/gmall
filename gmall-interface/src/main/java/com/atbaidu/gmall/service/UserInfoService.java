package com.atbaidu.gmall.service;

import com.atbaidu.gmall.bean.UserAddress;
import com.atbaidu.gmall.bean.UserInfo;

import java.util.List;

public interface UserInfoService {

    List<UserInfo> getUserInfoListAll();

    void addUser(UserInfo userInfo);

    void updateUser(UserInfo userInfo);

    void updateUserByName(String name,UserInfo userInfo);

    void delUser(UserInfo userInfo);

     List<UserAddress> getUserAddressList(String userId);

}
