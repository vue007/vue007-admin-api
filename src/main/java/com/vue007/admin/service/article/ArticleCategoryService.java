package com.vue007.admin.service.article;

import com.vue007.admin.mapper.article.ArticleCategoryMapper;
import com.vue007.admin.model.article.ArticleCategory;
import com.vue007.admin.util.CommUtil;
import com.vue007.admin.util.ContextUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 资讯分类 article_category 业务层
 *
 * @author Xiangyang
 * @date 2017年10月27日
 */
@Service
public class ArticleCategoryService {

    @Resource
    private ArticleCategoryMapper articleCategoryMapper;

    public List<ArticleCategory> findPage(ArticleCategory articleCategory) {
        return articleCategoryMapper.findPage(articleCategory);
    }

    public ArticleCategory getById(String id) {
        return articleCategoryMapper.getById(id);
    }

    public boolean create(ArticleCategory articleCategory) {
        Date nowDate = new Date();
        String user = ContextUtil.getSessionUsername();

        if(articleCategory.getParentId() == null){
            articleCategory.setParentId("");
        }
        articleCategory.setId(CommUtil.random());
        articleCategory.setCreateUser(user);
        articleCategory.setUpdateUser(user);
        articleCategory.setCreateTime(nowDate);
        articleCategory.setUpdateTime(nowDate);

        return articleCategoryMapper.create(articleCategory) > 0;
    }

    public boolean update(ArticleCategory articleCategory) throws Exception {
        if (null == articleCategory || StringUtils.isBlank(articleCategory.getId()) || null == this.getById(articleCategory.getId())) {
            throw new Exception("分类不存在！");
        }
        if(articleCategory.getParentId() == null){
            articleCategory.setParentId("");
        }

        articleCategory.setUpdateUser(ContextUtil.getSessionUsername());
        articleCategory.setUpdateTime(new Date());

        return articleCategoryMapper.update(articleCategory) > 0;
    }

    public boolean changeFlag(ArticleCategory articleCategory) throws Exception {
        if (articleCategory == null || StringUtils.isBlank(articleCategory.getId())) {
            throw new Exception("分类不存在！");
        }

        ArticleCategory entity = articleCategoryMapper.getById(articleCategory.getId());

        if (null == entity) {
            throw new Exception("分类不存在！");
        }

        entity.setId(articleCategory.getId());
        entity.setUpdateTime(new Date());
        entity.setUpdateUser(ContextUtil.getSessionUsername());
        entity.setFlag(articleCategory.getFlag());

        return articleCategoryMapper.update(articleCategory) > 0;
    }

    public boolean sort(List<ArticleCategory> sortList) {
        if ( null == sortList || 0 == sortList.size() ) {
            return false;
        }

        return articleCategoryMapper.sortBatch(sortList) > 0;
    }

    public List<ArticleCategory> findPages(ArticleCategory articleCategory) {
        return articleCategoryMapper.findPages(articleCategory);
    }
}
