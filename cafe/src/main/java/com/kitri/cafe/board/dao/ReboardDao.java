package com.kitri.cafe.board.dao;

import java.util.List;
import java.util.Map;

import com.kitri.cafe.board.model.ReboardDto;

public interface ReboardDao {
	//작성후 자신의 글번호를 return
	public int writeArticle(ReboardDto reboardDto);
	public List<ReboardDto> listArticle(Map<String, String> parameter);
	public ReboardDto viewArticle(int seq);
	public int modifyArticle(ReboardDto reboardDto);
	public void deleteArticle(int seq);	
	public void deleteReboardArticle(int seq);
	
	//reply 부분
	public void updateStep(ReboardDto reboardDto);
	public int replyArticle(ReboardDto reboardDto);
	public void updateReply(int pseq);
}
