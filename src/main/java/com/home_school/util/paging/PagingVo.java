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
    private String userCode;
    private Map<String, ?> param;
}