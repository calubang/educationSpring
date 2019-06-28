package com.kitri.cafe.board.dao;

import java.util.List;
import java.util.Map;

import com.kitri.cafe.board.model.BoardDto;

public interface BoardDao {
	//작성후 자신의 글번호를 return
	public int writeArticle(BoardDto albumDto);
	public List<BoardDto> listArticle(Map<String, String> parameter);
	public BoardDto viewArticle(int seq);
	public int modifyArticle(BoardDto albumDto);
	public void deleteArticle(int seq);
}
