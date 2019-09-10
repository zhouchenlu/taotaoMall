package com.taotao.portal.controller;

import com.taotao.common.Ad1Node;
import com.taotao.common.utils.JsonUtils;
import com.taotao.content.service.ContentService;
import com.taotao.pojo.TbContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private ContentService contentService;
    @RequestMapping("index")
    public String index(Model model){
        List<TbContent> tbContentsc=contentService.findByCategoryId(89L);
        List<Ad1Node>ad_list=new ArrayList<>();
        for (TbContent tbContent : tbContentsc) {
            Ad1Node ad1Node=new Ad1Node();
            ad1Node.setAlt(tbContent.getTitle());
            ad1Node.setHeight("240");
            ad1Node.setHeightB("240");
            ad1Node.setHref(tbContent.getUrl());
            ad1Node.setSrc(tbContent.getPic());
            ad1Node.setSrcB(tbContent.getPic2());
            ad1Node.setWidth("670");
            ad1Node.setWidthB("550");
            ad_list.add(ad1Node);
        }
        model.addAttribute("ad1", JsonUtils.objectToJson(ad_list));
        return "index";
    }
}
