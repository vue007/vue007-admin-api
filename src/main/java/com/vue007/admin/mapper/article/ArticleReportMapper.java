package com.vue007.admin.mapper.article;

import com.vue007.admin.model.article.Article;
import com.vue007.admin.model.article.ArticleReport;

import java.util.List;

/**
 * article_report 文章举报表 mapper
 * @author Akai
 * @date 2018-01-12 15:43:59
 */
public interface ArticleReportMapper {

    List<Article> findPage(ArticleReport articleReport);

    Article getById(String id);

    int create(ArticleReport articleReport);

    int update(ArticleReport articleReport);

    int deleteById(String id);

}
