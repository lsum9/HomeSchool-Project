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

    @GetMapping(value = "/teacher/classes")
    public ResponseEntity<List<ClassDto>> readClassList(@RequestParam Map<String, String> keywords){
        PagingVo pagingVo = new PagingVo();
        pagingVo.setKeywords(keywords);
        return ResponseEntity.ok(classService.readClassList(pagingVo));
    }

    @GetMapping(value = "/teacher/classes/{classNo}")
    public ResponseEntity<Map<String,Object>> readClassDetail(@PathVariable Long classNo){
        return ResponseEntity.ok((classService.readClassDetail(classNo)));
    }

    @PostMapping(value = "/teacher/classes")
    public ResponseEntity<Integer> createClass(@RequestBody ClassDto classDto){
        return ResponseEntity.ok(classService.createClass(classDto));
    }

    @DeleteMapping(value = "/teacher/Classes/{classNo}")
    public ResponseEntity<Integer> deleteClass(@PathVariable Long classNo){
        return ResponseEntity.ok(classService.deleteClass(classNo));
    }

    @PatchMapping(value = "/teacher/classes")
    public ResponseEntity<Integer> updateClass(@RequestBody ClassDto classDto){
        return ResponseEntity.ok(classService.updateClass(classDto));
    }
}
