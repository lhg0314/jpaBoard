package com.board.mine.controller;



import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.board.mine.dto.BoardDto;
import com.board.mine.dto.CommentDto;
import com.board.mine.service.BoardService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class BoardController {
    private BoardService boardService;

    /* 게시글 목록 */
    @GetMapping("/board/all")
    public List<BoardDto> list() {
        List<BoardDto> boardList = boardService.getBoardlist();

       
        return boardList;
    }
    
    /* 게시글 상세 */
    @GetMapping("/board/detail/{id}")
    public BoardDto getDetail(@PathVariable(name = "id")  Long id) {
    	System.out.println("id:  " + id);
    	BoardDto result = boardService.getBoardDetail(id);
    	
       
        return result;
    }
    
    @PostMapping("/comment/save")
    public void saveComment(@RequestBody CommentDto commentDto) {
    	System.out.println("받은값:::"+commentDto);
    	Long res = boardService.saveComment(commentDto);
    	System.out.println("t새로운 아이디 ::" + res);
    }
}
