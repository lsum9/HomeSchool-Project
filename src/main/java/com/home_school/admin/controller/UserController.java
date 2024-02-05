package com.home_school.admin.controller;

import com.home_school.admin.dto.UserDto;
import com.home_school.admin.service.UserService;
import com.home_school.util.paging.Paging;
import com.home_school.util.paging.PagingVo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping(value = "/admin/users")
    public ResponseEntity<List<UserDto>> readUserList(@RequestParam Map<String, String> keywords){
        PagingVo pagingVo = new PagingVo();
        pagingVo.setKeywords(keywords);
        return ResponseEntity.ok(userService.readUserList(pagingVo));
    }

    @DeleteMapping(value = "admin/users/{userNo}")
    public ResponseEntity<Integer> deleteUser(@PathVariable int userNo){
        return ResponseEntity.ok(userService.deleteUser(userNo));
    }

    @PatchMapping(value = "admin/users/{userNo}")
    public ResponseEntity<Integer> updateUser(@PathVariable int userNo
                                        ,@RequestBody UserDto userDto){
        return ResponseEntity.ok(userService.updateUser(userDto));
    }
}
