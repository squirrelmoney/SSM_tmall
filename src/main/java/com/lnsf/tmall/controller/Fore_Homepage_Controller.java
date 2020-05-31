package com.lnsf.tmall.controller;

import com.lnsf.tmall.pojo.*;
import com.lnsf.tmall.service.CategoryService;
import com.lnsf.tmall.service.ProductService;
import com.lnsf.tmall.service.ProductimageService;
import com.lnsf.tmall.service.PropertyService;
import com.mysql.cj.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller

public class Fore_Homepage_Controller {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductimageService productimageService;
    @Autowired
    private PropertyService propertyService;

    /*主页*/
    @RequestMapping(value ="home", method = RequestMethod.GET)
    public String homepage(Model m, HttpServletRequest request){
        HttpSession session=request.getSession();
        User user = (User) session.getAttribute("user");
        List<Category> categories = categoryService.allCategory();
        for (Category category : categories) {
            List<Product> products=productService.selectBycid(category.getCid());
            category.setProducts(products);
            //把category里的产品固定为每行6个，再次组成List<List<Products>>,放入category
            productService.fillByRow(category,6);
            //获取每个产品的第一张图片，放入product，组成List<products>
            productService.ProductFirstImage(products);//首页产品展示
            category.setProducts(products);
        }
        List<Productimage> hotest = productService.ProductHotest(productService.allProduct(),4);
        m.addAttribute("hotestProduct",hotest);
        m.addAttribute("category",categories);
        return "fore/homepage";
    }

    /*通过搜索获取产品列表*/
   @RequestMapping(value = "search",method = RequestMethod.POST)
    public String search(Model m, String keyword){
       List<Product> products = productService.selectByWords(keyword);
       productService.ProductFirstImage(products);
       for (Product product : products){
          product.setSalecount(productService.saleCount(product));
          product.setReviewcount(productService.reviewCount(product));
       }
       m.addAttribute("ps",products);
       return "fore/searchResult";
   }

   /*通过分类获取产品列表*/
   @RequestMapping(value = "categories/{cid}/products",method = RequestMethod.GET)
   public String search(Model m, @PathVariable Integer cid){
       Category category=categoryService.selectCategoryById(cid);
       List<Product> products = productService.selectBycid(cid);
       productService.ProductFirstImage(products);
       for (Product product : products){
           product.setSalecount(productService.saleCount(product));
           product.setReviewcount(productService.reviewCount(product));
       }
       category.setProducts(products);
       m.addAttribute("c",category);
       return "fore/categorysearch";
   }

   /*在分类页面下选择排序，查看所需商品*/
    @RequestMapping(value = "categories/{cid}/products/{sort}",method = RequestMethod.GET)
    public String sort(Model m, @PathVariable Integer cid,@PathVariable String sort){
        List<Product> list=new ArrayList<Product>();
        Category category=categoryService.selectCategoryById(cid);
        List<Product> products = productService.selectBycid(cid);
        productService.ProductFirstImage(products);
        if (sort.equals("review")){
            list=productService.sortByreview(products);
        }
        if (sort.equals("date")){
            list = productService.sortBydate(products);
        }
        if (sort.equals("saleCount")){
            list=productService.sortBysaleCount(products);
        }
        if (sort.equals("price")){
            list=productService.sortBysaleprice(products);
        }
        for (Product product : list){
            product.setSalecount(productService.saleCount(product));
            product.setReviewcount(productService.reviewCount(product));
        }
        category.setProducts(list);
        m.addAttribute("c",category);
        return "fore/categorysearch";
    }
}
