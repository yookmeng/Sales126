package com.SpringMVC.daoimpl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.SpringMVC.dao.CommonDAO;
import com.SpringMVC.dao.UserLoginDAO;
import com.SpringMVC.model.ExcelDetail;
import com.SpringMVC.model.UserLogin;
import com.SpringMVC.model.UserMonthlySummary;

@Repository 
public class CommonDAOImpl implements CommonDAO { 

	@Autowired
    private UserLoginDAO userLoginDAO;

    public List<String> periodList() {
        int currentyear = Calendar.getInstance().get(Calendar.YEAR);
        int currentmonth = Calendar.getInstance().get(Calendar.MONTH)+1;
        List<String> periods = new ArrayList<String>();
        for(int m=currentmonth-3;m<=currentmonth+3;m++){
        	switch(m){
        		case -2:
	        	case -1:
	        	case 0:{
	        		periods.add(String.valueOf(currentyear-1)+"-"+String.valueOf(m+12));        		
	        		break;
	        	}
	        	case 1:
	        	case 2:
	        	case 3:
	        	case 4:
	        	case 5:
	        	case 6:
	        	case 7:
	        	case 8:
	        	case 9:
	        	case 10:
	        	case 11:
	        	case 12:{
	        		periods.add(String.valueOf(currentyear)+"-"+String.valueOf(m));        			        		
	        		break;
	        	}
	        	case 13:
	        	case 14:
	        	case 15:{
	        		periods.add(String.valueOf(currentyear+1)+"-"+String.valueOf(m-12));        			        		
	        		break;
	        	}	        		
        	}
        }
        return periods;
    }

    public void sendEmail(HttpServletRequest request, List<ExcelDetail> prospect, 
    		List<UserMonthlySummary> summary, int userid, String period) throws IOException{
    	UserLogin userLogin = userLoginDAO.findUser(userid);
    	XSSFWorkbook workbook = new XSSFWorkbook();
    	
    	// create style for header cells
        CellStyle titleStyle = workbook.createCellStyle();
        Font titleFont = workbook.createFont();
        titleFont.setFontName("Arial");
        titleFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        titleFont.setUnderline(HSSFFont.U_SINGLE);
        titleStyle.setFont(titleFont);

        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(HSSFColor.GREY_50_PERCENT.index);
        headerStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        headerStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        headerStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        headerStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        headerStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        Font headerFont = workbook.createFont();
        headerFont.setFontName("Arial");
        headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        headerFont.setColor(HSSFColor.WHITE.index);
        headerStyle.setFont(headerFont);

        CellStyle headerStyleCenter = workbook.createCellStyle();
        headerStyleCenter.setFillForegroundColor(HSSFColor.GREY_50_PERCENT.index);
        headerStyleCenter.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        headerStyleCenter.setFillPattern(CellStyle.SOLID_FOREGROUND);
        headerStyleCenter.setBorderTop(HSSFCellStyle.BORDER_THIN);
        headerStyleCenter.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        headerStyleCenter.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        headerStyleCenter.setBorderRight(HSSFCellStyle.BORDER_THIN);
        headerStyleCenter.setFont(headerFont);

        CellStyle detailStyle = workbook.createCellStyle();
        detailStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        detailStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        detailStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        detailStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);

        CellStyle detailStyleCenter = workbook.createCellStyle();
        detailStyleCenter.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        detailStyleCenter.setBorderTop(HSSFCellStyle.BORDER_THIN);
        detailStyleCenter.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        detailStyleCenter.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        detailStyleCenter.setBorderRight(HSSFCellStyle.BORDER_THIN);

    	int rownum = 0;
    	if (!userLogin.getrole().equals("USER")){
        	XSSFSheet sheetSummary = workbook.createSheet("Summary Report");    		
        	Row row0 = sheetSummary.createRow(rownum);
        	row0.createCell(0).setCellValue("Summary Report As "+period);
        	row0.getCell(0).setCellStyle(titleStyle); 
        	
        	//loop summary
        	UserMonthlySummary curr_summary = new UserMonthlySummary();
        	if (userLogin.getrole().equals("MD")){
        		String companyname = "";
        		int companytargetprospect = 0;
        		int companytargettestdrive = 0;
        		int companytargetclosed = 0;
        		int companyactualprospect = 0;
        		int companyactualtestdrive = 0;
        		int companyactualclosed = 0;
        		float companypercentprospect = 0;
        		float companypercenttestdrive = 0;
        		float companypercentclosed = 0;
            	for (int i=0;i<summary.size(); i++){
            		curr_summary = summary.get(i);
            		companyname = curr_summary.getcompanyname();
            		companytargetprospect = companytargetprospect + curr_summary.gettargetprospect();
            		companytargettestdrive = companytargettestdrive + curr_summary.gettargettestdrive();
            		companytargetclosed = companytargetclosed + curr_summary.gettargetclosed();
            		companyactualprospect = companyactualprospect + curr_summary.getactualprospect();
            		companyactualtestdrive = companyactualtestdrive + curr_summary.getactualtestdrive();
            		companyactualclosed = companyactualclosed + curr_summary.getactualclosed();
            	}; 
            	companypercentprospect = (float)(Math.round((double)companyactualprospect/(double)companytargetprospect*10000))/100;
            	companypercenttestdrive = (float)(Math.round((double)companyactualtestdrive/(double)companytargettestdrive*10000))/100;
            	companypercentclosed = (float)(Math.round((double)companyactualclosed/(double)companytargetclosed*10000))/100;
            	rownum = rownum + 2;
    	    	Row rowcompany1 = sheetSummary.createRow(rownum);
    	    	rowcompany1.createCell(0).setCellValue("Company Name");
    	    	rowcompany1.getCell(0).setCellStyle(headerStyleCenter);
    	    	rowcompany1.createCell(1).setCellValue("Prospect");
    	    	rowcompany1.getCell(1).setCellStyle(headerStyleCenter);
    	    	rowcompany1.createCell(4).setCellValue("Test Drive");
    	    	rowcompany1.getCell(4).setCellStyle(headerStyleCenter);
    	    	rowcompany1.createCell(7).setCellValue("Closed");
    	    	rowcompany1.getCell(7).setCellStyle(headerStyleCenter);
    	    	sheetSummary.addMergedRegion(new CellRangeAddress(rownum, rownum, 1, 3));
    	    	sheetSummary.addMergedRegion(new CellRangeAddress(rownum, rownum, 4, 6));
    	    	sheetSummary.addMergedRegion(new CellRangeAddress(rownum, rownum, 7, 9));
            	rownum = rownum + 1;
    	    	Row rowcompany2 = sheetSummary.createRow(rownum);
    	    	sheetSummary.addMergedRegion(new CellRangeAddress(rownum-1, rownum, 0, 0));
    	    	rowcompany2.createCell(1).setCellValue("Actual");
    	    	rowcompany2.getCell(1).setCellStyle(headerStyleCenter);
    	    	rowcompany2.createCell(2).setCellValue("Target");
    	    	rowcompany2.getCell(2).setCellStyle(headerStyleCenter);
    	    	rowcompany2.createCell(3).setCellValue("%");
    	    	rowcompany2.getCell(3).setCellStyle(headerStyleCenter);
    	    	rowcompany2.createCell(4).setCellValue("Actual");
    	    	rowcompany2.getCell(4).setCellStyle(headerStyleCenter);
    	    	rowcompany2.createCell(5).setCellValue("Target");
    	    	rowcompany2.getCell(5).setCellStyle(headerStyleCenter);
    	    	rowcompany2.createCell(6).setCellValue("%");
    	    	rowcompany2.getCell(6).setCellStyle(headerStyleCenter);
    	    	rowcompany2.createCell(7).setCellValue("Actual");
    	    	rowcompany2.getCell(7).setCellStyle(headerStyleCenter);
    	    	rowcompany2.createCell(8).setCellValue("Target");
    	    	rowcompany2.getCell(8).setCellStyle(headerStyleCenter);
    	    	rowcompany2.createCell(9).setCellValue("%");
    	    	rowcompany2.getCell(9).setCellStyle(headerStyleCenter);
            	rownum = rownum + 1;
    	    	Row rowcompanydetail = sheetSummary.createRow(rownum);
    	    	rowcompanydetail.createCell(0).setCellValue(companyname);
    	    	rowcompanydetail.getCell(0).setCellStyle(detailStyleCenter);
    	    	rowcompanydetail.createCell(1).setCellValue(companyactualprospect);
    	    	rowcompanydetail.getCell(1).setCellStyle(detailStyleCenter);            	
    	    	rowcompanydetail.createCell(2).setCellValue(companytargetprospect);
    	    	rowcompanydetail.getCell(2).setCellStyle(detailStyleCenter);
    	    	rowcompanydetail.createCell(3).setCellValue(companypercentprospect);
    	    	rowcompanydetail.getCell(3).setCellStyle(detailStyleCenter);            	
    	    	rowcompanydetail.createCell(4).setCellValue(companyactualtestdrive);
    	    	rowcompanydetail.getCell(4).setCellStyle(detailStyleCenter);            	
    	    	rowcompanydetail.createCell(5).setCellValue(companytargettestdrive);
    	    	rowcompanydetail.getCell(5).setCellStyle(detailStyleCenter);            	
    	    	rowcompanydetail.createCell(6).setCellValue(companypercenttestdrive);
    	    	rowcompanydetail.getCell(6).setCellStyle(detailStyleCenter);            	
    	    	rowcompanydetail.createCell(7).setCellValue(companyactualclosed);
    	    	rowcompanydetail.getCell(7).setCellStyle(detailStyleCenter);            	
    	    	rowcompanydetail.createCell(8).setCellValue(companytargetclosed);
    	    	rowcompanydetail.getCell(8).setCellStyle(detailStyleCenter);            	
    	    	rowcompanydetail.createCell(9).setCellValue(companypercentclosed);
    	    	rowcompanydetail.getCell(9).setCellStyle(detailStyleCenter);            	
        	};
        	if (userLogin.getrole().equals("MD") || userLogin.getrole().equals("MA")){
        		int branchtargetprospect = 0;
        		int branchtargettestdrive = 0;
        		int branchtargetclosed = 0;
        		int branchactualprospect = 0;
        		int branchactualtestdrive = 0;
        		int branchactualclosed = 0;
        		float branchpercentprospect = 0;
        		float branchpercenttestdrive = 0;
        		float branchpercentclosed = 0;
        		int branchid = 0;
        		String branchname = "";
        		// write header
            	rownum = rownum + 2;
    	    	Row rowbranch1 = sheetSummary.createRow(rownum);
    	    	rowbranch1.createCell(0).setCellValue("Branch Name");
    	    	rowbranch1.getCell(0).setCellStyle(headerStyleCenter);
    	    	rowbranch1.createCell(1).setCellValue("Prospect");
    	    	rowbranch1.getCell(1).setCellStyle(headerStyleCenter);
    	    	rowbranch1.createCell(4).setCellValue("Test Drive");
    	    	rowbranch1.getCell(4).setCellStyle(headerStyleCenter);
    	    	rowbranch1.createCell(7).setCellValue("Closed");
    	    	rowbranch1.getCell(7).setCellStyle(headerStyleCenter);
    	    	sheetSummary.addMergedRegion(new CellRangeAddress(rownum, rownum, 1, 3));
    	    	sheetSummary.addMergedRegion(new CellRangeAddress(rownum, rownum, 4, 6));
    	    	sheetSummary.addMergedRegion(new CellRangeAddress(rownum, rownum, 7, 9));
            	rownum = rownum + 1;
    	    	Row rowbranch2 = sheetSummary.createRow(rownum);
    	    	sheetSummary.addMergedRegion(new CellRangeAddress(rownum-1, rownum, 0, 0));
    	    	rowbranch2.createCell(1).setCellValue("Actual");
    	    	rowbranch2.getCell(1).setCellStyle(headerStyleCenter);
    	    	rowbranch2.createCell(2).setCellValue("Target");
    	    	rowbranch2.getCell(2).setCellStyle(headerStyleCenter);
    	    	rowbranch2.createCell(3).setCellValue("%");
    	    	rowbranch2.getCell(3).setCellStyle(headerStyleCenter);
    	    	rowbranch2.createCell(4).setCellValue("Actual");
    	    	rowbranch2.getCell(4).setCellStyle(headerStyleCenter);
    	    	rowbranch2.createCell(5).setCellValue("Target");
    	    	rowbranch2.getCell(5).setCellStyle(headerStyleCenter);
    	    	rowbranch2.createCell(6).setCellValue("%");
    	    	rowbranch2.getCell(6).setCellStyle(headerStyleCenter);
    	    	rowbranch2.createCell(7).setCellValue("Actual");
    	    	rowbranch2.getCell(7).setCellStyle(headerStyleCenter);
    	    	rowbranch2.createCell(8).setCellValue("Target");
    	    	rowbranch2.getCell(8).setCellStyle(headerStyleCenter);        		
    	    	rowbranch2.createCell(9).setCellValue("%");
    	    	rowbranch2.getCell(9).setCellStyle(headerStyleCenter);
            	for (int i=0;i<summary.size(); i++){
            		curr_summary = summary.get(i);
            		if (curr_summary.getbranchid()!=branchid){
            			if (branchid!=0){
            				// write excel
                        	branchpercentprospect = (float)(Math.round((double)branchactualprospect/(double)branchtargetprospect*10000))/100;
                        	branchpercenttestdrive = (float)(Math.round((double)branchactualtestdrive/(double)branchtargettestdrive*10000))/100;
                        	branchpercentclosed = (float)(Math.round((double)branchactualclosed/(double)branchtargetclosed*10000))/100;
                        	rownum = rownum + 1;
                	    	Row rowbranchdetail = sheetSummary.createRow(rownum);
                	    	rowbranchdetail.createCell(0).setCellValue(branchname);
                	    	rowbranchdetail.getCell(0).setCellStyle(detailStyleCenter);
                	    	rowbranchdetail.createCell(1).setCellValue(branchactualprospect);
                	    	rowbranchdetail.getCell(1).setCellStyle(detailStyleCenter);            	
                	    	rowbranchdetail.createCell(2).setCellValue(branchtargetprospect);
                	    	rowbranchdetail.getCell(2).setCellStyle(detailStyleCenter);
                	    	rowbranchdetail.createCell(3).setCellValue(branchpercentprospect);
                	    	rowbranchdetail.getCell(3).setCellStyle(detailStyleCenter);            	
                	    	rowbranchdetail.createCell(4).setCellValue(branchactualtestdrive);
                	    	rowbranchdetail.getCell(4).setCellStyle(detailStyleCenter);            	
                	    	rowbranchdetail.createCell(5).setCellValue(branchtargettestdrive);
                	    	rowbranchdetail.getCell(5).setCellStyle(detailStyleCenter);            	
                	    	rowbranchdetail.createCell(6).setCellValue(branchpercenttestdrive);
                	    	rowbranchdetail.getCell(6).setCellStyle(detailStyleCenter);            	
                	    	rowbranchdetail.createCell(7).setCellValue(branchactualclosed);
                	    	rowbranchdetail.getCell(7).setCellStyle(detailStyleCenter);            	
                	    	rowbranchdetail.createCell(8).setCellValue(branchtargetclosed);
                	    	rowbranchdetail.getCell(8).setCellStyle(detailStyleCenter);            	
                	    	rowbranchdetail.createCell(9).setCellValue(branchpercentclosed);
                	    	rowbranchdetail.getCell(9).setCellStyle(detailStyleCenter);            	

                    		branchtargetprospect = 0;
                    		branchtargettestdrive = 0;
                    		branchtargetclosed = 0;
                    		branchactualprospect = 0;
                    		branchactualtestdrive = 0;
                    		branchactualclosed = 0;
                    		branchpercentprospect = 0;
                    		branchpercenttestdrive = 0;
                    		branchpercentclosed = 0;                    		
            			}
        				branchid = curr_summary.getbranchid();
        				branchname = curr_summary.getbranchname();
            		};
            		branchtargetprospect = branchtargetprospect + curr_summary.gettargetprospect();
            		branchtargettestdrive = branchtargettestdrive + curr_summary.gettargettestdrive();
            		branchtargetclosed = branchtargetclosed + curr_summary.gettargetclosed();
            		branchactualprospect = branchactualprospect + curr_summary.getactualprospect();
            		branchactualtestdrive = branchactualtestdrive + curr_summary.getactualtestdrive();
            		branchactualclosed = branchactualclosed + curr_summary.getactualclosed();
        		};
        		if (branchid!=0){
    				// write last record
                	branchpercentprospect = (float)(Math.round((double)branchactualprospect/(double)branchtargetprospect*10000))/100;
                	branchpercenttestdrive = (float)(Math.round((double)branchactualtestdrive/(double)branchtargettestdrive*10000))/100;
                	branchpercentclosed = (float)(Math.round((double)branchactualclosed/(double)branchtargetclosed*10000))/100;
                	rownum = rownum + 1;
        	    	Row rowbranchdetail = sheetSummary.createRow(rownum);
        	    	rowbranchdetail.createCell(0).setCellValue(branchname);
        	    	rowbranchdetail.getCell(0).setCellStyle(detailStyleCenter);
        	    	rowbranchdetail.createCell(1).setCellValue(branchactualprospect);
        	    	rowbranchdetail.getCell(1).setCellStyle(detailStyleCenter);            	
        	    	rowbranchdetail.createCell(2).setCellValue(branchtargetprospect);
        	    	rowbranchdetail.getCell(2).setCellStyle(detailStyleCenter);
        	    	rowbranchdetail.createCell(3).setCellValue(branchpercentprospect);
        	    	rowbranchdetail.getCell(3).setCellStyle(detailStyleCenter);            	
        	    	rowbranchdetail.createCell(4).setCellValue(branchactualtestdrive);
        	    	rowbranchdetail.getCell(4).setCellStyle(detailStyleCenter);            	
        	    	rowbranchdetail.createCell(5).setCellValue(branchtargettestdrive);
        	    	rowbranchdetail.getCell(5).setCellStyle(detailStyleCenter);            	
        	    	rowbranchdetail.createCell(6).setCellValue(branchpercenttestdrive);
        	    	rowbranchdetail.getCell(6).setCellStyle(detailStyleCenter);            	
        	    	rowbranchdetail.createCell(7).setCellValue(branchactualclosed);
        	    	rowbranchdetail.getCell(7).setCellStyle(detailStyleCenter);            	
        	    	rowbranchdetail.createCell(8).setCellValue(branchtargetclosed);
        	    	rowbranchdetail.getCell(8).setCellStyle(detailStyleCenter);            	
        	    	rowbranchdetail.createCell(9).setCellValue(branchpercentclosed);
        	    	rowbranchdetail.getCell(9).setCellStyle(detailStyleCenter);            	
        		};
        	};
    		int teamtargetprospect = 0;
    		int teamtargettestdrive = 0;
    		int teamtargetclosed = 0;
    		int teamactualprospect = 0;
    		int teamactualtestdrive = 0;
    		int teamactualclosed = 0;
    		float teampercentprospect = 0;
    		float teampercenttestdrive = 0;
    		float teampercentclosed = 0;
    		int teamid = 0;
    		String teamname = "";
    		// write header
        	rownum = rownum + 2;
	    	Row rowteam1 = sheetSummary.createRow(rownum);
	    	rowteam1.createCell(0).setCellValue("Team Name");
	    	rowteam1.getCell(0).setCellStyle(headerStyleCenter);
	    	rowteam1.createCell(1).setCellValue("Prospect");
	    	rowteam1.getCell(1).setCellStyle(headerStyleCenter);
	    	rowteam1.createCell(4).setCellValue("Test Drive");
	    	rowteam1.getCell(4).setCellStyle(headerStyleCenter);
	    	rowteam1.createCell(7).setCellValue("Closed");
	    	rowteam1.getCell(7).setCellStyle(headerStyleCenter);
	    	sheetSummary.addMergedRegion(new CellRangeAddress(rownum, rownum, 1, 3));
	    	sheetSummary.addMergedRegion(new CellRangeAddress(rownum, rownum, 4, 6));
	    	sheetSummary.addMergedRegion(new CellRangeAddress(rownum, rownum, 7, 9));
        	rownum = rownum + 1;
	    	Row rowteam2 = sheetSummary.createRow(rownum);
	    	sheetSummary.addMergedRegion(new CellRangeAddress(rownum-1, rownum, 0, 0));
	    	rowteam2.createCell(1).setCellValue("Actual");
	    	rowteam2.getCell(1).setCellStyle(headerStyleCenter);
	    	rowteam2.createCell(2).setCellValue("Target");
	    	rowteam2.getCell(2).setCellStyle(headerStyleCenter);
	    	rowteam2.createCell(3).setCellValue("%");
	    	rowteam2.getCell(3).setCellStyle(headerStyleCenter);
	    	rowteam2.createCell(4).setCellValue("Actual");
	    	rowteam2.getCell(4).setCellStyle(headerStyleCenter);
	    	rowteam2.createCell(5).setCellValue("Target");
	    	rowteam2.getCell(5).setCellStyle(headerStyleCenter);
	    	rowteam2.createCell(6).setCellValue("%");
	    	rowteam2.getCell(6).setCellStyle(headerStyleCenter);
	    	rowteam2.createCell(7).setCellValue("Actual");
	    	rowteam2.getCell(7).setCellStyle(headerStyleCenter);
	    	rowteam2.createCell(8).setCellValue("Target");
	    	rowteam2.getCell(8).setCellStyle(headerStyleCenter);        		
	    	rowteam2.createCell(9).setCellValue("%");
	    	rowteam2.getCell(9).setCellStyle(headerStyleCenter);
        	for (int i=0;i<summary.size(); i++){
        		curr_summary = summary.get(i);
        		if (curr_summary.getteamid()!=teamid){
        			if (teamid!=0){
        				// write excel
                    	teampercentprospect = (float)(Math.round((double)teamactualprospect/(double)teamtargetprospect*10000))/100;
                    	teampercenttestdrive = (float)(Math.round((double)teamactualtestdrive/(double)teamtargettestdrive*10000))/100;
                    	teampercentclosed = (float)(Math.round((double)teamactualclosed/(double)teamtargetclosed*10000))/100;
                    	rownum = rownum + 1;
            	    	Row rowteamdetail = sheetSummary.createRow(rownum);
            	    	rowteamdetail.createCell(0).setCellValue(teamname);
            	    	rowteamdetail.getCell(0).setCellStyle(detailStyleCenter);
            	    	rowteamdetail.createCell(1).setCellValue(teamactualprospect);
            	    	rowteamdetail.getCell(1).setCellStyle(detailStyleCenter);            	
            	    	rowteamdetail.createCell(2).setCellValue(teamtargetprospect);
            	    	rowteamdetail.getCell(2).setCellStyle(detailStyleCenter);
            	    	rowteamdetail.createCell(3).setCellValue(teampercentprospect);
            	    	rowteamdetail.getCell(3).setCellStyle(detailStyleCenter);            	
            	    	rowteamdetail.createCell(4).setCellValue(teamactualtestdrive);
            	    	rowteamdetail.getCell(4).setCellStyle(detailStyleCenter);            	
            	    	rowteamdetail.createCell(5).setCellValue(teamtargettestdrive);
            	    	rowteamdetail.getCell(5).setCellStyle(detailStyleCenter);            	
            	    	rowteamdetail.createCell(6).setCellValue(teampercenttestdrive);
            	    	rowteamdetail.getCell(6).setCellStyle(detailStyleCenter);            	
            	    	rowteamdetail.createCell(7).setCellValue(teamactualclosed);
            	    	rowteamdetail.getCell(7).setCellStyle(detailStyleCenter);            	
            	    	rowteamdetail.createCell(8).setCellValue(teamtargetclosed);
            	    	rowteamdetail.getCell(8).setCellStyle(detailStyleCenter);            	
            	    	rowteamdetail.createCell(9).setCellValue(teampercentclosed);
            	    	rowteamdetail.getCell(9).setCellStyle(detailStyleCenter);            	

                		teamtargetprospect = 0;
                		teamtargettestdrive = 0;
                		teamtargetclosed = 0;
                		teamactualprospect = 0;
                		teamactualtestdrive = 0;
                		teamactualclosed = 0;
                		teampercentprospect = 0;
                		teampercenttestdrive = 0;
                		teampercentclosed = 0;                    		
        			}
    				teamid = curr_summary.getteamid();
    				teamname = curr_summary.getteamname();
        		};
        		teamtargetprospect = teamtargetprospect + curr_summary.gettargetprospect();
        		teamtargettestdrive = teamtargettestdrive + curr_summary.gettargettestdrive();
        		teamtargetclosed = teamtargetclosed + curr_summary.gettargetclosed();
        		teamactualprospect = teamactualprospect + curr_summary.getactualprospect();
        		teamactualtestdrive = teamactualtestdrive + curr_summary.getactualtestdrive();
        		teamactualclosed = teamactualclosed + curr_summary.getactualclosed();
    		};
    		if (teamid!=0){
				// write last record
            	teampercentprospect = (float)(Math.round((double)teamactualprospect/(double)teamtargetprospect*10000))/100;
            	teampercenttestdrive = (float)(Math.round((double)teamactualtestdrive/(double)teamtargettestdrive*10000))/100;
            	teampercentclosed = (float)(Math.round((double)teamactualclosed/(double)teamtargetclosed*10000))/100;
            	rownum = rownum + 1;
    	    	Row rowbranchdetail = sheetSummary.createRow(rownum);
    	    	rowbranchdetail.createCell(0).setCellValue(teamname);
    	    	rowbranchdetail.getCell(0).setCellStyle(detailStyleCenter);
    	    	rowbranchdetail.createCell(1).setCellValue(teamactualprospect);
    	    	rowbranchdetail.getCell(1).setCellStyle(detailStyleCenter);            	
    	    	rowbranchdetail.createCell(2).setCellValue(teamtargetprospect);
    	    	rowbranchdetail.getCell(2).setCellStyle(detailStyleCenter);
    	    	rowbranchdetail.createCell(3).setCellValue(teampercentprospect);
    	    	rowbranchdetail.getCell(3).setCellStyle(detailStyleCenter);            	
    	    	rowbranchdetail.createCell(4).setCellValue(teamactualtestdrive);
    	    	rowbranchdetail.getCell(4).setCellStyle(detailStyleCenter);            	
    	    	rowbranchdetail.createCell(5).setCellValue(teamtargettestdrive);
    	    	rowbranchdetail.getCell(5).setCellStyle(detailStyleCenter);            	
    	    	rowbranchdetail.createCell(6).setCellValue(teampercenttestdrive);
    	    	rowbranchdetail.getCell(6).setCellStyle(detailStyleCenter);            	
    	    	rowbranchdetail.createCell(7).setCellValue(teamactualclosed);
    	    	rowbranchdetail.getCell(7).setCellStyle(detailStyleCenter);            	
    	    	rowbranchdetail.createCell(8).setCellValue(teamtargetclosed);
    	    	rowbranchdetail.getCell(8).setCellStyle(detailStyleCenter);            	
    	    	rowbranchdetail.createCell(9).setCellValue(teampercentclosed);
    	    	rowbranchdetail.getCell(9).setCellStyle(detailStyleCenter);            	
    		};        	
    		for (int i=0; i<4; i++){
    			sheetSummary.autoSizeColumn(i);
    		};
    	};
    	
    	rownum = 0;
    	XSSFSheet sheetProspects = workbook.createSheet("Prospects");
    	// set header    	
    	Row row0 = sheetProspects.createRow(rownum);
    	row0.createCell(0).setCellValue("Prospects As "+period);
    	row0.getCell(0).setCellStyle(titleStyle);
    	// loop ExcelDetail
    	int curr_branchid = 0;
    	int curr_teamid = 0;
    	int curr_userid = 0;
    	boolean setHeader = false;
    	int line = 0;
    	ExcelDetail curr_prospect = new ExcelDetail();
    	for (int i=0;i<prospect.size(); i++){
    		curr_prospect = prospect.get(i);
    		if (curr_prospect.getbranchid()!=curr_branchid){
    			if (rownum<5){
    				rownum = rownum + 1;
    			} else {
    				rownum = rownum + 3;    				
    			}
    	    	Row rowbranch = sheetProspects.createRow(rownum);
    	    	rowbranch.createCell(0).setCellValue("Branch Name :");
    	    	rowbranch.getCell(0).setCellStyle(headerStyle);
    	    	rowbranch.createCell(1).setCellValue(curr_prospect.getbranchname());
    			curr_branchid = curr_prospect.getbranchid();
    		};
    		if (curr_prospect.getteamid()!=curr_teamid){
				rownum = rownum + 1;
    	    	Row rowteam = sheetProspects.createRow(rownum);
    	    	rowteam.createCell(0).setCellValue("Team Name :");
    	    	rowteam.getCell(0).setCellStyle(headerStyle);
    	    	rowteam.createCell(1).setCellValue(curr_prospect.getteamname());
    			curr_teamid = curr_prospect.getteamid();
    		};
    		if (curr_prospect.getuserid()!=curr_userid){
				rownum = rownum + 1;
    	    	Row row1 = sheetProspects.createRow(rownum);
    	    	row1.createCell(0).setCellValue("SC Name :");
    	    	row1.getCell(0).setCellStyle(headerStyle);
    	    	row1.createCell(1).setCellValue(curr_prospect.getusername());
    			curr_userid = curr_prospect.getuserid();
    			line = 0;
    	    	setHeader = true;
    		}
    		if (setHeader){
    	    	rownum = rownum + 2;
    	    	Row headerRow = sheetProspects.createRow(rownum);
    	    	headerRow.createCell(0).setCellValue("No");
    	    	headerRow.getCell(0).setCellStyle(headerStyleCenter);
    	    	headerRow.createCell(1).setCellValue("Prospect Name");
    	    	headerRow.getCell(1).setCellStyle(headerStyle);
    	    	headerRow.createCell(2).setCellValue("Tel");
    	    	headerRow.getCell(2).setCellStyle(headerStyle);
    	    	headerRow.createCell(3).setCellValue("Email");
    	    	headerRow.getCell(3).setCellStyle(headerStyle);
    	    	headerRow.createCell(4).setCellValue("Model Interest");
    	    	headerRow.getCell(4).setCellStyle(headerStyle);
    	    	headerRow.createCell(5).setCellValue("Presentation");
    	    	headerRow.getCell(5).setCellStyle(headerStyle);
    	    	headerRow.createCell(6).setCellValue("Test Drive");
    	    	headerRow.getCell(6).setCellStyle(headerStyle);
    	    	headerRow.createCell(7).setCellValue("Quotation");
    	    	headerRow.getCell(7).setCellStyle(headerStyle);
    	    	headerRow.createCell(8).setCellValue("100/75/50/25");
    	    	headerRow.getCell(8).setCellStyle(headerStyle);
    	    	headerRow.createCell(9).setCellValue("Closed?");
    	    	headerRow.getCell(9).setCellStyle(headerStyle);
    	    	headerRow.createCell(10).setCellValue("Lost");
    	    	headerRow.getCell(10).setCellStyle(headerStyle);
    	    	headerRow.createCell(11).setCellValue("Diary");
    	    	headerRow.getCell(11).setCellStyle(headerStyle);
    	    	headerRow.createCell(12).setCellValue("Remark");
    	    	headerRow.getCell(12).setCellStyle(headerStyle);
    			setHeader = false;
    		};
	    	rownum = rownum + 1;
	    	line = line + 1;
	    	Row detailRow = sheetProspects.createRow(rownum);
	    	detailRow.createCell(0).setCellValue(line);
	    	detailRow.getCell(0).setCellStyle(detailStyleCenter);
	    	detailRow.createCell(1).setCellValue(curr_prospect.getfirstname());
	    	detailRow.getCell(1).setCellStyle(detailStyle);
	    	detailRow.createCell(2).setCellValue(curr_prospect.getmobile());
	    	detailRow.getCell(2).setCellStyle(detailStyle);
	    	detailRow.createCell(3).setCellValue(curr_prospect.getemail());
	    	detailRow.getCell(3).setCellStyle(detailStyle);
	    	detailRow.createCell(4).setCellValue(curr_prospect.getmodelname());
	    	detailRow.getCell(4).setCellStyle(detailStyle);
	    	detailRow.createCell(5).setCellValue(curr_prospect.getdemo());
	    	detailRow.getCell(5).setCellStyle(detailStyle);
	    	detailRow.createCell(6).setCellValue(curr_prospect.gettestdrive());
	    	detailRow.getCell(6).setCellStyle(detailStyle);
	    	detailRow.createCell(7).setCellValue(curr_prospect.getquotation());
	    	detailRow.getCell(7).setCellStyle(detailStyle);
	    	detailRow.createCell(8).setCellValue(curr_prospect.getstatusname());
	    	detailRow.getCell(8).setCellStyle(detailStyle);
	    	detailRow.createCell(9).setCellValue(curr_prospect.getclosed());
	    	detailRow.getCell(9).setCellStyle(detailStyle);
	    	detailRow.createCell(10).setCellValue(curr_prospect.getlost());
	    	detailRow.getCell(10).setCellStyle(detailStyle);
	    	detailRow.createCell(11).setCellValue(curr_prospect.getdiary());
	    	detailRow.getCell(11).setCellStyle(detailStyle);
	    	detailRow.createCell(12).setCellValue(curr_prospect.getremark());
	    	detailRow.getCell(12).setCellStyle(detailStyle);
		};

		for (int i=0; i<13; i++){
			sheetProspects.autoSizeColumn(i);
		}
		
		String filename = "SummaryReport"+userid+".xlsx";
    	String excelPath = "/excel";
    	String absoluteExcelPath = request.getSession().getServletContext().getRealPath(excelPath);
    	File Excelfile = new File(absoluteExcelPath, filename);
    	if (!Excelfile.exists()) {
    		Excelfile.createNewFile();
		}
    	
		FileOutputStream fop = new FileOutputStream(Excelfile);
		workbook.write(fop);
		fop.close();
		workbook.close();

    	System.out.println("Excel generated...");
    	// send email
        final String subject = "Summary Report";
        final String msg = "Summary Report "+period;

        // send email
        String to = userLogin.getemail();
        String from = "help.salesdiarypro@gmail.com";

        final String username = "help.salesdiarypro@gmail.com";
        final String password = "mamapapa@msp";
        String host = "smtp.gmail.com";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.debug", "true");

        Session session = Session.getInstance(props, new GMailAuthenticator(username, password));
        
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);

            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(msg);
            
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);

            // attachment
            messageBodyPart = new MimeBodyPart();
            javax.activation.DataSource source = new FileDataSource(Excelfile);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(filename);
            multipart.addBodyPart(messageBodyPart);

            // Send the complete message parts
            message.setContent(multipart);

            // Send message
            Transport.send(message);

            System.out.println("Sent message successfully....");
     
	     } catch (MessagingException e) {
	        throw new RuntimeException(e);
	     }
    }
}