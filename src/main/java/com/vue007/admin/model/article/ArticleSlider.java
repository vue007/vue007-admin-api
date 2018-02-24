package com.vue007.admin.model.article;

import com.vue007.admin.model.BaseEntity;

/**
 * article_slider 资讯伦比图表实体
 *
 * @author Xiangyang
 * @date 2017年10月23日
 */
public class ArticleSlider extends BaseEntity {

    //轮播图类型：文章
    public final static int TYPE_ARTICLE = 1;
    //轮播图类型：帖子
    public final static int TYPE_TOPIC = 2;

    private String url;
    private int type;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
