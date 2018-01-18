package com.smallteam.smallteamaccount.bean;

import java.io.Serializable;

/**
 * 账本bean
 * Created by TENGFEI on 2018/1/18.
 */
public class NewAccountBean implements Serializable {


    /**
     * id : d4fbcde9-94b2-4eba-ae21-98b103b68173
     * name : 驴儿乖
     * creater : 589dafbf-2470-4a7a-815d-86aadf722e30
     * createTime : 1516159098173
     * description : 第一个账本
     * type : NORMAL
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

    public String getDescription() {
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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
