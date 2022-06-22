package com.sj.blog.service;

import com.sj.blog.dto.ReplySaveRequestDto;
import com.sj.blog.model.Board;
import com.sj.blog.model.Reply;
import com.sj.blog.model.RoleType;
import com.sj.blog.model.User;
import com.sj.blog.repository.BoardRepository;
import com.sj.blog.repository.ReplyRepository;
import com.sj.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


//스프링이 컴포넌트 스캔을 통해서 Bean에 등록해줌, Ioc를 해준다
@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ReplyRepository replyRepository;

    @Transactional
    public int 글쓰기(Board board, User user) {
        try {
            board.setCount(0);
            board.setUser(user);
            boardRepository.save(board);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("BoardService: 글쓰기(): " + e.getMessage());
        }
        return -1;
    }

    @Transactional(readOnly = true)
    public Page<Board> 글목록(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Board 글상세보기(int id) {
        return boardRepository.findById(id)
                .orElseThrow(() -> {
                    return new IllegalArgumentException("글 상세보기 실패");
                });
    }

    @Transactional
    public void 삭제하기(int id) {
        boardRepository.deleteById(id);
    }

    @Transactional
    public void 수정하기(int id, Board requestBoard) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> {
                    return new IllegalArgumentException("글 찾기 실패!!!");
                });//영속화 완료
        board.setTitle(requestBoard.getTitle());
        board.setContent(requestBoard.getContent());
        //해당함수로 종료시(service가 종료될 때) 트랜잭션이 종료됩니다. 이때 더티체킹 자동 업데이트가 됩니다. db쪽으로 flush(commit)이 됨
    }

    @Transactional
    public void 댓글쓰기(ReplySaveRequestDto replySaveRequestDto) {
        User user = userRepository.findById(replySaveRequestDto.getUserId()).orElseThrow(() -> {
            return new IllegalArgumentException("유저 찾기 실패!!!");
        });
        Board board = boardRepository.findById(replySaveRequestDto.getBoardId()).orElseThrow(() -> {
            return new IllegalArgumentException("글 찾기 실패!!!");
        });//영속화 완료;
//        Reply reply = Reply.builder()
//                .user(user)
//                .board(board)
//                .content(replySaveRequestDto.getContent())
//                .build();
        Reply reply = new Reply();
        reply.update(user,board,replySaveRequestDto.getContent());
        replyRepository.save(reply);
    }
}
