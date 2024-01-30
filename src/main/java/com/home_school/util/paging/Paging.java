package com.home_school.util.paging;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class Paging {
    private final Environment environment;

    public PagingVo pagingInfo(PagingVo pagingVo){
        int pageNumCnt = Integer.parseInt(environment.getProperty("paging.pageNumCnt"));
        int rowPerPage = Integer.parseInt(environment.getProperty("paging.rowPerPage"));
        //int nowPage = Integer.parseInt(pagingVo.getKeywords().get("nowPage"));
        int nowPage = pagingVo.getNowPage();
        int totalRow = pagingVo.getTotalRow();
        int startPageNum = 0;
        int endPageNum = 0;

        //최초 nowPage=1
        int startRow = (nowPage-1) * rowPerPage;
        int totalPage = totalRow / rowPerPage + 1;

        //페이지네비 로직
        //네비 시작번호 노출 로직
        if(nowPage <= pageNumCnt / 2 + 1){
            //이전없는 앞번호일 경우
            startPageNum = 1;
        }else if(nowPage >= totalPage - (pageNumCnt / 2)){
            //다음없는 뒷번호일 경우
            startPageNum = totalPage - pageNumCnt + 1;
        }else {
            //이전, 다음 있는 경우
            startPageNum = nowPage - pageNumCnt / 2;
        }//if end

        //네비 끝번호 노출 로직
        if(totalPage <= pageNumCnt){
            endPageNum = totalPage;
        }else{
            //총페이지수가 노출할 페이지숫자 수보다 클 경우
            if(nowPage <= pageNumCnt/2 + 1){
                endPageNum = pageNumCnt;
            }else{
                endPageNum = nowPage + pageNumCnt/2;
            }//if end
            //뒷번호
            if(totalPage < nowPage + pageNumCnt/2 +1){
                endPageNum = totalPage;
            }//if end
        }//if end

        pagingVo.setPageNumCnt(pageNumCnt);
        pagingVo.setRowPerPage(rowPerPage);
        pagingVo.setStartRow(startRow);
        pagingVo.setStartPageNum(startPageNum);
        pagingVo.setEndPageNum(endPageNum);
        pagingVo.setTotalPage(totalPage);

        return pagingVo;
    }
}
