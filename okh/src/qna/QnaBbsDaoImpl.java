package qna;

import java.util.List;

import qna.PagingBean;
import techbbs.TechbbsDto;


public interface QnaBbsDaoImpl {

	public List<QnaDto> getQnaList();
	
	public boolean writeQnaBbs(QnaDto dto);
	
	public List<QnaDto> getBbsPagingList(PagingBean paging);
	
	public List<QnaDto> getQnaPagingList(PagingBean paging, String searchWord, int search);
	
	public QnaDto getBbs(int seq);
	public void readcount(int seq);
		
	public boolean qnaupdate(QnaDto dto); //detail에서 update사용
	
	public boolean answer(int seq, QnaDto dto);
	
	public String RemoveHTMLTag(String changeStr);	
	public String[] getTagName(String tagname);
	public boolean checkcomment(int seq);
	
	public List<QnaDto> getqnaBbssortPagingList(PagingBean paging, String whatsort);
	public void likecountplus(int seq) ;
	public void dislikecount(int seq);
	public boolean delete(int seq);
	public void readcountplus(int seq);
	public void commentcountplus(int seq);
	public void commentcountminus(int seq);
	
	public boolean getparent(int seq);
	public List<QnaDto> getpdsdetail(int seq);
	public List<QnaDto> getdetail(int seq);
	public boolean update(int seq, String title, String content);
	public boolean repAlldelete(int seq);
	public boolean writeBbs(QnaDto bbs);
	
	// like
	
	
}
