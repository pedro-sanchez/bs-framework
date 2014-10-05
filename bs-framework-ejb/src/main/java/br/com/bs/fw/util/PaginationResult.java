package br.com.bs.fw.util;

import java.util.ArrayList;
import java.util.List;

public class PaginationResult<T> {

	private List<T> result = new ArrayList<>();

	private Integer rowPerPage = 10;

	private Integer currentIndex = 0;

	private Integer pageNumbers = 0;

	private Integer currentPage = 0;

	private String sortField;
	
	private String sortDirection;

	private Long size = 0L;

	private GenericWrapper<T> wrapper;

	public PaginationResult() {

	}

	public PaginationResult(List<T> result, Long size) {
		this.result = result;
		this.size = size;
	}

	public PaginationResult(List<T> result, Long size, Integer currentIndex,
			Integer rowPerPage) {
		this(result, size);
		this.currentIndex = currentIndex;
		this.rowPerPage = rowPerPage;
	}

	public List<T> getResult() {
		return result;
	}

	public void setResult(List<T> result) {
		this.result = result;
	}

	public Integer getRowPerPage() {
		return rowPerPage;
	}

	public void setRowPerPage(Integer rowPerPage) {
		this.rowPerPage = rowPerPage;
	}

	public Integer getCurrentIndex() {
		return currentIndex;
	}

	public void setCurrentIndex(Integer currentIndex) {
		this.currentIndex = currentIndex;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public Integer getPageNumbers() {
		return pageNumbers;
	}

	public void setPageNumbers(Integer pageNumbers) {
		this.pageNumbers = pageNumbers;
	}

	public List<Integer> getPageList() {
		List<Integer> result = new ArrayList<>();

		pageNumbers = (int) (size / rowPerPage);

		if (size % rowPerPage > 0) {
			pageNumbers++;
		}

		Integer initialIndex = currentIndex;
		Integer finalIndex = pageNumbers - 1;

		if (currentIndex > 1) {
			initialIndex = currentIndex - 2;
			if (finalIndex > 4) {
				finalIndex = currentIndex + 2;
			}
		} else if (currentIndex > 0) {
			initialIndex = currentIndex - 1;
			if (finalIndex > 4) {
				finalIndex = 4;
			}
		} else if (finalIndex > 4) {
			finalIndex = 4;
		}

		if (finalIndex > pageNumbers) {
			finalIndex = pageNumbers - 1;
		}

		while (initialIndex <= finalIndex) {
			result.add(++initialIndex);
		}

		currentPage = currentIndex + 1;

		return result;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public GenericWrapper<T> getWrapper() {
		return wrapper;
	}

	public void setWrapper(GenericWrapper<T> wrapper) {
		this.wrapper = wrapper;
	}

	public String getSortField() {
		return sortField;
	}

	public void setSortField(String sortField) {
		this.sortField = sortField;
	}

	public String getSortDirection() {
		return sortDirection;
	}

	public void setSortDirection(String sortDirection) {
		this.sortDirection = sortDirection;
	}
	
	

}
