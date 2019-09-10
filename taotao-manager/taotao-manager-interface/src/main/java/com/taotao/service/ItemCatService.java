package com.taotao.service;

import com.taotao.common.model.TreeResult;

import java.util.List;

public interface ItemCatService {
    public List<TreeResult> findAll(Long id);
}
