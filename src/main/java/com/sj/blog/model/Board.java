package com.sj.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 30)
    private String title;

    @Lob
    private String content;

    private int count;

    @ManyToOne(fetch = FetchType.EAGER)//Many board , one user
    @JoinColumn(name="userId")
    private User user;//DB는 오브젝트 저장X 자바는 가능

    @OneToMany(mappedBy = "board",fetch = FetchType.EAGER)//연관관계의 주인이 아니다.
    private List<Reply> reply;

    @CreationTimestamp
    private Timestamp createDate;
}
