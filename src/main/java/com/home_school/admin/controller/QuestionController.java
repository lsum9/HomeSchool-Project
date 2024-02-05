package com.home_school.admin.controller;

import com.home_school.admin.dto.QuestionDto;
import com.home_school.admin.dto.QuestionScriptDto;
import com.home_school.admin.service.QuestionService;
import com.home_school.util.paging.PagingVo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;

    //문제 목록보기
    @GetMapping(value="/admin/questions")
    public ResponseEntity<List<QuestionDto>> readQuestionList(@RequestParam Map<String, String> keywords){
        PagingVo pagingVo = new PagingVo();
        pagingVo.setKeywords(keywords);
        return ResponseEntity.ok(questionService.readQuestionList(pagingVo));
    }


    @PostMapping(value = "/admin/questions")
    public ResponseEntity<Integer> createQuestion(@RequestBody QuestionDto questionDto){
        return ResponseEntity.ok(questionService.createQuestion(questionDto));
    }

    @DeleteMapping(value = "/admin/questions/{questionNo}")
    public ResponseEntity<Integer> deleteQuestion(@PathVariable int questionNo){
        return ResponseEntity.ok(questionService.deleteQuestion(questionNo));
    }

    @PatchMapping(value = "/admin/questions")
    public ResponseEntity<Integer> updateQuestion(@RequestBody QuestionDto questionDto){
        return ResponseEntity.ok(questionService.updateQuestion(questionDto));
    }


    ///questionScript
    @GetMapping(value="/admin/question-scripts")
    public ResponseEntity<List<QuestionScriptDto>> questionScript(@RequestParam Map<String, String> keywords){
        PagingVo pagingVo = new PagingVo();
        pagingVo.setKeywords(keywords);
        return ResponseEntity.ok(questionService.readQuestionScript(pagingVo));
    }

    @PostMapping(value = "/admin/question-scripts")
    public ResponseEntity<Integer> createQuestionScript(@RequestBody QuestionScriptDto questionScriptDto){
        return ResponseEntity.ok(questionService.createQuestionScript(questionScriptDto));
    }

    @DeleteMapping(value = "/admin/question-scripts/{scriptNo}")
    public ResponseEntity<Integer> deleteQuestionScript(@PathVariable int scriptNo){
        return ResponseEntity.ok(questionService.deleteQuestionScript(scriptNo));
    }

    @PatchMapping(value = "/admin/question-scripts")
    public ResponseEntity<Integer> updateQuestionScript(@RequestBody QuestionScriptDto questionScriptDto){
        return ResponseEntity.ok(questionService.updateQuestionScript(questionScriptDto));
    }
}
