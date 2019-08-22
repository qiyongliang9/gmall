package com.atbaidu.gmall.service;

import com.atbaidu.gmall.bean.*;

import java.util.List;

public interface ManageService {
    //一级分类
    public List<BaseCatalog1> getCatalog1();

    //二级分类
    public List<BaseCatalog2> getCatalog2(String catalog1Id);

    //三级分类
    public List<BaseCatalog3> getCatalog3(String catalog2Id);

    //平台属性
    public List<BaseAttrInfo> getAttrList(String catalog3Id);

    //添加平台属性
    void saveAttrInfo(BaseAttrInfo baseAttrInfo);

    //获得平台属性值
    List<BaseAttrValue> getAttrValueList(String attrId);
    //获得spu
    List<SpuInfo> getSpuInfoList(SpuInfo spuInfo);

    //销售属性表
    List<BaseSaleAttr> getBaseSaleAttrList();

    //保存商品的展示信息
    public void saveSpuInfo(SpuInfo spuInfo);

    List<SpuImage> getAllSkuImage(SpuImage spuImage);

    List<SpuSaleAttr> getSpuSaleAttrList(String spuId);

    void saveSkuInfo(SkuInfo skuInfo);

    SkuInfo selectSkuById(String skuId);

    List<SkuImage> getSkuImageBySkuId(String skuId);

    List<SpuSaleAttr> getAttrValueListBySpuId(String spuId, String skuId);

    List<SkuSaleAttrValue> getSkuAttrList(String spuId);
}
