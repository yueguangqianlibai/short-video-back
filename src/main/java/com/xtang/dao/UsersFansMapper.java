package com.xtang.dao;

import com.xtang.pojo.UsersFans;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersFansMapper {
    int deleteByPrimaryKey(String id);

    int insert(UsersFans record);

    int insertSelective(UsersFans record);

    UsersFans selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UsersFans record);

    int updateByPrimaryKey(UsersFans record);

    UsersFans selectByObject(@Param("userId") String userId, @Param("fanId") String fanId);
}