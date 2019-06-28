package com.kitri.cafe.board.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.kitri.cafe.board.service.BbsService;
import com.kitri.cafe.common.service.CommonService;

@Controller
@RequestMapping("/bbs")
public class BbsController {
	
	@Autowired
	private BbsService service;
	
	@Autowired
	private CommonService commonService;
	
	//단순 페이지이동의 경우
	//void 로 하면 requestmapping 의 경로로 이동시켜준다
	//ex) /reboard/write.jsp 로 이동
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public void write(@RequestParam Map<String, String> map, Model model) {
		model.addAttribute("parameter", map);
		//return "reboard/write";
	}
}
