package com.kitri.cafe.board.controller;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.kitri.cafe.board.model.AlbumDto;
import com.kitri.cafe.board.model.ReboardDto;
import com.kitri.cafe.board.service.AlbumService;
import com.kitri.cafe.common.service.CommonService;
import com.kitri.cafe.member.model.MemberDto;

@Controller
@SessionAttributes("userInfo")
@RequestMapping("/album")
public class AlbumController {
	@Autowired
	private AlbumService albumService;
	@Autowired
	private CommonService commonService;
	
	@Autowired
	private ServletContext servletContext;
	
	//단순 페이지이동의 경우
	//void 로 하면 requestmapping 의 경로로 이동시켜준다
	//ex) /reboard/write.jsp 로 이동
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public void write(@RequestParam Map<String, String> parameter, Model model) {
		model.addAttribute("parameter", parameter);
		//return "reboard/write";
	}
	
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String write(AlbumDto albumDto
			, @SessionAttribute("userInfo") MemberDto user
			, @RequestParam Map<String, String> parameter
			, @RequestParam("picture") MultipartFile multipartFile
			, Model model) {
		
		//System.out.println(parameter);
		//System.out.println(user);
		
		//dto 만들기
		//글번호 가져오기
		int seq = commonService.getNextSeq();
		albumDto.setSeq(seq);
		albumDto.setId(user.getId());
		albumDto.setName(user.getName());
		albumDto.setEmail(user.getEmail());
		
		//파일 부분 시작
		if(multipartFile != null & !multipartFile.isEmpty()) {
			String orignPicture = multipartFile.getOriginalFilename();
			String realpath = servletContext.getRealPath(File.separator + "upload" + File.separator + "album");
			
			//폴더 시작 : 날짜별로 파일을 관리한다.
			DateFormat dateFormat = new SimpleDateFormat("yyMMdd");
			String saveFolder = dateFormat.format(new Date());
			String realSaveFolder = realpath + File.separator + saveFolder;
			File dir = new File(realSaveFolder);
			
			if(!dir.exists()) {
				dir.mkdirs();
			}
			
			String savePicture = UUID.randomUUID().toString() + orignPicture.substring(orignPicture.lastIndexOf('.'));
			File file = new File(realSaveFolder, savePicture);
			
			try {
				multipartFile.transferTo(file);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			albumDto.setOrignPicture(orignPicture);
			albumDto.setSaveFolder(saveFolder);
			albumDto.setSavePicture(savePicture);			
			//System.out.println("orignPicture : " + orignPicture);
			//System.out.println("realpath : " + realpath);
		}
		//TODO : 나중에 할것.....
		//1. image file 검사
		//2. thumbnail image 
		//3. spring 하고 관련없지만 해볼만 한것 
		seq = albumService.writeArticle(albumDto);
		System.out.println("seq : " + seq );
		if(seq != 0) {
			model.addAttribute("seq", seq);
		}else {	
			model.addAttribute("errorMsg", "서버문제로 글작성 실패!!!");
		}
		//글작성 성공 or 실패에 상관없는 부분
		model.addAttribute("parameter", parameter);
		return "album/writeok";
	}
}
