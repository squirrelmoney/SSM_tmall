package com.lnsf.tmall.service;

import com.lnsf.tmall.pojo.Category;
import com.lnsf.tmall.pojo.Page;

import java.util.List;

public interface CategoryService {
    List<Category> allCategory();
    Category selectCategoryById(Integer cid);
    int  updateCategoryById(Category category);
    int  deleteCategoryById(Integer cid);
    int insert(Category category);
    Category selectCategoryByname(String name);
    List<Category> showCategoriesByPage(Integer page, Page page1);
}
