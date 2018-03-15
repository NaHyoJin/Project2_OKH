package techbbs;
import java.util.List;



public class TechbbsService implements TechbbsServiceImpl{
	private static TechbbsService techbbsService=new TechbbsService();
	public iTechbbsDao techbbsdao;
	private TechbbsService() {
		techbbsdao=new TechbbsDao();
	}
	public static TechbbsService getInstance() {
		return techbbsService;
	}

	@Override
	public List<TechbbsDto> gettechBbsPagingList(PagingBean paging, String searchWord, int search) {
		// TODO Auto-generated method stub
		return techbbsdao.gettechBbsPagingList(paging, searchWord, search);
	}

	@Override
	public List<TechbbsDto> gettechBbsList() {
		// TODO Auto-generated method stub
		return techbbsdao.gettechBbsList();
	}

	@Override
	public String[] getTagName(String tagname) {
		// TODO Auto-generated method stub
		return techbbsdao.getTagName(tagname);
	}

	@Override
	public boolean writeBbs(TechbbsDto bbs) {
		// TODO Auto-generated method stub
		return techbbsdao.writeBbs(bbs);
	}
	
	@Override
	public void countplus(int seq, String whatcount) {
		techbbsdao.countplus(seq, whatcount);
	}
	@Override
	public void dislikecount(int seq) {
		techbbsdao.dislikecount(seq);
		
	}
	@Override
	public boolean update(int seq, String title, String content) {
		// TODO Auto-generated method stub
		return techbbsdao.update(seq, title, content);
	}
	@Override
	public boolean delete(int seq) {
		// TODO Auto-generated method stub
		return techbbsdao.delete(seq);
	}
	
	@Override
	public List<TechbbsDto> getdetail(int seq) {
		// TODO Auto-generated method stub
		return techbbsdao.getdetail(seq);
	}
	@Override
	public List<TechbbsDto> getpdsdetail(int seq) {
		// TODO Auto-generated method stub
		return techbbsdao.getpdsdetail(seq);
	}
	@Override
	public boolean getparent(int seq) {
		// TODO Auto-generated method stub
		return techbbsdao.getparent(seq);
	}
	@Override
	public boolean pdsdelete(int seq) {
		// TODO Auto-generated method stub
		return techbbsdao.pdsdelete(seq);
	}
	@Override
	public boolean repAlldelete(int seq) {
		// TODO Auto-generated method stub
		return techbbsdao.repAlldelete(seq);
	}
	
}
