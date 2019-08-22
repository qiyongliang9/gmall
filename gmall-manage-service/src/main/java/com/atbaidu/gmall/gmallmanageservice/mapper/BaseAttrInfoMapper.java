package com.atbaidu.gmall.gmallmanageservice.mapper;

import com.atbaidu.gmall.bean.BaseAttrInfo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface BaseAttrInfoMapper extends Mapper<BaseAttrInfo> {
    List<BaseAttrInfo> selectAttrInfoAndAttrValueBycatalog3Id(String catalog3Id);
}
