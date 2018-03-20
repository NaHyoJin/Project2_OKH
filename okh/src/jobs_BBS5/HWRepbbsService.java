package jobs_BBS5;
import java.util.List;



public class HWRepbbsService implements HWRepbbsServiceImpl{
	private static HWRepbbsService techrepbbsService = new HWRepbbsService();
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
	//글 작성시 인간 쪽 점수 올라가는 것.
		public boolean writeBbsMemSCORE(byte score, String writescoreid) {
			return techrepbbsdao.writeBbsMemSCORE(score, writescoreid);
		}
	
	@Override
	public boolean repupdate(int seq, String content) {
		// TODO Auto-generated method stub
		return techrepbbsdao.repupdate(seq, content);
	}
	
	//덧글 삭제.
	@Override
	public boolean repdelete(int seq) {
		// TODO Auto-generated method stub
		return techrepbbsdao.repdelete(seq);
	}
	//글 삭제시 인간 쪽 점수 빼는 것. 나중에 시간 되면 만들어보자.
		public boolean deleteBbsMemSCORE(byte score, int seq) {
			return techrepbbsdao.deleteBbsMemSCORE(score, seq);
		}

	
	
}
