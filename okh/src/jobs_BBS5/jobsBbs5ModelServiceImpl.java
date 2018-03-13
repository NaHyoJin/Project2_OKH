package jobs_BBS5;

import java.util.List;


public interface jobsBbs5ModelServiceImpl {//서비스 부분. 
	
	//dto, bean, VO 다 같은말
	

	//게시판4. 하드웨어 코딩 부분 글 전체 가지고 오는것.
	public List<BbsHWCodingBeanDtoVO> getBbsHWCodingBeanList();
/*	
	public boolean writeSns(SnsDto dto);
	
	public boolean snsDelete(int seq);
	
	public boolean snsUpdate(int seq,String content);
	
	public SnsDto getSNS(int seq);
	
	public List<SnsDto> getMyContent(String myid);
	
	public boolean likeCount(int seq);
	
	public boolean dislikeCount(int seq);
	
	public int PrintconNum(String myid, String selid);
	
	public boolean confollow(String myid, String selid);
	
	public boolean nameSelect(String myid, String selid);
	
	public boolean following(String myid, String selid);
	
	public List<selectFollowDto> selectfollow(String myid);	
	
	public boolean disconfollow(String myid, String followid);

	// bang
	public List<MemberDto> chatList(String id);
	
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
