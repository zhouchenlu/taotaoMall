package com.taotao.controller;

import com.taotao.common.model.PageInfoResult;
import com.taotao.common.model.TaotaoResult;
import com.taotao.content.service.ContentService;
import com.taotao.pojo.TbContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ContentController {
   @Autowired
    private ContentService contentService;
   @RequestMapping("/content/query/list")
   @ResponseBody
   public PageInfoResult loadContentCategoryByParentId(long categoryId, Integer page, Integer rows){
       return contentService.findByCategoryId(categoryId,page,rows);
   }
   @RequestMapping("/content/save")
   @ResponseBody
   public TaotaoResult save(TbContent tbContent){
       return contentService.save(tbContent);
   }
   @RequestMapping("rest/content/edit")
   @ResponseBody
   public TaotaoResult edit(TbContent tbContent){
       return contentService.update(tbContent);
   }
   @RequestMapping("/content/delete")
   @ResponseBody
   public TaotaoResult delete(@RequestParam(name = "ids") long ids ){
       return contentService.delete(ids);
   }
}
