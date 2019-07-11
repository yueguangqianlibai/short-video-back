package com.xtang.service.impl;

import com.xtang.dao.BgmMapper;
import com.xtang.pojo.Bgm;
import com.xtang.service.IBgmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: short-video-back
 * @Date: 2019/7/9 22:20
 * @Author: xTang
 * @Description:
 */
@Service
public class BgmServiceImpl implements IBgmService {

    @Autowired
    private BgmMapper bgmMapper;

    @Override
    public List<Bgm> queryBgmList() {
        return bgmMapper.selectAllBgm();
    }
}
