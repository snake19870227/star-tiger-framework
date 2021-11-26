package com.snake19870227.stiger.tplus.imp;

/**
 * @author BuHuaYang
 * 7/25 025
 */
public class ExcelHeader {

    private String name;

    private String comment;

    private String impRecordName;

    public ExcelHeader() {
    }

    public ExcelHeader(String name, String comment, String impRecordName) {
        this.name = name;
        this.comment = comment;
        this.impRecordName = impRecordName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getImpRecordName() {
        return impRecordName;
    }

    public void setImpRecordName(String impRecordName) {
        this.impRecordName = impRecordName;
    }
}
