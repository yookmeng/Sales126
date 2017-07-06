package com.SpringMVC.util;

import java.util.Map;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.springframework.web.servlet.view.document.AbstractExcelView;
 
@SuppressWarnings("deprecation")
public class ExcelBuilder extends AbstractExcelView {
 
    @Override
    protected void buildExcelDocument(Map<String, Object> model,
            HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
    	System.out.println("Generating Excel start...");

    	// get data model which is passed by the Spring container
//		List<String> listMonthlySummary = (List<String>) model.get("listMonthlySummary");
         
        // create a new Excel sheet
        HSSFSheet sheet = workbook.createSheet("Monthly Summary");
        sheet.setDefaultColumnWidth(30);
         
        // create style for header cells
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName("Arial");
        style.setFillForegroundColor(HSSFColor.BLUE.index);
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setColor(HSSFColor.WHITE.index);
        style.setFont(font);
         
        // create header row
        HSSFRow header1 = sheet.createRow(0);         
        header1.createCell(0).setCellValue("SC Name :");
        header1.getCell(0).setCellStyle(style);
         
        HSSFRow header2 = sheet.createRow(1);
        header2.createCell(1).setCellValue("Team Name :");
        header2.getCell(1).setCellStyle(style);         
        // create data rows
        // .....
    	System.out.println("Generating Excel end...");
    }
}