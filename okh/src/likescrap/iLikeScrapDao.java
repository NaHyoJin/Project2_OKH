package likescrap;

import java.util.List;

import techbbs.TechbbsDto;



public interface iLikeScrapDao {
	
	//좋아요,싫어요,스크랩아이디저장하기
	public boolean addLikeID(TechbbsDto bbs);
	public boolean addDisLikeID(TechbbsDto bbs);
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
