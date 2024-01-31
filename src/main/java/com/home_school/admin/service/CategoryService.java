package com.home_school.admin.service;

import com.home_school.admin.dto.CategoryDto;
import com.home_school.admin.mapper.CategoryMapper;
import com.home_school.util.category.Category;
import com.home_school.util.paging.Paging;
import com.home_school.util.paging.PagingVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryMapper categoryMapper;
    private final Paging paging;
    private final Category category;

    @Transactional(readOnly = true)
    public List<CategoryDto> readCategory(PagingVo pagingVo){
        //총 로우수 pagingVo에 추가
        pagingVo.setTotalRow(categoryCnt(pagingVo));
        //가공된 키워드, 현재페이지, 총 로우 수 담긴 pagingVo 페이징 클래스로 넘기기
        pagingVo=paging.pagingInfo(pagingVo);
        System.out.println("서비스 페이징"+pagingVo);
        return categoryMapper.readCategory(pagingVo);
    }

    //총 글개수
    @Transactional(readOnly = true)
    public int categoryCnt(PagingVo pagingVo){
        pagingVo=paging.pagingInfo(pagingVo);
        return categoryMapper.categoryCnt(pagingVo);
    }
    @Transactional
    public int createCategory(CategoryDto categoryDto){
        //categoryType 추가 로직
        categoryDto = category.category(categoryDto);
        return categoryMapper.createCategory(categoryDto);
    }
    @Transactional
    public int deleteCategory(CategoryDto categoryDto){
        //categoryType 추가 로직
        categoryDto = category.category(categoryDto);
        return categoryMapper.deleteCategory(categoryDto);
    }

    @Transactional
    public int updateCategory(CategoryDto categoryDto){
        //categoryType 추가 로직
        categoryDto = category.category(categoryDto);
        return categoryMapper.updateCategory(categoryDto);
    }




}
