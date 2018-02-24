package com.vue007.admin.service.article;

import com.vue007.admin.mapper.article.ArticleMapper;
import com.vue007.admin.model.article.Article;
import com.vue007.admin.util.CommUtil;
import com.vue007.admin.util.ContextUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 资讯业务逻辑层
 *
 * @author Xiangyang
 * @date 2017年10月27日
 */
@Service
public class ArticleService {

    @Resource
    private ArticleMapper articleMapper;

    public List<Article> findPage(Article article) {
        return articleMapper.findPage(article);
    }

    public Article getById(String articleId) {
        return articleMapper.getById(articleId);
    }

    public Article create(Article article) {
        Date now = new Date();
        String curUser = ContextUtil.getSessionUsername();

        article.setId( CommUtil.random() );
        article.setRelationId( CommUtil.random() );

        article.setCreateTime(now);
        article.setUpdateTime(now);
        article.setCreateUser(curUser);
        article.setUpdateUser(curUser);

        return (articleMapper.create(article) > 0) ? article : null;
    }

    public boolean update(Article article) throws Exception {
        if (article == null || StringUtils.isBlank(article.getId()) || null == this.getById(article.getId())) {
            throw new Exception("不存在此资讯！");
        }

        article.setUpdateUser(ContextUtil.getSessionUsername());
        article.setUpdateTime(new Date());
        article.setCreateTime(new Date());

        return articleMapper.update(article) > 0;
    }

    public Article recommend(Article article) {
        Date now = new Date();
        String curUser = ContextUtil.getSessionUsername();

        // 存在则删除 不存在则 新增推荐记录
        if ( null == article.getRecommendId() || "".equals( article.getRecommendId() ) ) {
            // 更新时间
            Article record = new Article();
            record.setId(article.getId());
            record.setUpdateTime(new Date());
            articleMapper.update(record);

            // 创建推荐
            article.setCreateTime(now);
            article.setCreateUser(curUser);
            article.setRecommendId( CommUtil.random() );
            return (articleMapper.createRecommend(article) > 0) ? article : null;
        } else {
            boolean flag = articleMapper.deleteRecommendById( article.getRecommendId() ) > 0;
            if (flag) article.setRecommendId(null);
            return (flag) ? article : null;
        }

    }

    public boolean changeStatus(Article article) throws Exception {
        if (article == null || StringUtils.isBlank(article.getId())) {
            throw new Exception("不存在此资讯！");
        }

        Article entity =  this.getById(article.getId());

        if (null == entity) {
            throw new Exception("不存在此资讯！");
        }

        entity.setUpdateTime(new Date());
        entity.setUpdateUser(ContextUtil.getSessionUsername());
        entity.setStatus(article.getStatus());

        return articleMapper.update(entity) > 0;
    }

    public boolean changeFlag(Article article) throws Exception {
        if (article == null || StringUtils.isBlank(article.getId())) {
            throw new Exception("不存在此资讯！");
        }

        Article entity =  this.getById(article.getId());

        if (null == entity) {
            throw new Exception("不存在此资讯！");
        }

        entity.setId(article.getId());
        entity.setUpdateTime(new Date());
        entity.setUpdateUser(ContextUtil.getSessionUsername());
        entity.setFlag(article.getFlag());

        return articleMapper.update(entity) > 0;
    }

}
