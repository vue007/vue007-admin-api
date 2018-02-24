package com.vue007.admin.model.article;

import com.vue007.admin.model.BaseEntity;

/**
 * article_recommend 资讯推荐表实体
 *
 * @author Xiangyang
 * @date 2017年10月23日
 */
public class ArticleRecommend extends BaseEntity {

    private String articleId;

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }
}
