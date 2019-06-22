package com.kitri.member.dao;

import com.kitri.member.model.MemberDetailDto;

public interface MemberModifyDao {
	public MemberDetailDto getMember(String id);
	public int modifyMember(MemberDetailDto dto);
	public int deleteMember(String id);
}
