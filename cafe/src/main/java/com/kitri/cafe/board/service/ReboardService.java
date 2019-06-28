package com.kitri.cafe.board.service;

import java.util.List;
import java.util.Map;

import com.kitri.cafe.board.model.MemoDto;
import com.kitri.cafe.board.model.ReboardDto;

public interface ReboardService {
	//작성후 자신의 글번호를 return
	public int writeArticle(ReboardDto reboardDto);
	public List<ReboardDto> listArticle(Map<String, String> parameter);
	public ReboardDto viewArticle(int seq);
	public ReboardDto getArticle(int seq);
	public int modifyArticle(ReboardDto reboardDto);
	public void deleteArticle(int seq);	
	
	public int replyArticle(ReboardDto reboardDto);
}
