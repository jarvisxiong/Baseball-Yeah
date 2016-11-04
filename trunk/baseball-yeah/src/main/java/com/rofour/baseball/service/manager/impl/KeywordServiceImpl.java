package com.rofour.baseball.service.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.rofour.baseball.controller.model.manager.KeywordInfo;
import com.rofour.baseball.dao.manager.bean.KeywordBean;
import com.rofour.baseball.dao.manager.mapper.KeywordMapper;
import com.rofour.baseball.service.manager.KeywordService;

@Service("keywordService")
public class KeywordServiceImpl implements KeywordService {

	@Autowired
	@Qualifier("keywordMapper")
	private KeywordMapper dao;
	
	
	@Override
	public int insertKeyword(KeywordBean bean) { 
		return dao.insertKeyword(bean);
	}

	@Override
	public int deleteByPrimaryKey(KeywordBean bean) { 
		return dao.deleteByPrimaryKey(bean);
	}

	@Override
	public List<KeywordInfo> getList(KeywordInfo info) { 
		return dao.getList(info);
	}

	@Override
	public int getListCount(KeywordInfo info) { 
		return dao.getListCount(info);
	}

	@Override
	public boolean validateKeyword(KeywordBean bean) {
		List<KeywordBean> list = dao.validateKeyword(bean);
		if(list != null) {
			if(list.size() > 0) {
				return true;
			}
		}
		return false;
	}

}
