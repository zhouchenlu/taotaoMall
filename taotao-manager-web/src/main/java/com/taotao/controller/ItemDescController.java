package com.taotao.controller;

import com.taotao.common.model.TaotaoResult;
import com.taotao.pojo.TbItemDesc;
import com.taotao.service.ItemDescService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/rest/item")
public class ItemDescController {
    @Autowired
    private ItemDescService itemDescService;

    @ResponseBody
    @RequestMapping("/query/item/desc/{id}")
    public TaotaoResult loadDesc(@PathVariable("id")long id ){
    return itemDescService.findDescById(id);
    }
}
