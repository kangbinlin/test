package com._520it.crm.service.impl;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;

/**
 * EXCEL导入导出就不做了，网上工具类很多
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application.xml")
public class ExcelTest {
    @Test
    public void testOut() throws Exception {
        // 创建Excel对象
        WritableWorkbook wb = Workbook.createWorkbook(new File("out.xls"));
        // 创建sheet
        WritableSheet sheet = wb.createSheet("测试sheet", 0);
        // 设置列宽
        sheet.setColumnView(0, 30);
        // 设置行高
        sheet.setRowView(0, 500);
        // 创建单元格Lable（行，列，文本内容）
        Label cell = new Label(0, 0, "测试文本");
        sheet.addCell(cell);

        // 将内容写入Excel文件
        wb.write();
        // 关闭
        wb.close();
    }

    @Test
    public void testRead() throws Exception {
        // 读取文件
        Workbook workbook = Workbook.getWorkbook(new File("read.xls"));
        // 拿到sheet
        Sheet sheet = workbook.getSheet(0);
        // 计算sheet中的行数、列数
        int rows = sheet.getRows();
        int columns = sheet.getColumns();
        for (int i = 0; i < rows; i++) {
            for (int k = 0; k < columns; k++) {
                Cell cell = sheet.getCell(k, i);
                System.out.println(cell.getContents() + "\t");
            }
        }

        workbook.close();
    }

}
