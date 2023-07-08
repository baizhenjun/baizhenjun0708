package com.example.java_springboot.controller;


import com.example.java_springboot.entity.User;
import com.example.java_springboot.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author baizhenjun
 * @since 2023年07月07日
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;
    @ApiOperation(value= "测试con")
    @PostMapping(value="test",produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<User> test(@ApiParam("测试")@RequestBody User user){
        List<User> list = userService.list();
        return list;
    }
}

