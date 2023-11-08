package com.b2.supercoding_prj01.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "board")
public class BoardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_idx")
    private Long boardId;

    @ManyToOne
    @JoinColumn(name = "user_idx")
    private UserEntity user;

    @Column(name = "email")
    private String email;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "content")
    private String content;

}