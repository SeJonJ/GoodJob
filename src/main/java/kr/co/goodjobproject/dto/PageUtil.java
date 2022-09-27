package kr.co.goodjobproject.dto;

import java.util.Arrays;

import lombok.Data;

public class PageUtil {
	
	// 현재 페이지
	private int pageNum;
	
	// 한 페이지 당 보여질 갯수
	private int amount;
	
	// 스킵 할 게시물 수 ((pageNum-1) * amount)
	private int skip;
	
	// 검색 키워드
	private String keyword;

	// 검색 타입
	private String type;
	
	// 검색 지역코드
	private int smallno;
	
	// 검색 직무코드
	private int jno;
	
	// 검색 타입 배열
	private String[] typeArr;
		
	// 정렬
	private String sort;
	
	@Override
	public String toString() {
		return "PageUtil [pageNum=" + pageNum + ", amount=" + amount + ", skip=" + skip + ", keyword=" + keyword
				+ ", type=" + type + ", smallno=" + smallno + ", jno=" + jno + ", typeArr=" + Arrays.toString(typeArr)
				+ ", sort=" + sort + "]";
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	
	public int getSmallno() {
		return smallno;
	}

	public void setSmallno(int smallno) {
		this.smallno = smallno;
	}

	public int getJno() {
		return jno;
	}

	public void setJno(int jno) {
		this.jno = jno;
	}
	
	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	// 기본 생성자
	public PageUtil() {
		this(1, 10);
		this.skip = 0;
	}
	
	// 생성자
	public PageUtil(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
		this.skip = (pageNum-1)*amount;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
		this.typeArr = type.split("");
	}

	public String[] getTypeArr() {
		return typeArr;
	}

	public void setTypeArr(String[] typeArr) {
		this.typeArr = typeArr;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.skip = (pageNum-1)*this.amount;
		this.pageNum = pageNum;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.skip = (pageNum-1)*this.amount;
		this.amount = amount;
	}

	public int getSkip() {
		return skip;
	}

	public void setSkip(int skip) {
		this.skip = skip;
	}

}
