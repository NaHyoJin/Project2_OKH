package techbbs;
import java.util.List;

import totalbbs.CombbsDto;
import totalbbs.LifeBbsDto;
import totalbbs.QnaDto;
import totalbbs.newbbs5HWCodingVO;
import totalbbs.totalbbsdto;



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
	public void likecountplus(int seq) {
		techbbsdao.likecountplus(seq);
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
	@Override
	public void readcountplus(int seq) {
		// TODO Auto-generated method stub
		techbbsdao.readcountplus(seq);
	}
	@Override
	public void scrapcountplus(int seq) {
		// TODO Auto-generated method stub
		techbbsdao.scrapcountplus(seq);
	}
	@Override
	public void commentcountplus(int seq) {
		// TODO Auto-generated method stub
		techbbsdao.commentcountplus(seq);
	}
	@Override
	public void scrapcountminus(int seq) {
		// TODO Auto-generated method stub
		techbbsdao.scrapcountminus(seq);
	}
	@Override
	public void commentcountminus(int seq) {
		// TODO Auto-generated method stub
		techbbsdao.commentcountminus(seq);
	}
	@Override
	public boolean checkcomment(int seq) {
		// TODO Auto-generated method stub
		return techbbsdao.checkcomment(seq);
	}
	@Override
	public List<TechbbsDto> gettechBbssortPagingList(PagingBean paging, String whatsort) {
		// TODO Auto-generated method stub
		return techbbsdao.gettechBbssortPagingList(paging,whatsort);
	}
	@Override
	public List<LifeBbsDto> getlifeBbsList() {
		// TODO Auto-generated method stub
		return techbbsdao.getlifeBbsList();
	}
	@Override
	public List<newbbs5HWCodingVO> getbbs5BbsList() {
		// TODO Auto-generated method stub
		return techbbsdao.getbbs5BbsList();
	}
	@Override
	public List<QnaDto> getqnaBbsList() {
		// TODO Auto-generated method stub
		return techbbsdao.getqnaBbsList();
	}
	@Override
	public List<TechbbsDto> alltechBbsList() {
		// TODO Auto-generated method stub
		return techbbsdao.alltechBbsList();
	}
	@Override
	public List<LifeBbsDto> alllifeBbsList() {
		// TODO Auto-generated method stub
		return techbbsdao.alllifeBbsList();
	}
	@Override
	public List<newbbs5HWCodingVO> allbbs5BbsList() {
		// TODO Auto-generated method stub
		return techbbsdao.allbbs5BbsList();
	}
	@Override
	public List<QnaDto> allqnaBbsList() {
		// TODO Auto-generated method stub
		return techbbsdao.allqnaBbsList();
	}
	@Override
	public List<CombbsDto> allcomBbsList() {
		// TODO Auto-generated method stub
		return techbbsdao.allcomBbsList();
	}
	@Override
	public List<totalbbsdto> gettotalBbsList() {
		// TODO Auto-generated method stub
		return techbbsdao.gettotalBbsList();
	}
	
	
}
