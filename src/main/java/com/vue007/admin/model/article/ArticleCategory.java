package com.vue007.admin.model.article;

import com.vue007.admin.model.BaseEntity;

import java.util.Date;

/**
 * article_category 资讯分类表实体
 * @author Xiangyang
 * @date 2017年10月23日
 */
public class ArticleCategory extends BaseEntity {

    private String name;
    private Integer sort;

    private Integer categoryType; // 分类类型:1-资讯分类 2-视频分类 3-壁纸分类
    private String parentId;  // 分类父ID(一级分类该字段为空)

    private Date latestArticleUpdateTime;
    private long articleCount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(Integer categoryType) {
        this.categoryType = categoryType;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Date getLatestArticleUpdateTime() {
        return latestArticleUpdateTime;
    }

    public void setLatestArticleUpdateTime(Date latestArticleUpdateTime) {
        this.latestArticleUpdateTime = latestArticleUpdateTime;
    }

    public long getArticleCount() {
        return articleCount;
    }

    public void setArticleCount(long articleCount) {
        this.articleCount = articleCount;
    }
}
