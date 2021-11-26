package com.snake19870227.stiger.tplus.openapi.entity.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExpenseVoucherDetail {

    @JsonProperty("expenseitem")
    private Expenseitem expenseitem;

    @JsonProperty("TaxRate")
    private BigDecimal taxRate;

    @JsonProperty("OrigAmount")
    private BigDecimal origAmount;

    @JsonProperty("OrigTax")
    private BigDecimal origTax;

    @JsonProperty("OrigTaxAmount")
    private BigDecimal origTaxAmount;

    public Expenseitem getExpenseitem() {
        return expenseitem;
    }

    public void setExpenseitem(Expenseitem expenseitem) {
        this.expenseitem = expenseitem;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public BigDecimal getOrigAmount() {
        return origAmount;
    }

    public void setOrigAmount(BigDecimal origAmount) {
        this.origAmount = origAmount;
    }

    public BigDecimal getOrigTax() {
        return origTax;
    }

    public void setOrigTax(BigDecimal origTax) {
        this.origTax = origTax;
    }

    public BigDecimal getOrigTaxAmount() {
        return origTaxAmount;
    }

    public void setOrigTaxAmount(BigDecimal origTaxAmount) {
        this.origTaxAmount = origTaxAmount;
    }
}
