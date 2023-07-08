package com.example.java_springboot.mapper;

import com.example.java_springboot.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author baizhenjun
 * @since 2023年07月07日
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
