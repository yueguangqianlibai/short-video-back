package com.xtang.dao;

import com.xtang.pojo.SearchRecords;
import org.springframework.stereotype.Repository;
import sun.security.krb5.internal.tools.Klist;

import java.util.List;

@Repository
public interface SearchRecordsMapper {
    int deleteByPrimaryKey(String id);

    int insert(SearchRecords record);

    int insertSelective(SearchRecords record);

    SearchRecords selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SearchRecords record);

    int updateByPrimaryKey(SearchRecords record);

    List<String> getHotWords();
}