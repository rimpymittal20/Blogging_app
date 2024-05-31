package com.blogapp.demo.articles;


import com.blogapp.demo.user.UserEntity;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;

import lombok.*;

import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Entity(name = "articles")
@Data
@Getter
@Setter
@Builder
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ArticleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false)
    private Long id;
    @NonNull
    //@Max(100)
    private String title;
    @NonNull
    @Column(unique = true)
    private String slug;
    @Nullable
    private String subtitle;

    @NonNull
    private String body;
    @CreatedDate
    private Date createdAt;

    @ManyToOne
    @JoinColumn(name = "authorId", nullable = false)
    private UserEntity author;

    // TODO: add tags

}
