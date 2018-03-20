package jobs_BBS5;

import java.util.List;


public interface newbbs5HWCodingDaoImpl {

	public List<newbbs5HWCodingVO> gettechBbsList();
	public List<newbbs5HWCodingVO> gettechBbsPagingList(PagingBean paging, String searchWord, int search);
	public String[] getTagName(String tagname);
	
	//글작성 부분.
	public boolean writeBbs(newbbs5HWCodingVO bbs);
	//글 작성시 인간 쪽 점수 올라가는 것.
	public boolean writeBbsMemSCORE(byte score, String writescoreid);
	
	public List<newbbs5HWCodingVO> getdetail(int seq);
	public List<newbbs5HWCodingVO> getpdsdetail(int seq);
	public boolean getparent(int seq);
	public void likecountplus(int seq);
	public void dislikecount(int seq);
	public boolean update(int seq,String title, String content);
	//게시글 삭제 부분.
	public boolean delete(int seq);
	//글 삭제시 인간 쪽 점수 빼는 것. 나중에 시간 되면 만들어보자.
	public boolean deleteBbsMemSCORE(byte score, int seq);
	
	public boolean pdsdelete(int seq);
	public boolean repAlldelete(int seq);
	public void readcountplus(int seq);
	public void scrapcountplus(int seq);
	public void commentcountplus(int seq);
	public void scrapcountminus(int seq);
	public void commentcountminus(int seq);
	
	public boolean checkcomment(int seq);
}
