package com.vue007.admin.model.banner;

import com.vue007.admin.model.BaseEntity;

import java.util.Date;

public class Slider extends BaseEntity {

    private String sliderId;
    private String sliderTitle;
    private String posterLittle;
    private String poster;
    private String sliderUrl;
    private Byte sliderType;
    private Integer sort;
    private String createUser;
    private Date createTime;
    private String updateUser;
    private Date updateTime;

    public String getSliderId() {
        return sliderId;
    }

    public void setSliderId(String sliderId) {
        this.sliderId = sliderId;
    }

    public String getSliderTitle() {
        return sliderTitle;
    }

    public void setSliderTitle(String sliderTitle) {
        this.sliderTitle = sliderTitle;
    }

    public String getPosterLittle() {
        return posterLittle;
    }

    public void setPosterLittle(String posterLittle) {
        this.posterLittle = posterLittle;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getSliderUrl() {
        return sliderUrl;
    }

    public void setSliderUrl(String sliderUrl) {
        this.sliderUrl = sliderUrl;
    }

    public Byte getSliderType() {
        return sliderType;
    }

    public void setSliderType(Byte sliderType) {
        this.sliderType = sliderType;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}