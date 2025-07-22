package com.ldw.loginservice.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ldw.loginservice.dao.pojo.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description:
 * @Author: ldw
 * @Date: 2022/4/27
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
