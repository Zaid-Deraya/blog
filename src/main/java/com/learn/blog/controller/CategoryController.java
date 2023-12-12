package com.learn.blog.controller;

import com.learn.blog.payloads.ApiResponse;
import com.learn.blog.payloads.CategoryDto;
import com.learn.blog.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    @PostMapping("/addCategory")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
        CategoryDto addedCategory = this.categoryService.createCategory(categoryDto);
        return new ResponseEntity<CategoryDto>(addedCategory, HttpStatus.CREATED);
    }

    @GetMapping("/getCategoryById/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Integer id) {
        CategoryDto getCategoryById = this.categoryService.getCategoryById(id);
        return new ResponseEntity<CategoryDto>(getCategoryById, HttpStatus.OK);
    }

    @GetMapping("/getAllCategory")
    public ResponseEntity<List<CategoryDto>> getAllCategory() {
        List<CategoryDto> getAllCategory = this.categoryService.getAllCategory();
        return new ResponseEntity<List<CategoryDto>>(getAllCategory, HttpStatus.OK);
    }

    @PutMapping("/updateCategoryById/{id}")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable Integer id) {
        CategoryDto category = this.categoryService.updateCategory(categoryDto, id);
//        return new ResponseEntity<CategoryDto>(category, HttpStatus.OK);
        return ResponseEntity.ok(category);
    }


    @DeleteMapping("/deleteCategory/{id}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer id) {
        this.categoryService.deleteCategory(id);
        return new ResponseEntity<>(new ApiResponse("Category deleted!", true), HttpStatus.OK);
    }


}
