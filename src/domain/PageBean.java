package domain;

import java.util.List;

public class PageBean {

	private int totalrecord;
	private int pagesize;
	private int totalpage;
	private int currentpage;
	private int previouspage;
	private int nextpage;
	private int[] pagebar;
	private List<Book> list;
	
	public int getTotalrecord() {
		return totalrecord;
	}
	public void setTotalrecord(int totalrecord) {
		this.totalrecord = totalrecord;
	}
	public int getPagesize() {
		return pagesize;
	}
	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}
	
	public int getTotalpage() {
		if(this.totalrecord%this.pagesize==0){
			this.totalpage = this.totalrecord/this.pagesize;
		}
		this.totalpage = this.totalrecord/this.pagesize+1;
		return totalpage;
	}

	public int getCurrentpage() {
		return currentpage;
	}
	
	public void setCurrentpage(int currentpage) {
		this.currentpage = currentpage;
	}
	public int getPreviouspage() {
		if(this.currentpage-1<1){
			return 1;
		}else{
			return this.currentpage-1;
		}
	}

	public int getNextpage() {
		if(this.currentpage+1>this.totalpage){
			return this.totalpage;
		}else{
			return this.currentpage+1;
		}
	}

	public int[] getPagebar() {
		int startindex;
		int endindex;
		if(this.totalpage<10){
			startindex = 1;
			endindex =this.totalpage;
		}else{
			if(this.currentpage-4<1){
			    startindex=1;
				endindex=10;
			}else if(this.currentpage+5>this.totalpage){
				startindex=this.totalpage-9;
				endindex = this.totalpage;
			}else{
				startindex = this.currentpage-4;
				endindex = this.currentpage+5;
			}
		}
		this.pagebar = new int[endindex-startindex+1];
		int index=0;
		for(int i=startindex;i<=endindex;i++){
			this.pagebar[index++] = i;
 		}
		return pagebar;
	}

	public List<Book> getList() {
		return list;
	}
	public void setList(List<Book> list) {
		this.list = list;
	}

	

}
