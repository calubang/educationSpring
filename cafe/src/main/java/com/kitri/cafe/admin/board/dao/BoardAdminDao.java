package com.kitri.cafe.admin.board.dao;

import java.util.List;

import com.kitri.cafe.admin.board.model.BoardListDto;
import com.kitri.cafe.admin.board.model.BoardTypeDto;
import com.kitri.cafe.admin.board.model.CategoryDto;
import com.kitri.cafe.board.model.BoardDto;

public interface BoardAdminDao {
	//게시판 불러오기
	//ccode = 0 이면 전체
	public List<BoardListDto> getBoardMenuList(int ccode);
	
	//카테고리관련
	public List<CategoryDto>getCategoryList();
	public void makeCategory(CategoryDto categoryDto);
	
	//게시판 형식 관련
	public List<BoardTypeDto> getBoardTypeList();
	
	//게시판 만들기
	public void makeBoard(BoardListDto boardListDto);
	
	
	
}
