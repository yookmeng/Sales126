package com.SpringMVC.daoimpl;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import com.SpringMVC.model.Quotation;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.SpringMVC.dao.QuotationDAO;
import com.SpringMVC.mapper.QuotationMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
@Repository 
public class QuotationDAOImpl extends JdbcDaoSupport implements QuotationDAO {
 
    @Autowired
    public QuotationDAOImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }
	
    public void save(Quotation quotation) {
		Connection conn = this.getConnection();
		try {
			conn.setAutoCommit(true);
	    	CallableStatement proc = conn.prepareCall("{ ? = call spQuotationInsUpd("
	    			+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, "
	    			+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, "
	    			+ "?, ?) }");
	    	proc.registerOutParameter(1, Types.OTHER);
	    	proc.setString(2, "0");
	    	proc.setInt(3, quotation.getquotationid());
	    	proc.setDate(4, quotation.getquotationdate());
	    	proc.setInt(5, quotation.getprospectid());
	    	proc.setInt(6, quotation.getactivityid());
	    	proc.setInt(7, quotation.getbrandid());
	    	proc.setInt(8, quotation.getmodelid());
	    	proc.setString(9, quotation.getcolour());
	    	proc.setFloat(10, quotation.getretailprice());
	    	proc.setFloat(11, quotation.getsuminsured());
	    	proc.setString(12, quotation.getncd());
	    	proc.setFloat(13, quotation.getpremium());
	    	proc.setFloat(14, quotation.getpremiumafterncd());
	    	proc.setFloat(15, quotation.getroadtax());
	    	proc.setFloat(16, quotation.getregistrationfee());
	    	proc.setFloat(17, quotation.gethandlingcharges());
	    	proc.setFloat(18, quotation.getextendedwarranty());
	    	proc.setFloat(19, quotation.getothercharges());
	    	proc.setFloat(20, quotation.getdiscount());
	    	proc.setFloat(21, quotation.getquoteamount());
	    	proc.setString(22, quotation.getterm());
	    	proc.setString(23, quotation.getremark());
	    	proc.execute();
	    	proc.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    	
    }
    
    public void update(Quotation quotation) {
		Connection conn = this.getConnection();
    	try {
			conn.setAutoCommit(true);
	    	CallableStatement proc = conn.prepareCall("{ ? = call spQuotationInsUpd("
	    			+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, "
	    			+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, "
	    			+ "?, ?) }");
	    	proc.registerOutParameter(1, Types.OTHER);
	    	proc.setString(2, "1");
	    	proc.setInt(3, quotation.getquotationid());
	    	proc.setDate(4, quotation.getquotationdate());
	    	proc.setInt(5, quotation.getprospectid());
	    	proc.setInt(6, quotation.getactivityid());
	    	proc.setInt(7, quotation.getbrandid());
	    	proc.setInt(8, quotation.getmodelid());
	    	proc.setString(9, quotation.getcolour());
	    	proc.setFloat(10, quotation.getretailprice());
	    	proc.setFloat(11, quotation.getsuminsured());
	    	proc.setString(12, quotation.getncd());
	    	proc.setFloat(13, quotation.getpremium());
	    	proc.setFloat(14, quotation.getpremiumafterncd());
	    	proc.setFloat(15, quotation.getroadtax());
	    	proc.setFloat(16, quotation.getregistrationfee());
	    	proc.setFloat(17, quotation.gethandlingcharges());
	    	proc.setFloat(18, quotation.getextendedwarranty());
	    	proc.setFloat(19, quotation.getothercharges());
	    	proc.setFloat(20, quotation.getdiscount());
	    	proc.setFloat(21, quotation.getquoteamount());
	    	proc.setString(22, quotation.getterm());
	    	proc.setString(23, quotation.getremark());
	    	proc.execute();
	    	proc.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    	
    }

    public void delete(int quotationid) {
		Connection conn = this.getConnection();
    	try {
			conn.setAutoCommit(true);
	    	CallableStatement proc = conn.prepareCall("{ ? = call spQuotationDel(?) }");
	    	proc.registerOutParameter(1, Types.OTHER);
	    	proc.setInt(2, quotationid);

	    	proc.execute();
	    	proc.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    	
    }
    
    public void createpdf(Quotation quotation, HttpServletRequest request) {
    	String base = request.getRequestURL().toString().substring(0, request.getRequestURL().toString().length() - request.getRequestURI().toString().length())+request.getContextPath();
    	String filename = quotation.getquotationdate()+"_"+quotation.getquotationid()+".pdf";
    	String quotationPath = "/quote";
    	String absoluteQuotationPath = request.getSession().getServletContext().getRealPath(quotationPath);
    	File PDFfile = new File(absoluteQuotationPath, filename);
    	Font TitleFont = FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.UNDERLINE);
    	Font BodyFont = FontFactory.getFont(FontFactory.TIMES, 12);
    	Font FooterFont = FontFactory.getFont(FontFactory.TIMES, 8);
    	Document document = new Document();
        try
        {        	
           PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(PDFfile.getAbsolutePath()));
           document.open();
           String imageUrl = base+"/img/company_logo.png";
           Image image = Image.getInstance(new URL(imageUrl));
           image.setAbsolutePosition(25f, 725f);
           image.scaleAbsolute(100, 90);
           document.add(image);
           document.add(new Phrase("\n"));
           document.add(new Phrase("\n"));
           document.add(new Phrase("\n"));
           document.add(new Phrase("\n"));
           document.add(new Phrase("\n"));
           document.add(new Paragraph(quotation.getquotationdate().toString(), BodyFont));           
           document.add(new Paragraph(quotation.getfirstname() + quotation.getlastname(), BodyFont));           
           document.add(new Paragraph("Dear Sir,", BodyFont));
           document.add( Chunk.NEWLINE );
           Paragraph paragraphTitle = new Paragraph("QUOTATION FOR "+quotation.getmodelname(), TitleFont);
           document.add(paragraphTitle);
           document.add(new Paragraph("We thank you for the opportunity to be  of service to you and your kind support for VOLVO.", BodyFont));
           document.add(new Paragraph("With this, we, FEDERAL AUTO CARS would like to propose the following offer for your consideration.", BodyFont));
           
           PdfPTable tableDetail = new PdfPTable(2); // 2 columns.
           tableDetail.setWidthPercentage(90); //Width 100%
           tableDetail.setSpacingBefore(5f); //Space before table
           tableDetail.setSpacingAfter(5f); //Space after table
           //Set Column widths
           float[] detailColumnWidths = {30f, 10f};
           tableDetail.setWidths(detailColumnWidths);
           
           PdfPCell cellDLine1Col1 = new PdfPCell(new Paragraph("Description", BodyFont));
           cellDLine1Col1.setBorder(0);
           cellDLine1Col1.setPaddingLeft(10);
           cellDLine1Col1.setHorizontalAlignment(Element.ALIGN_LEFT);
           cellDLine1Col1.setVerticalAlignment(Element.ALIGN_MIDDLE);
           
           PdfPCell cellDLine1Col2 = new PdfPCell(new Paragraph("RM", BodyFont));
           cellDLine1Col2.setBorder(0);
           cellDLine1Col2.setHorizontalAlignment(Element.ALIGN_RIGHT);
           cellDLine1Col2.setVerticalAlignment(Element.ALIGN_MIDDLE);
           
           PdfPCell cellDLine2Col1 = new PdfPCell(new Paragraph("Nett Selling Price (Including GST)", BodyFont));
           cellDLine2Col1.setBorder(0);
           cellDLine2Col1.setPaddingLeft(10);
           cellDLine2Col1.setHorizontalAlignment(Element.ALIGN_LEFT);
           cellDLine2Col1.setVerticalAlignment(Element.ALIGN_MIDDLE);

           PdfPCell cellDLine2Col2 = new PdfPCell(new Paragraph(String.format("%.2f", quotation.getretailprice()), BodyFont));
           cellDLine2Col2.setBorder(0);
           cellDLine2Col2.setHorizontalAlignment(Element.ALIGN_RIGHT);
           cellDLine2Col2.setVerticalAlignment(Element.ALIGN_MIDDLE);

           PdfPCell cellDLine3Col1 = new PdfPCell(new Paragraph("Registration Fee", BodyFont));
           cellDLine3Col1.setBorder(0);
           cellDLine3Col1.setPaddingLeft(10);
           cellDLine3Col1.setHorizontalAlignment(Element.ALIGN_LEFT);
           cellDLine3Col1.setVerticalAlignment(Element.ALIGN_MIDDLE);

           PdfPCell cellDLine3Col2 = new PdfPCell(new Paragraph(String.format("%.2f", quotation.getregistrationfee()), BodyFont));
           cellDLine3Col2.setBorder(0);
           cellDLine3Col2.setHorizontalAlignment(Element.ALIGN_RIGHT);
           cellDLine3Col2.setVerticalAlignment(Element.ALIGN_MIDDLE);

           PdfPCell cellDLine4Col1 = new PdfPCell(new Paragraph("Road Tax", BodyFont));
           cellDLine4Col1.setBorder(0);
           cellDLine4Col1.setPaddingLeft(10);
           cellDLine4Col1.setHorizontalAlignment(Element.ALIGN_LEFT);
           cellDLine4Col1.setVerticalAlignment(Element.ALIGN_MIDDLE);

           PdfPCell cellDLine4Col2 = new PdfPCell(new Paragraph(String.format("%.2f", quotation.getroadtax()), BodyFont));
           cellDLine4Col2.setBorder(0);
           cellDLine4Col2.setHorizontalAlignment(Element.ALIGN_RIGHT);
           cellDLine4Col2.setVerticalAlignment(Element.ALIGN_MIDDLE);

           PdfPTable tableDetail2 = new PdfPTable(2); // 2 columns.
           tableDetail2.setWidthPercentage(90); //Width 100%
           tableDetail2.setWidths(detailColumnWidths);

           PdfPCell cellDLine5Col1 = new PdfPCell(new Paragraph("Isurance Premium ("+String.format("%.2f", quotation.getpremium())+" - "+quotation.getncd()+"%)", BodyFont));
           cellDLine5Col1.setBorder(0);
           cellDLine5Col1.setPaddingLeft(10);
           cellDLine5Col1.setHorizontalAlignment(Element.ALIGN_LEFT);
           cellDLine5Col1.setVerticalAlignment(Element.ALIGN_MIDDLE);

           PdfPCell cellDLine5Col2 = new PdfPCell(new Paragraph(String.format("%.2f", quotation.getpremiumafterncd()), BodyFont));
           cellDLine5Col2.setBorder(0);
           cellDLine5Col2.setHorizontalAlignment(Element.ALIGN_RIGHT);
           cellDLine5Col2.setVerticalAlignment(Element.ALIGN_MIDDLE);

           PdfPCell cellDLine6Col1 = new PdfPCell(new Paragraph("Others", BodyFont));
           cellDLine6Col1.setBorder(0);
           cellDLine6Col1.setPaddingLeft(10);
           cellDLine6Col1.setHorizontalAlignment(Element.ALIGN_LEFT);
           cellDLine6Col1.setVerticalAlignment(Element.ALIGN_MIDDLE);

           PdfPCell cellDLine6Col2 = new PdfPCell(new Paragraph(String.format("%.2f", quotation.getothercharges()), BodyFont));
           cellDLine6Col2.setBorder(0);
           cellDLine6Col2.setHorizontalAlignment(Element.ALIGN_RIGHT);
           cellDLine6Col2.setVerticalAlignment(Element.ALIGN_MIDDLE);

           PdfPCell cellDLine7Col1 = new PdfPCell(new Paragraph("Grand Total", TitleFont));
           cellDLine7Col1.setBorder(0);
           cellDLine7Col1.setPaddingLeft(10);
           cellDLine7Col1.setHorizontalAlignment(Element.ALIGN_LEFT);
           cellDLine7Col1.setVerticalAlignment(Element.ALIGN_MIDDLE);

           PdfPCell cellDLine7Col2 = new PdfPCell(new Paragraph(String.format("%.2f", quotation.getquoteamount()), TitleFont));
           cellDLine7Col2.setBorder(0);
           cellDLine7Col2.setHorizontalAlignment(Element.ALIGN_RIGHT);
           cellDLine7Col2.setVerticalAlignment(Element.ALIGN_MIDDLE);

           cellDLine1Col1.setUseBorderPadding(true);
           cellDLine1Col2.setUseBorderPadding(true);
           cellDLine2Col1.setUseBorderPadding(true);
           cellDLine2Col2.setUseBorderPadding(true);
           cellDLine3Col1.setUseBorderPadding(true);
           cellDLine3Col2.setUseBorderPadding(true);
           cellDLine4Col1.setUseBorderPadding(true);
           cellDLine4Col2.setUseBorderPadding(true);
           cellDLine5Col1.setUseBorderPadding(true);
           cellDLine5Col2.setUseBorderPadding(true);
           cellDLine6Col1.setUseBorderPadding(true);
           cellDLine6Col2.setUseBorderPadding(true);
           cellDLine7Col1.setUseBorderPadding(true);
           cellDLine7Col2.setUseBorderPadding(true);

           tableDetail.addCell(cellDLine1Col1);
           tableDetail.addCell(cellDLine1Col2);
           tableDetail.addCell(cellDLine2Col1);
           tableDetail.addCell(cellDLine2Col2);
           tableDetail.addCell(cellDLine3Col1);
           tableDetail.addCell(cellDLine3Col2);
           tableDetail.addCell(cellDLine4Col1);
           tableDetail.addCell(cellDLine4Col2);
           tableDetail.addCell(cellDLine5Col1);
           tableDetail.addCell(cellDLine5Col2);
           tableDetail.addCell(cellDLine6Col1);
           tableDetail.addCell(cellDLine6Col2);
           tableDetail.addCell(cellDLine7Col1);
           tableDetail.addCell(cellDLine7Col2);

           document.add(tableDetail);

           PdfPTable tableFooter = new PdfPTable(2); // 2 columns.
           tableFooter.setWidthPercentage(90); //Width 100%
           tableFooter.setSpacingBefore(5f); //Space before table
           tableFooter.setSpacingAfter(5f); //Space after table
           //Set Column widths
           float[] footerColumnWidths = {10f, 40f};
           tableFooter.setWidths(footerColumnWidths);
           
           PdfPCell cellFLine1Col1 = new PdfPCell(new Paragraph("Colour", BodyFont));
           cellFLine1Col1.setBorder(0);
           cellFLine1Col1.setPaddingLeft(10);
           cellFLine1Col1.setHorizontalAlignment(Element.ALIGN_LEFT);
           cellFLine1Col1.setVerticalAlignment(Element.ALIGN_MIDDLE);

           PdfPCell cellFLine1Col2 = new PdfPCell(new Paragraph(": "+quotation.getcolour(), BodyFont));
           cellFLine1Col2.setBorder(0);
           cellFLine1Col2.setPaddingLeft(10);
           cellFLine1Col2.setHorizontalAlignment(Element.ALIGN_LEFT);
           cellFLine1Col2.setVerticalAlignment(Element.ALIGN_MIDDLE);
           
           PdfPCell cellFLine2Col1 = new PdfPCell(new Paragraph("Booking Fee", BodyFont));
           cellFLine2Col1.setBorder(0);
           cellFLine2Col1.setPaddingLeft(10);
           cellFLine2Col1.setHorizontalAlignment(Element.ALIGN_LEFT);
           cellFLine2Col1.setVerticalAlignment(Element.ALIGN_MIDDLE);

           PdfPCell cellFLine2Col2 = new PdfPCell(new Paragraph(": Cash / Cheque of RM5,000.00 or Local Purchase Order", BodyFont));
           cellFLine2Col2.setBorder(0);
           cellFLine2Col2.setPaddingLeft(10);
           cellFLine2Col2.setHorizontalAlignment(Element.ALIGN_LEFT);
           cellFLine2Col2.setVerticalAlignment(Element.ALIGN_MIDDLE);
           
           PdfPCell cellFLine3Col1 = new PdfPCell(new Paragraph("FOC", BodyFont));
           cellFLine3Col1.setBorder(0);
           cellFLine3Col1.setPaddingLeft(10);
           cellFLine3Col1.setHorizontalAlignment(Element.ALIGN_LEFT);
           cellFLine3Col1.setVerticalAlignment(Element.ALIGN_MIDDLE);

           PdfPCell cellFLine3Col2 = new PdfPCell(new Paragraph(": 5 Years Extended Warranty", BodyFont));
           cellFLine3Col2.setBorder(0);
           cellFLine3Col2.setPaddingLeft(10);
           cellFLine3Col2.setHorizontalAlignment(Element.ALIGN_LEFT);
           cellFLine3Col2.setVerticalAlignment(Element.ALIGN_MIDDLE);
           
           PdfPCell cellFLine4Col1 = new PdfPCell(new Paragraph("FOC", BodyFont));
           cellFLine4Col1.setBorder(0);
           cellFLine4Col1.setPaddingLeft(10);
           cellFLine4Col1.setHorizontalAlignment(Element.ALIGN_LEFT);
           cellFLine4Col1.setVerticalAlignment(Element.ALIGN_MIDDLE);

           PdfPCell cellFLine4Col2 = new PdfPCell(new Paragraph(": 5 Years VOC", BodyFont));
           cellFLine4Col2.setBorder(0);
           cellFLine4Col2.setPaddingLeft(10);
           cellFLine4Col2.setHorizontalAlignment(Element.ALIGN_LEFT);
           cellFLine4Col2.setVerticalAlignment(Element.ALIGN_MIDDLE);
           
           PdfPCell cellFLine5Col1 = new PdfPCell(new Paragraph("FOC", BodyFont));
           cellFLine5Col1.setBorder(0);
           cellFLine5Col1.setPaddingLeft(10);
           cellFLine5Col1.setHorizontalAlignment(Element.ALIGN_LEFT);
           cellFLine5Col1.setVerticalAlignment(Element.ALIGN_MIDDLE);

           PdfPCell cellFLine5Col2 = new PdfPCell(new Paragraph(": 5 Years Lube Service", BodyFont));
           cellFLine5Col2.setBorder(0);
           cellFLine5Col2.setPaddingLeft(10);
           cellFLine5Col2.setHorizontalAlignment(Element.ALIGN_LEFT);
           cellFLine5Col2.setVerticalAlignment(Element.ALIGN_MIDDLE);
           
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

           document.add( Chunk.NEWLINE );
           document.add(new Paragraph("Please note that the above price is not inclusive of insurance and HP and subject to change without prior notice.", BodyFont));
           document.add( Chunk.NEWLINE );
           document.add(new Paragraph("For further enquiry on this quotation kindly contact us. We hope that our quotation will serve your purpose and awaiting for your valuable order.", BodyFont));
           document.add( Chunk.NEWLINE );
           document.add(new Paragraph("Thank you.", BodyFont));
           document.add( Chunk.NEWLINE );
           document.add(new Paragraph("Yours faithfully,", BodyFont));
           document.add(new Paragraph("FEDERAL AUTO CARS SDN BHD", BodyFont));
           document.add( Chunk.NEWLINE );
           document.add( Chunk.NEWLINE );
           document.add( Chunk.NEWLINE );
           Paragraph paragraphFooter = new Paragraph("*This is computer generated quotation, no signature is required.", FooterFont);
           document.add(paragraphFooter);
           
           document.close();
           writer.close();
           
           String sql = "UPDATE tblActivity SET quotationpdflink=? "
           		+ "WHERE activityid=?";
           this.getJdbcTemplate().update(sql, base+quotationPath+"/"+filename, quotation.getactivityid());
           
        } catch (Exception e) {
            e.printStackTrace();
        }   
    }

    public Quotation get(int quotationid) {
	    String sql = "SELECT q.quotationid, q.quotationdate, q.prospectid, p.firstname, p.lastname, "
	    		+ "q.activityid, q.brandid, b.brandname, q.modelid, m.modelname, m.colour, "
	    		+ "q.retailprice, q.suminsured, q.ncd, q.premium, q.premiumafterncd, q.roadtax, "
	    		+ "q.registrationfee, q.handlingcharges, q.extendedwarranty, q.othercharges,"
	    		+ "q.discount, q.quoteamount, q.term, q.remark, a.quotationpdflink "
	    		+ "FROM tblQuotation q "
	    		+ "LEFT JOIN tblProspect p ON p.prospectid = q.prospectid "
	    		+ "LEFT JOIN tblActivity a ON a.activityid = q.activityid "	    		
	    		+ "LEFT JOIN tblBrand b ON b.brandid = q.brandid "
	    		+ "LEFT JOIN tblModel m ON m.brandid = q.brandid AND m.modelid = q.modelid "
	    		+ "WHERE q.quotationid="+quotationid;
	    return this.getJdbcTemplate().query(sql, new ResultSetExtractor<Quotation>() {
	 
			@Override
	        public Quotation extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	            if (rs.next()) {
	            	Quotation quotation = new Quotation();
	            	quotation.setquotationid(rs.getInt("quotationid"));
	            	quotation.setquotationdate(rs.getDate("quotationdate"));
	            	quotation.setprospectid(rs.getInt("prospectid"));
	            	quotation.setfirstname(rs.getString("firstname"));
	            	quotation.setlastname(rs.getString("lastname"));
	            	quotation.setactivityid(rs.getInt("activityid"));
	            	quotation.setbrandid(rs.getInt("brandid"));
	            	quotation.setbrandname(rs.getString("brandname"));
	            	quotation.setmodelid(rs.getInt("modelid"));
	            	quotation.setmodelname(rs.getString("modelname"));
	            	quotation.setcolour(rs.getString("colour"));
	            	quotation.setretailprice(rs.getFloat("retailprice"));
	            	quotation.setsuminsured(rs.getFloat("suminsured"));
	            	quotation.setncd(rs.getString("ncd"));
	            	quotation.setpremium(rs.getFloat("premium"));
	            	quotation.setpremiumafterncd(rs.getFloat("premiumafterncd"));
	            	quotation.setroadtax(rs.getFloat("roadtax"));
	            	quotation.setregistrationfee(rs.getFloat("registrationfee"));
	            	quotation.sethandlingcharges(rs.getFloat("handlingcharges"));
	            	quotation.setextendedwarranty(rs.getFloat("extendedwarranty"));
	            	quotation.setothercharges(rs.getFloat("othercharges"));
	            	quotation.setdiscount(rs.getFloat("discount"));
	            	quotation.setquoteamount(rs.getFloat("quoteamount"));
	            	quotation.setterm(rs.getString("term"));
	            	quotation.setremark(rs.getString("remark"));
	            	quotation.setquotationpdflink(rs.getString("quotationpdflink"));
	                return quotation;
	            }	 
	            return null;
	        }
        });
    }

    public List<Quotation> list(int prospectid) {
	    String sql = "SELECT q.quotationid, q.quotationdate, q.prospectid, p.firstname AS prospectname, "
	    		+ "q.activityid, q.brandid, b.brandname, q.modelid, m.modelname, m.colour, "
	    		+ "q.retailprice, q.suminsured, q.ncd, q.premium, q.premiumafterncd, q.roadtax, "
	    		+ "registrationfee, q.handlingcharges, q.extendedwarranty, q.othercharges,"
	    		+ "q.discount, q.quoteamount, q.term, q.remark, a.quotationpdfink "
	    		+ "FROM tblQuotation q "
	    		+ "LEFT JOIN tblProspect p ON p.prospectid = q.prospectid "
	    		+ "LEFT JOIN tblActivity a ON a.activityid = q.activityid "	    		
	    		+ "LEFT JOIN tblBrand b ON b.brandid = q.brandid "
	    		+ "LEFT JOIN tblModel m ON m.brandid = q.brandid AND m.modelid = q.modelid "
	    		+ "WHERE q.prospectid="+prospectid+" "
				+ "ORDER BY quotationid ";
	    QuotationMapper mapper = new QuotationMapper();
        List<Quotation> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }

    public List<Quotation> listByUser(int userid) {
	    String sql = "SELECT q.quotationid, q.quotationdate, q.prospectid, p.firstname AS prospectname, "
	    		+ "q.activityid, q.brandid, b.brandname, q.modelid, m.modelname, m.colour, "
	    		+ "q.retailprice, q.suminsured, q.ncd, q.premium, q.premiumafterncd, q.roadtax, "
	    		+ "q.registrationfee, q.handlingcharges, q.extendedwarranty, q.othercharges,"
	    		+ "q.discount, q.quoteamount, q.term, q.remark, a.quotationpdfink "
	    		+ "FROM tblQuotation q "
	    		+ "LEFT JOIN tblProspect p ON p.prospectid = q.prospectid "
	    		+ "LEFT JOIN tblActivity a ON a.activityid = q.activityid "	    		
	    		+ "LEFT JOIN tblBrand b ON b.brandid = q.brandid "
	    		+ "LEFT JOIN tblModel m ON m.brandid = q.brandid AND m.modelid = q.modelid "
	    		+ "WHERE p.userid="+userid+" "
				+ "ORDER BY q.quotationid ";
	    QuotationMapper mapper = new QuotationMapper();
        List<Quotation> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }

    public List<Quotation> listByTeam(int teamid) {
	    String sql = "SELECT q.quotationid, q.quotationdate, q.prospectid, p.firstname AS prospectname, "
	    		+ "q.activityid, q.brandid, b.brandname, q.modelid, m.modelname, m.colour, "
	    		+ "q.retailprice, q.suminsured, q.ncd, q.premium, q.premiumafterncd, q.roadtax, "
	    		+ "q.registrationfee, q.handlingcharges, q.extendedwarranty, q.othercharges,"
	    		+ "q.discount, q.quoteamount, q.term, q.remark, a.quotationpdfink "
	    		+ "FROM tblQuotation q "
	    		+ "LEFT JOIN tblProspect p ON p.prospectid = q.prospectid "
	    		+ "LEFT JOIN tblActivity a ON a.activityid = q.activityid "	    		
	    		+ "LEFT JOIN tblUser u ON p.userid = u.userid "
	    		+ "LEFT JOIN tblBrand b ON b.brandid = q.brandid "
	    		+ "LEFT JOIN tblModel m ON m.brandid = q.brandid AND m.modelid = q.modelid "
	    		+ "WHERE u.teamid="+teamid+" "
				+ "ORDER BY q.quotationid ";
	    QuotationMapper mapper = new QuotationMapper();
        List<Quotation> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }

    public List<Quotation> listByBranch(int branchid) {
	    String sql = "SELECT q.quotationid, q.quotationdate, q.prospectid, p.firstname AS prospectname, "
	    		+ "q.activityid, q.brandid, b.brandname, q.modelid, m.modelname, m.colour, "
	    		+ "q.retailprice, q.suminsured, q.ncd, q.premium, q.premiumafterncd, q.roadtax, "
	    		+ "q.registrationfee, q.handlingcharges, q.extendedwarranty, q.othercharges,"
	    		+ "q.discount, q.quoteamount, q.term, q.remark, a.quotationpdfink "
	    		+ "FROM tblQuotation q "
	    		+ "LEFT JOIN tblProspect p ON p.prospectid = q.prospectid "
	    		+ "LEFT JOIN tblActivity a ON a.activityid = q.activityid "	    		
	    		+ "LEFT JOIN tblUser u ON p.userid = u.userid "
	    		+ "LEFT JOIN tblBrand b ON b.brandid = q.brandid "
	    		+ "LEFT JOIN tblModel m ON m.brandid = q.brandid AND m.modelid = q.modelid "
	    		+ "WHERE u.branchid="+branchid+" "
				+ "ORDER BY q.quotationid ";
	    QuotationMapper mapper = new QuotationMapper();
        List<Quotation> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }

    public List<Quotation> listByCompany(int companyid) {
	    String sql = "SELECT quotationid, quotationdate, q.prospectid, p.firstname AS prospectname, "
	    		+ "q.activityid, q.brandid, b.brandname, q.modelid, m.modelname, m.colour, "
	    		+ "q.retailprice, q.suminsured, q.ncd, q.premium, q.premiumafterncd, q.roadtax, "
	    		+ "q.registrationfee, q.handlingcharges, q.extendedwarranty, q.othercharges,"
	    		+ "q.discount, q.quoteamount, q.term, q.remark, a.quotationpdfink "
	    		+ "FROM tblQuotation q "
	    		+ "LEFT JOIN tblProspect p ON p.prospectid = q.prospectid "
	    		+ "LEFT JOIN tblActivity a ON a.activityid = q.activityid "	    		
	    		+ "LEFT JOIN tblUser u ON p.userid = u.userid "
	    		+ "LEFT JOIN tblBrand b ON b.brandid = q.brandid "
	    		+ "LEFT JOIN tblModel m ON m.brandid = q.brandid AND m.modelid = q.modelid "
	    		+ "WHERE u.companyid="+companyid+" "
				+ "ORDER BY q.quotationid ";
	    QuotationMapper mapper = new QuotationMapper();
        List<Quotation> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }

    public int getlastquotationid(int prospectid) {
    	String sql = "SELECT MAX(quotationid) FROM tblQuotation WHERE prospectid = ?";
        int quotationid = (int)getJdbcTemplate().queryForObject(sql, new Object[] {prospectid}, int.class);
        return quotationid;
    }
}