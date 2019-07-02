package com.kitri.cafe.board.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.kitri.cafe.board.model.MemoDto;
import com.kitri.cafe.board.service.MemoService;
import com.kitri.cafe.member.model.MemberDto;

@RestController
@RequestMapping("/memo")
public class MemoController {
	
	@Autowired
	private MemoService memoService;
	
	@RequestMapping(method = RequestMethod.POST)
	public String write(@RequestBody MemoDto memoDto, HttpSession session) {
		System.out.println("memodto : " + memoDto);
		//로그인 확인
		MemberDto user = (MemberDto)session.getAttribute("userInfo");
		if(user == null) {
			return "0";
		}
		
		memoDto.setId(user.getId());
		memoDto.setName(user.getName());
		memoService.writeMemo(memoDto);
		String json = memoService.listMemo(memoDto.getSeq());
		System.out.println(json);
		
		return json;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String memoList(@RequestParam int seq, HttpSession session) {
		//System.out.println("memodto : " + memoDto);
		//로그인 확인
		MemberDto user = (MemberDto)session.getAttribute("userInfo");
		if(user == null) {
			return "0";
		}
		
		String json = memoService.listMemo(seq);
		//System.out.println(json);
		
		return json;
	}
	
	@RequestMapping(value = "/{mseq}", method = RequestMethod.DELETE)
	public String delete(@PathVariable(name = "mseq") int mseq, @RequestBody Map<String, String> map) {
		String json = memoService.deleteMemo(mseq, Integer.parseInt(map.get("seq")));
		System.out.println(map);
		
		return json;
	}
	
	@RequestMapping(value = "/{mseq}", method = RequestMethod.PUT)
	public String modify(@PathVariable(name = "mseq") int mseq, @RequestBody MemoDto memoDto) {
		System.out.println("mseq : " + mseq);
		System.out.println("memoDto : " + memoDto);
		String json = memoService.modifyMemo(memoDto);
		System.out.println(json);
		return json;
	}
}
