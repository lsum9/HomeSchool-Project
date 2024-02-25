package com.home_school.teacher.controller;

import com.home_school.teacher.dto.LectureDto;
import com.home_school.teacher.service.LectureService;
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
public class LectureController {
    private final LectureService lectureService;

    //강좌목록조회
    @GetMapping(value = "/teacher/lectures")
    public ResponseEntity<List<LectureDto>> readLectureList(@RequestParam Map<String, String> keywords
                                                        ,@AuthenticationPrincipal User user){
        PagingVo pagingVo = new PagingVo();
        pagingVo.setKeywords(keywords);
        pagingVo.setUserCode(user.getUsername());
        return ResponseEntity.ok(lectureService.readLectureList(pagingVo));
    }

    //강좌상세조회
    /*@GetMapping(value = "/teacher/lectures/{LectureNo}")
    public ResponseEntity<Map<String,Object>> readLectureDetail(@PathVariable Long LectureNo){
        return ResponseEntity.ok((LectureService.readLectureDetail(LectureNo)));
    }*/

    //강좌생성
    @PostMapping(value = "/teacher/lectures")
    public ResponseEntity<Integer> createLecture(@RequestBody LectureDto lectureDto
                                            ,@AuthenticationPrincipal User user){
        //LectureDto.setUserCode(user.getUsername());
        return ResponseEntity.ok(lectureService.createLecture(lectureDto));
    }

    //강좌삭제
    @DeleteMapping(value = "/teacher/lectures/{lectureNo}")
    public ResponseEntity<Integer> deleteLecture(@PathVariable Long lectureNo){
        return ResponseEntity.ok(lectureService.deleteLecture(lectureNo));
    }

    //강좌 수정
    @PatchMapping(value = "/teacher/lectures")
    public ResponseEntity<Integer> updateLecture(@RequestBody LectureDto lectureDto){
        return ResponseEntity.ok(lectureService.updateLecture(lectureDto));
    }
}
