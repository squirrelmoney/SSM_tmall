package com.lnsf.tmall.controller;

import com.lnsf.tmall.pojo.Category;
import com.lnsf.tmall.pojo.Page;
import com.lnsf.tmall.pojo.Product;
import com.lnsf.tmall.pojo.Property;
import com.lnsf.tmall.service.CategoryService;
import com.lnsf.tmall.service.ProductService;
import com.lnsf.tmall.service.ProductimageService;
import com.lnsf.tmall.service.PropertyService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
@Controller
public class Admin_Category_Controller {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductimageService productimageService;
    @Autowired
    private ProductService productService;
    @Autowired
    private PropertyService propertyService;

    /*获取所有分类*/
    @RequestMapping(value = "/categories",method = RequestMethod.GET)
    public String allCategories(Model m,Integer start) {
        Page page = new Page();
        if (start == null)
            start = 1;
        List<Category> categories = categoryService.showCategoriesByPage(start,page);
        m.addAttribute("page",page);
        m.addAttribute("categories",categories);
        return "admin/listCategory";
    }

    /*新增/更新一个新分类*/
    @RequestMapping(value = "/categories",method = RequestMethod.POST)
    public String savecategory(Model m, Category category){
        int i;
        Category name = categoryService.selectCategoryByname(category.getName());
        if (name!=null) {
             i = categoryService.updateCategoryById(category);
        }else {
             i = categoryService.insert(category);
        }
        if (i!=0)
            return "redirect:/categories";
        else
            return null;
    }

   /*删除/编辑一个分类*/
   @RequestMapping(value = "/categories/{cid}/{msg}",method = RequestMethod.GET)
   public String deleteCategories(@PathVariable Integer cid,@PathVariable String msg,Model m){
       if(msg.equals("del")) {
           int i = categoryService.deleteCategoryById(cid);
           if (i != 0)
               return "redirect:/categories";
           else
               return "/";
       }else if (msg.equals("edit")){
           Category category = categoryService.selectCategoryById(cid);
           m.addAttribute("c",category);
           return "admin/editCategory";
       }
       return null;
   }


   /*检查分类名字有否重复*/
   @RequestMapping(value = "CheckCategoryname",method = RequestMethod.POST)
    public  @ResponseBody Category Checkname(@RequestBody Category category){
       Category record = categoryService.selectCategoryByname(category.getName());
       return record;
   }

  /*获取一个分类下的属性*/
  @RequestMapping(value = "categories/{cid}/property",method = RequestMethod.GET)
  public String property(@PathVariable Integer cid,Model m,Integer start){
      if (start == null)
          start=1;
      Page page=new Page();
      List<Property> properties=propertyService.showByPage(start,page,cid);
      Category category = categoryService.selectCategoryById(cid);
      m.addAttribute("page",page);
      m.addAttribute("c",category);
      m.addAttribute("properties",properties);
      return "admin/listProperty";
  }

    /*删除/编辑一个分类下的属性*/
    @RequestMapping(value = "categories/{cid}/property/{ptid}/{msg}",method = RequestMethod.GET)
    public String deleteCategories(@PathVariable Integer cid,@PathVariable String msg,
                                   @PathVariable Integer ptid,Model m)
    {
        if(msg.equals("del")) {
            int i = propertyService.deletePropertyById(ptid);
            if (i != 0)
                return "redirect:/categories/"+cid+"/property";
        }else if (msg.equals("edit")){
            Property property = propertyService.selectPropertyById(ptid);
            Category category = categoryService.selectCategoryById(cid);
            m.addAttribute("c",category);
            m.addAttribute("p",property);
            return "admin/editProperty";
        }
        return "/";
    }


    /*更新或新增一个属性*/
    @RequestMapping(value = "/categories/property",method = RequestMethod.POST)
    public String  updateproperty( Property property){
        int i;
        if (property.getPtid()!=null) {
            i = propertyService.updatePropertyitemById(property);
        }else {
            i = propertyService.insert(property);
        }
        if (i!=0)
            return "redirect:/categories/"+property.getCid()+"/property";
        else
            return null;
    }
}
