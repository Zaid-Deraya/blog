package com.learn.blog.service;

import com.learn.blog.payloads.CategoryDto;

import java.util.List;

public interface CategoryService {

    public CategoryDto createCategory(CategoryDto categoryDto);

    public CategoryDto getCategoryById(Integer categoryId);

    public List<CategoryDto> getAllCategory();

    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);

    public void deleteCategory(Integer categoryId);


}
