package com.taotao.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.model.PageInfoResult;
import com.taotao.common.model.TaotaoResult;
import com.taotao.common.utils.IDUtils;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService{
    @Autowired
    private TbItemMapper tbItemMapper;
    @Autowired
    private TbItemDescMapper tbItemDescMapper;
    @Override
    public PageInfoResult findAll(Integer page,Integer rows) {
        PageHelper.startPage(page,rows);
        TbItemExample example = new TbItemExample();
       // example.setOrderByClause("update desc");
        List<TbItem> list = tbItemMapper.selectByExample(example);
        PageInfo<TbItem>pageInfo=new PageInfo<TbItem>(list);
        PageInfoResult pageInfoResult=new PageInfoResult();
        pageInfoResult.setTotal(pageInfo.getTotal());
        pageInfoResult.setRows(list);
        return pageInfoResult;
    }

    @Override
    public TaotaoResult save(TbItem record, String desc) {

        long id = IDUtils.genItemId();
        record.setId(id);
        Date date = new Date();
        record.setCreated(date);
        record.setUpdated(date);
        record.setStatus((byte)1);
        int insertItem = tbItemMapper.insert(record);
        TbItemDesc tbItemDesc=new TbItemDesc();
        tbItemDesc.setItemId(id);
        tbItemDesc.setItemDesc(desc);
        tbItemDesc.setCreated(date);
        tbItemDesc.setUpdated(date);

        int insertItemDesc = tbItemDescMapper.insert(tbItemDesc);
        if(insertItem>0&&insertItemDesc>0){
            return TaotaoResult.ok();
        }
        return null;
    }

    @Override
    public TaotaoResult deleteItemById(Long ids) {
        int isDelete = tbItemMapper.deleteByPrimaryKey(ids);
        if(isDelete>0){
            return TaotaoResult.ok();
        }
        return null;
    }

    @Override
    public TaotaoResult instockItemById(Long ids) {
        TbItem tbItem=new TbItem();
        tbItem.setId(ids);
        tbItem.setStatus((byte)2);
        int isInstock = tbItemMapper.updateByPrimaryKeySelective(tbItem);
        if(isInstock>0){
            return TaotaoResult.ok();
        }
        return null;
    }

    @Override
    public TaotaoResult reshelfItemById(Long ids) {
        TbItem tbItem=new TbItem();
        tbItem.setId(ids);
        tbItem.setStatus((byte)1);
        int isReshelf = tbItemMapper.updateByPrimaryKeySelective(tbItem);
        if(isReshelf>0){
            return TaotaoResult.ok();
        }
        return null;
    }
}
