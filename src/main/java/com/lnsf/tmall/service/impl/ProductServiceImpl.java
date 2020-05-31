package com.lnsf.tmall.service.impl;


import com.lnsf.tmall.mapper.OrderitemMapper;
import com.lnsf.tmall.mapper.ProductMapper;
import com.lnsf.tmall.mapper.ProductimageMapper;
import com.lnsf.tmall.mapper.ReviewMapper;
import com.lnsf.tmall.pojo.Category;
import com.lnsf.tmall.pojo.Page;
import com.lnsf.tmall.pojo.Product;
import com.lnsf.tmall.pojo.Productimage;
import com.lnsf.tmall.service.ProductService;
import com.lnsf.tmall.service.ProductimageService;
import com.mysql.cj.xdevapi.Collection;
import com.sun.javafx.collections.SortableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ProductimageMapper productimageMapper;
    @Autowired
    private  ProductimageMapper productimageService;
    @Autowired
    private OrderitemMapper orderitemMapper;
    @Autowired
    private ReviewMapper reviewMapper;

    @Override
    public List<Product> allProduct() {
        return productMapper.selectByExample(null);
    }

    @Override
    public Product selectProductById(Integer id) {
        return productMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateProductitemById(Product Product) {
        return productMapper.updateByPrimaryKeySelective(Product);
    }

    @Override
    public int deleteProductById(Integer id) {
        return productMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Product product) {
        return productMapper.insertSelective(product);
    }

    @Override
    public List<Product> selectBycid(Integer cid) {
        return productMapper.selectBycid(cid);
    }

    //分类后，把一个类别下的产品List<Product>按8个一组存入List<List<Product>>
    @Override
    public void fillByRow(Category category,int productNumberEachRow) {
        //每行显示的产品个数
        //对每一个分类进行遍历
            List<Product> products=category.getProducts();
            List<List<Product>> productsByRow =  new ArrayList<>();
            for (int i = 0; i < products.size(); i+=productNumberEachRow) {
                //每次增加8个长度，第一次size=0+6，第二次size=6+6以此类推
                int size = i+productNumberEachRow;
                //判断size大小，若size大于该分类集合下所有产品总数量，则size=产品数，否则size=i+8
                size= size>products.size()?products.size():size;
                //sublist代表把prodcts里的第i个和第 size-1 个之间产品装入productsOfEachRow。（第一次0至5，第二次5至11依此类推）
                List<Product> productsOfEachRow =products.subList(i, size);
                //List<Product>装入List<List<Product>>
                // 这样productsByRow里面就是按行隔离了
                productsByRow.add(productsOfEachRow);
            }
            category.setProductsList(productsByRow);
        }

/*
        */
/*购物车的图片*/
    @Override
    public void FirstImage(Product product) {
  List<Productimage> productimages = productimageMapper.selectByPid(product.getPid());
            if(productimages.size()!=0){
                Productimage productimage = productimages.get(0);
                product.setProductimage(productimage);
            }

    }


    /*产品页的图片*/
    @Override
    public void ProductFirstImage(List<Product> productList) {
      //  List<Product> products=new ArrayList<Product>();
        for (Product product : productList){
            List<Productimage> productimages = productimageMapper.selectByPid(product.getPid());
            if(productimages.size()!=0){
                Productimage productimage = productimages.get(0);
                product.setProductimage(productimage);
            }
        }
    }

    @Override
    public List<Productimage> ProductHotest(List<Product> products,int n) {
        List<Productimage> list=new ArrayList<Productimage>();
        Random rand = new Random();
        boolean[] bool = new boolean[products.size()];
        int num =0;
        for (int i = 0; i<n; i++){
            do{
                 num = rand.nextInt(products.size());
                }while(bool[num]);//如果产生的数相同继续循环
                bool[num] =true;
                Product product=products.get(num);
                List<Productimage> productimage= productimageService.selectByPid(product.getPid());
                if (productimage.size()!=0)
                list.add(productimage.get(0));
                }

          return list;
    }

    @Override
    public List<Product> selectByWords(String words) {
        return productMapper.selectByWords(words);
    }

    @Override
    public int saleCount(Product product) {
        return orderitemMapper.selectBypid(product.getPid()).size();

    }

    @Override
    public int reviewCount(Product product) {
        return reviewMapper.selectBypid(product.getPid()).size();

    }


    @Override
    public List<Product> sortByreview(List<Product> products) {
        Collections.sort(products, new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                int count1 = reviewCount(o1);
                int count2 = reviewCount(o2);
                if (count1>count2)
                    return 1;
                else if (count1==count2)
                    return 0;
                else
                    return -1;
            }
        });
        return products;
    }

    @Override
    public List<Product> sortBydate(List<Product> products) {
        Collections.sort(products, new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                if (o1.getCreatedate().compareTo(o2.getCreatedate())>0)
                    return 1;
                else if (o1.getCreatedate().compareTo(o2.getCreatedate())==0)
                    return 0;
                else
                    return -1;
            }
        });
        return products;
    }

    @Override
    public List<Product> sortBysaleCount(List<Product> products) {
        Collections.sort(products, new Comparator<Product>() {
        @Override
        public int compare(Product o1, Product o2) {
            int saleCount1 = saleCount(o1);
            int saleCount2 = saleCount(o2);
            if (saleCount1>saleCount2)
                return 1;
            else if (saleCount1==saleCount2)
                return 0;
            else
                return -1;
        }
    });
      return products;
    }

    @Override
    public List<Product> sortBysaleprice(List<Product> products) {
        Collections.sort(products, new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                if (o1.getPromoteprice().compareTo(o2.getPromoteprice())>0)
                    return 1;
                else if (o1.getPromoteprice().compareTo(o2.getPromoteprice())==0)
                    return 0;
                else
                    return -1;
            }
        });
        return products;
    }


    @Override
    public List<Product> showByPage(Integer start, Page page) {
        int totalCount =productMapper.count();
        page.setTotalNumber(totalCount);
        page.setCurrentPage(start);
        List<Product> Products=productMapper.page(page);
        return Products;
    }
}

