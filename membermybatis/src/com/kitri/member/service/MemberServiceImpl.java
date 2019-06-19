package com.kitri.member.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.*;

import com.kitri.member.dao.MemberDao;
import com.kitri.member.dao.MemberDaoImpl;
import com.kitri.member.model.*;

public class MemberServiceImpl implements MemberService {
	
	private static MemberService memberService;
	
	static {
		memberService = new MemberServiceImpl();
	}
	
	private MemberServiceImpl() {
	}
		
	public static MemberService getMemberService() {
		return memberService;
	}

	@Override
	public String idCheck(String searchId) {
		int cnt = MemberDaoImpl.getMemberDao().idCheck(searchId);
		String result = "";
		result += "<idcount>\n";
		result += "\t<cnt>"+ cnt + "</cnt>\n";
		result += "</idcount>";
		return result;
	}

	@Override
	public String zipSearch(String searchAddress) {
		String resultXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
		List<ZipcodeDto> list = MemberDaoImpl.getMemberDao().zipSearch(searchAddress);
		resultXML += "<ziplist>";
		
		for(ZipcodeDto dto : list) {
			resultXML += "<zip>\n";
			resultXML += "	<zipcode>" + dto.getZipcode() + "\n";
			resultXML += "	</zipcode>" + "\n";
			resultXML += "	<address><![CDATA[" + dto.getSido() + dto.getGugun() + dto.getUpmyon();
			resultXML += dto.getDoro() + dto.getBuilding_number() + dto.getSigugun_building_name() + "]]>\n";
			resultXML += "	</address>"+ "\n";
			resultXML += "</zip>" + "\n";
		}
		resultXML += "</ziplist>";
		//System.out.println(resultXML);
		return resultXML;
	}

	@Override
	public int registerMember(MemberDetailDto dto) {
		return MemberDaoImpl.getMemberDao().registerMember(dto);
	}

	@Override
	public MemberDto loginMember(String id, String pass) {
		Map<String, String> loginInfo = new HashMap<String, String>();
		loginInfo.put("userId", id);
		loginInfo.put("userPass", pass);
		return MemberDaoImpl.getMemberDao().loginMember(loginInfo);
	}

	@Override
	public MemberDetailDto getMember(String id) {
		return null;
	}

	@Override
	public int modifyMember(MemberDetailDto dto) {
		return MemberDaoImpl.getMemberDao().modifyMember(dto);
	}

	@Override
	public int deleteMember(String id) {
		return MemberDaoImpl.getMemberDao().deleteMember(id);
	}

	@Override
	public MemberDetailDto passCheck(String id, String pass) {
		return MemberDaoImpl.getMemberDao().passCheck(id, pass);
	}

	@Override
	public StringBuffer zipSearchWeb(String searchAddress, int currentPage) {
		String key = "g066YY%2F%2Fd4D1%2FKBNzd4UniRDi8znS%2B9CpbjpSk25vo4Luk%2BdPR7sn%2FYr0WDMx1uMOlOa5mEkAvQJ85tWVP0XKw%3D%3D";
		int countPerPage = 50;
		StringBuffer resultXML = new StringBuffer();
		String apiUrl = "http://openapi.epost.go.kr/postal/retrieveNewAdressAreaCdSearchAllService/retrieveNewAdressAreaCdSearchAllService/getNewAddressListAreaCdSearchAll?ServiceKey=g066YY%2F%2Fd4D1%2FKBNzd4UniRDi8znS%2B9CpbjpSk25vo4Luk%2BdPR7sn%2FYr0WDMx1uMOlOa5mEkAvQJ85tWVP0XKw%3D%3D"
				+ "&countPerPage=" +  countPerPage 
				+ "&currentPage=" + currentPage
				+ "&srchwrd=";

		//System.out.println(apiUrl + searchAddress);
		BufferedReader in = null;
		try {
			URL url = new URL(apiUrl + URLEncoder.encode(searchAddress, "UTF-8"));
			in = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
			String temp = null;
			while(true) {
				temp = in.readLine();
				if(temp == null) {
					break;
				}
				resultXML.append(temp);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(in != null) {
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return resultXML;
	}

}
