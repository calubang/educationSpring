package com.kitri.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kitri.member.dao.MemberModifyDao;
import com.kitri.member.model.MemberDetailDto;

@Service
public class MemberModifyServiceImpl implements MemberModifyService{

	@Autowired
	private MemberModifyDao dao;
	
	@Override
	public MemberDetailDto getMember(String id) {
		return dao.getMember(id);
	}

	@Override
	public int modifyMember(MemberDetailDto dto) {
		return dao.modifyMember(dto);
	}

	@Override
	public int deleteMember(String id) {
		return dao.deleteMember(id);
	}
	
}
