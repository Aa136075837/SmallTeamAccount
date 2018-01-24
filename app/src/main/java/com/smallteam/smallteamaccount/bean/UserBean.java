package com.smallteam.smallteamaccount.bean;

import java.io.Serializable;

/**
 *
 * @author TENGFEI
 * @date 2018/1/9
 */
public class UserBean implements Serializable {

    /**
     * start : -1
     * limit : -1
     * orderByClause : null
     * paraMap : {}
     * id : bacfc8bb-1412-4463-8c86-36e437ae61af
     * loginName : null
     * loginPhone : 17620480466
     * loginQq : null
     * loginEmail : null
     * loginWechatOpenid : null
     * loginSina : null
     * loginPassword : null
     * createTime : 1515595697837
     * accountStatus : ENABLED
     * onlineStatus : null
     * loggedDevice : null
     * inviteCode : xeBDEEgMH
     * acceptInvitedCode : null
     * createMode : GENERAL_REGISTER
     * name : null
     * phone : 17620480466
     * qq : null
     * email : null
     * company : null
     * userLevel : null
     * groupId : null
     * gender : null
     * remark : null
     * latestLoginTime : null
     * areaCode : null
     * wechatId : null
     * headImage : null
     * totalCash : null
     * defaultGroupId : afd2d6a4-f079-437c-8723-056d1e8f5e0a
     */

    private int start;
    private int limit;
    private String orderByClause;
    private String id;
    private String loginName;
    private String loginPhone;
    private String loginQq;
    private String loginEmail;
    private String loginWechatOpenid;
    private String loginSina;
    private String loginPassword;
    private long createTime;
    private String accountStatus;
    private String onlineStatus;
    private String loggedDevice;
    private String inviteCode;
    private String acceptInvitedCode;
    private String createMode;
    private String name;
    private String phone;
    private String qq;
    private String email;
    private String company;
    private String userLevel;
    private String groupId;
    private String gender;
    private String remark;
    private String latestLoginTime;
    private String areaCode;
    private String wechatId;
    private String headImage;
    private String totalCash;
    private String defaultGroupId;

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

//    public String getParaMap() {
//        return paraMap;
//    }
//
//    public void setParaMap(String paraMap) {
//        this.paraMap = paraMap;
//    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginPhone() {
        return loginPhone;
    }

    public void setLoginPhone(String loginPhone) {
        this.loginPhone = loginPhone;
    }

    public String getLoginQq() {
        return loginQq;
    }

    public void setLoginQq(String loginQq) {
        this.loginQq = loginQq;
    }

    public String getLoginEmail() {
        return loginEmail;
    }

    public void setLoginEmail(String loginEmail) {
        this.loginEmail = loginEmail;
    }

    public String getLoginWechatOpenid() {
        return loginWechatOpenid;
    }

    public void setLoginWechatOpenid(String loginWechatOpenid) {
        this.loginWechatOpenid = loginWechatOpenid;
    }

    public String getLoginSina() {
        return loginSina;
    }

    public void setLoginSina(String loginSina) {
        this.loginSina = loginSina;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public String getOnlineStatus() {
        return onlineStatus;
    }

    public void setOnlineStatus(String onlineStatus) {
        this.onlineStatus = onlineStatus;
    }

    public String getLoggedDevice() {
        return loggedDevice;
    }

    public void setLoggedDevice(String loggedDevice) {
        this.loggedDevice = loggedDevice;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public String getAcceptInvitedCode() {
        return acceptInvitedCode;
    }

    public void setAcceptInvitedCode(String acceptInvitedCode) {
        this.acceptInvitedCode = acceptInvitedCode;
    }

    public String getCreateMode() {
        return createMode;
    }

    public void setCreateMode(String createMode) {
        this.createMode = createMode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(String userLevel) {
        this.userLevel = userLevel;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getLatestLoginTime() {
        return latestLoginTime;
    }

    public void setLatestLoginTime(String latestLoginTime) {
        this.latestLoginTime = latestLoginTime;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getWechatId() {
        return wechatId;
    }

    public void setWechatId(String wechatId) {
        this.wechatId = wechatId;
    }

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    public String getTotalCash() {
        return totalCash;
    }

    public void setTotalCash(String totalCash) {
        this.totalCash = totalCash;
    }

    public String getDefaultGroupId() {
        return defaultGroupId;
    }

    public void setDefaultGroupId(String defaultGroupId) {
        this.defaultGroupId = defaultGroupId;
    }


}
