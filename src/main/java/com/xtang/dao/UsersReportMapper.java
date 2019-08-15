package com.xtang.dao;

import com.xtang.pojo.UsersReport;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersReportMapper {
    int deleteByPrimaryKey(String id);

    int insert(UsersReport record);

    int insertSelective(UsersReport record);

    UsersReport selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UsersReport record);

    int updateByPrimaryKey(UsersReport record);
}