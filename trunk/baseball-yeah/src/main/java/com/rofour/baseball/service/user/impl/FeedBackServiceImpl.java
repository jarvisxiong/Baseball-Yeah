	package com.rofour.baseball.service.user.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.rofour.baseball.common.AxpUtils;
import com.rofour.baseball.common.WebUtils;
import com.rofour.baseball.controller.model.ResultInfo;
import com.rofour.baseball.controller.model.user.FeedBackInfo;
import com.rofour.baseball.dao.user.bean.FeedBackBean;
import com.rofour.baseball.dao.user.mapper.FeedBackMapper;
import com.rofour.baseball.service.user.FeedBackService;

/**
 * @author sdd
 * @ClassName: FeedBackServiceImpl
 * @Description: 反馈信息类型的接口实现类
 * @date 2016年3月27日 下午5:58:42
 */
@Service("feedBackService")
public class FeedBackServiceImpl implements FeedBackService {

	@Autowired
	@Qualifier("feedBackMapper")
	private FeedBackMapper dao;
	  @Autowired
	    private WebUtils webUtils;
	public ResultInfo addFeedBack(FeedBackInfo feedBack) {
			FeedBackBean fbb = new FeedBackBean();
			AxpUtils.copyProperties(feedBack, fbb);
			int insertResult = dao.insert(fbb);
			if (insertResult <= 0) {
				return new ResultInfo(-1, "-1", "添加反馈信息失败");
			}
		return new ResultInfo(0, "", "用户反馈信息成功！");
	}

	/* (非 Javadoc)
	 * @return
	 */
	@Override
	public List<FeedBackInfo> selectAll(FeedBackInfo feedBackInfo) {
			List<FeedBackInfo> beanList = dao.selectAll(feedBackInfo);
		return beanList;
	}

	/* (非 Javadoc)
	 * @param feedBack
	 * @return
	 */
	@Override
	public int deleteBatch(FeedBackInfo feedBack, HttpServletRequest request) {
	
		int res = 0;

			res = dao.deleteBatch(feedBack);
			 webUtils.userDeleteLog(request,12,feedBack);
		return res;
	}


	/* (非 Javadoc)
	 * @param feedBack
	 * @return
	 */
	@Override
	public List<FeedBackInfo> selectLoginLog(FeedBackInfo feedBack) {
		List<FeedBackInfo> ilist = null;
			List<FeedBackBean> blist = dao.selectByUserId(feedBack.getUserId());
			ilist = new ArrayList<FeedBackInfo>();
			for (FeedBackBean fbb : blist) {
				FeedBackInfo fbf = new FeedBackInfo();
				AxpUtils.copyProperties(fbb, fbf);
				ilist.add(fbf);
			}
		return ilist;
	}

	@Override
	public Integer getfeedBackTotal(FeedBackInfo feedBack) {
		return dao.getfeedBackTotal(feedBack);
	
	}
	


}
