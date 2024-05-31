package com.blogapp.demo.articles;

import com.blogapp.demo.articles.dtos.CreateArticleRequest;
import com.blogapp.demo.articles.dtos.UpdateArticleRequest;
import com.blogapp.demo.user.UserEntity;
import com.blogapp.demo.user.UserService;
import com.blogapp.demo.user.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticlesService {
    @Autowired
    ArticlesRepository articlesRepository;

    @Autowired
    UsersRepository usersRepository;

    public Iterable<ArticleEntity> getAllArticles()
    {
        return articlesRepository.findAll();
    }

    public ArticleEntity getArticleBySlug(String slug)
    {
        var article=articlesRepository.findBySlug(slug);
        if(article==null)
        {
            throw new ArticleNotFoundException(slug);
        }
        return article;
    }

    public ArticleEntity createArticle(CreateArticleRequest req, Long authorId)
    {
        var author=usersRepository.findById(authorId).orElseThrow(() -> new UserService.UserNotFoundException(authorId));
        var newArticle=ArticleEntity.builder()
                .title(req.getTitle())
                // TODO : create a proper slugification function
                .slug(req.getTitle().toLowerCase().replaceAll("\\s+","-"))
                .body(req.getBody())
                .subtitle(req.getSubtitle())
                .author(author)
                .build();
        return articlesRepository.save(newArticle);
    }

    public ArticleEntity updateArticle(Long articleId, UpdateArticleRequest req)
    {
        var article= articlesRepository.findById(articleId).orElseThrow(()-> new ArticleNotFoundException(articleId));
        if(req.getTitle()!=null)
        {
            article.setTitle(req.getTitle());
            article.setSlug(req.getTitle().toLowerCase().replaceAll("\\s+", "-"));
        }
        if(req.getBody()!=null)
        {
            article.setBody(req.getBody());
        }
        if(req.getSubtitle()!=null)
        {
            article.setTitle(req.getTitle());
        }
        return article;
    }

    static class ArticleNotFoundException extends IllegalArgumentException{
        public ArticleNotFoundException(String slug)
        {
            super("Article "+slug+" not found");
        }

        public ArticleNotFoundException(Long articleId)
        {
            super("Article with id: "+articleId+" not found");
        }
    }
}
