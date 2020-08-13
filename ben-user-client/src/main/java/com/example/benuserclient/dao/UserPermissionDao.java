package com.example.benuserclient.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.ben.dto.UserPermissionUrlDto;
import com.example.ben.model.Permission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: wangben
 * @Description: 用户资源权限的dao层
 * @Date: 2020/8/11 23:30
 * @Version: 1.0
 */
public interface UserPermissionDao extends BaseMapper<Permission> {

    /**
     * 根据userId获取有权限的接口
     * @param userId
     * @return
     */
    List<UserPermissionUrlDto> getUserPermissionUrls(@Param("userId") Long userId);
}
