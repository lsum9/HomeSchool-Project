package com.home_school.util.paging;

import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.beans.factory.annotation.Value;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
public class PagingVo {

    private int rowPerPage;
    private int pageNumCnt;
    private int nowPage;
    private int totalRow;
    private int totalPage;
    private int startRow;
    private int startPageNum;
    private int endPageNum;
    private Map<String, String> keywords;

    // setKeywords 메서드 수정
    public void setKeywords(Map<String, String> keywords) {
        this.keywords = keywords;
        keywords();  // processKeywords 메서드 호출
    }

    //키워드가공로직
    private void keywords(){
        Iterator<String> iterator = keywords.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            String word = keywords.get(key).trim();
            if (word.isEmpty()) {
                //키는 있으나 값이 없을경우 key제거
                iterator.remove();
            }//값없는 키제거 - if end
        }
        if(keywords.containsKey("nowPage")){
            nowPage = Integer.parseInt(keywords.get("nowPage"));
        }else {
            nowPage=1;
        }


    }//keywords() end
}