package com.kitri.cafe.board.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kitri.cafe.board.model.BbsDto;
@Service
public class BbsServiceImpl implements BbsService{

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int writeArticle(BbsDto albumDto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<BbsDto> listArticle(Map<String, String> parameter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BbsDto viewArticle(int seq) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int modifyArticle(BbsDto albumDto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deleteArticle(int seq) {
		// TODO Auto-generated method stub
		
	}

}
