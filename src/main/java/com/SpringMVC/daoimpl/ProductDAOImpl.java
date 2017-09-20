	package com.SpringMVC.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
 
import javax.sql.DataSource;
 
import com.SpringMVC.model.Product;
import com.SpringMVC.dao.ProductDAO;
import com.SpringMVC.mapper.ProductMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
@Repository 
public class ProductDAOImpl extends JdbcDaoSupport implements ProductDAO { 
    @Autowired
    public ProductDAOImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }
	
    public void save(Product product) {
        // insert
        String sql = "INSERT INTO tblProduct ("
        		+ "productname, prodtype, companyid, price, "
        		+ "desc01, desc02, desc03, desc04, desc05,"
        		+ "desc06, desc07, desc08, desc09, desc10) "
        		+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        this.getJdbcTemplate().update(sql, 
        		product.getproductname(), product.getprodtype(), product.getcompanyid(), product.getprice(), 
        		product.getdesc01(), product.getdesc02(), product.getdesc03(), 
        		product.getdesc04(), product.getdesc05(), product.getdesc06(),
        		product.getdesc07(), product.getdesc08(), product.getdesc09(), 
        		product.getdesc10());
    }
    
    public void update(Product product) {
        // update
        String sql = "UPDATE tblProduct SET productname=?, prodtype=?, price=?, "
        		+ "desc01=?, desc02=?, desc03=?, desc04=?, desc05=?, "
        		+ "desc06=?, desc07=?, desc08=?, desc09=?, desc10=? WHERE productid=?";
        this.getJdbcTemplate().update(sql, 
        		product.getproductname(), product.getprodtype(), product.getprice(), 
        		product.getdesc01(), product.getdesc02(), product.getdesc03(), 
        		product.getdesc04(), product.getdesc05(), product.getdesc06(),
        		product.getdesc07(), product.getdesc08(), product.getdesc09(), 
        		product.getdesc10(), product.getproductid());
    }

    public void delete(int productid) {
        String sql = "DELETE FROM tblProduct WHERE productid=?";
        this.getJdbcTemplate().update(sql, productid);
    }
    
    public List<Product> getAll(int companyid) {
        String sql = "SELECT productid, productname, prodtype, codename AS prodtypename, "
        		+ "companyid, price, "
        		+ "desc01, desc02, desc03, desc04, desc05, "
        		+ "desc06, desc07, desc08, desc09, desc10 "
        		+ "FROM tblProduct "
        		+ "LEFT JOIN tblCodeMaster ON codetype = 'PRODUCT' AND codeid = prodtype "        		
        		+ "WHERE companyid = " + companyid;
        ProductMapper mapper = new ProductMapper();
        List<Product> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }

    public Product get(int productid) {
        String sql = "SELECT productid, productname, prodtype, codename AS prodtypename, "
        		+ "companyid, price, "
        		+ "desc01, desc02, desc03, desc04, desc05, "
        		+ "desc06, desc07, desc08, desc09, desc10 "
        		+ "FROM tblProduct "
        		+ "LEFT JOIN tblCodeMaster ON codetype = 'PRODUCT' AND codeid = prodtype "        		
	    		+ "WHERE productid=" + productid;
	    return this.getJdbcTemplate().query(sql, new ResultSetExtractor<Product>() {
	 
	        @Override
	        public Product extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	            if (rs.next()) {
	                Product product = new Product();
	                product.setproductid(rs.getInt("productid"));
	                product.setproductname(rs.getString("productname"));
	                product.setprodtype(rs.getString("prodtype"));
	                product.setprodtypename(rs.getString("prodtypename"));
	                product.setcompanyid(rs.getInt("companyid"));
	                product.setprice(rs.getFloat("price"));
	                product.setdesc01(rs.getString("desc01"));
	                product.setdesc02(rs.getString("desc02"));
	                product.setdesc03(rs.getString("desc03"));
	                product.setdesc04(rs.getString("desc04"));
	                product.setdesc05(rs.getString("desc05"));
	                product.setdesc06(rs.getString("desc06"));
	                product.setdesc07(rs.getString("desc07"));
	                product.setdesc08(rs.getString("desc08"));
	                product.setdesc09(rs.getString("desc09"));
	                product.setdesc10(rs.getString("desc10"));
	                return product;
	            }	 
	            return null;
	        }
        });
    }

   public boolean isExist(Product product) {
        return get(product.getproductid())!=null;
    }
}