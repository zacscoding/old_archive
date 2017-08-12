package com.faceonshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.faceonshop.dto.ProductVO;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class ProductDAO {
	//singleton
	private static ProductDAO inst = null;
	public static ProductDAO getInstance() {
		if(inst == null)
			inst = new ProductDAO();
		return inst;
	}
	private ProductDAO() {
		//empty
	}
	
	/////////////
	//상품 목록 가져오기
	/////////////
	public List<ProductVO> selectAllProducts() {
		String sql = "select * from product order by code desc";
		List<ProductVO> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		try {
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				list.add(convertProduct(rs));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn,pstmt,rs);
		}
		return list;
	}
	
	///////////////
	//상품 저장하기
	///////////////
	public void insertProduct(ProductVO product) {
		String sql = "insert into product (code,name,price,pictureurl,description) values(product_seq.nextval,?,?,?,?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, product.getName());
			pstmt.setInt(2,product.getPrice());
			pstmt.setString(3, product.getPictureUrl());
			pstmt.setString(4, product.getDescription());
			pstmt.executeUpdate();			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn,pstmt);
		}
	}
	
	///////////////
	//상품 찾기
	///////////////
	public ProductVO selectProductByCode(String code) {
		return selectProductByCode(Integer.parseInt(code));
	}
	public ProductVO selectProductByCode(int code) {
		String sql = "select * from product where code=?";		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ProductVO product = null;
		try {
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, code);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				product = convertProduct(rs);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn,pstmt,rs);
		}
		return product;
	}
	
	///////////////
	// 상품 변경
	///////////////
	public void updateProduct(ProductVO product) {
		String sql = "update product set name=?,price=?,pictureUrl=?,description=? where code=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, product.getName());
			pstmt.setInt(2, product.getPrice());
			pstmt.setString(3, product.getPictureUrl());
			pstmt.setString(4, product.getDescription());
			pstmt.setInt(5, product.getCode());
			pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn,pstmt);
		}	
	}
	
	///////////////
	//상품 삭제
	///////////////
	public int deleteProduct(String code) {
		String sql = "delete from product where code=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = -1;
		try {
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,code);			
			result = pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn,pstmt);
		}	
		return result;
	}
	
	
	///////////////
	//
	///////////////
	
	///////////
	//private methods
	private ProductVO convertProduct(ResultSet rs) throws SQLException {
		return new ProductVO(
				rs.getInt("code"),
				rs.getString("name"),
				rs.getInt("price"),
				rs.getString("description"),
				rs.getString("pictureUrl"));
	}

}
