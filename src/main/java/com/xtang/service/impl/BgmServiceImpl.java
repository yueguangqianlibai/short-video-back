package com.xtang.service.impl;

import com.xtang.dao.BgmMapper;
import com.xtang.pojo.Bgm;
import com.xtang.service.IBgmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    @Override
    public List<Bgm> queryBgmList() {
        return bgmMapper.selectAllBgm();
    }

    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    @Override
    public Bgm selectById(String bgmId) {
        return bgmMapper.selectByPrimaryKey(bgmId);
    }
}
