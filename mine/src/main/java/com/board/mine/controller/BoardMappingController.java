package com.board.mine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.board.mine.service.BoardService;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Controller

public class BoardMappingController {

	@Autowired
	private BoardService boardService;

	/* 게시글 목록 */
	@GetMapping("/list")
	public String list() {

		return "list";
	}


	//회원 상세
	@GetMapping("/detail/{id}")
	public String getUser(@PathVariable(name = "id") Long id, Model model){
		System.out.println("id"+ id);
		model.addAttribute("id",id);
		return "detail";
	}

}
