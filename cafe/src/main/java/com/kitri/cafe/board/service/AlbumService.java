package com.kitri.cafe.board.service;

import java.util.List;
import java.util.Map;

import com.kitri.cafe.board.model.AlbumDto;

public interface AlbumService {
	//작성후 자신의 글번호를 return
	public int writeArticle(AlbumDto albumDto);
	public List<AlbumDto> listArticle(Map<String, String> parameter);
	public AlbumDto viewArticle(int seq);
	public int modifyArticle(AlbumDto albumDto);
	public void deleteArticle(int seq);
	
}
