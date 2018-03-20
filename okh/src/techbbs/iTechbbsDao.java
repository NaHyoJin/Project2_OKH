package techbbs;

import java.util.List;



public interface iTechbbsDao {
	public List<TechbbsDto> gettechBbsList();
	public List<TechbbsDto> gettechBbsPagingList(PagingBean paging, String searchWord, int search);
	public String[] getTagName(String tagname);
	public boolean writeBbs(TechbbsDto bbs);
	
	public List<TechbbsDto> getdetail(int seq);
	public List<TechbbsDto> getpdsdetail(int seq);
	public boolean getparent(int seq);
	public void likecountplus(int seq);
	public void dislikecount(int seq);
	public boolean update(int seq,String title, String content);
	public boolean delete(int seq);
	
	public boolean pdsdelete(int seq);
	public boolean repAlldelete(int seq);
	public void readcountplus(int seq);
	public void scrapcountplus(int seq);
	public void commentcountplus(int seq);
	public void scrapcountminus(int seq);
	public void commentcountminus(int seq);
	
	public boolean checkcomment(int seq);
	
	public List<TechbbsDto> gettechBbssortPagingList(PagingBean paging, String whatsort);
}
