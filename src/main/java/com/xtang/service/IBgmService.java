package com.xtang.service;

import com.xtang.pojo.Bgm;

import java.util.List;

/**
 * @program: short-video-back
 * @Date: 2019/7/9 22:20
 * @Author: xTang
 * @Description:
 */
public interface IBgmService {

    /**
     * 查询所有Bgm
     */
    List<Bgm> queryBgmList();

}
