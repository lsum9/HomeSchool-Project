package com.home_school.admin.mapper;

import com.home_school.admin.dto.CategoryDto;
import com.home_school.util.paging.PagingVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {
    List<CategoryDto> readCategoryList(PagingVo pagingVo);

    int categoryCnt(PagingVo pagingVo);
    int createCategory(CategoryDto categoryDto);
    int deleteCategory(CategoryDto categoryDto);
    int updateCategory(CategoryDto categoryDto);
    
}
