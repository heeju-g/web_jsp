package com.my.dao;

import com.my.dto.MyBoardDto;
import java.util.List;

import org.apache.ibatis.session.SqlSession;




public class MyBoardDao extends SqlMapConfig{
	private String namespace = "com.my.mapper.";
	public List<MyBoardDto> selectList(){
		
		SqlSession session = getSqlSessionFactory().openSession();
							//session.selectList는 sqlSeesion클래스의 메소드
												//myboard-mapper가서 selectList는 id가 될 것
		List<MyBoardDto> list = session.selectList("com.my.mapper."+"selectList");
		session.close();
		
		return list;
	}
	public MyBoardDto selectOne(int myno) {
		
		SqlSession session = null;
		MyBoardDto dto = null;
		
		try {
			session = getSqlSessionFactory().openSession();
			dto = session.selectOne(namespace + "selectOne", myno);
		} catch (Exception e) {
			
			e.printStackTrace();
		}finally {
			session.close();
		}
		
		return dto;
	}
	public int insert(MyBoardDto dto) {
		int res = 0;
		//openSession(boolean)타입 오면 자동커밋관련
		try(SqlSession session = getSqlSessionFactory().openSession(true)){
			res = session.insert(namespace+"insert", dto);
		}
		return res;
	}
	public int update(MyBoardDto dto) {
		int res = 0;
		try(SqlSession session = getSqlSessionFactory().openSession(true)){
			res = session.update(namespace+"update",dto);
			
		}
		return res;
	}
	public int delete(int myno) {
		int res = 0;
		//오픈세션 true면 커밋 안해도 되는데 false면 session.commit해야함(update,delete이런 애들)
		try(SqlSession session = getSqlSessionFactory().openSession(true)){
			res = session.delete(namespace+"delete",myno);
		}
				
		return res;
	}

}
