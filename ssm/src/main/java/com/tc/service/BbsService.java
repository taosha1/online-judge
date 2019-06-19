package com.tc.service;

import com.tc.domain.BBS;
import com.tc.domain.User_BBS;
import com.tc.utils.Result;

public interface BbsService {

    Result get_resent_notic(int searchNum);

    Result findBBSById(String bbsId);

    Result addByPost(BBS bbs);

    Result addReply(User_BBS user_bbs);

    Result findReplyByBBSId(int id);

    Result findBBSByPage(int page, int bbsNum);

    Result findBBSCount();

    Result RemoveBBSById(int id);
}
