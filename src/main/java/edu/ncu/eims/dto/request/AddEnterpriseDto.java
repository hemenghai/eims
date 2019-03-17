package edu.ncu.eims.dto.request;

import edu.ncu.eims.entity.Enterprise;
import lombok.Data;

/**
 * @author hemenghai
 * @date 2019-03-17
 */
@Data
public class AddEnterpriseDto {

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
    private String establishmentTime;
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

    public Enterprise toEntity(){
        Enterprise enterprise = new Enterprise();
        enterprise.setEnterpriseName(this.enterpriseName);
        enterprise.setEnterpriseNumber(this.enterpriseNumber);
        enterprise.setEnterpriseScale(this.enterpriseScale);
        enterprise.setLegalPerson(this.legalPerson);
        enterprise.setEstablishmentTime(this.establishmentTime);
        enterprise.setContactNumber(this.contactNumber);
        enterprise.setIndustryCategory(this.industryCategory);
        enterprise.setIndustryName(this.industryName);
        enterprise.setIndustryChain(this.industryChain);
        enterprise.setMainBusiness1(this.mainBusiness1);
        enterprise.setMainBusiness2(this.mainBusiness2);
        return enterprise;
    }
}
