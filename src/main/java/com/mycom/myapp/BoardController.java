package com.mycom.myapp;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mycom.myapp.board.BoardDAO;
import com.mycom.myapp.board.BoardVO;

@Controller
public class BoardController {
	
private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */

	@Autowired
	BoardDAO boardDAO;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		ArrayList<String> posts = new ArrayList<String>();
		posts.add("안녕하세요");
		posts.add("14주차 과제");
		posts.add("종강까지 12일");
		
		model.addAttribute("list",posts);
		return "home";
	}
	
	@RequestMapping(value = "/board/list", method = RequestMethod.GET)
	public String boardlist(Model model) {
		logger.info("post.jsp loaded");
		model.addAttribute("list", boardDAO.getBoardList());
		return "posts";
	}
	
	@RequestMapping(value = "/board/add", method = RequestMethod.GET)
	public String addPost() {
		logger.info("addok.jsp loaded");
		return "addpostform";
	}
	
	@RequestMapping(value = "/board/addok", method = RequestMethod.POST)
	public String addPostOk(BoardVO vo) {
		logger.info("addpostform.jsp loaded");
		int i = boardDAO.insertBoard(vo);
		if(i==0)
			System.out.println("데이터 추가 실패");
		else
			System.out.println("데이터 추가 성공");
		return "redirect:list";
	}
	
	@RequestMapping(value = "/board/editform/{id}", method = RequestMethod.GET)
	public String editPost(@PathVariable("id") int id,Model model) {
		logger.info("editpostform.jsp loaded");
		BoardVO boardVO = boardDAO.getBoard(id);
		model.addAttribute("boardVO", boardVO);
		return "editform";
	}
	
	@RequestMapping(value = "/board/editok", method = RequestMethod.POST)
	public String editPostOK(BoardVO vo) {
		logger.info("editok.jsp loaded");
		int i = boardDAO.updateBoard(vo);
		if(i==0)
			System.out.println("데이터 수정 실패");
		else
			System.out.println("데이터 수정 성공");
		return "redirect:list";
	}

	@RequestMapping(value = "/board/deleteok/{id}", method = RequestMethod.GET)
	public String deletePost(@PathVariable("id") int id) {
		int i = boardDAO.deleteBoard(id);
		if (i==0)
			System.out.println("데이터 삭제 실패");
		else
			System.out.println("데이터 삭제 성공");
		return "redirect:../list";
	}
}
