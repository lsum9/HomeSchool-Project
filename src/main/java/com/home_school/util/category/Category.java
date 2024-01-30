package com.home_school.util.category;

import com.home_school.admin.dto.CategoryDto;
import org.springframework.stereotype.Component;

@Component
public class Category {
    public CategoryDto category(CategoryDto categoryDto){
        if(categoryDto.getSubareaTitle() != null){
            categoryDto.setCategoryType("subarea");
        } else if (categoryDto.getAreaTitle() != null) {
            categoryDto.setCategoryType("area");
        }else if(categoryDto.getSubjectTitle() != null){
            categoryDto.setCategoryType("subject");
        }
        return categoryDto;
    }

}
