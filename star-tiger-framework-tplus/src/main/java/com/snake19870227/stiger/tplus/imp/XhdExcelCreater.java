package com.snake19870227.stiger.tplus.imp;

import cn.hutool.core.util.ReflectUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Comment;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import com.snake19870227.stiger.tplus.imp.ExcelHeader;
import com.snake19870227.stiger.tplus.imp.ImpRecord;

/**
 * @author BuHuaYang
 * 7/25 025
 */
public class XhdExcelCreater {

    public static final String sheetName = "销货单";

    public static List<ExcelHeader> headers = new ArrayList<>();

    static {
        headers.add(new ExcelHeader("*客户编码", "Customer", "khbm"));
        headers.add(new ExcelHeader("客户", "CustomerName", "kh"));
        headers.add(new ExcelHeader("*单据日期", "VoucherDate", "djrq"));
        headers.add(new ExcelHeader("*单据编号", "Code", "djbh"));
        headers.add(new ExcelHeader("*业务类型编码", "BusinessType", "ywlxbm"));
        headers.add(new ExcelHeader("*票据类型", "InvoiceType", "pjlx"));
        headers.add(new ExcelHeader("发票介质", "SaleInvoiceMedia", "fpjz"));
        headers.add(new ExcelHeader("存货编码", "SaleDeliveryDetails_Inventory", "chbm"));
        headers.add(new ExcelHeader("存货名称", "SaleDeliveryDetails_InventoryName", "chmc"));
        headers.add(new ExcelHeader("*仓库编码", "SaleDeliveryDetails_Warehouse", "ckbm"));
        headers.add(new ExcelHeader("批号", "SaleDeliveryDetails_Batch", "ph"));
        headers.add(new ExcelHeader("*数量", "SaleDeliveryDetails_Quantity", "sl"));
        headers.add(new ExcelHeader("含税金额", "SaleDeliveryDetails_OrigTaxAmount", "hsje"));
    }

    public static void createExcel(List<ImpRecord> impRecords, File outputFile) {
        createExcel(impRecords, outputFile, headers);
    }

    public static void createExcel(List<ImpRecord> impRecords, File outputFile, List<ExcelHeader> headers) {
        ExcelWriter writer = ExcelUtil.getWriter(outputFile, sheetName);
        Sheet sheet = writer.getSheet();
        Drawing<?> p = sheet.createDrawingPatriarch();
        Row headRow = sheet.createRow(0);
        for (int i = 0; i < headers.size(); i++) {
            ExcelHeader excelHeader = headers.get(i);
            Cell cell = headRow.createCell(i);
            cell.setCellValue(excelHeader.getName());
            Comment comment = p.createCellComment(new XSSFClientAnchor(0, 0, 0, 0, (short) 3, 3, (short) 5, 6));
            comment.setString(new XSSFRichTextString(excelHeader.getComment()));
            comment.setAuthor("buhy");
            cell.setCellComment(comment);
        }
        for (int i = 1; i <= impRecords.size(); i++) {
            int index = i - 1;
            ImpRecord impRecord = impRecords.get(index);
            Row row = sheet.createRow(i);

            for (int j = 0; j < headers.size(); j++) {
                ExcelHeader header = headers.get(j);
                Object o = ReflectUtil.getFieldValue(impRecord, header.getImpRecordName());
                Cell cell = row.createCell(j);
                if (o != null) {
                    cell.setCellValue(o.toString());
                }
            }
        }
        writer.close();
    }
}
