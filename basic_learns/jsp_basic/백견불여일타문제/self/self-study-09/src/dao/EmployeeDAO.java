package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dto.EmployeeVO;
import jdbc.DBManager;

public class EmployeeDAO {
	private EmployeeDAO(){}
	private static EmployeeDAO inst = null;
	public static EmployeeDAO getInstance() {
		if(inst == null)
			inst = new EmployeeDAO();
		return inst;
	}
	
	public int userCheck(String id,String password,String lev) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement("select * from employees where id = ?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {				
				if(password.equals(rs.getString("password")) && lev.equals(rs.getString("lev"))) 
					result = 1;
				else 
					result = -1;				
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt,rs);
		}
		return result;
	}
	
	public int insertEmployee(EmployeeVO emp) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String query = "insert into employees (id,password,name,lev,enter,gender,phone) values(?,?,?,?,?,?,?)";
		int result = -1;		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, emp.getId());
			pstmt.setString(2, emp.getPassword());
			pstmt.setString(3, emp.getName());
			pstmt.setString(4, emp.getLev());
			pstmt.setTimestamp(5, toTimestamp(emp.getEnter()));
			pstmt.setString(6, emp.getGender());
			pstmt.setString(7, emp.getPhone());
			result = pstmt.executeUpdate();			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}
		return result;
	}
	
	public EmployeeVO getEmployee(String id) {		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		EmployeeVO employee = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement("select * from employees where id = ?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				employee = convertEmployee(rs);								
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt,rs);
		}
		return employee;
	}
	
	public int updateEmployee(EmployeeVO emp) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = -1;	
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement("update employees set lev=?,phone=?,password=? where id=?");			
			pstmt.setString(1, emp.getLev());			
			pstmt.setString(2, emp.getPhone());
			pstmt.setString(3, emp.getPassword());
			pstmt.setString(4, emp.getId());
			result = pstmt.executeUpdate();			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}
		return result;
	}
	
	public List<EmployeeVO> getEmployeeList() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<EmployeeVO> list = new ArrayList<>();
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement("select * from employees");			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				list.add(convertEmployee(rs));
			}			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt,rs);
		}
		return list;
	}
	
	private EmployeeVO convertEmployee(ResultSet rs) throws SQLException {
		return new EmployeeVO(
				rs.getString("id"),
				rs.getString("password"),
				rs.getString("name"),
				rs.getString("lev"),
				toDate(rs.getTimestamp("enter")),
				rs.getString("gender"),
				rs.getString("phone")
				);
				
	}	
	private Date toDate(Timestamp date) {
		return date == null ? null : new Date(date.getTime());
	}
	
	private Timestamp toTimestamp(Date date) {
		return new Timestamp(date.getTime());
	}

}
