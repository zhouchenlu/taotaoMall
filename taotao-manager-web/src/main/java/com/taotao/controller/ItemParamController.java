package com.taotao.controller;

import com.taotao.common.model.TaotaoResult;
import com.taotao.service.ItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ItemParamController {
    @Autowired
    private ItemParamService itemParamService;
    @RequestMapping("/rest/item/param/item/query/{id}")
    @ResponseBody
    public TaotaoResult loadItemParam(@PathVariable("id")Long id){
        return null;
    }
}
