package com.kitri.member.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kitri.member.model.MemberDetailDto;
import com.kitri.sqlmap.MyBatisConfiguration;

@Repository
public class MemberModifyDaoImpl implements MemberModifyDao{
	
	private final String NAME_SPACE = "com.kitri.member.dao.MemberDao";
	
	@Override
	public MemberDetailDto getMember(String id) {
		SqlSession session = MyBatisConfiguration.getSqlSessionFactory().openSession();
		try {
			return session.selectOne(NAME_SPACE + ".getMember", id);
		} finally {
			session.close();
		}
	}

	@Override
	public int modifyMember(MemberDetailDto dto) {
		SqlSession session = MyBatisConfiguration.getSqlSessionFactory().openSession();
		try {
			session.update(NAME_SPACE + ".modifyMember", dto);
			int result = session.update(NAME_SPACE + ".modifyMemberDetail", dto);
			session.commit();
			return result;
		} finally {
			session.close();
		}
	}

	@Override
	public int deleteMember(String id) {
		SqlSession session = MyBatisConfiguration.getSqlSessionFactory().openSession();
		try {
			session.delete(NAME_SPACE + ".deleteMemberDetail", id);
			int result = session.delete(NAME_SPACE + ".deleteMember", id);
			session.commit();
			return result;
		} finally {
			session.close();
		}
	}

}
