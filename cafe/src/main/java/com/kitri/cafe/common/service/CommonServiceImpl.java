package com.kitri.cafe.common.service;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kitri.cafe.common.dao.CommonDao;
import com.kitri.cafe.util.CafeConstance;
import com.kitri.cafe.util.NumberCheck;
import com.kitri.cafe.util.PageNavigation;

@Service
public class CommonServiceImpl implements CommonService{

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int getNextSeq() {
		return sqlSession.getMapper(CommonDao.class).getNextSeq();
	}

	@Override
	public PageNavigation getPageNavigation(Map<String, String> parameter) {
		PageNavigation pageNavigation = new PageNavigation();
		int newArticleCount = sqlSession.getMapper(CommonDao.class).getNewArticleCount(Integer.parseInt(parameter.get("bcode")));
		int totalArticleCount = sqlSession.getMapper(CommonDao.class).getTotalArticleCount(parameter);
		int totalPageCount = (totalArticleCount - 1) / CafeConstance.ARTICLE_SIZE + 1;
		int pg = NumberCheck.notNumberToOne(parameter.get("pg"));
		boolean nowFirst = pg <= CafeConstance.PAGE_SIZE;
		boolean nowEnd = (totalPageCount-1) / CafeConstance.PAGE_SIZE * CafeConstance.PAGE_SIZE < pg;  
		
		pageNavigation.setNewArticleCount(newArticleCount);
		pageNavigation.setTotalArticleCount(totalArticleCount);
		pageNavigation.setNowFirst(nowFirst);
		pageNavigation.setNowEnd(nowEnd);
		pageNavigation.setPageNo(pg);
		pageNavigation.setTotalPageCount(totalPageCount);
		
		return pageNavigation;
	}
	
}
