package com.kitri.cafe.board.dao;

import java.util.List;
import java.util.Map;

import com.kitri.cafe.board.model.MemoDto;

public interface MemoDao {
	//작성후 자신의 글번호를 return
	public int writeMemo(MemoDto memoDto);
	public List<MemoDto> listMemo(int seq);
	public MemoDto viewMemo(int seq);
	public int modifyMemo(MemoDto memoDto);
	public void deleteMemo(int mseq);
	public void deleteMemoSeq(int seq);
}
