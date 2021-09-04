package com.test.dto;

import java.util.Date;

public class TestDto {

	private int seq;
	private String writer;
	private String title;
	private String content;
	private Date date;
	
	public TestDto() {
	
	}
	
	public TestDto(int seq, String writer, String title, String content, Date date) {
	
		this.seq = seq;
		this.writer = writer;
		this.title = title;
		this.content = content;
		this.date = date;
	}

	public TestDto(String writer, String title, String content) {
		
		this.writer = writer;
		this.title = title;
		this.content = content;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	
}
