package com.taotao.content.service;

import com.taotao.common.model.PageInfoResult;
import com.taotao.common.model.TaotaoResult;
import com.taotao.pojo.TbContent;

import java.util.List;

public interface ContentService {
    public PageInfoResult findByCategoryId(long categoryId, Integer page, Integer rows);
    public TaotaoResult save(TbContent tbContent) ;

    public List<TbContent> findByCategoryId(long categoryId);

    TaotaoResult update(TbContent tbContent);

    TaotaoResult delete(long ids);

}
