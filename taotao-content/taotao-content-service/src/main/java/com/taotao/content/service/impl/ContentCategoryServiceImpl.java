package com.taotao.content.service.impl;

import com.taotao.common.model.TreeResult;
import com.taotao.content.service.ContentCategoryService;
import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.pojo.TbContentCategory;
import com.taotao.pojo.TbContentCategoryExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {
    @Autowired
    private TbContentCategoryMapper tbContentCategoryMapper;
    @Override
    public List<TreeResult> findByCategoryId(long parentId) {
        List<TreeResult> treeResults=new ArrayList<>();
        TbContentCategoryExample example = new TbContentCategoryExample();
        example.createCriteria().andParentIdEqualTo(parentId);
        List<TbContentCategory> tbContentCategories = tbContentCategoryMapper.selectByExample(example);
        for (TbContentCategory tbContentCategory : tbContentCategories) {
            TreeResult treeResult=new TreeResult();
            treeResult.setId(tbContentCategory.getId());
            treeResult.setText(tbContentCategory.getName());
            treeResult.setState(tbContentCategory.getIsParent()?"closed":"open");
            treeResults.add(treeResult);
        }
        return treeResults;
    }
}
