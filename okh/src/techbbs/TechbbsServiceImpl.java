package techbbs;

import java.util.List;

import totalbbs.ColumnBbsDto;
import totalbbs.CombbsDto;
import totalbbs.LifeBbsDto;
import totalbbs.QnaDto;
import totalbbs.newbbs5HWCodingVO;
import totalbbs.totalbbsdto;


public interface TechbbsServiceImpl {
	
	public List<TechbbsDto> gettechBbsPagingList(PagingBean paging, String searchWord, int search);
	public String[] getTagName(String tagname);
	public boolean writeBbs(TechbbsDto bbs);
	
	public List<TechbbsDto> getdetail(int seq);
	public List<TechbbsDto> getpdsdetail(int seq);
	public boolean getparent(int seq);
	public void likecountplus(int seq);
	public void dislikecount(int seq);
	public boolean update(int seq,String title, String content);
	public boolean delete(int seq);
	public boolean pdsdelete(int seq);
	public boolean repAlldelete(int seq);
	
	public void readcountplus(int seq);
	public void scrapcountplus(int seq);
	public void commentcountplus(int seq);
	public void scrapcountminus(int seq);
	public void commentcountminus(int seq);
	
	public boolean checkcomment(int seq);
	
	public List<TechbbsDto> gettechBbssortPagingList(PagingBean paging, String whatsort);
	//메인페이지뿌리기용
		public List<TechbbsDto> gettechBbsList();
		public List<LifeBbsDto> getlifeBbsList();
		public List<newbbs5HWCodingVO> getbbs5BbsList();
		public List<QnaDto> getqnaBbsList();
		public boolean checkqnacomment(int seq);
		public boolean checklifecomment(int seq);
		//전체게시판 만들기용
		public List<TechbbsDto> alltechBbsList();
		public List<LifeBbsDto> alllifeBbsList();
		public List<newbbs5HWCodingVO> allbbs5BbsList();
		public List<QnaDto> allqnaBbsList();
		public List<CombbsDto> allcomBbsList();
		public List<ColumnBbsDto> allcolBbsList();
		public List<totalbbsdto> gettotalBbsList();
		//mypage 좋아요한글뿌리기
		public List<TechbbsDto> getliketechBbsList(String likeid);
		public List<LifeBbsDto> getlikealllifeBbsList(String likeid);
		public List<newbbs5HWCodingVO> getlikeallbbs5BbsList(String likeid);
}
