package com.example.java_springboot.service.impl;

import com.example.java_springboot.entity.User;
import com.example.java_springboot.mapper.UserMapper;
import com.example.java_springboot.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author baizhenjun
 * @since 2023年07月07日
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
