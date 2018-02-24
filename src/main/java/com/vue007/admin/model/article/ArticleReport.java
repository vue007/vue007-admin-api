package com.vue007.admin.model.article;

import com.vue007.admin.model.BaseEntity;

/**
 * article_report 资讯举报表实体
 *
 * @author Xiangyang
 * @date 2017年10月23日
 */
public class ArticleReport extends BaseEntity {

    private String userId;
    private String articleId;
    private String content;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
