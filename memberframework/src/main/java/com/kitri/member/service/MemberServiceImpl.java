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

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kitri.member.dao.MemberDao;
import com.kitri.member.dao.MemberDaoImpl;
import com.kitri.member.model.*;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberDao dao;
	
	@Override
	public String idCheck(String searchId) {
		int cnt = dao.idCheck(searchId);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("idcount", cnt);
		return jsonObject.toString();
	}

	@Override
	public String zipSearch(String searchAddress) {
		List<ZipcodeDto> list = dao.zipSearch(searchAddress);
		JSONObject jsonObject = new JSONObject();
		JSONArray zipArray = new JSONArray(list);
		
		//System.out.println(list);
//		for(ZipcodeDto dto : list) {
//			JSONObject zip = new JSONObject();
//			String address = dto.getSido() 
//					+ dto.getGugun() 
//					+ dto.getUpmyon() 
//					+ dto.getDoro() 
//					+ dto.getBuilding_number()
//					+ dto.getSigugun_building_name();
//			zip.put("zipcode", dto.getZipcode());
//			zip.put("address", address);
//			zipArray.put(zip);
//		}
		jsonObject.put("ziplist", zipArray);
		//System.out.println(jsonObject.toString());
		return jsonObject.toString();
	}

	@Override
	public int registerMember(MemberDetailDto dto) {
		return dao.registerMember(dto);
	}

	@Override
	public MemberDto loginMember(Map<String, String> map) {
//		Map<String, String> loginInfo = new HashMap<String, String>();
//		loginInfo.put("userId", id);
//		loginInfo.put("userPass", pass);
		return dao.loginMember(map);
	}

	@Override
	public MemberDetailDto getMember(String id) {
		return null;
	}

	@Override
	public int modifyMember(MemberDetailDto dto) {
		return dao.modifyMember(dto);
	}

	@Override
	public int deleteMember(String id) {
		return dao.deleteMember(id);
	}

	@Override
	public MemberDetailDto passCheck(String id, String pass) {
		return dao.passCheck(id, pass);
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
