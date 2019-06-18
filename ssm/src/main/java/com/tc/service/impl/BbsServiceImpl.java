package com.tc.service.impl;

import com.alibaba.fastjson.JSON;
import com.tc.dao.BbsDao;
import com.tc.dao.User_BBSDao;
import com.tc.domain.BBS;
import com.tc.domain.User_BBS;
import com.tc.service.BbsService;
import com.tc.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BbsServiceImpl implements BbsService {

    @Autowired
    private BbsDao bbsDao;
    @Autowired
    private User_BBSDao user_bbsDao;

    @Override
    public Result get_resent_notic(int searchNum) {
        List<BBS> list = bbsDao.findBBSByTopNum(searchNum);
        if (list != null) {
            return new Result(true, JSON.toJSONString(list));
        } else {
            return new Result(false, "没有讨论！");
        }
    }

    @Override
    public Result findBBSById(String bbsId) {
        BBS bbs = bbsDao.findBBSById(Integer.parseInt(bbsId));
        if (bbs != null) {
            return new Result(true, JSON.toJSONString(bbs));
        } else {
            return new Result(false, "找不到该讨论！");
        }
    }

    @Override
    public Result addByPost(BBS bbs) {
        int i = bbsDao.addBBS(bbs);
        if (i == 0) {
            return new Result(false, "add bbs fail");
        } else {
            return new Result(true, "add bbs success");
        }
    }

    @Override
    public Result addReply(User_BBS user_bbs) {
        int insertRow = 0;
        insertRow = user_bbsDao.addUser_BBS(user_bbs);
        if (insertRow == 0) {
            return new Result(false, "添加回复失败");
        } else {
            return new Result(true, "添加回复成功");
        }
    }

    @Override
    public Result findReplyByBBSId(int id) {
        List<User_BBS> user_bbsList = null;
        user_bbsList = user_bbsDao.findUser_BBSByBBSId(id);
        if (user_bbsList != null) {
            return new Result(true, JSON.toJSON(user_bbsList).toString());
        } else {
            return new Result(false, "没有更多的回复了");
        }
    }

    @Override
    public Result findBBSByPage(int page, int bbsNum) {
        List<BBS> bbsList = null;
        int startNum = (page - 1) * bbsNum;
        bbsList = bbsDao.findBBSLimit(startNum, bbsNum);
        if (bbsList != null) {
            return new Result(true, JSON.toJSON(bbsList).toString());
        } else {
            return new Result(false, "没有更多的讨论了");
        }
    }

    @Override
    public Result findBBSCount() {
        int countNum = 0;
        countNum = bbsDao.findCount();
        if (countNum != 0) {
            return new Result(true, "" + countNum);
        } else {
            return new Result(false, "BBS数据库为空");
        }
    }
}
