package com.smallteam.smallteamaccount.bean;

import java.io.Serializable;

/**
 * Created by TENGFEI on 2018/1/14.
 */
public class GroupBean implements Serializable {


    /**
     * id : 353c7fc8-7a40-4d15-abc6-56d97246232e
     * name : 默认账本
     * creater : 589dafbf-2470-4a7a-815d-86aadf722e30
     * createTime : 1515998169000
     * description : null
     * type : DEFAULT
     * icon : null
     */

    private String id;
    private String name;
    private String creater;
    private long createTime;
    private String description;
    private String type;
    private String icon;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public Object getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
