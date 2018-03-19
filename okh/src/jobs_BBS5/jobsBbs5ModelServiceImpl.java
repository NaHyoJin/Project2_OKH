package jobs_BBS5;

import java.util.List;

import lifeBbs.LifeBbsDto;
import qna.QnaDto;
import studysrc.CombbsDto;
import studysrc.PagingBean;
import studysrc.comment_bbsDto;
import techbbs.TechbbsDto;


public interface jobsBbs5ModelServiceImpl {//서비스 부분. 
	
	//dto, bean, VO 다 같은말
	
/*
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
	*/
	//게시판5. 하드웨어 코딩 부분 글 전체 가지고 오는것.
	public List<BbsHWCodingBeanDtoVO> getBbsHWCodingBeanList();
		//글 작성 부분.
		public boolean writeBbs(BbsHWCodingBeanDtoVO dto);
		//디테일 부분.
		public BbsHWCodingBeanDtoVO detailHWbbs(int seq);
		//조회수 부분.
		public void readcounthwbbs(int seq);
		//글 수정.
		public boolean updateBbs(BbsHWCodingBeanDtoVO bbs);
		//글 삭제.
		public boolean deleteHWBbs(int seq);
		
		
	//게시판5. 일반 글 전체 가지고 오는것.
	public List<BbsBoardBeanDtoVO> getBbsNormalBeanDTOList();
		//글 작성 부분.
		public boolean writenormalBbs(BbsBoardBeanDtoVO dto);
		//디테일 부분.
		public BbsBoardBeanDtoVO detailbbs(int seq);
		//조회수 부분.
		public void readcountnormalbbs(int seq);
		//글 수정.
		public boolean updateBbs(BbsBoardBeanDtoVO bbs);
		//글 삭제.
		public boolean deleteBbs(int seq);
		
/*	
	public void readcount(int seq);
	public String[] getTagName(String tagname);
	
	
	public List<CombbsDto> getpagingComList(PagingBean paging, String searchWord, int search);
	public List<CombbsDto> commentnull(int seq);
	public void commentcount(int seq);
*/	
	//게시판5. 자료실 글 전체 가지고 오는것.
/*	public List<BbsMaterialsBeanDtoVO> getBbsMaterialsBeanDtoVOList();
		//글 작성 부분.
		public boolean writenormalBbs(BbsMaterialsBeanDtoVO dto);
		//디테일 부분.
		public BbsMaterialsBeanDtoVO detailmaterials(int seq);
		//조회수 부분.
		public void readcountmaterials(int seq);
		//글 수정.
		public boolean updateBbs(BbsMaterialsBeanDtoVO bbs);
		//글 삭제.
		public boolean deletematerials(int seq);*/
	
	
	
	
	
	
	
	/*
	//조회수 부분.
	public void readcount(int seq);
	
	public int PrintconNum(String myid, String selid);
	
	public boolean confollow(String myid, String selid);
	
	public boolean nameSelect(String myid, String selid);
	
	public boolean following(String myid, String selid);
	
	public List<selectFollowDto> selectfollow(String myid);	
	
	public boolean disconfollow(String myid, String followid);

	// bang
	
	
	public boolean addchat(ChatDto dto);
	
	public boolean chatTrue(String myid, String chatid);
	
	public boolean chatIdTrue(String myid);
	
	public List<ChatDto> acceptid(String myid);
	
	public void startChat(String myid, String chatid);
	
	public void finishChat(String myid, String chatid);
	
	// 소현
	public void sortsup(int bgroup, int sorts);
	
	public boolean replyBbs(SnsDto dto, int bgroup, int depth);
	
	// 아라
	public boolean likeCheck(int seq, String id);
	
	public boolean likelist(int seq, String id);
	
	public boolean dislikelist(int seq, String id);
	
	public boolean dislikecheck(int seq, String id);
	
	public boolean dislikedel(int seq, String id);
	
	// 소
	public boolean shareBbs(SnsDto dto, String id);
	*/
	
}////////////////BBS4boardImpl
