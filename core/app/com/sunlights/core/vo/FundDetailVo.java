package com.sunlights.core.vo;

import com.sunlights.common.service.CommonService;
import com.sunlights.common.utils.ArithUtil;
import com.sunlights.common.utils.CommonUtil;
import models.Fund;
import models.FundCompany;
import models.ProductManage;
import models.ProductRecommend;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>Project: fsp</p>
 * <p>Title: FundDetailVo.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */
public class FundDetailVo extends FundVo {
    //取现到帐
    private String toAccountType;
    //风险
    private String riskLevel;
    //基金公司
    private String companyName;
    // 基金规模
    private String fundScale;
    // 30天购买人数
    private Long buiersOf30Days;
    //最新：2014-10-26
    private String currentDate;


    public FundDetailVo() {
        super();
    }

    public FundDetailVo(Fund fund, ProductRecommend pr, ProductManage pm, FundCompany fundCompany) {
        super(fund, pr, pm);
        inFundDetail(fund, fundCompany);
    }

    public void inFundDetail(Fund fund, FundCompany fundCompany) {
        //取现到帐
        this.toAccountType = new CommonService().findValueByCatPointKey(fund.getToAccountType());
        //风险
        this.riskLevel = new CommonService().findValueByCatPointKey(fund.getRiskLevel());
        //基金公司
        this.companyName = fundCompany.getCompanyName();
        // 基金规模
        BigDecimal scale = fund.getFundScale();

        this.fundScale = scale == null ? null : ArithUtil.bigToScale2(scale.divide(new BigDecimal("100000000"))) + "亿";
        //最新：2014-10-26
        this.currentDate = CommonUtil.dateToString(new Date(), CommonUtil.DATE_FORMAT_LONG);
        this.buiersOf30Days = fund.getOneMonthBuyedCount();
    }

    public String getToAccountType() {
        return toAccountType;
    }

    public void setToAccountType(String toAccountType) {
        this.toAccountType = toAccountType;
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getFundScale() {
        return fundScale;
    }

    public void setFundScale(String fundScale) {
        this.fundScale = fundScale;
    }

    public Long getBuiersOf30Days() {
        return buiersOf30Days;
    }

    public void setBuiersOf30Days(Long buiersOf30Days) {
        this.buiersOf30Days = buiersOf30Days;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }
}
