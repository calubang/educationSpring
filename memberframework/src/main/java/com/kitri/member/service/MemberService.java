package com.kitri.member.service;

import java.util.List;
import java.util.Map;

import com.kitri.member.model.*;

public interface MemberService {
	
	public String idCheck(String searchId);
	public String zipSearch(String searchAddress);
	
	public int registerMember(MemberDetailDto dto);
	public MemberDto loginMember(Map<String, String> map);
	
	public MemberDetailDto getMember(String id);
	public int modifyMember(MemberDetailDto dto);
	public int deleteMember(String id);
	public MemberDetailDto passCheck(String id, String pass);
	public StringBuffer zipSearchWeb(String searchAddress, int currentPage);

}
