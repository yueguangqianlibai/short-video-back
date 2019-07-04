package com.xtang.service.impl;

import com.xtang.common.ServerResponse;
import com.xtang.dao.UsersMapper;
import com.xtang.pojo.Users;
import com.xtang.service.IUsersService;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @program: short-video-back
 * @Date: 2019/7/4 18:27
 * @Author: xTang
 * @Description:
 */
@Service
public class UsersServiceImpl implements IUsersService {

    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private Sid sid;

    @Transactional(propagation = Propagation.SUPPORTS,rollbackFor = Exception.class)
    @Override
    public boolean queryUserNameIsExist(String username) {
        System.err.println(username);
        int result = usersMapper.queryName(username);
        return result == 0 ? true:false;
    }

    @Override
    public ServerResponse saveUser(Users users) {
        String uid = sid.nextShort();
        users.setId(uid);
        int row = usersMapper.insert(users);
        if (row >= 0){
            return ServerResponse.createBySuccessMsg("注册成功");
        }
        return ServerResponse.createByErrorMsg("注册失败");
    }
}
