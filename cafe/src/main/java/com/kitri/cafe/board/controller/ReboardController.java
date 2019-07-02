package com.kitri.cafe.board.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.kitri.cafe.board.model.MemoDto;
import com.kitri.cafe.board.model.ReboardDto;
import com.kitri.cafe.board.service.ReboardService;
import com.kitri.cafe.common.service.CommonService;
import com.kitri.cafe.member.model.MemberDto;
import com.kitri.cafe.util.PageNavigation;

@Controller
@RequestMapping("/reboard")
@SessionAttributes("userInfo")
public class ReboardController {
	
	@Autowired
	private ReboardService reboardService;
	
	@Autowired
	private CommonService commonService;
	
	//단순 페이지이동의 경우
	//void 로 하면 requestmapping 의 경로로 이동시켜준다
	//ex) /reboard/write.jsp 로 이동
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public void write(@RequestParam Map<String, String> map, Model model, HttpServletRequest request) {
		System.out.println("referer : " + request.getHeader("referer"));
		model.addAttribute("parameter", map);
		//return "reboard/write";
	}
	
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String write(ReboardDto reboardDto, @SessionAttribute("userInfo") MemberDto user, @RequestParam Map<String, String> parameter, Model model) {
		
		String path = "";
		
		//글번호 가져오기
		int seq = commonService.getNextSeq();
		//System.out.println(user);
		//System.out.println("seq : " + seq);
		reboardDto.setSeq(seq);
		reboardDto.setId(user.getId());
		reboardDto.setName(user.getName());
		reboardDto.setEmail(user.getEmail());
		reboardDto.setRef(seq);
		
		seq = reboardService.writeArticle(reboardDto);
		
		if(seq != 0) {
			model.addAttribute("seq", seq);
			path = "reboard/writeok";
		}else {	
			path = "reboard/writefail";
		}
		//글작성 성공 or 실패에 상관없는 부분
		model.addAttribute("parameter", parameter);
		return path;
	}
	
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public void view(@RequestParam("seq") int seq
				, @ModelAttribute("userInfo") MemberDto user
				, @RequestParam Map<String, String> parameter, Model model) {
		//System.out.println("view 들어옴");
		
		ReboardDto reboardDto = reboardService.viewArticle(seq);
		//System.out.println("seq : " + seq);
		//System.out.println("parameter : " + parameter);
		
		model.addAttribute("article", reboardDto);
		model.addAttribute("parameter", parameter);
		
	}
	
	//한화면에 20개
	//
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void list(@RequestParam Map<String, String> parameter, Model model, HttpServletRequest request) {
		List<ReboardDto> list = reboardService.listArticle(parameter);
		PageNavigation pageNavigation = commonService.getPageNavigation(parameter);
		pageNavigation.setRoot(request.getContextPath());
		pageNavigation.makeNavigator();
		
		model.addAttribute("navigator", pageNavigation);
		model.addAttribute("parameter", parameter);
		model.addAttribute("articleList", list);	
	}
	
//-------------------------------------------------------------------------------------------------------------------------------
//일반 게시판 기능 종료
	
	@RequestMapping(value = "/reply", method = RequestMethod.GET)
	public void reply(@RequestParam("seq") int seq, @SessionAttribute("userInfo") MemberDto user, @RequestParam Map<String, String> parameter, Model model, HttpServletRequest request) {
		ReboardDto reboardDto = reboardService.getArticle(seq);
		//System.out.println("seq : " + seq);
		//System.out.println("parameter : " + parameter);
		
		model.addAttribute("article", reboardDto);
		model.addAttribute("parameter", parameter);
		
		//return "reboard/write";
	}
	
	
	@RequestMapping(value = "/reply", method = RequestMethod.POST)
	public String reply(ReboardDto reboardDto, @SessionAttribute("userInfo") MemberDto user, @RequestParam Map<String, String> parameter, Model model) {
		
		String path = "";
		
		//글번호 가져오기
		int seq = commonService.getNextSeq();
		//System.out.println(user);
		//System.out.println("seq : " + seq);
		reboardDto.setSeq(seq);
		reboardDto.setId(user.getId());
		reboardDto.setName(user.getName());
		reboardDto.setEmail(user.getEmail());
		
		seq = reboardService.replyArticle(reboardDto);
		
		if(seq != 0) {
			model.addAttribute("seq", seq);
			path = "reboard/writeok";
		}else {	
			path = "reboard/writefail";
		}
		//글작성 성공 or 실패에 상관없는 부분
		model.addAttribute("parameter", parameter);
		return path;
	}
	
	//modify
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public void modify(@RequestParam("seq") int seq , @SessionAttribute("userInfo") MemberDto user, @RequestParam Map<String, String> parameter, Model model) {
		
		ReboardDto reboardDto = reboardService.getArticle(seq);
		
		model.addAttribute("article", reboardDto);
		model.addAttribute("parameter", parameter);
		
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify(ReboardDto reboardDto, @SessionAttribute("userInfo") MemberDto user, @RequestParam Map<String, String> parameter, Model model) {
		
		String path = "";
		//System.out.println(reboardDto);
		
		int seq = reboardService.modifyArticle(reboardDto);
		
		if(seq != 0) {
			model.addAttribute("seq", seq);
			path = "reboard/writeok";
		}else {	
			path = "reboard/writefail";
		}
		//글작성 성공 or 실패에 상관없는 부분
		model.addAttribute("parameter", parameter);
		//System.out.println("parameter : " + parameter);
		//System.out.println("seq : " + seq);
		return path;
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public @ResponseBody String deleteConfirm(@RequestParam("seq") int seq, HttpSession session) {
		String result = "impossible";
		//로그인 확인
		if(session.getAttribute("userInfo") == null) {
			return result;
		}
		ReboardDto reboardDto = reboardService.getArticle(seq);
		if(reboardDto.getReply() == 0 ) {
			result = "possible";
		}
		
		return result;
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public @ResponseBody int delete(@RequestBody Map<String, String> map, HttpSession session) {
		//로그인 확인
		//System.out.println(map);
		if(session.getAttribute("userInfo") == null) {
			return 0;
		}
		
		reboardService.deleteArticle(Integer.parseInt(map.get("seq")));
		return 1;
	}
	
}



