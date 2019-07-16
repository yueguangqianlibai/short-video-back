package com.xtang.dao;

import com.xtang.pojo.Videos;
import org.springframework.stereotype.Repository;

@Repository
public interface VideosMapper {
    int deleteByPrimaryKey(String id);

    int insert(Videos record);

    int insertSelective(Videos record);

    Videos selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Videos record);

    int updateByPrimaryKey(Videos record);

}