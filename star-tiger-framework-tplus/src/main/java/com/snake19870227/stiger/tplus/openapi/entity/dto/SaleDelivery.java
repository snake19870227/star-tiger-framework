package com.snake19870227.stiger.tplus.openapi.entity.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SaleDelivery {

    @JsonProperty("InvoiceType")
    private InvoiceType invoiceType;

    @JsonProperty("BusinessType")
    private BusinessType businessType;

    @JsonProperty("Customer")
    private Customer customer;

    @JsonProperty("ExternalCode")
    private String externalCode;

    @JsonProperty("VoucherDate")
    private String voucherDate;

    @JsonProperty("SaleDeliveryDetails")
    private List<SaleDeliveryDetail> saleDeliveryDetails;

    public SaleDelivery() {
        this.saleDeliveryDetails = new ArrayList<>();
    }

    public InvoiceType getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(InvoiceType invoiceType) {
        this.invoiceType = invoiceType;
    }

    public BusinessType getBusinessType() {
        return businessType;
    }

    public void setBusinessType(BusinessType businessType) {
        this.businessType = businessType;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

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

    public List<SaleDeliveryDetail> getSaleDeliveryDetails() {
        return saleDeliveryDetails;
    }

    public void setSaleDeliveryDetails(List<SaleDeliveryDetail> saleDeliveryDetails) {
        this.saleDeliveryDetails = saleDeliveryDetails;
    }
}
