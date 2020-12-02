package com.example.readwriteseparationv1.domain;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

@Entity
@Table(name = "s_user")
@Data
public class DUser implements Serializable {
//    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    BigInteger id;
    /**
     * 登录帐号
     */

    String loginName;
    /**
     * 登录密码（32位md5加密）
     */
    String loginPass;
    /**
     * 用户类型（1-商户；2-代理商；3-商户员工；4-营运人员；5-维护人员；6-顾客）
     */
    Integer userType;
//    /**
//     * 邀请码（此字段已停用）
//     */
//    String inviteCode
    /**
     * 用户绑定的手机号
     */
    String bindMobile;
    /**
     * 用户绑定的微信号OpenID
     */
    String bindWechat;
    /**
     * 用户绑定的qq号
     */
    String bindQq;
    /**
     * 用户绑定的邮箱
     */
    String bindEmail;
    /**
     * 用户状态（0-未激活；1-启用；2-停用）未激活的帐号为无效帐号，3分钟后可重复注册
     */
    Integer state;
    /**
     * 姓名
     */
    String name;
    /**
     * 登录次数
     */
    Integer loginCount;
    /**
     * 上次登录时间
     */
    Date lastLoginTime;
    /**
     * 上次登录IP
     */
    String lastLoginIp;
    /**
     * 备注
     */
    String remark;


    Date createAt;
    String createBy;
    Date lastUpdateAt;
    String lastUpdateBy;
    String welType="0";
    boolean isDeleted;
}
