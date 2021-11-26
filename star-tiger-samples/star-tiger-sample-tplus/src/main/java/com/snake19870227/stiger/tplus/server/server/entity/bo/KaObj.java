package com.snake19870227.stiger.tplus.server.server.entity.bo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.snake19870227.stiger.tplus.imp.ImpRecord;

/**
 * @author BuHuaYang
 * 7/24 024
 */
public class KaObj {

    private String jdrq;

    private String djbh;

    private String lx;

    private String lxCode;

    private String cl;

    private String clCode;

    private String xt;

    private String xtCode;

    private List<KaItem> items;

    public List<ImpRecord> toImpRecords() {
        List<ImpRecord> impRecords = new ArrayList<>();
        for (KaItem item : items) {
            ImpRecord impRecord = new ImpRecord();
            impRecord.setKhbm(xtCode);
            impRecord.setKh(xt);
            impRecord.setDjrq(jdrq);
            impRecord.setDjbh(djbh);
            impRecord.setYwlxbm(lxCode);
            impRecord.setPjlx("普通发票");
            impRecord.setFpjz("纸票");
            impRecord.setChbm(item.getCpdm());
            impRecord.setChmc(item.getCpmc());
            impRecord.setCkbm(clCode);
//                impRecord.setPh();
            impRecord.setSl(item.getSl());
            impRecord.setHsje(BigDecimal.valueOf(item.getJe()));
            impRecords.add(impRecord);
        }
        return impRecords;
    }

    public KaObj() {
        this.items = new ArrayList<>();
    }

    public String getJdrq() {
        return jdrq;
    }

    public void setJdrq(String jdrq) {
        this.jdrq = jdrq;
    }

    public String getDjbh() {
        return djbh;
    }

    public void setDjbh(String djbh) {
        this.djbh = djbh;
    }

    public String getLx() {
        return lx;
    }

    public void setLx(String lx) {
        this.lx = lx;
    }

    public String getCl() {
        return cl;
    }

    public void setCl(String cl) {
        this.cl = cl;
    }

    public String getXt() {
        return xt;
    }

    public void setXt(String xt) {
        this.xt = xt;
    }

    public List<KaItem> getItems() {
        return items;
    }

    public void setItems(List<KaItem> items) {
        this.items = items;
    }

    public String getClCode() {
        return clCode;
    }

    public void setClCode(String clCode) {
        this.clCode = clCode;
    }

    public String getXtCode() {
        return xtCode;
    }

    public void setXtCode(String xtCode) {
        this.xtCode = xtCode;
    }

    public String getLxCode() {
        return lxCode;
    }

    public void setLxCode(String lxCode) {
        this.lxCode = lxCode;
    }
}
