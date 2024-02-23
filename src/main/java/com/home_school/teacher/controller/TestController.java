package com.home_school.teacher.controller;

import com.home_school.admin.dto.ExamDto;
import com.home_school.admin.service.ExamService;
import com.home_school.teacher.dto.TestDto;
import com.home_school.teacher.service.TestService;
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
public class TestController {
    private final TestService testService;
    private final ExamService examService;

    //부여한 시험 목록 조회
    @GetMapping(value = "/teacher/tests")
    public ResponseEntity<List<TestDto>> readTestList(@RequestParam Map<String, String> keywords){
        PagingVo pagingVo = new PagingVo();
        pagingVo.setKeywords(keywords);
        return ResponseEntity.ok(testService.readTestList(pagingVo));
    }

    //시험생성
    @PostMapping(value = "/teacher/tests")
    public ResponseEntity<Integer> createTest(@RequestBody TestDto testDto
                                            , @AuthenticationPrincipal User user){
        //testDto.setUserCode(user.getUsername());
        return ResponseEntity.ok(testService.createTest(testDto));
    }

    //시험 프리셋 불러오기
    @GetMapping(value = "/teacher/exams")
    public ResponseEntity<List<ExamDto>> readExamListForTest(@RequestParam Map<String, String> keywords){
        PagingVo pagingVo = new PagingVo();
        pagingVo.setKeywords(keywords);
        return ResponseEntity.ok(examService.readExamList(pagingVo));
    }

    //시험삭제
    @DeleteMapping(value = "/teacher/tests/{testNo}")
    public ResponseEntity<Integer> deleteTest(@PathVariable Long testNo){
        return ResponseEntity.ok(testService.deleteTest(testNo));
    }

    //시험수정
    @PatchMapping(value = "/teacher/tests")
    public ResponseEntity<Integer> updateTest(@RequestBody TestDto testDto){
        return ResponseEntity.ok(testService.updateTest(testDto));
    }
}
