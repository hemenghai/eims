package edu.ncu.eims.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author hemenghai
 * @date 2019-02-23
 */
@Data
public class Enterprise {

    /**
     * 代理主键
     */
    private String enterpriseId;

    /**
     * 企业名称
     */
    private String enterpriseName;
    /**
     * 企业代码
     */
    private String enterpriseNumber;
    /**
     * 企业规模
     */
    private String enterpriseScale;

    /**
     * 法人
     */
    private String legalPerson;
    /**
     * 成立时间
     */
    private Date establishmentTime;
    /**
     * 联系号码
     */
    private String contactNumber;

    /**
     * 行业类别
     */
    private String industryCategory;
    /**
     * 行业名称
     */
    private String industryName;
    /**
     * 所属产业链
     */
    private String industryChain;

    /**
     * 主营业务1
     */
    private String mainBusiness1;
    /**
     * 主营业务2
     */
    private String mainBusiness2;


    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 更新时间
     */
    private String updateTime;
}
