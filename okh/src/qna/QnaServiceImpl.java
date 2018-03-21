package qna;

import java.io.Serializable;
import java.util.List;

import qna.PagingBean;




public interface QnaServiceImpl extends Serializable {

	public List<QnaDto> getQnaList();
	public boolean writeQnaBbs(QnaDto dto);
	public List<QnaDto> getQnaPagingList(PagingBean paging, String searchWord, int search);	
	public List<QnaDto> getBbsPagingList(PagingBean paging);
	public QnaDto getBbs(int seq);	
	public String RemoveHTMLTag(String changeStr);
	public boolean qnaupdate(QnaDto dto);	
	public boolean answer(int seq, QnaDto dto);
	
	
	public boolean writeAnswer(QnaDto dto, int seq);
	
	public String[] getTagName(String tagname);
	public boolean checkcomment(int seq);
	public List<QnaDto> getqnaBbssortPagingList(PagingBean paging, String whatsort);
	
	public int getSeq();
	public void likecountplus(int seq) ;
	public void dislikecount(int seq);
	public boolean delete(int seq);
	public void readcountplus(int seq);
	public void commentcountplus(int seq) ;
	public void commentcountminus(int seq); 
	public List<QnaAnswerDto> getCommentList(int seq);
	
	
	//좋아요,싫어요,스크랩아이디저장하기
	public boolean addLikeID(QnaDto bbs);
	public boolean addDisLikeID(QnaDto bbs);
	//아이디가져오기
	public String getLikeID(int seq);
	public String getDisLikeID(int seq);
	//아이디검색해서있는지유무	- 있으면 또눌렀을때 다른걸주기위해서
	public boolean isitlikeid(int seq,String serchlikeid);
	public boolean isitdislikeid(int seq,String serchdislikeid);
	//아이디빼주기
	public boolean deleteLikeID(String deleteid,int seq);
	public boolean deleteaddDisLikeID(String deleteid,int seq);
	//스플릿하는함수-아이디빼주려고
	public String[] getids(String serchid);
	
	
	
	
}
