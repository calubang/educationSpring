package com.kitri.cafe.board.dao;

import java.util.List;
import java.util.Map;

import com.kitri.cafe.board.model.BbsDto;

public interface BbsDao {
	//작성후 자신의 글번호를 return
		public int writeArticle(BbsDto albumDto);
		public List<BbsDto> listArticle(Map<String, String> parameter);
		public BbsDto viewArticle(int seq);
		public int modifyArticle(BbsDto albumDto);
		public void deleteArticle(int seq);
}
