package com.itheima.reggie.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.reggie.common.R;
import com.itheima.reggie.entity.Category;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface CategoryService extends IService<Category> {

    boolean save(Category category);

    R<Page> page(int page, int pageSize);

    void remove(Long id);

    R<String> update(Category category);

    R<List<Category>> list(Category category);
}

