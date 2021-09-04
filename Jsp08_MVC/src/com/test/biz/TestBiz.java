package com.test.biz;

import java.util.List;

import com.test.dto.TestDto;

public interface TestBiz {

	
	public List<TestDto> selectList();
	
	public TestDto selectOne(int seq);
	
	public int insert(TestDto dto);
	
	public int update(TestDto dto);

	public int delete(int seq);
}
