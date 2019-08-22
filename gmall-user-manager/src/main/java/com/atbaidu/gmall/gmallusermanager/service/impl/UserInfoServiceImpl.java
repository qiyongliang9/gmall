package com.atbaidu.gmall.gmallusermanager.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atbaidu.gmall.bean.UserAddress;
import com.atbaidu.gmall.bean.UserInfo;
import com.atbaidu.gmall.gmallusermanager.mapper.UserAddressMapper;
import com.atbaidu.gmall.gmallusermanager.mapper.UserInfoMapper;
import com.atbaidu.gmall.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    UserAddressMapper userAddressMapper;

    @Autowired
    UserInfoMapper userInfoMapper;

    @Override
    public List<UserInfo> getUserInfoListAll() {
        return userInfoMapper.selectAll();
    }

    @Override
    public void addUser(UserInfo userInfo) {
        userInfoMapper.insert(userInfo);
    }

    @Override
    public void updateUser(UserInfo userInfo) {
            userInfoMapper.updateByPrimaryKey(userInfo);
    }

    @Override
    public void updateUserByName( String name,UserInfo userInfo) {
         Example example = new Example(UserInfo.class);
         example.createCriteria().andEqualTo("name",name);
        userInfoMapper.updateByExample(userInfo,example);
    }

    @Override
    public void delUser(UserInfo userInfo) {
        userInfoMapper.delete(userInfo);
    }

    @Override
    public List<UserAddress> getUserAddressList(String userId) {
        // 调用mapper
        // select * from userAddress where userId=?
        UserAddress userAddress = new UserAddress();
        userAddress.setUserId(userId);
        return    userAddressMapper.select(userAddress);
    }

}
