package Study_like;
import java.util.List;

import studysrc.CombbsDto;



public class LikeScrapService implements LikeScrapServiceImpl{
	private static LikeScrapService liscService=new LikeScrapService();
	public iLikeScrapDao liscdao;
	private LikeScrapService() {
		liscdao=new LikeScrapDao();
	}
	public static LikeScrapService getInstance() {
		return liscService;
	}
	
	
	@Override
	public String[] getids(String serchid) {
		// TODO Auto-generated method stub
		return liscdao.getids(serchid);
	}
	@Override
	public boolean addLikeID(CombbsDto bbs) {
		// TODO Auto-generated method stub
		return liscdao.addLikeID(bbs);
	}
	@Override
	public String getLikeID(int seq) {
		// TODO Auto-generated method stub
		return liscdao.getLikeID(seq);
	}

	@Override
	public boolean isitlikeid(int seq, String serchlikeid) {
		// TODO Auto-generated method stub
		return liscdao.isitlikeid(seq, serchlikeid);
	}
	@Override
	public boolean deleteLikeID(String deleteid, int seq) {
		// TODO Auto-generated method stub
		return liscdao.deleteLikeID(deleteid, seq);
	}
	
}
