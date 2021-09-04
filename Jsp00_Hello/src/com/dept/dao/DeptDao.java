package com.dept.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dept.dto.DeptDto;

public class DeptDao {
	public List<DeptDto> selectList(){
		//1.드라이버 연결 
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		//2. 계정연결
				
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
		String user = "kh";
		String password = "kh";
		Connection con = null;
		try {
			con = DriverManager.getConnection(url,user,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//3. query준비
		String sql = " SELECT DEPTNO, DNAME, LOC "
					+ " FROM DEPT "
					+ " ORDER BY DEPTNO ";
		//4
		Statement stmt = null;
		ResultSet rs = null;
		List<DeptDto> list = new ArrayList<DeptDto>();
		
		try {
			stmt = con.createStatement();
			System.out.println("3쿼리준비 : "+sql);
			//4
			rs = stmt.executeQuery(sql);
			System.out.println("4.쿼리 실행 및 리턴");
			while(rs.next()) {
				DeptDto dto = new DeptDto();
				dto.setDeptno(rs.getInt(1));
				dto.setDname(rs.getString(2));
				dto.setLoc(rs.getString(3));
				list.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				stmt.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
			}
		
		}
		return list;
	}
}
