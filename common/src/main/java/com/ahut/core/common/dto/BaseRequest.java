package com.ahut.core.common.dto;

import com.ahut.core.common.packet.Packet;
import com.ahut.core.common.utils.JsonUtils;

import java.io.Serializable;

/**
 * Created by c2292 on 2017/10/23.
 */
public class BaseRequest<T> implements Serializable{

    private static final long serialVersionUID = 2459200864538483025L;
    public static final String APPID = "appId";
    public static final String MERCHANTID = "merchantId";
    public static final String PARTNERID = "partnerId";
    public static final String TRANSCODE = "transCode";
    public static final String MSGTIME = "msgTime";
    public static final String NONCESTR = "nonceStr";
    public static final String PARTNERAPPID = "partnerAppId";
    public static final String OPENID = "openId";
    public static final String CHNLTYPE = "chnlType";
    public static final String TXIP = "txIp";
    public static final String AREACODE = "areaCode";
    public static final String DEVICENO = "deviceNo";
    public static final String SIGN = "sign";
    public static final String SIGNTYPE = "signType";
    public static final String BIZCONTENT = "bizContent";
    public static final String VERSION = "version";

    /*商户可以自行申请，给每一个商户生成一个唯一的应用ID*/
    private String appId;
    /*商户的唯一表示*/
    private String merchangId;
    /*兼容引起聚合平台的合作商编号*/
    private String partnerId;
    /*兼容银企聚合平台的合作商应用ID*/
    private String partnerAppId;
    /*交易码*/
    private String transCode;
    /*交易时间*/
    private String msgTime;
    /*随机字符串，主要保证签名不可预测*/
    private String nonceStr;
    /*微信用户端Id*/
    private String openId;
    /*渠道分类 web app*/
    private String chnlType;
    /*设备Ip*/
    private String txIp;
    /*地区代码*/
    private String areaCode;
    /*设备 编号*/
    private String deviceNo;
    /*签名*/
    private String sign;
    /*签名类型*/
    private String signType;
    /*业务内容*/
    private String bizContent;
    /*版本号*/
    private String version;

    public static String getPARTNERID() {
        return PARTNERID;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getMerchangId() {
        return merchangId;
    }

    public void setMerchangId(String merchangId) {
        this.merchangId = merchangId;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getPartnerAppId() {
        return partnerAppId;
    }

    public void setPartnerAppId(String partnerAppId) {
        this.partnerAppId = partnerAppId;
    }

    public String getTransCode() {
        return transCode;
    }

    public void setTransCode(String transCode) {
        this.transCode = transCode;
    }

    public String getMsgTime() {
        return msgTime;
    }

    public void setMsgTime(String msgTime) {
        this.msgTime = msgTime;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getChnlType() {
        return chnlType;
    }

    public void setChnlType(String chnlType) {
        this.chnlType = chnlType;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }

    public String getSign() {
        return sign;
    }

    public String getTxIp() {
        return txIp;
    }

    public void setTxIp(String txIp) {
        this.txIp = txIp;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getBizContent() {
        return bizContent;
    }

    public void setBizContent(String bizContent) {
        this.bizContent = bizContent;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
    public void setBizcontent(T bizcontent){
        String content = Packet.setResponse(bizcontent);
        this.bizContent = content;
    }

    public T getBizContent(Class<T> clazz){
        return JsonUtils.strToJson(this.bizContent,clazz);
    }


    @Override
    public String toString() {
        return "BaseRequest{" +
                "appId='" + appId + '\'' +
                ", merchangId='" + merchangId + '\'' +
                ", partnerId='" + partnerId + '\'' +
                ", partnerAppId='" + partnerAppId + '\'' +
                ", transCode='" + transCode + '\'' +
                ", msgTime='" + msgTime + '\'' +
                ", nonceStr='" + nonceStr + '\'' +
                ", openId='" + openId + '\'' +
                ", chnlType='" + chnlType + '\'' +
                ", areaCode='" + areaCode + '\'' +
                ", deviceNo='" + deviceNo + '\'' +
                ", sign='" + sign + '\'' +
                ", signType='" + signType + '\'' +
                ", bizContent='" + bizContent + '\'' +
                ", version='" + version + '\'' +
                '}';
    }
}

