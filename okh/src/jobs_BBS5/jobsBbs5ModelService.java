package jobs_BBS5;

import java.util.ArrayList;
import java.util.List;

import qna.QnaDto;
import techbbs.TechbbsDao;
import techbbs.TechbbsService;
import techbbs.iTechbbsDao;


public class jobsBbs5ModelService implements jobsBbs5ModelServiceImpl {

	//모델에서 dao 생성 부분.

	private static jobsBbs5ModelService jobsBbs5ModelService = new jobsBbs5ModelService();
	public jobsBbs5DaoImpl dao;
	
	private jobsBbs5ModelService() {
		//게시판 dao 생성 부분.
		dao = new jobsBbs5Dao();
	}
	
	public static jobsBbs5ModelService getInstance() {
		return jobsBbs5ModelService;
	}
	
	
	//H/W 모든 게시판 가지고 오는것.
	@Override
	public List<BbsHWCodingBeanDtoVO> getBbsHWCodingBeanList() {
		// TODO Auto-generated method stub
		return dao.getBbsHWCodingBeanList();
	}
		//HW 게시판 글 작성 부분.
		public boolean writeBbs(BbsHWCodingBeanDtoVO dto) {
			return dao.writeBbs(dto);
		}
		//디테일 부분.
		public BbsHWCodingBeanDtoVO detailHWbbs(int seq) {
			return dao.detailHWbbs(seq);
		}
		//조회수 부분.
		public void readcounthwbbs(int seq) {
			dao.readcounthwbbs(seq);
		}
		//HW 게시판 수정 부분.
		public boolean updateBbs(BbsHWCodingBeanDtoVO bbs) {
			return dao.updateBbs(bbs);
		}
		//HW 게시판 글 삭제 부분.
		public boolean deleteHWBbs(int seq) {
			return dao.deleteHWBbs(seq);
		}

	//일반 모든 게시판 가지고 오는것.
	@Override
	public List<BbsBoardBeanDtoVO> getBbsNormalBeanDTOList() {
		// TODO Auto-generated method stub
		return dao.getBbsNormalBeanDTOList();
	}
		@Override
		//일반 게시판 글 작성 부분.
		public boolean writenormalBbs(BbsBoardBeanDtoVO dto) {
			return dao.writenormalBbs(dto);
		}
		//디테일 부분.
		@Override
		public BbsBoardBeanDtoVO detailbbs(int seq) {
			return dao.detailbbs(seq);
		}
		//조회수 부분.
		public void readcountnormalbbs(int seq) {
			dao.readcountnormalbbs(seq);
		}
		//일반 게시판 수정 부분.
		public boolean updateBbs(BbsBoardBeanDtoVO bbs) {
			return dao.updateBbs(bbs);
		}
		//일반 게시판 글 삭제 부분.
		public boolean deleteBbs(int seq) {
			return dao.deleteBbs(seq);
		}
	/*
	@Override
	public boolean snsDelete(int seq) {
		// TODO Auto-generated method stub
		return dao.snsDelete(seq);
	}

	@Override
	public boolean snsUpdate(int seq, String content) {
		// TODO Auto-generated method stub
		return dao.snsUpdate(seq, content);
	}

	@Override
	public SnsDto getSNS(int seq) {
		// TODO Auto-generated method stub
		return dao.getSNS(seq);
	}

	
	
	@Override
	public boolean likeCount(int seq) {
		return dao.likeCount(seq);
	}

	@Override
	public boolean dislikeCount(int seq) {
		return dao.dislikeCount(seq);
	}

	@Override
	public int PrintconNum(String myid, String selid) {
		return dao.PrintconNum(myid, selid);
	}

	@Override
	public boolean confollow(String myid,String selid) {
		return dao.confollow(myid, selid);
	}

	
	@Override
	public boolean nameSelect(String myid, String selid) {
		return dao.nameSelect(myid, selid);
	}

	@Override
	public boolean following(String myid, String selid) {
		return dao.following(myid, selid);
	}

	@Override
	public List<selectFollowDto> selectfollow(String myid) {
		return dao.selectfollow(myid);
	}

	@Override
	public boolean disconfollow(String myid, String followid) {
		return dao.disconfollow(myid, followid);
	}
	
	// bang
	public List<MemberDto> chatList(String id){
		return dao.chatList(id);
	}

	@Override
	public boolean addchat(ChatDto dto) {
		return dao.addchat(dto);
	}

	@Override
	public boolean chatTrue(String myid, String chatid) {
		return dao.chatTrue(myid, chatid);
	}

	@Override
	public boolean chatIdTrue(String myid) {
		return dao.chatIdTrue(myid);
	}
	
	@Override
	public List<ChatDto> acceptid(String myid){
		return dao.acceptid(myid);
	}

	@Override
	public void startChat(String myid, String chatid) {
		dao.startChat(myid, chatid);
	}

	@Override
	public void finishChat(String myid, String chatid) {
		dao.finishChat(myid, chatid);
	}
	
	// 소현
	@Override
	public void sortsup(int bgroup, int sorts) {
		dao.sortsup(bgroup, sorts);
	}

	@Override
	public boolean replyBbs(SnsDto dto, int bgroup, int depth) {
		return dao.replyBbs(dto, bgroup, depth);
	}
	
	// 아라
	@Override
	public boolean likeCheck(int seq,String id) {
		return dao.likeCheck(seq,id);
	}
	
	@Override
	public boolean likelist(int seq, String id) {
		// TODO Auto-generated method stub
		return dao.likelist(seq, id);
	}

	@Override
	public boolean dislikelist(int seq, String id) {
		// TODO Auto-generated method stub
		return dao.dislikelist(seq, id);
	}

	@Override
	public boolean dislikecheck(int seq, String id) {
		// TODO Auto-generated method stub
		return dao.dislikecheck(seq, id);
	}

	@Override
	public boolean dislikedel(int seq, String id) {
		// TODO Auto-generated method stub
		return dao.dislikedel(seq, id);
	}

	@Override
	public boolean shareBbs(SnsDto dto, String id) {
		return dao.shareBbs(dto, id);
	}
*/

	

}
