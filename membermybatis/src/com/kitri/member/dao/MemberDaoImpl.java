package com.kitri.member.dao;

import java.sql.*;
import java.util.*;

import org.apache.ibatis.session.SqlSession;

import com.kitri.member.model.*;
import com.kitri.sqlmap.MyBatisConfiguration;

public class MemberDaoImpl implements MemberDao {
	private final String NAME_SPACE = "com.kitri.member.dao.MemberDao";
	private static MemberDao memberDao;
	
	static {
		memberDao = new MemberDaoImpl();
	}
	
	private MemberDaoImpl() {}
	
	public static MemberDao getMemberDao() {
		return memberDao;
	}

	@Override
	public int idCheck(String searchId) {
		SqlSession session = MyBatisConfiguration.getSqlSessionFactory().openSession();
		try {
			return session.selectOne(NAME_SPACE + ".idCheck", searchId);
		} finally {
			session.close();
		}
	}

	@Override
	public List<ZipcodeDto> zipSearch(String searchAddress) {
		SqlSession session = MyBatisConfiguration.getSqlSessionFactory().openSession();
		try {
			return session.selectList(NAME_SPACE + ".zipSearch", searchAddress);
		} finally {
			session.close();
		}
	}

	@Override
	public int registerMember(MemberDetailDto dto) {
		SqlSession session = MyBatisConfiguration.getSqlSessionFactory().openSession();
		int result = 0;
		try {
			result = session.insert(NAME_SPACE + ".registerMember", dto);
			session.commit();
		} finally {
			session.close();
		}
		return result;
	}

	@Override
	public MemberDto loginMember(Map<String, String> loginInfo) {
		SqlSession session = MyBatisConfiguration.getSqlSessionFactory().openSession();
		try {
			return session.selectOne(NAME_SPACE + ".loginMember", loginInfo);
		} finally {
			session.close();
		}
	}

	@Override
	public MemberDetailDto getMember(String id) {
		SqlSession session = MyBatisConfiguration.getSqlSessionFactory().openSession();
		return null;
	}

	@Override
	public int modifyMember(MemberDetailDto dto) {
		SqlSession session = MyBatisConfiguration.getSqlSessionFactory().openSession();
		int result = 0;
		try {
			session.update(NAME_SPACE + ".modifyMemberDetail", dto);
			result = session.update(NAME_SPACE + ".modifyMember", dto);
			session.commit();
		} finally {
			session.close();
		}
		return result;
	}

	@Override
	public int deleteMember(String id) {
		SqlSession session = MyBatisConfiguration.getSqlSessionFactory().openSession();
		int result = 0;
		try {
			session.delete(NAME_SPACE + ".deleteMemberDetail", id);
			session.delete(NAME_SPACE + ".deleteMember", id);
			session.commit();
		} finally {
			session.close();
		}
		
		return result;
	}

	@Override
	public MemberDetailDto passCheck(String id, String pass) {
		SqlSession session = MyBatisConfiguration.getSqlSessionFactory().openSession();
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("pass", pass);
		try {
			return session.selectOne(NAME_SPACE + ".passCheck", map);
		} finally {
			session.close();
		}
	}

}
