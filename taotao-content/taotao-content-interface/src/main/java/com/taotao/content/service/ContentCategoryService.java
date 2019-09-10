package com.taotao.content.service;

import com.taotao.common.model.TreeResult;

import java.util.List;

public interface ContentCategoryService {
    public List<TreeResult> findByCategoryId(long parentId);
}
