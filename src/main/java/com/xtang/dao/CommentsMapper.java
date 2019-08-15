package com.xtang.dao;

import com.xtang.pojo.Comments;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentsMapper {
    int deleteByPrimaryKey(String id);

    int insert(Comments record);

    int insertSelective(Comments record);

    Comments selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Comments record);

    int updateByPrimaryKeyWithBLOBs(Comments record);

    int updateByPrimaryKey(Comments record);

    /**
     * 根据videoId返回commnent(留言列表 )
     * @param videoId videoId
     * @return List<Comments>
     */
    List<Comments> queryAllComments(String videoId);
}