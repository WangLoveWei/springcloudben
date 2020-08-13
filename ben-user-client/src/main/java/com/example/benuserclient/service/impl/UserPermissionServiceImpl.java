package com.example.benuserclient.service.impl;

import com.example.ben.dto.UserPermissionUrlDto;
import com.example.benuserclient.dao.UserPermissionDao;
import com.example.benuserclient.service.UserPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: wangben
 * @Description:
 * @Date: 2020/8/11 23:32
 * @Version: 1.0
 */
@Service
public class UserPermissionServiceImpl implements UserPermissionService {

    @Autowired
    private UserPermissionDao userPermissionDao;

    @Override
    public List<UserPermissionUrlDto> getUserPermissionUrls(Long userId) {
        List<UserPermissionUrlDto> list=  userPermissionDao.getUserPermissionUrls(userId);
        return list;
    }
}
