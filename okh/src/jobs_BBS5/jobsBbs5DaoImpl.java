package jobs_BBS5;

import java.util.List;


public interface jobsBbs5DaoImpl {//bbs5 jobs 다오 IMPL부분.

	
	//게시판5. 하드웨어 코딩 부분 글 전체 가지고 오는것.
	public List<BbsHWCodingBeanDtoVO> getBbsHWCodingBeanList();
	
	
	
	//게시판5. 일반 게시판 부분. 인터페이스.
	
	

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
	
	// 1대 1 채팅이 시작하면 CDEL을 1로 변환
	public void startChat(String myid, String chatid);
	
	// 1대 1 채팅이 끝나면 CDEL을 0로 변환
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
	
	// ㅅㅎ
	public boolean shareBbs(SnsDto dto, String id);
*/	
}
