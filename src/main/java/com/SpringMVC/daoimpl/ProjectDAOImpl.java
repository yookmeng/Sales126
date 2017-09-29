	package com.SpringMVC.daoimpl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import com.SpringMVC.model.Address;
import com.SpringMVC.model.Project;
import com.SpringMVC.util.WatermarkedCell;
import com.SpringMVC.model.Order;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.SpringMVC.dao.ProjectDAO;
import com.SpringMVC.mapper.ProjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
@Repository 
public class ProjectDAOImpl extends JdbcDaoSupport implements ProjectDAO { 
    @Autowired
    public ProjectDAOImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }
	
    public void save(Project project) {
    	Address address = project.getaddress();
        // insert
        String sql = "INSERT INTO tblProject (projectname, address.country, address.zipcode, "
        		+ "address.state, address.city, address.street, userid, "
        		+ "name, mobile, email, titleid, "
        		+ "propertyid, units, smsflag, "
        		+ "datecreated, forecastperiod, hwdiscount, swdiscount, status) "
        		+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        this.getJdbcTemplate().update(sql, 
        		project.getprojectname(), address.getcountry(), address.getzipcode(), 
        		address.getstate(), address.getcity(), address.getstreet(), project.getuserid(), 
        		project.getname(), project.getmobile(), project.getemail(), project.gettitleid(), 
        		project.getpropertyid(), project.getunits(), project.getsmsflag(),
        		project.getdatecreated(), project.getforecastperiod(), 
        		project.gethwdiscount(), project.getswdiscount(), project.getstatus());
    }
    
    public void update(Project project) {
    	Address address = project.getaddress();
        // update
        String sql = "UPDATE tblProject SET projectname=?, address.country=?, "
        		+ "address.zipcode=?, address.state=?, address.city=?, address.street=?, "
        		+ "name=?, mobile=?, email=?, titleid=?, propertyid=?, "
        		+ "units=?, forecastperiod=?, hwdiscount=?, swdiscount=?, "
        		+ "status=? "
        		+ "WHERE projectid=?";
        this.getJdbcTemplate().update(sql, 
        		project.getprojectname(), address.getcountry(), address.getzipcode(), 
        		address.getstate(), address.getcity(), address.getstreet(), 
        		project.getname(), project.getmobile(), project.getemail(), 
        		project.gettitleid(), project.getpropertyid(), project.getunits(), 
        		project.getforecastperiod(), project.gethwdiscount(), project.getswdiscount(), 
        		project.getstatus(), project.getprojectid());
    }

    public void delete(int projectid) {
        String sql = "DELETE FROM tblProject WHERE projectid=?";
        this.getJdbcTemplate().update(sql, projectid);
    }

    public void createpdf(Project project, List<Order> listOrder, HttpServletRequest request) {    	
    	int currentyear = Calendar.getInstance().get(Calendar.YEAR);
        int currentmonth = Calendar.getInstance().get(Calendar.MONTH)+1;
        int currentday = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        String dateString = "";
        String quotationDate = "";
        if (currentmonth < 10){
        	dateString = String.valueOf(currentday)+"0"+String.valueOf(currentmonth)+String.valueOf(currentyear);
        	quotationDate = String.valueOf(currentday)+"-0"+String.valueOf(currentmonth)+"-"+String.valueOf(currentyear);
        } else {
        	dateString = String.valueOf(currentday)+String.valueOf(currentmonth)+String.valueOf(currentyear);
        	quotationDate = String.valueOf(currentday)+"-"+String.valueOf(currentmonth)+"-"+String.valueOf(currentyear);
        } 

        Address address = project.getaddress();
    	String base = request.getRequestURL().toString().substring(0, request.getRequestURL().toString().length() - request.getRequestURI().toString().length())+request.getContextPath();
    	String filename = "MYTQ-"+project.getprojectname()+"-"+dateString+".pdf";
    	String pdfPath = "/pdf";
    	String absolutePDFPath = request.getSession().getServletContext().getRealPath(pdfPath);
    	File PDFfile = new File(absolutePDFPath, filename);
    	BaseFont baseFont = null;
    	Font TitleFont = null;
    	Font BodyHeaderFont = null;    	
    	Font BodyHeaderFontWhite = null;    	
    	Font BodyFont = null;
    	Font FooterHeaderFont = null;    	
    	Font FooterFont = null;
    	Font FooterSmallFont = null;
    	Font FooterFontItalic = null;
    	try {
			baseFont = BaseFont.createFont(base+"/font/micross.ttf", BaseFont.WINANSI, BaseFont.EMBEDDED);
		} 
    	catch (DocumentException e) {}
    	catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	TitleFont = new Font(baseFont, 36, Font.BOLD);
    	BodyHeaderFont = new Font(baseFont, 10, Font.BOLD);
    	BodyHeaderFontWhite = new Font(baseFont, 10, Font.BOLD, BaseColor.WHITE);
    	BodyFont = new Font(baseFont, 10);
    	FooterHeaderFont = new Font(baseFont, 8, Font.BOLD);
    	FooterFont = new Font(baseFont, 8);
    	FooterSmallFont = new Font(baseFont, 6);
    	FooterFontItalic = new Font(baseFont, 8, Font.ITALIC);
    	Document document = new Document();
        try
        {        	
           PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(PDFfile.getAbsolutePath()));
           document.open();
           DottedLineSeparator dottedline = new DottedLineSeparator();
           dottedline.setOffset(-2);
           dottedline.setGap(2f);
           LineSeparator line = new LineSeparator();
           line.setOffset(-5);
           DecimalFormat df = new DecimalFormat("#.00");
           String companyimageUrl = base+"/img/company_logo.png";
           Image companyimage = Image.getInstance(new URL(companyimageUrl));
           companyimage.setAbsolutePosition(400f, 750f);
           companyimage.scaleAbsolute(150, 30);
           document.add(companyimage);
           String mscimageUrl = base+"/img/mscstatus.png";
           Image mscimage = Image.getInstance(new URL(mscimageUrl));
           mscimage.setAbsolutePosition(380f, 680f);
           mscimage.scaleAbsolute(40, 40);
           document.add(mscimage);
           document.add(new Paragraph("QUOTATION", TitleFont));
           document.add(line);
           
           PdfPTable tableHeader = new PdfPTable(3); // 3 columns.
           tableHeader.setWidthPercentage(100); //Width 100%
           tableHeader.setSpacingBefore(5f); //Space before table
           //Set Column widths
           float[] headerColumnWidths = {10f, 40f, 40f};
           tableHeader.setWidths(headerColumnWidths);
           
           PdfPCell cellHLine1Col1 = new PdfPCell(new Paragraph("Quote No.:", BodyHeaderFont));
           cellHLine1Col1.setBorder(0);
           cellHLine1Col1.setHorizontalAlignment(Element.ALIGN_LEFT);
           cellHLine1Col1.setVerticalAlignment(Element.ALIGN_MIDDLE);
           
           Chunk quotationNo = new Chunk("MYTQ-"+project.getprojectname()+"-"+dateString, BodyFont);
           Paragraph p_quotationNo = new Paragraph();           
           p_quotationNo.add(quotationNo);
           p_quotationNo.add(dottedline);
           PdfPCell cellHLine1Col2 = new PdfPCell(p_quotationNo);
           cellHLine1Col2.setBorder(0);
           cellHLine1Col2.setHorizontalAlignment(Element.ALIGN_LEFT);
           cellHLine1Col2.setVerticalAlignment(Element.ALIGN_MIDDLE);
           
           PdfPCell cellHLine1Col3 = new PdfPCell(new Paragraph("ZOINLA (MALAYSIA) SDN BHD (1020921-A)", BodyHeaderFont));
           cellHLine1Col3.setBorder(0);
           cellHLine1Col3.setHorizontalAlignment(Element.ALIGN_RIGHT);
           cellHLine1Col3.setVerticalAlignment(Element.ALIGN_MIDDLE);

           PdfPCell cellHLine2Col1 = new PdfPCell(new Paragraph("Date:", BodyHeaderFont));
           cellHLine2Col1.setBorder(0);
           cellHLine2Col1.setHorizontalAlignment(Element.ALIGN_LEFT);
           cellHLine2Col1.setVerticalAlignment(Element.ALIGN_MIDDLE);

           Chunk chunkQuotationDate = new Chunk(quotationDate, BodyFont);
           Paragraph p_quotationDate = new Paragraph();           
           p_quotationDate.add(chunkQuotationDate);
           p_quotationDate.add(dottedline);
           PdfPCell cellHLine2Col2 = new PdfPCell(p_quotationDate);
           cellHLine2Col2.setBorder(0);
           cellHLine2Col2.setHorizontalAlignment(Element.ALIGN_LEFT);
           cellHLine2Col2.setVerticalAlignment(Element.ALIGN_MIDDLE);

           PdfPCell cellHLine2Col3 = new PdfPCell(new Paragraph("Unit C1-3-1", BodyFont));
           cellHLine2Col3.setBorder(0);
           cellHLine2Col3.setHorizontalAlignment(Element.ALIGN_RIGHT);
           cellHLine2Col3.setVerticalAlignment(Element.ALIGN_MIDDLE);

           PdfPCell cellHLine3Col1 = new PdfPCell(new Paragraph("Attention:", BodyHeaderFont));
           cellHLine3Col1.setBorder(0);
           cellHLine3Col1.setHorizontalAlignment(Element.ALIGN_LEFT);
           cellHLine3Col1.setVerticalAlignment(Element.ALIGN_MIDDLE);

           Chunk projectName = new Chunk(project.getname(), BodyFont);
           Paragraph p_projectName = new Paragraph();           
           p_projectName.add(projectName);
           p_projectName.add(dottedline);
           PdfPCell cellHLine3Col2 = new PdfPCell(p_projectName);
           cellHLine3Col2.setBorder(0);
           cellHLine3Col2.setHorizontalAlignment(Element.ALIGN_LEFT);
           cellHLine3Col2.setVerticalAlignment(Element.ALIGN_MIDDLE);

           PdfPCell cellHLine3Col3 = new PdfPCell(new Paragraph("Solaris Dutamas, No.1", BodyFont));
           cellHLine3Col3.setBorder(0);
           cellHLine3Col3.setHorizontalAlignment(Element.ALIGN_RIGHT);
           cellHLine3Col3.setVerticalAlignment(Element.ALIGN_MIDDLE);

           PdfPCell cellHLine4Col1 = new PdfPCell(new Paragraph("Address:", BodyHeaderFont));
           cellHLine4Col1.setBorder(0);
           cellHLine4Col1.setHorizontalAlignment(Element.ALIGN_LEFT);
           cellHLine4Col1.setVerticalAlignment(Element.ALIGN_MIDDLE);

           Chunk address1 = new Chunk(address.getstreet()+", "+address.getzipcode()+" "+address.getcity(), BodyFont);
           Paragraph p_address1 = new Paragraph();           
           p_address1.add(address1);
           p_address1.add(dottedline);
           PdfPCell cellHLine4Col2 = new PdfPCell(p_address1);
           cellHLine4Col2.setBorder(0);
           cellHLine4Col2.setHorizontalAlignment(Element.ALIGN_LEFT);
           cellHLine4Col2.setVerticalAlignment(Element.ALIGN_MIDDLE);

           PdfPCell cellHLine4Col3 = new PdfPCell(new Paragraph("50480 Kuala Lumpur", BodyFont));
           cellHLine4Col3.setBorder(0);
           cellHLine4Col3.setHorizontalAlignment(Element.ALIGN_RIGHT);
           cellHLine4Col3.setVerticalAlignment(Element.ALIGN_MIDDLE);

           PdfPCell cellHLine5Col1 = new PdfPCell(new Paragraph("", BodyHeaderFont));
           cellHLine5Col1.setBorder(0);
           cellHLine5Col1.setHorizontalAlignment(Element.ALIGN_RIGHT);
           cellHLine5Col1.setVerticalAlignment(Element.ALIGN_MIDDLE);

           Chunk address2 = new Chunk(address.getstate()+", "+address.getcountry(), BodyFont);
           Paragraph p_address2 = new Paragraph();           
           p_address2.add(address2);
           p_address2.add(dottedline);
           PdfPCell cellHLine5Col2 = new PdfPCell(p_address2);
           cellHLine5Col2.setBorder(0);
           cellHLine5Col2.setHorizontalAlignment(Element.ALIGN_LEFT);
           cellHLine5Col2.setVerticalAlignment(Element.ALIGN_MIDDLE);

           PdfPCell cellHLine5Col3 = new PdfPCell(new Paragraph("support@mytaman.com", BodyFont));
           cellHLine5Col3.setBorder(0);
           cellHLine5Col3.setHorizontalAlignment(Element.ALIGN_RIGHT);
           cellHLine5Col3.setVerticalAlignment(Element.ALIGN_MIDDLE);

           cellHLine1Col1.setUseBorderPadding(true);
           cellHLine1Col2.setUseBorderPadding(true);
           cellHLine1Col3.setUseBorderPadding(true);
           cellHLine2Col1.setUseBorderPadding(true);
           cellHLine2Col2.setUseBorderPadding(true);
           cellHLine2Col3.setUseBorderPadding(true);
           cellHLine3Col1.setUseBorderPadding(true);
           cellHLine3Col2.setUseBorderPadding(true);
           cellHLine3Col3.setUseBorderPadding(true);
           cellHLine4Col1.setUseBorderPadding(true);
           cellHLine4Col2.setUseBorderPadding(true);
           cellHLine4Col3.setUseBorderPadding(true);
           cellHLine5Col1.setUseBorderPadding(true);
           cellHLine5Col2.setUseBorderPadding(true);
           cellHLine5Col3.setUseBorderPadding(true);

           tableHeader.addCell(cellHLine1Col1);
           tableHeader.addCell(cellHLine1Col2);
           tableHeader.addCell(cellHLine1Col3);
           tableHeader.addCell(cellHLine2Col1);
           tableHeader.addCell(cellHLine2Col2);
           tableHeader.addCell(cellHLine2Col3);
           tableHeader.addCell(cellHLine3Col1);
           tableHeader.addCell(cellHLine3Col2);
           tableHeader.addCell(cellHLine3Col3);
           tableHeader.addCell(cellHLine4Col1);
           tableHeader.addCell(cellHLine4Col2);
           tableHeader.addCell(cellHLine4Col3);
           tableHeader.addCell(cellHLine5Col1);
           tableHeader.addCell(cellHLine5Col2);
           tableHeader.addCell(cellHLine5Col3);

           document.add(tableHeader);

           PdfPTable tableDetail = new PdfPTable(5); // 5 columns.
           tableDetail.setWidthPercentage(100); //Width 100%
           tableDetail.setSpacingBefore(10f); //Space before table
           //Set Column widths
           float[] detailColumnWidths = {10f, 40f, 10f, 15f, 15f};
           tableDetail.setWidths(detailColumnWidths);

           PdfPCell cellDLine1Col1 = new PdfPCell(new Paragraph("ITEM", BodyHeaderFontWhite));
           cellDLine1Col1.setBorder(0);
           cellDLine1Col1.setBackgroundColor(BaseColor.BLACK);
           cellDLine1Col1.setHorizontalAlignment(Element.ALIGN_LEFT);
           cellDLine1Col1.setVerticalAlignment(Element.ALIGN_MIDDLE);
           
           PdfPCell cellDLine1Col2 = new PdfPCell(new Paragraph("DESCRIPTION", BodyHeaderFontWhite));
           cellDLine1Col2.setBorder(0);
           cellDLine1Col2.setBackgroundColor(BaseColor.BLACK);
           cellDLine1Col2.setHorizontalAlignment(Element.ALIGN_LEFT);
           cellDLine1Col2.setVerticalAlignment(Element.ALIGN_MIDDLE);

           PdfPCell cellDLine1Col3 = new PdfPCell(new Paragraph("YEARS", BodyHeaderFontWhite));
           cellDLine1Col3.setBorder(0);
           cellDLine1Col3.setBackgroundColor(BaseColor.BLACK);
           cellDLine1Col3.setHorizontalAlignment(Element.ALIGN_LEFT);
           cellDLine1Col3.setVerticalAlignment(Element.ALIGN_MIDDLE);

           Paragraph p;
           p = new Paragraph(12, "SUBSCRIPTION FEE (YEARLY)", BodyHeaderFontWhite);
           PdfPCell cellDLine1Col4 = new PdfPCell();
           cellDLine1Col4.addElement(p);
           cellDLine1Col4.setBorder(0);
           cellDLine1Col4.setBackgroundColor(BaseColor.BLACK);
           cellDLine1Col4.setHorizontalAlignment(Element.ALIGN_LEFT);
           cellDLine1Col4.setVerticalAlignment(Element.ALIGN_MIDDLE);

           p = new Paragraph(12, "TOTAL SUBSCRIPTION", BodyHeaderFontWhite);
           PdfPCell cellDLine1Col5 = new PdfPCell();
           cellDLine1Col5.addElement(p);
           cellDLine1Col5.setBorder(0);
           cellDLine1Col5.setBackgroundColor(BaseColor.BLACK);
           cellDLine1Col5.setHorizontalAlignment(Element.ALIGN_LEFT);
           cellDLine1Col5.setVerticalAlignment(Element.ALIGN_MIDDLE);

           cellDLine1Col1.setUseBorderPadding(true);
           cellDLine1Col2.setUseBorderPadding(true);
           cellDLine1Col3.setUseBorderPadding(true);
           cellDLine1Col4.setUseBorderPadding(true);
           cellDLine1Col5.setUseBorderPadding(true);

           tableDetail.addCell(cellDLine1Col1);
           tableDetail.addCell(cellDLine1Col2);
           tableDetail.addCell(cellDLine1Col3);
           tableDetail.addCell(cellDLine1Col4);
           tableDetail.addCell(cellDLine1Col5);
           
           // loop list Order
           PdfPCell orderCell;
           String prevProduct = "";
           String stringPrice = "";
           String stringAmount = "";
           Float total = (float) 0; 
           Float hwtotal = (float) 0; 
           Float swtotal = (float) 0; 
           for( Order order : listOrder){
        	   if (order.getprodtypename().equals("Hardware")){
        		   hwtotal += order.getamount();        		   
        	   };
        	   if (order.getprodtypename().equals("Software")){
        		   swtotal += order.getamount();
        	   };
        	   //ITEM
        	   if (!order.getprodtypename().equals(prevProduct)){
        		   if (prevProduct.length()!=0){
        			   if (prevProduct.equals("Hardware")){
        				   if (project.gethwdiscount()!=0){
        		               orderCell = new PdfPCell();
        		               orderCell.setBorder(0);
        		               orderCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        		               orderCell.setUseBorderPadding(true);
        		               tableDetail.addCell(orderCell);
        		               tableDetail.addCell(orderCell);
        		               tableDetail.addCell(orderCell);
        		               orderCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        		               orderCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        		               p = new Paragraph(12, "Discount", BodyFont);
        		               orderCell.addElement(p);
        		               tableDetail.addCell(orderCell);
        		               orderCell = new PdfPCell();
        		               orderCell.setBorder(0);
        		               orderCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        		               orderCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        		               orderCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        		               orderCell.setUseBorderPadding(true);
        		               p = new Paragraph(12, df.format(project.gethwdiscount()), BodyFont);
        		               orderCell.addElement(p);
        		               tableDetail.addCell(orderCell);
        		               
        		               hwtotal = hwtotal - project.gethwdiscount();
        		               orderCell = new PdfPCell();
        		               orderCell.setBorder(0);
        		               orderCell.setBackgroundColor(BaseColor.GRAY);
        		               orderCell.setUseBorderPadding(true);
        		               tableDetail.addCell(orderCell);
        		               tableDetail.addCell(orderCell);
        		               tableDetail.addCell(orderCell);
        		               orderCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        		               orderCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        		               p = new Paragraph(12, "Hardware Total", BodyFont);
        		               orderCell.addElement(p);
        		               tableDetail.addCell(orderCell);
        		               orderCell = new PdfPCell();
        		               orderCell.setBorder(0);
        		               orderCell.setBackgroundColor(BaseColor.GRAY);
        		               orderCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        		               orderCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        		               orderCell.setUseBorderPadding(true);
        		               p = new Paragraph(12, df.format(hwtotal), BodyFont);
        		               orderCell.addElement(p);
        		               tableDetail.addCell(orderCell);
        				   };
        			   };
        			   if (prevProduct.equals("Software")){
        				   if (project.getswdiscount()!=0){
        		               orderCell = new PdfPCell();
        		               orderCell.setBorder(0);
        		               orderCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        		               orderCell.setUseBorderPadding(true);
        		               tableDetail.addCell(orderCell);
        		               tableDetail.addCell(orderCell);
        		               tableDetail.addCell(orderCell);
        		               orderCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        		               orderCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        		               p = new Paragraph(12, "Discount", BodyFont);
        		               orderCell.addElement(p);
        		               tableDetail.addCell(orderCell);
        		               orderCell = new PdfPCell();
        		               orderCell.setBorder(0);
        		               orderCell.setBackgroundColor(BaseColor.GRAY);
        		               orderCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        		               orderCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        		               orderCell.setUseBorderPadding(true);
        		               p = new Paragraph(12, df.format(project.getswdiscount()), BodyFont);
        		               orderCell.addElement(p);
        		               tableDetail.addCell(orderCell);        		                       					   

        		               swtotal = swtotal - project.getswdiscount();
        		               orderCell = new PdfPCell();
        		               orderCell.setBorder(0);
        		               orderCell.setBackgroundColor(BaseColor.GRAY);
        		               orderCell.setUseBorderPadding(true);
        		               tableDetail.addCell(orderCell);
        		               tableDetail.addCell(orderCell);
        		               tableDetail.addCell(orderCell);
        		               orderCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        		               orderCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        		               p = new Paragraph(12, "Software Total", BodyFont);
        		               orderCell.addElement(p);
        		               tableDetail.addCell(orderCell);
        		               orderCell = new PdfPCell();
        		               orderCell.setBorder(0);
        		               orderCell.setBackgroundColor(BaseColor.GRAY);
        		               orderCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        		               orderCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        		               orderCell.setUseBorderPadding(true);
        		               p = new Paragraph(12, df.format(swtotal), BodyFont);
        		               orderCell.addElement(p);
        		               tableDetail.addCell(orderCell);
        				   };        				   
        			   };
        		   };
	               p = new Paragraph(12, order.getprodtypename(), BodyFont);
	               prevProduct = order.getprodtypename();
    		   } else {
	               p = new Paragraph(12, "", BodyFont);        		   
        	   };
               orderCell = new PdfPCell();
               orderCell.addElement(p);
               orderCell.setBorder(0);
               orderCell.setHorizontalAlignment(Element.ALIGN_LEFT);
               orderCell.setVerticalAlignment(Element.ALIGN_TOP);
               orderCell.setUseBorderPadding(true);
               tableDetail.addCell(orderCell);
               //DESCRIPTION
               p = new Paragraph(12, "", BodyFont);
               Chunk productname = new Chunk(order.getproductname());
               productname.setUnderline(1f, -2f);
               p.add(productname);
               if (order.getdesc01().length()!=0){p.add(Chunk.NEWLINE); p.add(new Chunk("- "+order.getdesc01()));};
               if (order.getdesc02().length()!=0){p.add(Chunk.NEWLINE); p.add(new Chunk("- "+order.getdesc02()));};
               if (order.getdesc03().length()!=0){p.add(Chunk.NEWLINE); p.add(new Chunk("- "+order.getdesc03()));};
               if (order.getdesc04().length()!=0){p.add(Chunk.NEWLINE); p.add(new Chunk("- "+order.getdesc04()));};
               if (order.getdesc05().length()!=0){p.add(Chunk.NEWLINE); p.add(new Chunk("- "+order.getdesc05()));};
               if (order.getdesc06().length()!=0){p.add(Chunk.NEWLINE); p.add(new Chunk("- "+order.getdesc06()));};
               if (order.getdesc07().length()!=0){p.add(Chunk.NEWLINE); p.add(new Chunk("- "+order.getdesc07()));};
               if (order.getdesc08().length()!=0){p.add(Chunk.NEWLINE); p.add(new Chunk("- "+order.getdesc08()));};
               if (order.getdesc09().length()!=0){p.add(Chunk.NEWLINE); p.add(new Chunk("- "+order.getdesc09()));};
               if (order.getdesc10().length()!=0){p.add(Chunk.NEWLINE); p.add(new Chunk("- "+order.getdesc10()));};
               orderCell = new PdfPCell();
               orderCell.addElement(p);
               orderCell.setBorder(0);
               orderCell.setHorizontalAlignment(Element.ALIGN_LEFT);
               orderCell.setVerticalAlignment(Element.ALIGN_TOP);
               orderCell.setUseBorderPadding(true);
               tableDetail.addCell(orderCell);
               
               // YEARS
               PdfTemplate template = writer.getDirectContent().createTemplate(20, 10);
               template.setColorFill(BaseColor.LIGHT_GRAY);
               template.rectangle(0, 0, 20, 10);
               template.fill();
               writer.releaseTemplate(template);
                              
               orderCell = new PdfPCell();
               orderCell.addElement(Image.getInstance(template));
               orderCell.setCellEvent(new WatermarkedCell(String.valueOf(order.getquantity())));
               orderCell.setBorder(0);
               orderCell.setHorizontalAlignment(Element.ALIGN_CENTER);
               orderCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               orderCell.setUseBorderPadding(false);
               tableDetail.addCell(orderCell);

               // FEE
               stringPrice = df.format(order.getprice());
               orderCell = new PdfPCell();
               orderCell.setPadding(15f);
               orderCell.addElement(Image.getInstance(template));
               orderCell.setCellEvent(new WatermarkedCell(stringPrice));
               orderCell.setBorder(0);
               orderCell.setHorizontalAlignment(Element.ALIGN_CENTER);
               orderCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               orderCell.setUseBorderPadding(true);
               tableDetail.addCell(orderCell);

               // AMOUNT
               total += order.getamount();
               stringAmount = df.format(order.getamount());
               orderCell = new PdfPCell();
               orderCell.setPadding(15f);
               orderCell.addElement(Image.getInstance(template));
               orderCell.setCellEvent(new WatermarkedCell(stringAmount));
               orderCell.setBorder(0);
               orderCell.setHorizontalAlignment(Element.ALIGN_CENTER);
               orderCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               orderCell.setUseBorderPadding(true);
               tableDetail.addCell(orderCell);
           };

		   if (prevProduct.length()!=0){
			   if (prevProduct.equals("Hardware")){
				   if (project.gethwdiscount()!=0){
		               orderCell = new PdfPCell();
		               orderCell.setBorder(0);
		               orderCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		               orderCell.setUseBorderPadding(true);
		               tableDetail.addCell(orderCell);
		               tableDetail.addCell(orderCell);
		               tableDetail.addCell(orderCell);
		               orderCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		               orderCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		               p = new Paragraph(12, "Discount", BodyFont);
		               orderCell.addElement(p);
		               tableDetail.addCell(orderCell);
		               orderCell = new PdfPCell();
		               orderCell.setBorder(0);
		               orderCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		               orderCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		               orderCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		               orderCell.setUseBorderPadding(true);
		               p = new Paragraph(12, df.format(project.gethwdiscount()), BodyFont);
		               orderCell.addElement(p);
		               tableDetail.addCell(orderCell);        		               

		               hwtotal = hwtotal - project.gethwdiscount();
		               orderCell = new PdfPCell();
		               orderCell.setBorder(0);
		               orderCell.setBackgroundColor(BaseColor.GRAY);
		               orderCell.setUseBorderPadding(true);
		               tableDetail.addCell(orderCell);
		               tableDetail.addCell(orderCell);
		               tableDetail.addCell(orderCell);
		               orderCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		               orderCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		               p = new Paragraph(12, "Hardware Total", BodyFont);
		               orderCell.addElement(p);
		               tableDetail.addCell(orderCell);
		               orderCell = new PdfPCell();
		               orderCell.setBorder(0);
		               orderCell.setBackgroundColor(BaseColor.GRAY);
		               orderCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		               orderCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		               orderCell.setUseBorderPadding(true);
		               p = new Paragraph(12, df.format(hwtotal), BodyFont);
		               orderCell.addElement(p);
		               tableDetail.addCell(orderCell);
				   };
			   };
			   if (prevProduct.equals("Software")){
				   if (project.getswdiscount()!=0){
		               orderCell = new PdfPCell();
		               orderCell.setBorder(0);
		               orderCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		               orderCell.setUseBorderPadding(true);
		               tableDetail.addCell(orderCell);
		               tableDetail.addCell(orderCell);
		               tableDetail.addCell(orderCell);
		               orderCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		               orderCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		               p = new Paragraph(12, "Discount", BodyFont);
		               orderCell.addElement(p);
		               tableDetail.addCell(orderCell);
		               orderCell = new PdfPCell();
		               orderCell.setBorder(0);
		               orderCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		               orderCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		               orderCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		               orderCell.setUseBorderPadding(true);
		               p = new Paragraph(12, df.format(project.getswdiscount()), BodyFont);
		               orderCell.addElement(p);
		               tableDetail.addCell(orderCell);        		                       					   

		               swtotal = swtotal - project.getswdiscount();
		               orderCell = new PdfPCell();
		               orderCell.setBorder(0);
		               orderCell.setBackgroundColor(BaseColor.GRAY);
		               orderCell.setUseBorderPadding(true);
		               tableDetail.addCell(orderCell);
		               tableDetail.addCell(orderCell);
		               tableDetail.addCell(orderCell);
		               orderCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		               orderCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		               p = new Paragraph(12, "Software Total", BodyFont);
		               orderCell.addElement(p);
		               tableDetail.addCell(orderCell);
		               orderCell = new PdfPCell();
		               orderCell.setBorder(0);
		               orderCell.setBackgroundColor(BaseColor.GRAY);
		               orderCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		               orderCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		               orderCell.setUseBorderPadding(true);
		               p = new Paragraph(12, df.format(swtotal), BodyFont);
		               orderCell.addElement(p);
		               tableDetail.addCell(orderCell);
				   };
			   };
		   };
		   total = total - project.gethwdiscount() - project.getswdiscount();
           orderCell = new PdfPCell();
           orderCell.setBorder(0);
           orderCell.setUseBorderPadding(true);
           tableDetail.addCell(orderCell);
           tableDetail.addCell(orderCell);
           tableDetail.addCell(orderCell);
           orderCell.setHorizontalAlignment(Element.ALIGN_CENTER);
           orderCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
           p = new Paragraph(12, "Grand Total", BodyHeaderFont);
           orderCell.addElement(p);
           tableDetail.addCell(orderCell);
           orderCell = new PdfPCell();
           orderCell.setBorder(0);
           orderCell.setHorizontalAlignment(Element.ALIGN_CENTER);
           orderCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
           orderCell.setUseBorderPadding(true);
           p = new Paragraph(12, df.format(total), BodyHeaderFont);
           orderCell.addElement(p);
           tableDetail.addCell(orderCell);        		                       					   		   
           document.add(tableDetail);

           p = new Paragraph(12, "", FooterHeaderFont);
           Chunk term = new Chunk("Terms and Conditions");
           term.setUnderline(1f, -2f);
           p.add(term);
           document.add(p);
           
           PdfPTable tableFooter = new PdfPTable(2); // 2 columns.
           tableFooter.setWidthPercentage(100); //Width 100%
           tableFooter.setSpacingBefore(5f); //Space before table
           //Set Column widths
           float[] footerColumnWidths = {20f, 80f};
           tableFooter.setWidths(footerColumnWidths);
           
           PdfPCell cellFLine1Col1 = new PdfPCell(new Paragraph("Validity:", FooterHeaderFont));
           cellFLine1Col1.setBorder(0);
           cellFLine1Col1.setHorizontalAlignment(Element.ALIGN_RIGHT);
           cellFLine1Col1.setVerticalAlignment(Element.ALIGN_TOP);

           PdfPCell cellFLine1Col2 = new PdfPCell(new Paragraph("15 days from the date of quotation", FooterFont));
           cellFLine1Col2.setBorder(0);
           cellFLine1Col2.setHorizontalAlignment(Element.ALIGN_LEFT);
           cellFLine1Col2.setVerticalAlignment(Element.ALIGN_TOP);

           PdfPCell cellFLine2Col1 = new PdfPCell(new Paragraph("Delivery:", FooterHeaderFont));
           cellFLine2Col1.setBorder(0);
           cellFLine2Col1.setHorizontalAlignment(Element.ALIGN_RIGHT);
           cellFLine2Col1.setVerticalAlignment(Element.ALIGN_TOP);

           PdfPCell cellFLine2Col2 = new PdfPCell(new Paragraph("1-2 weeks from the date of confirm order", FooterFont));
           cellFLine2Col2.setBorder(0);
           cellFLine2Col2.setHorizontalAlignment(Element.ALIGN_LEFT);
           cellFLine2Col2.setVerticalAlignment(Element.ALIGN_TOP);

           PdfPCell cellFLine3Col1 = new PdfPCell(new Paragraph("Others:", FooterHeaderFont));
           cellFLine3Col1.setBorder(0);
           cellFLine3Col1.setHorizontalAlignment(Element.ALIGN_RIGHT);
           cellFLine3Col1.setVerticalAlignment(Element.ALIGN_TOP);

           PdfPCell cellFLine3Col2 = new PdfPCell(new Paragraph("Annual Simcard Plan Subscription charges re subjected to plan offered by respective telco company", FooterFont));
           cellFLine3Col2.setBorder(0);
           cellFLine3Col2.setHorizontalAlignment(Element.ALIGN_LEFT);
           cellFLine3Col2.setVerticalAlignment(Element.ALIGN_TOP);

           PdfPCell cellFLine4Col1 = new PdfPCell(new Paragraph("Total Payment:", FooterHeaderFont));
           cellFLine4Col1.setBorder(0);
           cellFLine4Col1.setHorizontalAlignment(Element.ALIGN_RIGHT);
           cellFLine4Col1.setVerticalAlignment(Element.ALIGN_TOP);

           p = new Paragraph(8, "");
           p.add(new Chunk("Payable annually to", FooterFont));
           p.add(Chunk.NEWLINE); 
           p.add(Chunk.NEWLINE); 
           p.add(new Chunk("Zoinla (Malaysia) Sdn Bhd", FooterHeaderFont));
           p.add(Chunk.NEWLINE);
           p.add(new Chunk("MBB: 5648 9210 5090", FooterFont));
           p.add(Chunk.NEWLINE);
           p.add(new Chunk("Solaris Mont Kiara", FooterFont));
           p.add(Chunk.NEWLINE);
           
           PdfPCell cellFLine4Col2 = new PdfPCell();
           cellFLine4Col2.addElement(p);
           cellFLine4Col2.setBorder(0);           
           cellFLine4Col2.setHorizontalAlignment(Element.ALIGN_LEFT);
           cellFLine4Col2.setVerticalAlignment(Element.ALIGN_TOP);

           PdfPCell cellFLine5Col1 = new PdfPCell(new Paragraph("Note:", FooterHeaderFont));
           cellFLine5Col1.setBorder(0);
           cellFLine5Col1.setHorizontalAlignment(Element.ALIGN_RIGHT);
           cellFLine5Col1.setVerticalAlignment(Element.ALIGN_TOP);

           PdfPCell cellFLine5Col2 = new PdfPCell(new Paragraph("Zoinla (Malaysia) Sdn Bhd is the holding company for MyTaman App & Website Services.", FooterFontItalic));
           cellFLine5Col2.setBorder(0);
           cellFLine5Col2.setHorizontalAlignment(Element.ALIGN_LEFT);
           cellFLine5Col2.setVerticalAlignment(Element.ALIGN_TOP);

           cellFLine1Col1.setUseBorderPadding(true);
           cellFLine1Col2.setUseBorderPadding(true);
           cellFLine2Col1.setUseBorderPadding(true);
           cellFLine2Col2.setUseBorderPadding(true);
           cellFLine3Col1.setUseBorderPadding(true);
           cellFLine3Col2.setUseBorderPadding(true);
           cellFLine4Col1.setUseBorderPadding(true);
           cellFLine4Col2.setUseBorderPadding(true);
           cellFLine5Col1.setUseBorderPadding(true);
           cellFLine5Col2.setUseBorderPadding(true);

           tableFooter.addCell(cellFLine1Col1);
           tableFooter.addCell(cellFLine1Col2);
           tableFooter.addCell(cellFLine2Col1);
           tableFooter.addCell(cellFLine2Col2);
           tableFooter.addCell(cellFLine3Col1);
           tableFooter.addCell(cellFLine3Col2);
           tableFooter.addCell(cellFLine4Col1);
           tableFooter.addCell(cellFLine4Col2);
           tableFooter.addCell(cellFLine5Col1);
           tableFooter.addCell(cellFLine5Col2);
           
           document.add(tableFooter);

           PdfPTable tableSignature = new PdfPTable(2); // 2 columns.
           tableSignature.setWidthPercentage(100); //Width 100%
           tableSignature.setSpacingBefore(2f); //Space before table
           //Set Column widths
           float[] signatureColumnWidths = {70f, 30f};
           tableSignature.setWidths(signatureColumnWidths);

           p = new Paragraph(12, "");
           p.add(new Chunk("Best Regards,", FooterFont));
           p.add(Chunk.NEWLINE); 
           p.add(new Chunk("MyTaman Sales Team", FooterHeaderFont));
           p.add(Chunk.NEWLINE);
           p.add(new Chunk(project.getusername(), FooterHeaderFont));
           p.add(Chunk.NEWLINE);
           p.add(new Chunk("(This is computer generated Quotation. No signature is required)", FooterSmallFont));
           
           PdfPCell cellSignatureCol1 = new PdfPCell();
           cellSignatureCol1.addElement(p);
           cellSignatureCol1.setBorder(0);           
           cellSignatureCol1.setHorizontalAlignment(Element.ALIGN_LEFT);
           cellSignatureCol1.setVerticalAlignment(Element.ALIGN_TOP);

           p = new Paragraph(12, "");
           p.add(new Chunk("Purchase Confirmation:", FooterHeaderFont));
           p.add(Chunk.NEWLINE); 
           p.add(new Chunk("(Sign & Chop)", FooterFont));
           p.add(Chunk.NEWLINE);
           p.add(Chunk.NEWLINE);
           p.add(Chunk.NEWLINE);
           p.add(line);
           
           PdfPCell cellSignatureCol2 = new PdfPCell();
           cellSignatureCol2.addElement(p);
           cellSignatureCol2.setBorder(0);           
           cellSignatureCol2.setHorizontalAlignment(Element.ALIGN_RIGHT);
           cellSignatureCol2.setVerticalAlignment(Element.ALIGN_TOP);

           cellSignatureCol1.setUseBorderPadding(true);
           cellSignatureCol2.setUseBorderPadding(true);

           tableSignature.addCell(cellSignatureCol1);
           tableSignature.addCell(cellSignatureCol2);

           document.add(tableSignature);
           document.close();
           writer.close();
           
           String sql = "UPDATE tblProject SET pdflink=? WHERE projectid=?";
           this.getJdbcTemplate().update(sql, base+pdfPath+"/"+filename, project.getprojectid());
           
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
    
    public Project get(int projectid) {
        String sql = "SELECT proj.projectid, proj.projectname, (proj.address).country, "
        		+ "(proj.address).zipcode, (proj.address).state, (proj.address).city, (proj.address).street, "
        		+ "proj.userid, usr.username, proj.name, proj.mobile, proj.email, "
        		+ "proj.titleid, title.codename AS titlename, "
        		+ "proj.propertyid, property.codename AS propertyname, "
        		+ "proj.units, proj.smsflag, proj.datecreated, proj.forecastperiod, "
        		+ "proj.hwdiscount, proj.swdiscount, "
        		+ "proj.status, status.codename AS statusname, proj.pdflink "
        		+ "FROM tblProject proj "
        		+ "LEFT JOIN tblCodeMaster title ON title.codetype = 'TITLE' AND title.codeid = proj.titleid "        		
        		+ "LEFT JOIN tblCodeMaster property ON property.codetype = 'PROPERTY' AND property.codeid = proj.propertyid "        		
        		+ "LEFT JOIN tblCodeMaster status ON status.codetype = 'STATUS' AND status.codeid = proj.status "        		
        		+ "LEFT JOIN tblUser usr ON usr.userid = proj.userid "
        		+ "WHERE proj.projectid = " + projectid;
	    return this.getJdbcTemplate().query(sql, new ResultSetExtractor<Project>() {
	 
	        @Override
	        public Project extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	            if (rs.next()) {
	            	Project project = new Project();
	            	project.setprojectid(rs.getInt("projectid"));
	            	project.setprojectname(rs.getString("projectname"));
	                Address address = new Address();
	                address.setcountry(rs.getString("country"));
	                address.setzipcode(rs.getString("zipcode"));
	                address.setstate(rs.getString("state"));
	                address.setcity(rs.getString("city"));
	                address.setstreet(rs.getString("street"));
	                project.setaddress(address);
	            	project.setuserid(rs.getInt("userid"));
	            	project.setusername(rs.getString("username"));
	            	project.setname(rs.getString("name"));
	            	project.setmobile(rs.getString("mobile"));
	            	project.setemail(rs.getString("email"));
	                project.settitleid(rs.getString("titleid"));
	            	project.settitlename(rs.getString("titlename"));
	                project.setpropertyid(rs.getString("propertyid"));
	            	project.setpropertyname(rs.getString("propertyname"));
	                project.setunits(rs.getInt("units"));
	                project.setsmsflag(rs.getBoolean("smsflag"));
	                project.setdatecreated(rs.getDate("datecreated"));
	            	project.setforecastperiod(rs.getString("forecastperiod"));
	            	project.sethwdiscount(rs.getFloat("hwdiscount"));
	            	project.setswdiscount(rs.getFloat("swdiscount"));
	            	project.setstatus(rs.getString("status"));
	            	project.setstatusname(rs.getString("statusname"));
	            	project.setpdflink(rs.getString("pdflink"));
	                return project;
	            }	 
	            return null;
	        }
        });
    }

    public List<Project> list(int userid) {
        String sql = "SELECT proj.projectid, proj.projectname, (proj.address).country, "
        		+ "(proj.address).zipcode, (proj.address).state, (proj.address).city, (proj.address).street, "
        		+ "proj.userid, usr.username, proj.name, proj.mobile, proj.email, "
        		+ "proj.titleid, title.codename AS titlename, "
        		+ "proj.propertyid, property.codename AS propertyname, "
        		+ "proj.units, proj.smsflag, proj.datecreated, proj.forecastperiod, "
        		+ "proj.hwdiscount, proj.swdiscount, "
        		+ "proj.status, status.codename AS statusname, proj.pdflink "
        		+ "FROM tblProject proj "
        		+ "LEFT JOIN tblCodeMaster title ON title.codetype = 'TITLE' AND title.codeid = proj.titleid "        		
        		+ "LEFT JOIN tblCodeMaster property ON property.codetype = 'PROPERTY' AND property.codeid = proj.propertyid "        		
        		+ "LEFT JOIN tblCodeMaster status ON status.codetype = 'STATUS' AND status.codeid = proj.status "        		
        		+ "LEFT JOIN tblUser usr ON usr.userid = proj.userid "
        		+ "WHERE usr.userid = " + userid + " "
        		+ "ORDER BY projectname";
        ProjectMapper mapper = new ProjectMapper();
        List<Project> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }
    
    public List<Project> listByTeam(int teamid) {
        String sql = "SELECT proj.projectid, proj.projectname, (proj.address).country, "
        		+ "(proj.address).zipcode, (proj.address).state, (proj.address).city, (proj.address).street, "
        		+ "proj.userid, usr.username, proj.name, proj.mobile, proj.email, "
        		+ "proj.titleid, title.codename AS titlename, "
        		+ "proj.propertyid, property.codename AS propertyname, "
        		+ "proj.units, proj.smsflag, proj.datecreated, proj.forecastperiod, "
        		+ "proj.hwdiscount, proj.swdiscount, "
        		+ "proj.status, status.codename AS statusname, proj.pdflink "
        		+ "FROM tblProject proj "
        		+ "LEFT JOIN tblCodeMaster title ON title.codetype = 'TITLE' AND title.codeid = proj.titleid "        		
        		+ "LEFT JOIN tblCodeMaster property ON property.codetype = 'PROPERTY' AND property.codeid = proj.propertyid "        		
        		+ "LEFT JOIN tblCodeMaster status ON status.codetype = 'STATUS' AND status.codeid = proj.status "        		
        		+ "LEFT JOIN tblUser usr ON usr.userid = proj.userid "
        		+ "WHERE usr.teamid = " + teamid + " "
        		+ "ORDER BY projectname";
        ProjectMapper mapper = new ProjectMapper();
        List<Project> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }

    public List<Project> listByBranch(int branchid) {
        String sql = "SELECT proj.projectid, proj.projectname, (proj.address).country, "
        		+ "(proj.address).zipcode, (proj.address).state, (proj.address).city, (proj.address).street, "
        		+ "proj.userid, usr.username, proj.name, proj.mobile, proj.email, "
        		+ "proj.titleid, title.codename AS titlename, "
        		+ "proj.propertyid, property.codename AS propertyname, "
        		+ "proj.units, proj.smsflag, proj.datecreated, proj.forecastperiod, "
        		+ "proj.hwdiscount, proj.swdiscount, "
        		+ "proj.status, status.codename AS statusname, proj.pdflink "
        		+ "FROM tblProject proj "
        		+ "LEFT JOIN tblCodeMaster title ON title.codetype = 'TITLE' AND title.codeid = proj.titleid "        		
        		+ "LEFT JOIN tblCodeMaster property ON property.codetype = 'PROPERTY' AND property.codeid = proj.propertyid "        		
        		+ "LEFT JOIN tblCodeMaster status ON status.codetype = 'STATUS' AND status.codeid = proj.status "        		
        		+ "LEFT JOIN tblUser usr ON usr.userid = proj.userid "
        		+ "WHERE usr.branchid = " + branchid + " "
        		+ "ORDER BY projectname";
        ProjectMapper mapper = new ProjectMapper();
        List<Project> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }

    public List<Project> listByCompany(int companyid) {
        String sql = "SELECT proj.projectid, proj.projectname, (proj.address).country, "
        		+ "(proj.address).zipcode, (proj.address).state, (proj.address).city, (proj.address).street, "
        		+ "proj.userid, usr.username, proj.name, proj.mobile, proj.email, "
        		+ "proj.titleid, title.codename AS titlename, "
        		+ "proj.propertyid, property.codename AS propertyname, "
        		+ "proj.units, proj.smsflag, proj.datecreated, proj.forecastperiod, "
        		+ "proj.hwdiscount, proj.swdiscount, "
        		+ "proj.status, status.codename AS statusname, proj.pdflink "
        		+ "FROM tblProject proj "
        		+ "LEFT JOIN tblCodeMaster title ON title.codetype = 'TITLE' AND title.codeid = proj.titleid "        		
        		+ "LEFT JOIN tblCodeMaster property ON property.codetype = 'PROPERTY' AND property.codeid = proj.propertyid "        		
        		+ "LEFT JOIN tblCodeMaster status ON status.codetype = 'STATUS' AND status.codeid = proj.status "        		
        		+ "LEFT JOIN tblUser usr ON usr.userid = proj.userid "
        		+ "WHERE usr.companyid = " + companyid + " "
        		+ "ORDER BY projectname";
        ProjectMapper mapper = new ProjectMapper();
        List<Project> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }
    
   public boolean isExist(Project project) {
        return get(project.getprojectid())!=null;
    }
}