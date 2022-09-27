package kr.co.goodjobproject.dto;

import lombok.Data;

public class PageMakeDTO {
	
	// 시작 페이지
	private int startPage;
	
	// 끝 페이지
	private int endPage;
	
	// 이전페이지, 다음페이지 존재유무
	private boolean prev, next;
	
	// 전체 게시물 수
	private int total;
	
	// 현재페이지, 페이지당 게시물 표시수
	private PageUtil page;
	
	// 생성자
		public PageMakeDTO(PageUtil page, int total) {
			this.page = page;
			this.total = total;
			
			// 마지막페이지
			this.endPage = (int)(Math.ceil(page.getPageNum()/10.0))*10;
			
			// 시작페이지
			this.startPage = this.endPage - 9;
			
			// 전체 마지막페이지
			int realEnd = (int)(Math.ceil(total * 1.0/page.getAmount()));
			
			// 전체 마지막페이지가 화면에 보이는 마지막페이지보다 작은경우 보이는 페이지 값 조정
			if(realEnd < this.endPage) {
				this.endPage = realEnd;
			}
			
			// 시작페이지 값이 1보다 큰 경우 true
			this.prev = this.startPage > 1;
			
			// 마지막페이지 값이 1보다 큰 경우 true
			this.next = this.endPage < realEnd;
		}
	
	@Override
	public String toString() {
		return "PageMakeDTO [startPage=" + startPage + ", endPage=" + endPage + ", prev=" + prev + ", next=" + next
				+ ", total=" + total + ", page=" + page + "]";
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public PageUtil getPage() {
		return page;
	}

	public void setPage(PageUtil page) {
		this.page = page;
	}

}
