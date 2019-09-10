package com.taotao.content.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.model.PageInfoResult;
import com.taotao.common.model.TaotaoResult;
import com.taotao.common.utils.JsonUtils;
import com.taotao.content.service.ContentService;
import com.taotao.jedis.JedisClient;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class ContentServiceImpl implements ContentService {
    @Autowired
    private TbContentMapper tbContentMapper;
    @Autowired
    private JedisClient jedisClient;
    @Override
    public PageInfoResult findByCategoryId(long categoryId, Integer page, Integer rows) {
        PageHelper.startPage(page,rows);
        TbContentExample example = new TbContentExample();
        example.createCriteria().andCategoryIdEqualTo(categoryId);
        List<TbContent> tbContents = tbContentMapper.selectByExample(example);
        PageInfo<TbContent> pageInfo=new PageInfo<TbContent>(tbContents);

        PageInfoResult pageInfoResult=new PageInfoResult();
        pageInfoResult.setTotal(pageInfo.getTotal());
        pageInfoResult.setRows(pageInfo.getList());
        return pageInfoResult;
    }

    @Override
    public TaotaoResult save(TbContent tbContent) {
        tbContent.setCreated(new Date());
        tbContent.setUpdated(new Date());
        int isSuccess = tbContentMapper.insert(tbContent);
        if(isSuccess>0){
            //清空缓存
            jedisClient.hdel("CONTENT", tbContent.getCategoryId().toString());
            System.out.println("此时添加成功-------->清楚了redis");
            return TaotaoResult.ok("添加成功");
        }
        return null;
    }
     //条件查询(首页大广告的显示)
    /**
     * 分析：redis
     * 先查询redis---没有(mysql--保存redis）---有（返回）
     * redis：五种 ---存储字符串
     * 	string:key-value
     *  选hash:key fileds value
     */
    @Override
    public List<TbContent> findByCategoryId(long categoryId) {
        TbContentExample example = new TbContentExample();
        example.createCriteria().andCategoryIdEqualTo(categoryId);
        //1.先查询redis
        String contentJson = jedisClient.hget("CONTENT", categoryId + "");
        if(StringUtils.isNotBlank(contentJson)){
            return JsonUtils.jsonToList(contentJson, TbContent.class);
        }
        //2.如果没有查询数据库
        List<TbContent> tbContents = tbContentMapper.selectByExample(example);
        System.out.println("此时redis无所需数据-------->查询了数据库");
        //3.保存到redis
        jedisClient.hset("CONTENT",categoryId+"",JsonUtils.objectToJson(tbContents));
        return tbContents;
    }

    @Override
    public TaotaoResult update(TbContent tbContent) {
        tbContent.setUpdated(new Date());
        int isSuccess = tbContentMapper.updateByPrimaryKey(tbContent);
        if(isSuccess>0){
            //清空缓存
            jedisClient.hdel("CONTENT", tbContent.getCategoryId().toString());
            System.out.println("此时更新成功-------->清楚了redis");
            return TaotaoResult.ok("更新成功");
        }
        return null;
    }

    @Override
    public TaotaoResult delete(long ids) {
        int isSuccess = tbContentMapper.deleteByPrimaryKey(ids);
        if(isSuccess>0){
            //清空缓存--->这个地方ids是主键,不能达到清除清除redis的目的.需要进一步处理拿到categoryid
            jedisClient.hdel("CONTENT", ids+"");

            System.out.println("此时删除成功-------->清楚了redis");
            return TaotaoResult.ok("删除成功");
        }
        return null;
    }
}
