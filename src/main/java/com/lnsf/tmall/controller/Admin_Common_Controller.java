package com.lnsf.tmall.controller;


import com.lnsf.tmall.pojo.Category;
import com.lnsf.tmall.pojo.Product;
import com.lnsf.tmall.pojo.Productimage;
import com.lnsf.tmall.service.CategoryService;
import com.lnsf.tmall.service.ProductService;
import com.lnsf.tmall.service.ProductimageService;
import com.lnsf.tmall.util.Commons;
import com.lnsf.tmall.util.ImageUtils;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import sun.plugin.com.Utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class Admin_Common_Controller {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductimageService productimageService;
    @Autowired
    private ProductService productService;





    /*文件上传与回显*/
    @RequestMapping(value = "/uploadPic",method = RequestMethod.POST)
    public @ResponseBody JSONObject uploadPic(HttpServletRequest request, MultipartFile pictureFile) throws IOException {
        if (pictureFile.getBytes()!=null) {
            // 获得文件上传流
            byte[] fileBytes = pictureFile.getBytes();
            String newFilename = "";
            //使用UUID创建一个文件在服务器的随机名字，以免出现名字重复
            newFilename = ImageUtils.newname(request, pictureFile);
            //把文件上传至服务器
            ImageUtils.jeseyService(newFilename, fileBytes);
            // 图片需要回显：绝对路径
            String realPath = Commons.pic + "/upload/" + newFilename;
            // 数据库需要保存：相对路径
            String relativePath = "/upload/" + newFilename;
            String result = "{\"realPath\":\"" + realPath + "\",\"relativePath\":\"" + relativePath + "\"}";
            // 将相对路径写回（json格式）
            JSONObject jsonObject = new JSONObject();
            // 将图片上传到本地
            jsonObject.put("realPath", realPath);
            jsonObject.put("relativePath", relativePath);
            return jsonObject;
        }else {
            return null;
        }
    }


@RequestMapping("test")
    public String test(){
        return "test";
}

}
