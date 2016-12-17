package com.shoponeo.controller;

import com.shoponeo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Tomasz on 17.12.2016.
 */
@RequestMapping(value = "/category")
@RestController
public class CategoryController {

    @Autowired
    CategoryRepository categoryRepository;

    @RequestMapping(value = "/all")
    public List<String> getAllCategories() {
        return categoryRepository.getAllCategories();
    }

}
