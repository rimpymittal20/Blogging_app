package com.blogapp.demo.comments;

import com.blogapp.demo.articles.ArticleEntity;
import com.blogapp.demo.user.UserEntity;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Entity(name = "comments")
@Data
@Getter
@Setter
@Builder
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false)
    private Long id;

    @Nullable
    private String title;

    @NonNull
    private String body;

    @CreatedDate
    private Date createdAt;

    @ManyToOne
    @JoinColumn(name = "articleId",nullable = false)
    private ArticleEntity article;

    @ManyToOne
    @JoinColumn(name = "authorId",nullable = false)
    private UserEntity author;

}
