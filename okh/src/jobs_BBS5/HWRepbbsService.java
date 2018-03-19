package jobs_BBS5;
import java.util.List;



public class HWRepbbsService implements HWRepbbsServiceImpl{
	private static HWRepbbsService techrepbbsService=new HWRepbbsService();
	public HWRepbbsDaoImpl techrepbbsdao;
	private HWRepbbsService() {
		techrepbbsdao=new HWRepbbsDao();
	}
	public static HWRepbbsService getInstance() {
		return techrepbbsService;
	}
	@Override
	public List<HWRepbbsDto> getRepBbsList(int seq) {
		// TODO Auto-generated method stub
		return techrepbbsdao.getRepBbsList(seq);
	}
	
	@Override
	public boolean writeBbs(HWRepbbsDto bbs) {
		// TODO Auto-generated method stub
		return techrepbbsdao.writeBbs(bbs);
	}
	
	@Override
	public boolean repupdate(int seq, String content) {
		// TODO Auto-generated method stub
		return techrepbbsdao.repupdate(seq, content);
	}
	@Override
	public boolean repdelete(int seq) {
		// TODO Auto-generated method stub
		return techrepbbsdao.repdelete(seq);
	}

	
	
}
