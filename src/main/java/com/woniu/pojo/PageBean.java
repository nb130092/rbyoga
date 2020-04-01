package com.woniu.pojo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class PageBean<T> {
	private Integer nowPage = 1;//当前页是第几页
	private Integer pageSize = 5;//一页显示几条
	private Integer allRow;//总记录数
	private Integer allPage;//总页数
	private Integer offset;//从哪里开始
	private List<Object> list;

	public Integer getNowPage() {
		return nowPage;
	}

	public void setNowPage(Integer nowPage) {
		this.nowPage = nowPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getAllRow() {
		return allRow;
	}

	public void setAllRow(Integer allRow) {
		this.allRow = allRow;
	}

	public Integer getAllPage() {
		return (allRow+pageSize-1)/pageSize;
	}

	public void setAllPage(Integer allPage) {
		this.allPage = allPage;
	}

	public Integer getOffset() {
		return (nowPage-1)*pageSize;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	public List<Object> getList() {
		return list;
	}

	public void setList(List<Object> list) {
		this.list = list;
	}
}
