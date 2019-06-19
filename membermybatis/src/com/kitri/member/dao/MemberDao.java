package com.kitri.member.dao;

import java.util.List;
import java.util.Map;

import com.kitri.member.model.*;

public interface MemberDao {
	public int idCheck(String searchId);
	public List<ZipcodeDto> zipSearch(String searchAddress);
	
	public int registerMember(MemberDetailDto dto);
	public MemberDto loginMember(Map<String, String> loginInfo);
	
	public MemberDetailDto getMember(String id);
	public int modifyMember(MemberDetailDto dto);
	public int deleteMember(String id);
	public MemberDetailDto passCheck(String id, String pass);
}
