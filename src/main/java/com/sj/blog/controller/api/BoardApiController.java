package com.sj.blog.controller.api;

import com.sj.blog.config.auth.PrincipalDetail;
import com.sj.blog.dto.ReplySaveRequestDto;
import com.sj.blog.dto.ResponseDto;
import com.sj.blog.model.Board;
import com.sj.blog.model.Reply;
import com.sj.blog.model.User;
import com.sj.blog.service.BoardService;
import com.sj.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
public class BoardApiController {

    @Autowired
    private BoardService boardService;

    @PostMapping("/api/board")
    public ResponseDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principal) {
        boardService.글쓰기(board, principal.getUser());
        System.out.println("boardApiController: save 호출 완료");
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    @DeleteMapping("/api/board/{id}")
    public ResponseDto<Integer> deleteById(@PathVariable int id) {
        System.out.println("삭제하는 ID: "+id);
        boardService.삭제하기(id);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    @PutMapping("/api/board/{id}")
    public ResponseDto<Integer> update(@PathVariable int id, @RequestBody Board board){
        boardService.수정하기(id,board);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    @PostMapping("/api/board/{boardId}/reply")
    public ResponseDto<Integer> replySave(@RequestBody ReplySaveRequestDto replySaveRequestDto) {
        boardService.댓글쓰기(replySaveRequestDto);
        System.out.println("boardApiController: save 호출 완료");
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }
}
