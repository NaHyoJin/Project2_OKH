package lifeBbs;

import java.util.List;

public interface ILifeBbssReplyDao {

	public boolean writeBbsReply(LifeBbssReplyDto bbs);
	public boolean deleteBbsReply(int seq);
	public boolean updateBbsReply(LifeBbssReplyDto bbs);
	
	public boolean ReplyAnswer(int seq, LifeBbssReplyDto bbs);
	
	public List<LifeBbssReplyDto> getBbsReplyList(int bbsseq);
	
	public List<LifeBbssReplyDto> reply();
	
	public int getCountReply(int bbsseq);

}
