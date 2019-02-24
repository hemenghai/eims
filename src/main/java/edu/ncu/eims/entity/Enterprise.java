package edu.ncu.eims.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * @author hemenghai
 * @date 2019-02-23
 */
@Data
@Entity
@Table(name = "enterprise")
public class Enterprise {

    public static final String ENTERPRISE_ID = "enterpriseId";
    public static final String ENTERPRISE_NAME = "enterpriseName";
    public static final String ENTERPRISE_NUMBER = "enterpriseNumber";
    public static final String ENTERPRISE_SCALE = "enterpriseScale";
    public static final String LEGAL_PERSON = "legalPerson";
    public static final String ESTABLISHMENT_TIME = "establishmentTime";
    public static final String CONTACT_NUMBER = "contactNumber";
    public static final String INDUSTRY_CATEGORY = "industryCategory";
    public static final String INDUSTRY_NAME = "industryName";
    public static final String INDUSTRY_CHAIN = "industryChain";
    public static final String MAIN_BUSINESS1 = "mainBusiness1";
    public static final String MAIN_BUSINESS2 = "mainBusiness2";
    /**
     * 代理主键
     */
    @Id
    @Column(name = "enterprise_id")
    private String enterpriseId;

    /**
     * 企业名称
     */
    @Column(name = "enterprise_name")
    private String enterpriseName;
    /**
     * 企业代码
     */
    @Column(name = "enterprise_number")
    private String enterpriseNumber;
    /**
     * 企业规模
     */
    @Column(name = "enterprise_scale")
    private String enterpriseScale;

    /**
     * 法人
     */
    @Column(name = "legal_person")
    private String legalPerson;
    /**
     * 成立时间
     */
    @Column(name = "establishment_time")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date establishmentTime;
    /**
     * 联系号码
     */
    @Column(name = "contact_number")
    private String contactNumber;

    /**
     * 行业类别
     */
    @Column(name = "industry_category")
    private String industryCategory;
    /**
     * 行业名称
     */
    @Column(name = "industry_name")
    private String industryName;
    /**
     * 所属产业链
     */
    @Column(name = "industry_chain")
    private String industryChain;

    /**
     * 主营业务1
     */
    @Column(name = "main_business1")
    private String mainBusiness1;
    /**
     * 主营业务2
     */
    @Column(name = "main_business2")
    private String mainBusiness2;


    /**
     * 创建时间
     */
    @Column(name = "create_time")
    @CreationTimestamp
    @JsonIgnore
    private Date createTime;
    /**
     * 更新时间
     */
    @Column(name = "update_time")
    @UpdateTimestamp
    @JsonIgnore
    private Date updateTime;
}
