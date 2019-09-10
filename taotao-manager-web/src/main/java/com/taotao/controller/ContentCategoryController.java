package com.taotao.controller;

import com.taotao.common.model.TreeResult;
import com.taotao.content.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ContentCategoryController {
   @Autowired
    private ContentCategoryService contentCategoryService;
   @RequestMapping("/content/category/list")
   @ResponseBody
   public List<TreeResult> loadContentCategoryByParentId(@RequestParam(value = "id",defaultValue = "0")long parentId){
       return contentCategoryService.findByCategoryId(parentId);
   }
}
