package jobs_BBS5;
import java.util.List;

public class HWLikeScrapService implements HWLikeScrapServiceImpl{
	private static HWLikeScrapService liscService = new HWLikeScrapService();
	public HWLikeScrapDaoImpl liscdao;
	private HWLikeScrapService() {
		liscdao = new HWLikeScrapDao();
	}
	public static HWLikeScrapService getInstance() {
		return liscService;
	}
	
	
	@Override
	public String[] getids(String serchid) {
		// TODO Auto-generated method stub
		return liscdao.getids(serchid);
	}
	@Override
	public boolean addLikeID(newbbs5HWCodingVO bbs) {
		// TODO Auto-generated method stub
		return liscdao.addLikeID(bbs);
	}
	@Override
	public boolean addDisLikeID(newbbs5HWCodingVO bbs) {
		// TODO Auto-generated method stub
		return liscdao.addDisLikeID(bbs);
	}
	@Override
	public String getLikeID(int seq) {
		// TODO Auto-generated method stub
		return liscdao.getLikeID(seq);
	}
	@Override
	public String getDisLikeID(int seq) {
		// TODO Auto-generated method stub
		return liscdao.getDisLikeID(seq);
	}

	@Override
	public boolean isitlikeid(int seq, String serchlikeid) {
		// TODO Auto-generated method stub
		return liscdao.isitlikeid(seq, serchlikeid);
	}
	@Override
	public boolean isitdislikeid(int seq, String serchdislikeid) {
		// TODO Auto-generated method stub
		return liscdao.isitdislikeid(seq, serchdislikeid);
	}
	@Override
	public boolean deleteLikeID(String deleteid, int seq) {
		// TODO Auto-generated method stub
		return liscdao.deleteLikeID(deleteid, seq);
	}
	@Override
	public boolean deleteaddDisLikeID(String deleteid, int seq) {
		// TODO Auto-generated method stub
		return liscdao.deleteaddDisLikeID(deleteid, seq);
	}
	
	
}
