package com.home_school.admin.controller;

import com.home_school.admin.dto.ExamDto;
import com.home_school.admin.service.ExamService;
import com.home_school.util.paging.PagingVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ExamController {
    private final ExamService examservice;

    @GetMapping(value = "/admin/exams")
    public ResponseEntity<List<ExamDto>> exam(@RequestParam Map<String, String> keywords){
        PagingVo pagingVo = new PagingVo();
        pagingVo.setKeywords(keywords);
        return ResponseEntity.ok(examservice.readExam(pagingVo));
    }

    @PostMapping(value = "/admin/exams")
    public ResponseEntity<Integer> create(@RequestBody ExamDto examDto){
        return ResponseEntity.ok(examservice.createExam(examDto));
    }

    @DeleteMapping(value = "/admin/exams/{examNo}")
    public ResponseEntity<Integer> delete(@PathVariable Long examNo){
        return ResponseEntity.ok(examservice.deleteExam(examNo));
    }

    @PatchMapping(value = "/admin/exams/{examNo}")
    public ResponseEntity<Integer> update(@PathVariable int examNo
                                        ,@RequestBody ExamDto examDto){
        return ResponseEntity.ok(examservice.updateExam(examDto));
    }


}
