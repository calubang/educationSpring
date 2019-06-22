package com.kitri.member.service;

import com.kitri.member.model.MemberDetailDto;

public interface MemberModifyService {
	public MemberDetailDto getMember(String id);
	public int modifyMember(MemberDetailDto dto);
	public int deleteMember(String id);
}
