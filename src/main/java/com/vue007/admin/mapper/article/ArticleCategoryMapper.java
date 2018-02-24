package com.vue007.admin.mapper.article;

import com.vue007.admin.model.article.ArticleCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 资讯分类 article_category mapper
 *
 * @author Xiangyang
 * @date 2017年10月24日
 */
public interface ArticleCategoryMapper {

    List<ArticleCategory> findPage(ArticleCategory articleCategory);

    List<ArticleCategory> findByArticleId(String articleId);

    ArticleCategory getById(String id);

    int create(ArticleCategory articleCategory);

    int update(ArticleCategory articleCategory);

    int sortBatch(@Param("sortList") List<ArticleCategory> sortList);

    int deleteById(String id);

    List<ArticleCategory> findPages(ArticleCategory articleCategory);

}
