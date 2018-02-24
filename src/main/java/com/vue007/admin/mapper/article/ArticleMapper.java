package com.vue007.admin.mapper.article;

import com.vue007.admin.model.article.Article;

import java.util.List;

/**
 * 资讯 mapper
 *
 * @author Xiangyang
 * @date 2017年10月24日
 */
public interface ArticleMapper {

    List<Article> findPage(Article article);

    Article getById(String articleId);

    int create(Article article);

    int update(Article article);

    int deleteById(String articleId);

    int deleteRecommendById(String recommendId);

    int createRecommend(Article article);
}
