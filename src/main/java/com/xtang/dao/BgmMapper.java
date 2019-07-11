package com.xtang.dao;

import com.xtang.pojo.Bgm;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BgmMapper {
    int deleteByPrimaryKey(String id);

    int insert(Bgm record);

    int insertSelective(Bgm record);

    Bgm selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Bgm record);

    int updateByPrimaryKey(Bgm record);

    List<Bgm> selectAllBgm();
}