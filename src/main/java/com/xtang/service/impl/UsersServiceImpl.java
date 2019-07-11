package com.xtang.service.impl;

import com.xtang.common.ServerResponse;
import com.xtang.controller.BasicController;
import com.xtang.dao.UsersMapper;
import com.xtang.pojo.Users;
import com.xtang.service.IUsersService;
import com.xtang.utils.MD5Utils;
import com.xtang.utils.RedisOperator;
import com.xtang.vo.UsersVo;
import org.n3r.idworker.Sid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * @program: short-video-back
 * @Date: 2019/7/4 18:27
 * @Author: xTang
 * @Description:
 */
@Service
public class UsersServiceImpl extends BasicController implements IUsersService {

    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private Sid sid;

    @Autowired
    private RedisOperator redis;

    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    @Override
    public boolean queryUserNameIsExist(String username) {
        int result = usersMapper.queryName(username);
        return result == 0 ? true : false;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public ServerResponse saveUser(Users users) {
        String uid = sid.nextShort();
        users.setId(uid);
        int row = usersMapper.insert(users);
        if (row > 0) {
            users.setPassword(null);
            return ServerResponse.createBySuccessAll("注册成功", setUsersRedisSessionToken(users));
        }
        return ServerResponse.createByErrorMsg("注册失败");
    }


    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    @Override
    public ServerResponse login(Users users) throws Exception {
        if (!queryUserNameIsExist(users.getUsername())) {
            users.setPassword(MD5Utils.getMD5Str(users.getPassword()));
            Users newUsers = usersMapper.selectLogin(users.getUsername(), users.getPassword());
            if (newUsers != null) {
                newUsers.setPassword(null);
                return ServerResponse.createBySuccessAll("登录成功", setUsersRedisSessionToken(newUsers));
            } else {
                return ServerResponse.createByErrorMsg("账号或密码有误");
            }
        } else {
            return ServerResponse.createByErrorMsg("该用户名尚未存在");
        }
    }

    public UsersVo setUsersRedisSessionToken(Users usersModel){
        String uniqueToken = UUID.randomUUID().toString();
        redis.set(USERS_REDIS_SESSION + ":" +usersModel.getId(),uniqueToken,1000 * 60 *30);
        UsersVo usersVo = new UsersVo();
        BeanUtils.copyProperties(usersModel,usersVo);
        usersVo.setUserToken(uniqueToken);
        return usersVo;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public void updateUserInfo(Users users) {
        usersMapper.updateByPrimaryKeySelective(users);
    }

    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    @Override
    public Users queryUsersInfo(String userId) {
        Users users = usersMapper.selectByPrimaryKey(userId);
        users.setPassword(null);
        return users;
    }
}
