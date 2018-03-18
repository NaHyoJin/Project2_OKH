package techreplebbs;
import java.util.List;



public class TechRepbbsService implements TechRepbbsServiceImpl{
	private static TechRepbbsService techrepbbsService=new TechRepbbsService();
	public iTechRepbbsDao techrepbbsdao;
	private TechRepbbsService() {
		techrepbbsdao=new TechRepbbsDao();
	}
	public static TechRepbbsService getInstance() {
		return techrepbbsService;
	}
	@Override
	public List<TechRepbbsDto> getRepBbsList(int seq) {
		// TODO Auto-generated method stub
		return techrepbbsdao.getRepBbsList(seq);
	}
	
	@Override
	public boolean writeBbs(TechRepbbsDto bbs) {
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
