/*
package com.home_school.admin.controller;

import com.home_school.admin.dto.QuestionDto;
import com.home_school.admin.dto.QuestionScriptDto;
import com.home_school.admin.service.QuestionScriptService;
import com.home_school.admin.service.QuestionService;
import com.home_school.util.paging.PagingVo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class QuestionScriptController {
    private final QuestionScriptService questionScriptService;

    @GetMapping(value="/admin/question-scripts")
    public ResponseEntity<List<QuestionScriptDto>> question(@RequestParam Map<String, String> keywords){
        PagingVo pagingVo = new PagingVo();
        pagingVo.setKeywords(keywords);
        return ResponseEntity.ok(questionScriptService.readQuestionScript(pagingVo));
    }

    @PostMapping(value = "/admin/question-scripts")
    public ResponseEntity<Integer> create(@RequestBody QuestionScriptDto questionScriptDto){
        return ResponseEntity.ok(questionScriptService.createQuestionScript(questionScriptDto));
    }

    @DeleteMapping(value = "/admin/question-scripts/{scriptNo}")
    public ResponseEntity<Integer> delete(@PathVariable int scriptNo){
        return ResponseEntity.ok(questionScriptService.deleteQuestionScript(scriptNo));
    }

    @PatchMapping(value = "/admin/question-scripts")
    public ResponseEntity<Integer> update(@RequestBody QuestionScriptDto questionScriptDto){
        return ResponseEntity.ok(questionScriptService.updateQuestionScript(questionScriptDto));
    }
}
*/
