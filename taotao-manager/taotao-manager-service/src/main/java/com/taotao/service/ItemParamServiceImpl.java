package com.taotao.service;

import com.taotao.common.model.TaotaoResult;
import com.taotao.mapper.TbItemParamMapper;
import org.springframework.stereotype.Service;

@Service
public class ItemParamServiceImpl implements  ItemParamService{
    private TbItemParamMapper tbItemParamMapper;
    @Override
    public TaotaoResult findParamById(long id) {

        return null;
    }
}
