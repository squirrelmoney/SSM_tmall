package com.lnsf.tmall.controller;

import com.lnsf.tmall.pojo.*;
import com.lnsf.tmall.service.*;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class Admin_Product_Controller {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductimageService productimageService;
    @Autowired
    private ProductService productService;
    @Autowired
    private PropertyService propertyService;
    @Autowired
    private PropertyvalueService propertyvalueService;

    /*获取一个分类下的所有产品*/
    @RequestMapping(value = "products/{cid}",method = RequestMethod.GET)
    public String allPronducts(Model m, @PathVariable Integer cid,Integer start )
    {
        if (start == null)
            start=1;
        Page page=new Page();
        Category category = categoryService.selectCategoryById(cid);
        List<Product> productList = productService.showByPage(start,page);
        productService.ProductFirstImage(productList);
        m.addAttribute("page",page);
        m.addAttribute("c",category);
        m.addAttribute("ps",productList);
        return "admin/listProduct";
    }

    /*新增/更新一个新产品*/
    @RequestMapping(value="products",method = RequestMethod.POST)
    public  String newProduct(Product product){
      int i;
      if (product.getPid()!=null){
          i=productService.updateProductitemById(product);
      }else {
          i=  productService.insert(product);
      }
        if (i!=0){
            return "redirect:/products/"+product.getCid();
        }
          return null;
    }

    /*删除/编辑一个产品*/
    @RequestMapping(value="products/{cid}/{pid}/{msg}",method = RequestMethod.GET)
    public String Product(@PathVariable Integer pid,@PathVariable String msg,Model m,@PathVariable Integer cid ){
        int i;
        if (msg.equals("del")) {
            i = productService.deleteProductById(pid);
            if (i != 0) {
                return "redirect:/products/" + cid;
            }
        }else if (msg.equals("edit")){
            Category category = categoryService.selectCategoryById(cid);
            Product product = productService.selectProductById(pid);
            m.addAttribute("c",category);
            m.addAttribute("p",product);
            return "admin/editProduct";
        }
        return null;
    }

    /*编辑一个产品属性*/
    @RequestMapping(value = "products/{cid}/{pid}/propertyvalue",method = RequestMethod.GET)
    public String editPropertyvalue(Model m,@PathVariable Integer cid,@PathVariable Integer pid){
        List<Propertyvalue> propertyvalues = propertyvalueService.selectBypid(pid);
        Category category = categoryService.selectCategoryById(cid);
        Product product = productService.selectProductById(pid);
        for (Propertyvalue propertyvalue: propertyvalues) {
            Property property = propertyService.selectPropertyById(propertyvalue.getPtid());
            propertyvalue.setProperty(property);
        }
        m.addAttribute("c",category);
        m.addAttribute("p",product);
        m.addAttribute("pvs",propertyvalues);
        return "admin/editPropertyValue";

    }

    /*更新一个产品的属性------------------没有实现*/
    @RequestMapping(value = "products/propertyvalue",method = RequestMethod.POST)
    public String editPropertyvalue(Model m, Propertyvalue propertyvalue,Integer cid){
        int i = propertyvalueService.updatePropertyvalueitemById(propertyvalue);
        if (i!=0)
            return "redirect:/products/"+cid+"/"+propertyvalue.getPid()+"/propertyvalue";
         else
            return null;
    }


    /*编辑一个产品的图片*/
    @RequestMapping(value = "products/{cid}/{pid}/productimage",method = RequestMethod.GET)
    public String editProductimage(Model m,@PathVariable Integer cid,@PathVariable Integer pid){
        Category category = categoryService.selectCategoryById(cid);
        Product product = productService.selectProductById(pid);
        List<Productimage> imgsingle=productimageService.imgtype("type_single",pid);
        List<Productimage> imgdetail=productimageService.imgtype("type_detail",pid);
        m.addAttribute("imgsingle",imgsingle);
        m.addAttribute("imgdetail",imgdetail);
        m.addAttribute("c",category);
        m.addAttribute("p",product);
        return "admin/listProductImage";

    }


    /*新增一个产品的单个图片*/
    @RequestMapping(value="products/productimage",method = RequestMethod.POST)
    public  String newProductimage(Productimage productimage,String cid){
        int i;
            i=  productimageService.insert(productimage);
        if (i!=0){
            return "redirect:/products/"+cid+"/"+productimage.getPid()+"/productimage";
        }
        return null;
    }

    /*删除一个产品的单个图片*/
    @RequestMapping(value = "products/{cid}/{pid}/productimage/{id}",method = RequestMethod.GET)
    public String delimg(@PathVariable Integer cid,@PathVariable Integer pid,@PathVariable Integer id){
        int i=productimageService.deleteProductimageById(id);
        if (i!=0){
            return "redirect:/products/"+cid+"/"+pid+"/productimage";
        }
        return null;
    }




}
