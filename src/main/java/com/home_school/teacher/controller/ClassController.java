package com.home_school.teacher.controller;

import com.home_school.teacher.dto.ClassDto;
import com.home_school.teacher.service.ClassService;
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
public class ClassController {
    private final ClassService classService;

    //강좌목록조회
    @GetMapping(value = "/teacher/classes")
    public ResponseEntity<List<ClassDto>> readClassList(@RequestParam Map<String, String> keywords
                                                        ,@AuthenticationPrincipal User user){
        PagingVo pagingVo = new PagingVo();
        pagingVo.setKeywords(keywords);
        pagingVo.setUserCode(user.getUsername());
        return ResponseEntity.ok(classService.readClassList(pagingVo));
    }

    //강좌상세조회
    /*@GetMapping(value = "/teacher/classes/{classNo}")
    public ResponseEntity<Map<String,Object>> readClassDetail(@PathVariable Long classNo){
        return ResponseEntity.ok((classService.readClassDetail(classNo)));
    }*/

    //강좌생성
    @PostMapping(value = "/teacher/classes")
    public ResponseEntity<Integer> createClass(@RequestBody ClassDto classDto
                                            ,@AuthenticationPrincipal User user){
        //classDto.setUserCode(user.getUsername());
        return ResponseEntity.ok(classService.createClass(classDto));
    }

    //강좌삭제
    @DeleteMapping(value = "/teacher/Classes/{classNo}")
    public ResponseEntity<Integer> deleteClass(@PathVariable Long classNo){
        return ResponseEntity.ok(classService.deleteClass(classNo));
    }

    //강좌 수정
    @PatchMapping(value = "/teacher/classes")
    public ResponseEntity<Integer> updateClass(@RequestBody ClassDto classDto){
        return ResponseEntity.ok(classService.updateClass(classDto));
    }
}
