package com.kitri.cafe.board.dao;

import java.util.List;
import java.util.Map;

import com.kitri.cafe.board.model.MemoDto;

public interface MemoDao {
	//작성후 자신의 글번호를 return
	public int writeArticle(MemoDto albumDto);
	public List<MemoDto> listArticle(Map<String, String> parameter);
	public MemoDto viewArticle(int seq);
	public int modifyArticle(MemoDto albumDto);
	public void deleteArticle(int seq);
}
