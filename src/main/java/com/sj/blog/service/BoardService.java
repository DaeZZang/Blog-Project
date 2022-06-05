package com.sj.blog.service;

import com.sj.blog.model.Board;
import com.sj.blog.model.RoleType;
import com.sj.blog.model.User;
import com.sj.blog.repository.BoardRepository;
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

    public Page<Board> 글목록(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }

    public Board 글상세보기(int id) {
        return boardRepository.findById(id)
                .orElseThrow(() -> {
                    return new IllegalArgumentException("글 상세보기 실패");
                });
    }
}
