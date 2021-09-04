package com.test.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.test.dto.TestDto;

import static com.test.db.JDBCTemplate.*;
public class TestDaoImpl implements TestDao {

	@Override
	public List<TestDto> selectList() {
		Connection con = getConnection();
		
		Statement stmt = null;
		ResultSet rs = null;
		
		List<TestDto> list = new ArrayList<TestDto>();
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(SELECT_LIST_SQL);
			while(rs.next()) {
				TestDto dto = new TestDto(rs.getInt(1),
										rs.getString(2),
										rs.getString(3),
										rs.getString(4),
										rs.getDate(5)
						);
				list.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
		  close(rs);
		  close(stmt);
		  close(con);
		}
		return list;
	}

	@Override
	public TestDto selectOne(int seq) {
		Connection con = getConnection();
		
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		TestDto dto = new TestDto();
		try {
			pstm = con.prepareStatement(SELECT_ONE_SQL);
			pstm.setInt(1, seq);
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				dto = new TestDto(rs.getInt(1),
									rs.getString(2),
									rs.getString(3),
									rs.getString(4),
									rs.getDate(5)
						);
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

	@Override
	public int insert(TestDto dto) {
		Connection con = getConnection();
		
		PreparedStatement pstm = null;
		int res = 0;
		try {
			pstm = con.prepareStatement(INSERT_SQL);
			pstm.setString(1, dto.getWriter());
			pstm.setString(2, dto.getTitle());
			pstm.setString(3, dto.getContent());
			res = pstm.executeUpdate();
			if(res > 0) {
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

	@Override
	public int update(TestDto dto) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		try {
			pstm = con.prepareStatement(UPDATE_SQL);
			pstm.setString(1, dto.getTitle());
			pstm.setString(2, dto.getContent());
			pstm.setInt(3, dto.getSeq());
			res = pstm.executeUpdate();
			if(res > 0) {
				commit(con);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstm);
			close(con);
		}
		
		return res;
	}

	@Override
	public int delete(int seq) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		try {
			pstm = con.prepareStatement(DELETE_SQL);
			pstm.setInt(1, seq);
			res = pstm.executeUpdate();
			if(res>0) {
				commit(con);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstm);
			close(con);
		}
		return res;
	}

}
