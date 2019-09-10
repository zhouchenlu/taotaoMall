package com.taotao.service;

import com.taotao.common.model.TaotaoResult;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.pojo.TbItemDesc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemDescServiceImpl implements  ItemDescService{
    @Autowired
    private TbItemDescMapper tbItemDescMapper;
    @Override
    public TaotaoResult findDescById(long id) {
        TbItemDesc tbItemDesc = tbItemDescMapper.selectByPrimaryKey(id);
        return TaotaoResult.ok(tbItemDesc);
    }
}
