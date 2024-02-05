package com.home_school.admin.controller;

import com.home_school.admin.dto.CategoryDto;
import com.home_school.admin.dto.UserDto;
import com.home_school.admin.service.CategoryService;
import com.home_school.util.paging.Paging;
import com.home_school.util.paging.PagingVo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    //카테고리 조회 - subject, area, subarea 중 선택된 키의 value=1로 들어옴
    @GetMapping(value="/admin/categories")
    public ResponseEntity<List<CategoryDto>> readCategoryList(@RequestParam Map<String, String> keywords){
        PagingVo pagingVo = new PagingVo();
        pagingVo.setKeywords(keywords);
        return ResponseEntity.ok(categoryService.readCategoryList(pagingVo));
    }

    @PostMapping(value = "/admin/categories")
    public ResponseEntity<Integer> createCategory(@RequestBody CategoryDto categoryDto){
        return ResponseEntity.ok(categoryService.createCategory(categoryDto));
    }

    @DeleteMapping(value = "/admin/categories")
    public ResponseEntity<Integer> deleteCategory(@RequestBody CategoryDto categoryDto){
        return ResponseEntity.ok(categoryService.deleteCategory(categoryDto));
    }

    @PatchMapping(value = "/admin/categories")
    public ResponseEntity<Integer> updateCategory(@RequestBody CategoryDto categoryDto){
        return ResponseEntity.ok(categoryService.updateCategory(categoryDto));
    }
}
