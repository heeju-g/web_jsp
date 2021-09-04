package com.answer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.answer.dto.AnswerDto;
import static com.answer.db.JDBCTemplate.*;
public class AnswerDaoImpl implements AnswerDao {

	@Override
	public List<AnswerDto> selectList() {
		Connection con = getConnection();
		
		Statement stmt = null;
		ResultSet rs = null;
		List<AnswerDto> list = new ArrayList<AnswerDto>();
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(BOARD_LIST_SQL);
			while(rs.next()) {
				AnswerDto dto = new AnswerDto(rs.getInt(1),
												rs.getInt(2),
												rs.getInt(3),
												rs.getInt(4),
												rs.getString(5),
												rs.getString(6),
												rs.getString(7),
												rs.getDate(8));
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
	public AnswerDto selectOne(int boardno) {
		Connection con = getConnection();
		
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		AnswerDto dto = null;
		
		try {
			pstm = con.prepareStatement(BOARD_DETAIL_SQL);
			pstm.setInt(1, boardno);
			System.out.println("3");
			rs = pstm.executeQuery();
			System.out.println("4");
			while(rs.next()) {
				dto =new AnswerDto(rs.getInt(1),
									rs.getInt(2),
									rs.getInt(3),
									rs.getInt(4),
									rs.getString(5),
									rs.getString(6),
									rs.getString(7),
									rs.getDate(8));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstm);
			close(con);
			System.out.println("5");
		}
		
		return dto;
	}

	@Override
	public int boardInsert(AnswerDto dto) {
		Connection con = getConnection();
		
		PreparedStatement pstm = null;
		int res = 0;
		
		try {
			pstm = con.prepareStatement(BOARD_INSERT_SQL);
			pstm.setString(1, dto.getTitle());
			pstm.setString(2, dto.getContent());
			pstm.setString(3, dto.getWriter());
			
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
	public int boardUpdate(AnswerDto dto) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		
		try {
			pstm = con.prepareStatement(BOARD_UPDATE_SQL);
			pstm.setString(1, dto.getTitle());
			pstm.setString(2, dto.getContent());
			pstm.setInt(3, dto.getBoardno());
			
			res = pstm.executeUpdate();
			if( res > 0) {
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
	public int boardDelete(int boardno) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		
		int res = 0;
		
		try {
			pstm = con.prepareStatement(BOARD_DELETE_SQL);
			pstm.setInt(1, boardno);
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
	public int answerUpdate(int parentboardno) {
		Connection con = getConnection();
		
		PreparedStatement pstm = null;
		int res = 0;
		
		try {
			pstm = con.prepareStatement(ANSWER_UPDATE_SQL);
			pstm.setInt(1, parentboardno);
			pstm.setInt(2, parentboardno);
			res = pstm.executeUpdate();
			if( res > 0) {
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
	public int answerInsert(AnswerDto dto) {
		Connection con = getConnection();
		
		PreparedStatement pstm = null;
		int res = 0;
		
		try {
			pstm = con.prepareStatement(ANSWER_INSERT_SQL);
			pstm.setInt(1, dto.getBoardno());
			pstm.setInt(2, dto.getBoardno());
			pstm.setInt(3, dto.getBoardno());
		
			pstm.setString(4, dto.getTitle());
			pstm.setString(5, dto.getContent());
			pstm.setString(6, dto.getWriter());
			
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

}
