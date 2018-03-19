package jobs_BBS5;

import java.util.List;


public interface newbbs5HWCodingServiceImpl {

	public List<newbbs5HWCodingVO> gettechBbsList();
	public List<newbbs5HWCodingVO> gettechBbsPagingList(PagingBean paging, String searchWord, int search);
	public String[] getTagName(String tagname);
	public boolean writeBbs(newbbs5HWCodingVO bbs);
	
	public List<newbbs5HWCodingVO> getdetail(int seq);
	public List<newbbs5HWCodingVO> getpdsdetail(int seq);
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
}
