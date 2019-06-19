package com.kitri.admin.model.service;

import java.util.*;

import com.kitri.admin.model.dao.AdminDaoImpl;
import com.kitri.member.model.MemberDetailDto;

public class AdminServiceImpl implements AdminService{
	
	private static AdminService adminService;
	
	static {
		adminService = new AdminServiceImpl();
	}
	
	private AdminServiceImpl() {}
	
	public static AdminService getAdminService() {
		return adminService;
	}

	@Override
	public String getMemberList(String key, String word) {
		String result = "";
		Map<String, String> map = new HashMap<String, String>();
		map.put("key", key);
		map.put("word", word);
		List<MemberDetailDto> list = AdminDaoImpl.getAdminDao().getMemberList(map);
		
		result += "<?xml version='1.0' encoding='utf-8'?>\n";
		result += "<memberlist>"+"\n";
		
		for(MemberDetailDto dto : list) {
			result += "		<member>"+"\n";
			result += "			<id>"+ dto.getId() + "</id>"+"\n";
			result += "			<name>"+ dto.getName() + "</name>"+"\n";
			result += "			<email>"+ dto.getEmailid() + "@" + dto.getEmaildomain() + "</email>"+"\n";
			result += "			<tel>"+ dto.getTel1() + "-" + dto.getTel2() + "-" + dto.getTel3() + "</tel>"+"\n";
			result += "			<address><![CDATA["+ dto.getZipcode() + " " + dto.getAddress() + " " + dto.getAddressDetail() +"]]></address>"+"\n";
			result += "			<joindate></joindate>"+"\n";
			result += "		</member>"+"\n";
		}
		
		result += "</memberlist>"+"\n";
		
		return result;
	}
}
