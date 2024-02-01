/*
package com.home_school.teacher.controller;

import com.home_school.teacher.dto.ClassDto;
import com.home_school.teacher.service.ClassService;
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
public class ClassController {
    private final ClassService classService;

    @GetMapping(value = "/admin/Classes")
    public ResponseEntity<List<ClassDto>> readClassList(@RequestParam Map<String, String> keywords){
        PagingVo pagingVo = new PagingVo();
        pagingVo.setKeywords(keywords);
        return ResponseEntity.ok(classService.readClassList(pagingVo));
    }

    @GetMapping(value = "/admin/Classes/{ClassNo}")
    public ResponseEntity<Map<String,Object>> readClassDetail(@PathVariable Long ClassNo){
        return ResponseEntity.ok((classService.readClassDetail(ClassNo)));
    }

    @PostMapping(value = "/admin/Classes")
    public ResponseEntity<Integer> create(@RequestBody ClassDto ClassDto){
        return ResponseEntity.ok(classService.createClass(ClassDto));
    }

    @DeleteMapping(value = "/admin/Classes/{ClassNo}")
    public ResponseEntity<Integer> delete(@PathVariable Long ClassNo){
        return ResponseEntity.ok(classService.deleteClass(ClassNo));
    }

    @PatchMapping(value = "/admin/Classes")
    public ResponseEntity<Integer> update(@RequestBody ClassDto ClassDto){
        return ResponseEntity.ok(classService.updateClass(ClassDto));
    }
}
*/
