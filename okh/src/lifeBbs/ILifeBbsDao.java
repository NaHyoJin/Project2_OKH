package lifeBbs;

import java.util.List;

public interface ILifeBbsDao {

	public boolean writeBbs(LifeBbsDto bbs);
	public boolean deleteBbs(int seq);
	public boolean updateBbs(LifeBbsDto bbs);

	public LifeBbsDto getDetailBbs(int seq);
	public void readcount(int seq);
	public boolean downloadcount(int seq);
	
	public boolean answer(int seq, LifeBbsDto bbs);
	
	public List<LifeBbsDto> getBbsPagingList(LifeBbsPagingDto paging, String searchWord, int search);
	
	public boolean writeCountReply(int seq, int countreply);
	
	public LifeBbsDto getupdownid(int seq);
	public boolean updateupid(int seq, int up, String upid);
	public boolean updatedownid(int seq, int up, String downid);

}
