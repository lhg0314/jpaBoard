package com.board.mine.controller;



import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.board.mine.dto.BoardDto;
import com.board.mine.service.BoardService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class BoardController {
    private BoardService boardService;

    /* 게시글 목록 */
    @GetMapping("/board")
    public List<BoardDto> list() {
        List<BoardDto> boardList = boardService.getBoardlist();

       
        return boardList;
    }
}
