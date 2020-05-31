package com.lnsf.tmall.service.impl;

import com.lnsf.tmall.mapper.CategoryMapper;
import com.lnsf.tmall.pojo.Category;
import com.lnsf.tmall.pojo.Page;
import com.lnsf.tmall.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> allCategory() {
        return categoryMapper.selectByExample(null);
    }

    @Override
    public Category selectCategoryById(Integer cid) {
        return categoryMapper.selectByPrimaryKey(cid);
    }

    @Override
    public int updateCategoryById(Category category) {
        return categoryMapper.updateByPrimaryKeySelective(category);
    }

    @Override
    public int deleteCategoryById(Integer cid) {
        return categoryMapper.deleteByPrimaryKey(cid);
    }

    @Override
    public int insert(Category category) {
        return categoryMapper.insertSelective(category);
    }

    @Override
    public Category selectCategoryByname(String name) {
        return categoryMapper.selectCategoryByname(name);
    }


    @Override
    public List<Category> showCategoriesByPage(Integer start, Page page) {
        List<Category> categories = new ArrayList<Category>();
        int totalCount =categoryMapper.count();
        page.setTotalNumber(totalCount);
        page.setCurrentPage(start);
        List<Category> categoryList=categoryMapper.page(page);
        return categoryList;
    }
}
