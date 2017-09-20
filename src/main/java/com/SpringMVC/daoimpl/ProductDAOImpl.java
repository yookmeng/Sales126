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
        		+ "productdesc1, productdesc2, productdesc3) "
        		+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
        this.getJdbcTemplate().update(sql, 
        		product.getproductname(), product.getprodtype(), product.getcompanyid(), product.getprice(), 
        		product.getproductdesc1(), product.getproductdesc2(), product.getproductdesc3());
    }
    
    public void update(Product product) {
        // update
        String sql = "UPDATE tblProduct SET productname=?, prodtype=?, price=?, "
        		+ "productdesc1=?, productdesc2=?, productdesc3 WHERE productid=?";
        this.getJdbcTemplate().update(sql, 
        		product.getproductname(), product.getprodtype(), product.getprice(), 
        		product.getproductdesc1(), product.getproductdesc2(), product.getproductdesc3());
    }

    public void delete(int productid) {
        String sql = "DELETE FROM tblProduct WHERE productid=?";
        this.getJdbcTemplate().update(sql, productid);
    }
    
    public List<Product> getAll(int companyid) {
        String sql = "SELECT productid, productname, prodtype, codename AS prodtypename, "
        		+ "companyid, price, productdesc1, productdesc2, productdesc3 "
        		+ "FROM tblProduct "
        		+ "LEFT JOIN tblCodeMaster ON codetype = 'PRODUCT' AND codeid = prodtype "        		
        		+ "WHERE companyid = " + companyid;
        ProductMapper mapper = new ProductMapper();
        List<Product> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }

    public Product get(int productid) {
        String sql = "SELECT productid, productname, prodtype, codename AS prodtypename, "
        		+ "companyid, price, productdesc1, productdesc2, productdesc3 "
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
	                product.setprodtype(rs.getInt("prodtype"));
	                product.setprodtypename(rs.getString("prodtypename"));
	                product.setcompanyid(rs.getInt("companyid"));
	                product.setprice(rs.getFloat("price"));
	                product.setproductdesc1(rs.getString("productdesc1"));
	                product.setproductdesc2(rs.getString("productdesc2"));
	                product.setproductdesc3(rs.getString("productdesc3"));
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