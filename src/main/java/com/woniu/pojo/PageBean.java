package com.woniu.pojo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor

public class PageBean<T> {
	private Integer nowPage;
	private Integer pageSize = 5;
	private Integer allRow;
	private Integer allPage;
	private List<T> pageList;
	private String queryVal;
}
