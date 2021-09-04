package com.test.dao;

import java.util.List;

import com.test.dto.TestDto;

public interface TestDao {
	String SELECT_LIST_SQL = " SELECT SEQ, WRITER, TITLE, CONTENT, REGDATE FROM TESTBOARD ORDER BY SEQ DESC ";
	
	String SELECT_ONE_SQL = " SELECT SEQ, WRITER, TITLE, CONTENT, REGDATE FROM TESTBOARD "
							+ " WHERE SEQ = ? ";
	
	String INSERT_SQL = " INSERT INTO TESTBOARD VALUES(TESTSEQ.NEXTVAL, ?, ?, ?, SYSDATE ) ";
	
	String UPDATE_SQL = " UPDATE FROM TESTBOARD SET TITLE = ?, CONTENT = ? WHERE SEQ = ? ";
	
	String DELETE_SQL = " DELETE FROM TESTBOARD WHERE SEQ = ? ";
	
	public List<TestDto> selectList();
	
	public TestDto selectOne(int seq);
	
	public int insert(TestDto dto);
	
	public int update(TestDto dto);

	public int delete(int seq);
}
