package com.atbaidu.gmall.gmallmanageservice.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atbaidu.gmall.bean.*;
import com.atbaidu.gmall.gmallmanageservice.mapper.*;
import com.atbaidu.gmall.service.ManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
@Service
public class ManageServiceImpl implements ManageService {

    @Autowired
    private BaseCatalog1Mapper baseCatalog1Mapper;

    @Autowired
    private BaseCatalog2Mapper baseCatalog2Mapper;

    @Autowired
    private BaseCatalog3Mapper baseCatalog3Mapper;

    @Autowired
    private BaseAttrInfoMapper baseAttrInfoMapper;

    @Autowired
    private BaseAttrValueMapper baseAttrValueMapper;

    @Autowired
    private SpuInfoMapper spuInfoMapper;

    @Autowired
    private BaseSaleAttrMapper baseSaleAttrMapper;

    @Autowired
    private SpuSaleAttrMapper spuSaleAttrMapper;

    @Autowired
    private SpuImageMapper spuImageMapper;

    @Autowired
    private SpuSaleAttrValueMapper spuSaleAttrValueMapper;

    @Autowired
    private SkuInfoMapper skuInfoMapper;

    @Autowired
    private SkuImageMapper skuImageMapper;

    @Autowired
    private SkuAttrValueMapper skuAttrValueMapper;

    @Autowired
    private SkuSaleAttrValueMapper skuSaleAttrValueMapper;

    /**
     * 制作SPu
     * @param spuInfo
     */
    @Override
    @Transactional
    public void saveSpuInfo(SpuInfo spuInfo) {
        //插入商品spu
        if(spuInfo!=null){
            spuInfoMapper.insert(spuInfo);
        }
        //插入商品的销售名称
         List<SpuSaleAttr> spuSaleAttrList = spuInfo.getSpuSaleAttrList();
        if(spuSaleAttrList!=null&&spuSaleAttrList.size()>0){

            for (SpuSaleAttr spuSaleAttr:spuSaleAttrList) {
                List<SpuSaleAttrValue> spuSaleAttrValueList = spuSaleAttr.getSpuSaleAttrValueList();
                spuSaleAttr.setSpuId(spuInfo.getId());

                spuSaleAttrMapper.insert(spuSaleAttr);
                //插入商品的销售属性值
                if(spuSaleAttrValueList!=null&&spuSaleAttrValueList.size()>0){
                    for (SpuSaleAttrValue spuSaleAttrValue:spuSaleAttrValueList) {
                        spuSaleAttrValue.setSaleAttrId(spuSaleAttr.getSaleAttrId());
                        spuSaleAttrValue.setSpuId(spuInfo.getId());
                        spuSaleAttrValueMapper.insert(spuSaleAttrValue);
                    }
                }
            }
        }
        //插入海报图
        List<SpuImage> spuImageList = spuInfo.getSpuImageList();
        if(spuImageList!=null&&spuImageList.size()>0){
            for (SpuImage spuImage:spuImageList) {
                spuImage.setSpuId(spuInfo.getId());
                spuImageMapper.insert(spuImage);
            }
        }
    }

    /**
     * 根据spuId获得该商品的图片
     * @param SpuImage
     * @return
     */
    @Override
    public List<SpuImage> getAllSkuImage(SpuImage SpuImage) {
        return spuImageMapper.select(SpuImage);
    }

    /**
     *根据spuId返回销售属性及销售属性值
     * @param spuId
     * @return
     */
    @Override
    public List<SpuSaleAttr> getSpuSaleAttrList(String spuId) {

        List<SpuSaleAttr> spuSaleAttrList= spuSaleAttrMapper.getSpuSaleAttrList(spuId);

        return spuSaleAttrList;
    }

    /**
     * 制作Sku
     * @param skuInfo
     */
    @Override
    @Transactional
    public void saveSkuInfo(SkuInfo skuInfo) {
        if(skuInfo!=null){
            skuInfoMapper.insert(skuInfo);
            //保存图片
             List<SkuImage> skuImageList = skuInfo.getSkuImageList();
             if(skuImageList!=null && skuImageList.size()>0){
                 for (SkuImage skuImage:skuImageList
                      ) {
                     skuImage.setSkuId(skuInfo.getId());
                     skuImageMapper.insert(skuImage);
                 }
             }
             //保存销售属性
             List<SkuSaleAttrValue> skuSaleAttrValueList = skuInfo.getSkuSaleAttrValueList();
             if(skuSaleAttrValueList!=null && skuSaleAttrValueList.size()>0){
                 for (SkuSaleAttrValue skuSaleAttrValue:skuSaleAttrValueList
                      ) {
                     skuSaleAttrValue.setSkuId(skuInfo.getId());
                     skuSaleAttrValueMapper.insert(skuSaleAttrValue);
                 }
             }
            //保存属性

             List<SkuAttrValue> skuAttrValueList = skuInfo.getSkuAttrValueList();

             if(skuAttrValueList!=null &&skuAttrValueList.size()>0){
                 for (SkuAttrValue skuAttrValue:skuAttrValueList
                      ) {
                     skuAttrValue.setSkuId(skuInfo.getId());
                     skuAttrValueMapper.insert(skuAttrValue);
                 }
             }

        }
    }

    /**
     * 获取sku
     * @param skuId
     * @return
     */
    @Override
    public SkuInfo selectSkuById(String skuId) {
        return skuInfoMapper.selectByPrimaryKey(skuId);
    }

    @Override
    public List<SkuImage> getSkuImageBySkuId(String skuId) {
        SkuImage skuImage = new SkuImage();
        skuImage.setSkuId(skuId);
        return skuImageMapper.select(skuImage);
    }

    @Override
    public List<SpuSaleAttr> getAttrValueListBySpuId(String spuId, String skuId) {

        return spuSaleAttrMapper.getAttrValueListBySpuId( spuId, skuId);
    }

    @Override
    public List<SkuSaleAttrValue> getSkuAttrList(String spuId) {



        return skuSaleAttrValueMapper.getSkuSaleAttrValue(spuId);
    }


    @Override
     public List<BaseCatalog1> getCatalog1() {

        return  baseCatalog1Mapper.selectAll();
    }

    @Override
    public List<BaseCatalog2> getCatalog2(String catalog1Id) {
        Example example = new Example(BaseCatalog2.class);
        example.createCriteria().andEqualTo("catalog1Id",catalog1Id);
        return baseCatalog2Mapper.selectByExample(example);
    }

    @Override
    public List<BaseCatalog3> getCatalog3(String catalog2Id) {
            Example example = new Example(BaseCatalog3.class);
            example.createCriteria().andEqualTo("catalog2Id",catalog2Id);
            return baseCatalog3Mapper.selectByExample(example);
    }

    //并且获得他的平台属性值
    @Override
    public List<BaseAttrInfo> getAttrList(String catalog3Id) {
        Example example = new Example(BaseAttrInfo.class);
        example.createCriteria().andEqualTo("catalog3Id",catalog3Id);
        return baseAttrInfoMapper.selectAttrInfoAndAttrValueBycatalog3Id(catalog3Id);
    }

    /**
     * 添加或修改平台属性
     * @param baseAttrInfo
     */
    @Transactional
    @Override
    public void saveAttrInfo(BaseAttrInfo baseAttrInfo) {


        if(StringUtils.isEmpty(baseAttrInfo.getId())){
            baseAttrInfoMapper.insert(baseAttrInfo);
        }else {
            baseAttrInfoMapper.updateByPrimaryKey(baseAttrInfo);
        }

        //把原属性值全部清空
        BaseAttrValue baseAttrValue4Del = new BaseAttrValue();
        baseAttrValue4Del.setAttrId(baseAttrInfo.getId());
        baseAttrValueMapper.delete(baseAttrValue4Del);

        List<BaseAttrValue> attrValueList = baseAttrInfo.getAttrValueList();


        if(attrValueList!=null&&attrValueList.size()>0){
            for (int i =0;i<attrValueList.size();i++){
                 BaseAttrValue baseAttrValue = attrValueList.get(i);
                baseAttrValue.setId(null);
                baseAttrValue.setAttrId(baseAttrInfo.getId());
                baseAttrValueMapper.insert(baseAttrValue);
            }
        }
    }

    /**
     * 根据平台属性Id获取平台属性值 1对多关系
     * @param attrId
     * @return
     */
    @Override
    public List<BaseAttrValue> getAttrValueList(String attrId) {
        Example example = new Example(BaseAttrValue.class);
        example.createCriteria().andEqualTo("attrId",attrId);

        return baseAttrValueMapper.selectByExample(example);
    }

    /**
     * 获取商品的spu
     * @param spuInfo
     * @return
     */
    @Override
    public List<SpuInfo> getSpuInfoList(SpuInfo spuInfo) {

        return  spuInfoMapper.select(spuInfo);
    }

    /**
     * 获取销售属性表
     * @return
     */
    @Override
    public List<BaseSaleAttr> getBaseSaleAttrList() {

        return baseSaleAttrMapper.selectAll();
    }


}
