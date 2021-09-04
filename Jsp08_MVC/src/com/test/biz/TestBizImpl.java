package com.test.biz;

import java.util.List;

import com.test.dao.TestDao;
import com.test.dao.TestDaoImpl;
import com.test.dto.TestDto;

public class TestBizImpl implements TestBiz {

	TestDao dao = new TestDaoImpl();
	@Override
	public List<TestDto> selectList() {
		
		return dao.selectList();
	}

	@Override
	public TestDto selectOne(int seq) {
	
		return dao.selectOne(seq);
	}

	@Override
	public int insert(TestDto dto) {
		
		return dao.insert(dto);
	}

	@Override
	public int update(TestDto dto) {
		
		return dao.update(dto);
	}

	@Override
	public int delete(int seq) {
	
		return dao.delete(seq);
	}

}
