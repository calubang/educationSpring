package com.kitri.admin.model.service;

import java.util.*;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kitri.admin.model.dao.AdminDao;
import com.kitri.member.model.MemberDetailDto;

@Service
public class AdminServiceImpl implements AdminService{
	@Autowired
	private AdminDao dao;
	
	@Override
	public String getMemberList(Map<String, String> map) {
		String result = "";
		List<MemberDetailDto> list = dao.getMemberList(map);
		
		//System.out.println(list);
		JSONObject jsonObject = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		for(MemberDetailDto dto : list) {
			JSONObject member = new JSONObject();
			member.put("id", dto.getId());
			member.put("name", dto.getId());
			member.put("email", dto.getId()+"@"+dto.getEmaildomain());
			member.put("tel", dto.getTel1() + "-" + dto.getTel2() + "-" + dto.getTel3());
			member.put("address", dto.getZipcode() + " " + dto.getAddress() + " " +dto.getAddress_detail());
			member.put("joindate", dto.getJoindate());
			jsonArray.put(member);
		}
		jsonObject.put("memberlist", jsonArray);
		
		/*
		 * result += "<?xml version='1.0' encoding='utf-8'?>\n"; result +=
		 * "<memberlist>"+"\n";
		 * 
		 * for(MemberDetailDto dto : list) { result += "		<member>"+"\n"; result
		 * += "			<id>"+ dto.getId() + "</id>"+"\n"; result += "			<name>"+
		 * dto.getName() + "</name>"+"\n"; result += "			<email>"+
		 * dto.getEmailid() + "@" + dto.getEmaildomain() + "</email>"+"\n"; result +=
		 * "			<tel>"+ dto.getTel1() + "-" + dto.getTel2() + "-" +
		 * dto.getTel3() + "</tel>"+"\n"; result += "			<address><![CDATA["+
		 * dto.getZipcode() + " " + dto.getAddress() + " " + dto.getAddress_detail()
		 * +"]]></address>"+"\n"; result += "			<joindate></joindate>"+"\n";
		 * result += "		</member>"+"\n"; }
		 * 
		 * result += "</memberlist>"+"\n";
		 */
		
		return jsonObject.toString();
	}
}
