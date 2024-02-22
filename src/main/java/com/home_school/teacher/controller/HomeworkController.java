package com.home_school.teacher.controller;

import com.home_school.teacher.dto.HomeworkDto;
import com.home_school.teacher.service.HomeworkService;
import com.home_school.util.paging.PagingVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class HomeworkController {
    private final HomeworkService homeworkService;

    //부여한 과제 목록 조회
    @GetMapping(value = "/teacher/homeworks")
    public ResponseEntity<List<HomeworkDto>> readHomeworkList(@RequestParam Map<String, String> keywords
                                                        ,@AuthenticationPrincipal User user){
        PagingVo pagingVo = new PagingVo();
        pagingVo.setKeywords(keywords);
        pagingVo.setUserCode(user.getUsername());
        return ResponseEntity.ok(homeworkService.readHomeworkList(pagingVo));
    }


    //과제생성
    @PostMapping(value = "/teacher/homeworks")
    public ResponseEntity<Integer> createHomework(@RequestBody HomeworkDto homeworkDto
                                            ,@AuthenticationPrincipal User user){
        return ResponseEntity.ok(homeworkService.createHomework(homeworkDto, user.getUsername()));
    }

    //과제삭제
    @DeleteMapping(value = "/teacher/homeworks/{homeworkNo}")
    public ResponseEntity<Integer> deleteHomework(@PathVariable Long homeworkNo){
        return ResponseEntity.ok(homeworkService.deleteHomework(homeworkNo));
    }

    //과제수정
    @PatchMapping(value = "/teacher/homeworks")
    public ResponseEntity<Integer> updateHomework(@RequestBody HomeworkDto homeworkDto){
        return ResponseEntity.ok(homeworkService.updateHomework(homeworkDto));
    }
}
