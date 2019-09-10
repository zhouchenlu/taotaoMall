package com.taotao.controller;

import com.taotao.common.model.PageInfoResult;
import com.taotao.common.model.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
public class ItemController {
    @Autowired
    private ItemService itemServiceImpl;
    @RequestMapping("/item/list")
    @ResponseBody
    private PageInfoResult listItem(Integer page, Integer rows){
        return itemServiceImpl.findAll(page,rows);
    }
    @RequestMapping("/item/save")
    @ResponseBody
    private TaotaoResult saveItem(TbItem tbItem, String desc){
        return itemServiceImpl.save(tbItem,desc);
    }
    @RequestMapping("/rest/item/delete")
    @ResponseBody
    private TaotaoResult deleteItemById(Long ids){
        return itemServiceImpl.deleteItemById(ids);
    }
    //xiajia
    @RequestMapping("/rest/item/instock")
    @ResponseBody
    private TaotaoResult instockItemById(Long ids){
        return itemServiceImpl.instockItemById(ids);
    }
    @RequestMapping("/rest/item/reshelf")
    @ResponseBody
    private TaotaoResult reshelfItemById(Long ids){
        return itemServiceImpl.reshelfItemById(ids);
    }
}
