package cn.obwq.result;

public class PageResult<T> extends BaseResult {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6931289907302731169L;


	private T result;
	
	
	 /** 当前每页显示数量（原样传出） */
    private int               pageSize;

    /** 当前页码（原样传出） */
    private int               currentPage;

    /** 总共记录数 */
    private int               totalSize;

    /** 总页数 */
    private int               totalPage;

    
	
	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	
}
