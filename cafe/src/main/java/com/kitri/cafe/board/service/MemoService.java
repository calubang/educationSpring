package com.kitri.cafe.board.service;

import java.util.List;
import java.util.Map;

import com.kitri.cafe.board.model.BoardDto;
import com.kitri.cafe.board.model.MemoDto;

public interface MemoService {
	//작성후 자신의 글번호를 return
	public void writeMemo(MemoDto memoDto);
	public String listMemo(int seq);
	public String modifyMemo(MemoDto memoDto);
	public String deleteMemo(int mseq, int seq);	
}
