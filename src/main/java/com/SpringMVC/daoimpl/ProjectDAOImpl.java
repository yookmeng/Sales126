	package com.SpringMVC.daoimpl;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import com.SpringMVC.model.Address;
import com.SpringMVC.model.Project;
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

    public void createpdf(Project project, HttpServletRequest request) {
    	String base = request.getRequestURL().toString().substring(0, request.getRequestURL().toString().length() - request.getRequestURI().toString().length())+request.getContextPath();
    	String filename = project.getprojectname()+".pdf";
    	String pdfPath = "/pdf";
    	String absolutePDFPath = request.getSession().getServletContext().getRealPath(pdfPath);
    	File PDFfile = new File(absolutePDFPath, filename);
    	Font TitleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 24);
    	Font BodyFont = FontFactory.getFont(FontFactory.HELVETICA, 12);
    	Document document = new Document();
        try
        {        	
           PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(PDFfile.getAbsolutePath()));
           document.open();
           String imageUrl = base+"/img/company_logo.png";
           Image image = Image.getInstance(new URL(imageUrl));
           image.setAbsolutePosition(325f, 725f);
           image.scaleAbsolute(100, 200);
           document.add(image);
           document.add(new Phrase("\n"));
           document.add(new Paragraph("QUOTATION", TitleFont));
           document.add(new Phrase("\n"));
           document.add(new Phrase("\n"));
           document.add(new LineSeparator());
           LineSeparator sep = new LineSeparator();
           sep.setOffset(5);
           document.add(sep);
           document.add(new Phrase("\n"));
           document.add(new Phrase("\n"));
           document.add(new Paragraph(project.getprojectname().toString(), BodyFont));           
           document.add( Chunk.NEWLINE );
           
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
           
           PdfPCell cellDLine2Col1 = new PdfPCell(new Paragraph("Hardware Discount", BodyFont));
           cellDLine2Col1.setBorder(0);
           cellDLine2Col1.setPaddingLeft(10);
           cellDLine2Col1.setHorizontalAlignment(Element.ALIGN_LEFT);
           cellDLine2Col1.setVerticalAlignment(Element.ALIGN_MIDDLE);

           PdfPCell cellDLine2Col2 = new PdfPCell(new Paragraph(String.format("%.2f", project.gethwdiscount()), BodyFont));
           cellDLine2Col2.setBorder(0);
           cellDLine2Col2.setHorizontalAlignment(Element.ALIGN_RIGHT);
           cellDLine2Col2.setVerticalAlignment(Element.ALIGN_MIDDLE);

           PdfPCell cellDLine3Col1 = new PdfPCell(new Paragraph("Software Discount", BodyFont));
           cellDLine3Col1.setBorder(0);
           cellDLine3Col1.setPaddingLeft(10);
           cellDLine3Col1.setHorizontalAlignment(Element.ALIGN_LEFT);
           cellDLine3Col1.setVerticalAlignment(Element.ALIGN_MIDDLE);

           PdfPCell cellDLine3Col2 = new PdfPCell(new Paragraph(String.format("%.2f", project.getswdiscount()), BodyFont));
           cellDLine3Col2.setBorder(0);
           cellDLine3Col2.setHorizontalAlignment(Element.ALIGN_RIGHT);
           cellDLine3Col2.setVerticalAlignment(Element.ALIGN_MIDDLE);


           cellDLine1Col1.setUseBorderPadding(true);
           cellDLine1Col2.setUseBorderPadding(true);
           cellDLine2Col1.setUseBorderPadding(true);
           cellDLine2Col2.setUseBorderPadding(true);
           cellDLine3Col1.setUseBorderPadding(true);
           cellDLine3Col2.setUseBorderPadding(true);

           tableDetail.addCell(cellDLine1Col1);
           tableDetail.addCell(cellDLine1Col2);
           tableDetail.addCell(cellDLine2Col1);
           tableDetail.addCell(cellDLine2Col2);
           tableDetail.addCell(cellDLine3Col1);
           tableDetail.addCell(cellDLine3Col2);

           document.add(tableDetail);

           PdfPTable tableFooter = new PdfPTable(2); // 2 columns.
           tableFooter.setWidthPercentage(90); //Width 100%
           tableFooter.setSpacingBefore(5f); //Space before table
           tableFooter.setSpacingAfter(5f); //Space after table
           //Set Column widths
           float[] footerColumnWidths = {10f, 40f};
           tableFooter.setWidths(footerColumnWidths);
           document.add(tableFooter);
           
           document.close();
           writer.close();
           
           String sql = "UPDATE tblProject SET pdflink=? "
           		+ "WHERE projectid=?";
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