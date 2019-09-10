package com.taotao.service;

import com.taotao.common.model.TreeResult;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
@Service
public class ItemCatServiceImpl implements  ItemCatService{
    @Autowired
    private TbItemCatMapper tbItemCatMapper;

    @Override
    public List<TreeResult> findAll(Long id) {
        TbItemCatExample example = new TbItemCatExample();
        example.createCriteria().andParentIdEqualTo(id);
        List<TbItemCat> tbItemCats = tbItemCatMapper.selectByExample(example);
        List<TreeResult> list=new ArrayList<>();
        for (TbItemCat tbItemCat : tbItemCats) {
            TreeResult treeResult=new TreeResult();
            treeResult.setId(tbItemCat.getId());
            treeResult.setText(tbItemCat.getName());
            treeResult.setState(tbItemCat.getIsParent()?"closed":"open");
            list.add(treeResult);
        }
        return list;
    }
}
