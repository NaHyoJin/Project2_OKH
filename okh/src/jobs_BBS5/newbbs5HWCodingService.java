package jobs_BBS5;

import java.util.List;

import techbbs.PagingBean;
import techbbs.TechbbsDao;
import techbbs.TechbbsDto;
import techbbs.TechbbsService;
import techbbs.iTechbbsDao;

public class newbbs5HWCodingService implements newbbs5HWCodingServiceImpl {

	//싱글톤 부분.
	private static newbbs5HWCodingService techbbsService = new newbbs5HWCodingService();
	
	public newbbs5HWCodingDaoImpl techbbsdao;
	
	private newbbs5HWCodingService() {
		techbbsdao = new newbbs5HWCodingDao();
	}
	
	public static newbbs5HWCodingService getInstance() {
		return techbbsService;
	}

	@Override
	public List<newbbs5HWCodingVO> gettechBbsPagingList(jobs_BBS5.PagingBean paging, String searchWord, int search) {
		// TODO Auto-generated method stub
		return techbbsdao.gettechBbsPagingList(paging, searchWord, search);
	}

	@Override
	public List<newbbs5HWCodingVO> gettechBbsList() {
		// TODO Auto-generated method stub
		return techbbsdao.gettechBbsList();
	}

	@Override
	public String[] getTagName(String tagname) {
		// TODO Auto-generated method stub
		return techbbsdao.getTagName(tagname);
	}

	@Override
	public boolean writeBbs(newbbs5HWCodingVO bbs) {
		// TODO Auto-generated method stub
		return techbbsdao.writeBbs(bbs);
	}
	
	//글 작성시 인간 쪽 점수 올라가는 것.
	public boolean writeBbsMemSCORE(byte score, String writescoreid) {
		return techbbsdao.writeBbsMemSCORE(score, writescoreid);
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
	//글삭제하면 점수 빼는 작업.
	@Override
	public boolean deleteBbsMemSCORE(byte score, int seq) {
		// TODO Auto-generated method stub
		return techbbsdao.deleteBbsMemSCORE(score, seq);
	}
	
	//디테일 부분.
	@Override
	public List<newbbs5HWCodingVO> getdetail(int seq) {
		// TODO Auto-generated method stub
		return techbbsdao.getdetail(seq);
	}
	@Override
	public List<newbbs5HWCodingVO> getpdsdetail(int seq) {
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

	

}
