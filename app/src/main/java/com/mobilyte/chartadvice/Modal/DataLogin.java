package com.mobilyte.chartadvice.Modal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by root on 21/5/16.
 */
public class DataLogin {

    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("login")
    @Expose
    private String login;
    @SerializedName("pass")
    @Expose
    private String pass;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("name_f")
    @Expose
    private String nameF;
    @SerializedName("name_l")
    @Expose
    private String nameL;
    @SerializedName("street")
    @Expose
    private String street;
    @SerializedName("street2")
    @Expose
    private String street2;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("zip")
    @Expose
    private String zip;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("added")
    @Expose
    private String added;
    @SerializedName("remote_addr")
    @Expose
    private String remoteAddr;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("unsubscribed")
    @Expose
    private String unsubscribed;
    @SerializedName("lang")
    @Expose
    private String lang;
    @SerializedName("i_agree")
    @Expose
    private String iAgree;
    @SerializedName("is_approved")
    @Expose
    private String isApproved;
    @SerializedName("is_locked")
    @Expose
    private String isLocked;
    @SerializedName("reseller_id")
    @Expose
    private Object resellerId;
    @SerializedName("comment")
    @Expose
    private String comment;
    @SerializedName("tax_id")
    @Expose
    private Object taxId;
    @SerializedName("last_login")
    @Expose
    private String lastLogin;
    @SerializedName("last_ip")
    @Expose
    private String lastIp;
    @SerializedName("last_session")
    @Expose
    private String lastSession;
    @SerializedName("aff_id")
    @Expose
    private Object affId;
    @SerializedName("aff_added")
    @Expose
    private Object affAdded;
    @SerializedName("is_affiliate")
    @Expose
    private Object isAffiliate;
    @SerializedName("aff_payout_type")
    @Expose
    private Object affPayoutType;
    @SerializedName("bank_name")
    @Expose
    private String bankName;
    @SerializedName("branch")
    @Expose
    private String branch;
    @SerializedName("cheque_no")
    @Expose
    private String chequeNo;
    @SerializedName("date")
    @Expose
    private Object date;
    @SerializedName("looking")
    @Expose
    private Object looking;
    @SerializedName("information")
    @Expose
    private String information;
    @SerializedName("years")
    @Expose
    private Object years;
    @SerializedName("describe")
    @Expose
    private Object describe;
    @SerializedName("track")
    @Expose
    private Object track;
    @SerializedName("loser")
    @Expose
    private Object loser;
    @SerializedName("interlocutor")
    @Expose
    private Object interlocutor;

    @SerializedName("query_limit")
    @Expose
    private String queryLimit;

    public String getQueryLimit() {
        return queryLimit;
    }

    public void setQueryLimit(String queryLimit) {
        this.queryLimit = queryLimit;
    }

    /**
     *
     * @return
     * The userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     *
     * @param userId
     * The user_id
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     *
     * @return
     * The login
     */
    public String getLogin() {
        return login;
    }

    /**
     *
     * @param login
     * The login
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     *
     * @return
     * The pass
     */
    public String getPass() {
        return pass;
    }

    /**
     *
     * @param pass
     * The pass
     */
    public void setPass(String pass) {
        this.pass = pass;
    }

    /**
     *
     * @return
     * The email
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     * The email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return
     * The nameF
     */
    public String getNameF() {
        return nameF;
    }

    /**
     *
     * @param nameF
     * The name_f
     */
    public void setNameF(String nameF) {
        this.nameF = nameF;
    }

    /**
     *
     * @return
     * The nameL
     */
    public String getNameL() {
        return nameL;
    }

    /**
     *
     * @param nameL
     * The name_l
     */
    public void setNameL(String nameL) {
        this.nameL = nameL;
    }

    /**
     *
     * @return
     * The street
     */
    public String getStreet() {
        return street;
    }

    /**
     *
     * @param street
     * The street
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     *
     * @return
     * The street2
     */
    public String getStreet2() {
        return street2;
    }

    /**
     *
     * @param street2
     * The street2
     */
    public void setStreet2(String street2) {
        this.street2 = street2;
    }

    /**
     *
     * @return
     * The city
     */
    public String getCity() {
        return city;
    }

    /**
     *
     * @param city
     * The city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     *
     * @return
     * The state
     */
    public String getState() {
        return state;
    }

    /**
     *
     * @param state
     * The state
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     *
     * @return
     * The zip
     */
    public String getZip() {
        return zip;
    }

    /**
     *
     * @param zip
     * The zip
     */
    public void setZip(String zip) {
        this.zip = zip;
    }

    /**
     *
     * @return
     * The country
     */
    public String getCountry() {
        return country;
    }

    /**
     *
     * @param country
     * The country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     *
     * @return
     * The phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     *
     * @param phone
     * The phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     *
     * @return
     * The added
     */
    public String getAdded() {
        return added;
    }

    /**
     *
     * @param added
     * The added
     */
    public void setAdded(String added) {
        this.added = added;
    }

    /**
     *
     * @return
     * The remoteAddr
     */
    public String getRemoteAddr() {
        return remoteAddr;
    }

    /**
     *
     * @param remoteAddr
     * The remote_addr
     */
    public void setRemoteAddr(String remoteAddr) {
        this.remoteAddr = remoteAddr;
    }

    /**
     *
     * @return
     * The status
     */
    public String getStatus() {
        return status;
    }

    /**
     *
     * @param status
     * The status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     *
     * @return
     * The unsubscribed
     */
    public String getUnsubscribed() {
        return unsubscribed;
    }

    /**
     *
     * @param unsubscribed
     * The unsubscribed
     */
    public void setUnsubscribed(String unsubscribed) {
        this.unsubscribed = unsubscribed;
    }

    /**
     *
     * @return
     * The lang
     */
    public String getLang() {
        return lang;
    }

    /**
     *
     * @param lang
     * The lang
     */
    public void setLang(String lang) {
        this.lang = lang;
    }

    /**
     *
     * @return
     * The iAgree
     */
    public String getIAgree() {
        return iAgree;
    }

    /**
     *
     * @param iAgree
     * The i_agree
     */
    public void setIAgree(String iAgree) {
        this.iAgree = iAgree;
    }

    /**
     *
     * @return
     * The isApproved
     */
    public String getIsApproved() {
        return isApproved;
    }

    /**
     *
     * @param isApproved
     * The is_approved
     */
    public void setIsApproved(String isApproved) {
        this.isApproved = isApproved;
    }

    /**
     *
     * @return
     * The isLocked
     */
    public String getIsLocked() {
        return isLocked;
    }

    /**
     *
     * @param isLocked
     * The is_locked
     */
    public void setIsLocked(String isLocked) {
        this.isLocked = isLocked;
    }

    /**
     *
     * @return
     * The resellerId
     */
    public Object getResellerId() {
        return resellerId;
    }

    /**
     *
     * @param resellerId
     * The reseller_id
     */
    public void setResellerId(Object resellerId) {
        this.resellerId = resellerId;
    }

    /**
     *
     * @return
     * The comment
     */
    public String getComment() {
        return comment;
    }

    /**
     *
     * @param comment
     * The comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     *
     * @return
     * The taxId
     */
    public Object getTaxId() {
        return taxId;
    }

    /**
     *
     * @param taxId
     * The tax_id
     */
    public void setTaxId(Object taxId) {
        this.taxId = taxId;
    }

    /**
     *
     * @return
     * The lastLogin
     */
    public String getLastLogin() {
        return lastLogin;
    }

    /**
     *
     * @param lastLogin
     * The last_login
     */
    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }

    /**
     *
     * @return
     * The lastIp
     */
    public String getLastIp() {
        return lastIp;
    }

    /**
     *
     * @param lastIp
     * The last_ip
     */
    public void setLastIp(String lastIp) {
        this.lastIp = lastIp;
    }

    /**
     *
     * @return
     * The lastSession
     */
    public String getLastSession() {
        return lastSession;
    }

    /**
     *
     * @param lastSession
     * The last_session
     */
    public void setLastSession(String lastSession) {
        this.lastSession = lastSession;
    }

    /**
     *
     * @return
     * The affId
     */
    public Object getAffId() {
        return affId;
    }

    /**
     *
     * @param affId
     * The aff_id
     */
    public void setAffId(Object affId) {
        this.affId = affId;
    }

    /**
     *
     * @return
     * The affAdded
     */
    public Object getAffAdded() {
        return affAdded;
    }

    /**
     *
     * @param affAdded
     * The aff_added
     */
    public void setAffAdded(Object affAdded) {
        this.affAdded = affAdded;
    }

    /**
     *
     * @return
     * The isAffiliate
     */
    public Object getIsAffiliate() {
        return isAffiliate;
    }

    /**
     *
     * @param isAffiliate
     * The is_affiliate
     */
    public void setIsAffiliate(Object isAffiliate) {
        this.isAffiliate = isAffiliate;
    }

    /**
     *
     * @return
     * The affPayoutType
     */
    public Object getAffPayoutType() {
        return affPayoutType;
    }

    /**
     *
     * @param affPayoutType
     * The aff_payout_type
     */
    public void setAffPayoutType(Object affPayoutType) {
        this.affPayoutType = affPayoutType;
    }

    /**
     *
     * @return
     * The bankName
     */
    public String getBankName() {
        return bankName;
    }

    /**
     *
     * @param bankName
     * The bank_name
     */
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    /**
     *
     * @return
     * The branch
     */
    public String getBranch() {
        return branch;
    }

    /**
     *
     * @param branch
     * The branch
     */
    public void setBranch(String branch) {
        this.branch = branch;
    }

    /**
     *
     * @return
     * The chequeNo
     */
    public String getChequeNo() {
        return chequeNo;
    }

    /**
     *
     * @param chequeNo
     * The cheque_no
     */
    public void setChequeNo(String chequeNo) {
        this.chequeNo = chequeNo;
    }

    /**
     *
     * @return
     * The date
     */
    public Object getDate() {
        return date;
    }

    /**
     *
     * @param date
     * The date
     */
    public void setDate(Object date) {
        this.date = date;
    }

    /**
     *
     * @return
     * The looking
     */
    public Object getLooking() {
        return looking;
    }

    /**
     *
     * @param looking
     * The looking
     */
    public void setLooking(Object looking) {
        this.looking = looking;
    }

    /**
     *
     * @return
     * The information
     */
    public String getInformation() {
        return information;
    }

    /**
     *
     * @param information
     * The information
     */
    public void setInformation(String information) {
        this.information = information;
    }

    /**
     *
     * @return
     * The years
     */
    public Object getYears() {
        return years;
    }

    /**
     *
     * @param years
     * The years
     */
    public void setYears(Object years) {
        this.years = years;
    }

    /**
     *
     * @return
     * The describe
     */
    public Object getDescribe() {
        return describe;
    }

    /**
     *
     * @param describe
     * The describe
     */
    public void setDescribe(Object describe) {
        this.describe = describe;
    }

    /**
     *
     * @return
     * The track
     */
    public Object getTrack() {
        return track;
    }

    /**
     *
     * @param track
     * The track
     */
    public void setTrack(Object track) {
        this.track = track;
    }

    /**
     *
     * @return
     * The loser
     */
    public Object getLoser() {
        return loser;
    }

    /**
     *
     * @param loser
     * The loser
     */
    public void setLoser(Object loser) {
        this.loser = loser;
    }

    /**
     *
     * @return
     * The interlocutor
     */
    public Object getInterlocutor() {
        return interlocutor;
    }

    /**
     *
     * @param interlocutor
     * The interlocutor
     */
    public void setInterlocutor(Object interlocutor) {
        this.interlocutor = interlocutor;
    }

}
