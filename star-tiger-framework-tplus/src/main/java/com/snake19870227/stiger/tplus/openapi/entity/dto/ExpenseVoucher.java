package com.snake19870227.stiger.tplus.openapi.entity.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExpenseVoucher {

    @JsonProperty("ExternalCode")
    private String externalCode;

    @JsonProperty("VoucherDate")
    private String voucherDate;

    @JsonProperty("BusinessType")
    private BusinessType businessType;

    @JsonProperty("billType")
    private BillType billType;

    @JsonProperty("Partner")
    private PartnerCode partner;

    @JsonProperty("Details")
    private List<ExpenseVoucherDetail> details;

    public String getExternalCode() {
        return externalCode;
    }

    public void setExternalCode(String externalCode) {
        this.externalCode = externalCode;
    }

    public String getVoucherDate() {
        return voucherDate;
    }

    public void setVoucherDate(String voucherDate) {
        this.voucherDate = voucherDate;
    }

    public BusinessType getBusinessType() {
        return businessType;
    }

    public void setBusinessType(BusinessType businessType) {
        this.businessType = businessType;
    }

    public BillType getBillType() {
        return billType;
    }

    public void setBillType(BillType billType) {
        this.billType = billType;
    }

    public PartnerCode getPartner() {
        return partner;
    }

    public void setPartner(PartnerCode partner) {
        this.partner = partner;
    }

    public List<ExpenseVoucherDetail> getDetails() {
        return details;
    }

    public void setDetails(List<ExpenseVoucherDetail> details) {
        this.details = details;
    }
}
