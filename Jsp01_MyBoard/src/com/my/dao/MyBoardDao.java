package com.my.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.my.dto.MyBoardDto;
import static com.my.db.JDBCTemplate.*;

public class MyBoardDao {
	public List<MyBoardDto> selectList(){
		Connection con = getConnection();
		
		String sql = " SELECT MYNO , MYNAME, MYTITLE, MYCONTENT, SYSDATE "
					+ " FROM MYBOARD "
					+ " ORDER BY MYNO DESC ";
		
		Statement stmt = null;
		ResultSet rs = null;
		List<MyBoardDto> list = new ArrayList<MyBoardDto>();
		try {
			stmt = con.createStatement();
			System.out.println("3");
			rs = stmt.executeQuery(sql);
			System.out.println(" 4 ");
			while(rs.next()) {
				MyBoardDto dto = new MyBoardDto();
				dto.setMyno(rs.getInt(1));
				dto.setMyname(rs.getString(2));
				dto.setMytitle(rs.getString(3));
				dto.setMycontent(rs.getString(4));
				dto.setMydate(rs.getDate(5));
				list.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			System.out.println("5");
			close(rs);
			close(stmt);
			close(con);
		}
		return list;
	}
	public MyBoardDto selectOne(int myno) {
		Connection con = getConnection();
		
		String sql = " SELECT MYNO, MYNAME,  MYTITLE, MYCONTENT, MYDATE "
					+ " FROM MYBOARD "
					+ " WHERE MYNO = ? ";
		PreparedStatement pstm = null;
		ResultSet rs = null;
		MyBoardDto dto = null;
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, myno);
			rs = pstm.executeQuery();
			while(rs.next()) {
				dto = new MyBoardDto();
				dto.setMyno(rs.getInt(1));
				dto.setMyname(rs.getString(2));
				dto.setMytitle(rs.getString(3));
				dto.setMycontent(rs.getString(4));
				dto.setMydate(rs.getDate(5));
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstm);
			close(con);
		}
		return dto;
	}
	public int insert(MyBoardDto dto) {
		Connection con = getConnection();
		String sql = " INSERT INTO MYBOARD "
					+ " VALUES(MYBOARDSEQ.NEXTVAL, ?, ?, ?, SYSDATE) ";
		PreparedStatement pstm = null;
		int res = 0;
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, dto.getMyname());
			pstm.setString(2, dto.getMytitle());
			pstm.setString(3, dto.getMycontent());
			res = pstm.executeUpdate();
			if(res>0) {
				commit(con);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstm);
			close(con);
		}
		return res;
	}
	public int update(MyBoardDto dto) {
		
		Connection con = getConnection();
		String sql = " UPDATE MYBOARD "
					+ " SET MYTITLE = ?, MYCONTENT = ? "
					+ " WHERE MYNO = ? ";
		PreparedStatement pstm = null;
		int res = 0;
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, dto.getMytitle());
			pstm.setString(2, dto.getMycontent());
			pstm.setInt(3, dto.getMyno());
			res = pstm.executeUpdate();
			if(res>0) {
				commit(con);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstm);
			close(con);
		}
		
		
		return res;
	}
	public int delete(int myno) {
		Connection con = getConnection();
		String sql = " DELETE FROM MYBOARD "
					+" WHERE MYNO = ? ";
		PreparedStatement pstm = null;
		int res = 0;
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, myno);
			res = pstm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstm);
			close(con);
		}
		return res;
	}

}
