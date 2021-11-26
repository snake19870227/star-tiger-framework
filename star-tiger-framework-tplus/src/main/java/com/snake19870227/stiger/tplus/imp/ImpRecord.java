package com.snake19870227.stiger.tplus.imp;

import java.math.BigDecimal;

/**
 * @author BuHuaYang
 * 7/24 024
 */
public class ImpRecord {

    // 客户编码
    private String khbm;
    // 客户
    private String kh;
    // 单据日期
    private String djrq;
    // 单据编号
    private String djbh;
    // 业务类型编码
    private String ywlxbm;
    // 票据类型
    private String pjlx;
    // 发票介质
    private String fpjz;
    // 存货编码
    private String chbm;
    // 存货名称
    private String chmc;
    // 仓库编码
    private String ckbm;
    // 批号
    private String ph;
    // 数量
    private Long sl;
    // 含税金额
    private BigDecimal hsje;

    public String getKhbm() {
        return khbm;
    }

    public void setKhbm(String khbm) {
        this.khbm = khbm;
    }

    public String getKh() {
        return kh;
    }

    public void setKh(String kh) {
        this.kh = kh;
    }

    public String getDjrq() {
        return djrq;
    }

    public void setDjrq(String djrq) {
        this.djrq = djrq;
    }

    public String getDjbh() {
        return djbh;
    }

    public void setDjbh(String djbh) {
        this.djbh = djbh;
    }

    public String getYwlxbm() {
        return ywlxbm;
    }

    public void setYwlxbm(String ywlxbm) {
        this.ywlxbm = ywlxbm;
    }

    public String getPjlx() {
        return pjlx;
    }

    public void setPjlx(String pjlx) {
        this.pjlx = pjlx;
    }

    public String getFpjz() {
        return fpjz;
    }

    public void setFpjz(String fpjz) {
        this.fpjz = fpjz;
    }

    public String getChbm() {
        return chbm;
    }

    public void setChbm(String chbm) {
        this.chbm = chbm;
    }

    public String getChmc() {
        return chmc;
    }

    public void setChmc(String chmc) {
        this.chmc = chmc;
    }

    public String getCkbm() {
        return ckbm;
    }

    public void setCkbm(String ckbm) {
        this.ckbm = ckbm;
    }

    public String getPh() {
        return ph;
    }

    public void setPh(String ph) {
        this.ph = ph;
    }

    public Long getSl() {
        return sl;
    }

    public void setSl(Long sl) {
        this.sl = sl;
    }

    public BigDecimal getHsje() {
        return hsje;
    }

    public void setHsje(BigDecimal hsje) {
        this.hsje = hsje;
    }
}
