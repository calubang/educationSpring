package com.kitri.cafe.board.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kitri.cafe.board.dao.MemoDao;
import com.kitri.cafe.board.dao.ReboardDao;
import com.kitri.cafe.board.model.ReboardDto;
import com.kitri.cafe.common.dao.CommonDao;
import com.kitri.cafe.util.CafeConstance;
import com.kitri.cafe.util.NumberCheck;

@Service
public class ReboardServiceImpl implements ReboardService{
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int writeArticle(ReboardDto reboardDto) {
		int cnt = sqlSession.getMapper(ReboardDao.class).writeArticle(reboardDto);
		return cnt != 0 ? reboardDto.getSeq() : 0;
	}

	@Override
	public List<ReboardDto> listArticle(Map<String, String> parameter) {
		
		//페이징 계산
		int pg = NumberCheck.notNumberToOne(parameter.get("pg"));
		int endRow = pg * CafeConstance.ARTICLE_SIZE;
		int startRow  = endRow - CafeConstance.ARTICLE_SIZE;
		
		parameter.put("endRow", endRow + "");
		parameter.put("startRow", startRow + "");
		
		//System.out.println("endRow : " + endRow);
		//System.out.println("startRow : " + startRow);
		return sqlSession.getMapper(ReboardDao.class).listArticle(parameter);
	}

	@Override
	@Transactional
	public ReboardDto viewArticle(int seq) {
		sqlSession.getMapper(CommonDao.class).updateHit(seq);
		ReboardDto reboardDto = sqlSession.getMapper(ReboardDao.class).viewArticle(seq);
		reboardDto.setContent(reboardDto.getContent().replaceAll("\n", "<br>"));
		return reboardDto;
	}

	@Override
	public int modifyArticle(ReboardDto reboardDto) {
		int result = sqlSession.getMapper(ReboardDao.class).modifyArticle(reboardDto);
		return result > 0 ? reboardDto.getSeq() : 0;
	}

	@Override
	@Transactional
	public void deleteArticle(int seq) {
		//답글이 있으면 답글도 지운다...
		sqlSession.getMapper(MemoDao.class).deleteMemoSeq(seq);
		sqlSession.getMapper(ReboardDao.class).deleteReboardArticle(seq);
		sqlSession.getMapper(ReboardDao.class).deleteArticle(seq);
	}

	@Override
	@Transactional
	public int replyArticle(ReboardDto reboardDto) {
		//자신 밑의 글들의 
		ReboardDao reboardDao = sqlSession.getMapper(ReboardDao.class);
		reboardDao.updateStep(reboardDto);
		reboardDao.replyArticle(reboardDto);
		reboardDao.updateReply(reboardDto.getPseq());
		return reboardDto.getSeq();
	}

	@Override
	public ReboardDto getArticle(int seq) {
		return sqlSession.getMapper(ReboardDao.class).viewArticle(seq);
	}

}
