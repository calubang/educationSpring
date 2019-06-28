package com.kitri.cafe.board.service;

import java.util.List;
import java.util.Map;

import com.kitri.cafe.board.model.AlbumDto;
import com.kitri.cafe.board.model.BbsDto;

public interface BbsService {
	//작성후 자신의 글번호를 return
	public int writeArticle(BbsDto albumDto);
	public List<BbsDto> listArticle(Map<String, String> parameter);
	public BbsDto viewArticle(int seq);
	public int modifyArticle(BbsDto albumDto);
	public void deleteArticle(int seq);
}
