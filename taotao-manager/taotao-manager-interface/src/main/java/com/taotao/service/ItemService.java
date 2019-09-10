package com.taotao.service;

import com.taotao.common.model.PageInfoResult;
import com.taotao.common.model.TaotaoResult;
import com.taotao.pojo.TbItem;

public interface ItemService {
    public PageInfoResult findAll(Integer page,Integer rows);

    public TaotaoResult save(TbItem tbItem, String desc);

    public TaotaoResult deleteItemById(Long ids);

    public TaotaoResult instockItemById(Long ids);

    public  TaotaoResult reshelfItemById(Long ids);
}
