package com.kitri.cafe.admin.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.kitri.cafe.admin.board.model.BoardListDto;
import com.kitri.cafe.admin.board.service.BoardAdminService;

@Controller
@RequestMapping("/boardadmin")
public class BoardAdminController {
	
	@Autowired
	private BoardAdminService service;
	
	@RequestMapping("/boardmenu")
	public ModelAndView boardMenu(@RequestParam(name = "ccode", defaultValue = "0") int ccode) {
		//System.out.println("게시판 목록");
		List<BoardListDto> list =  service.getBoardMenuList(ccode);
		//System.out.println("list size : " + list);
		ModelAndView mav = new ModelAndView();
		mav.addObject("boardmenulist", list);
		mav.setViewName("admin/boardmenu");
		return mav;
	}
}
