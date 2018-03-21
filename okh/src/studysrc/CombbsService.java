package studysrc;

import java.util.List;

public class CombbsService implements ICombbsService {
	private static CombbsService comservice = new CombbsService();
	public iComDao comdao;
	private CombbsService() {
		comdao = new ComDao();
	}
	public static CombbsService getInstance() {
		return comservice;
	}
	
	@Override
	public List<CombbsDto> getComList() {
		return comdao.getComList();
	}

	@Override
	public void readcount(int seq) {
			comdao.readcount(seq);
	}
	@Override
	public String[] getTagName(String tagname) {
		return comdao.getTagName(tagname);
	}
	
	
	@Override
	public boolean writeBbs(CombbsDto dto) {
		return comdao.writeBbs(dto);
	}
	@Override
	public List<comment_bbsDto> detailbbs(int seq) {
		
		return comdao.detailbbs(seq);
	}
	@Override
	public List<CombbsDto> getpagingComList(PagingBean paging, String searchWord, int search) {
		return comdao.getpagingComList(paging, searchWord, search);
	}
	@Override
	public List<CombbsDto> commentnull(int seq) {
	
		return comdao.commentnull(seq);
	}
	
	public void commentcount(int seq) {
		comdao.commentcount(seq);
	}
	@Override
	public void updatebbs(CombbsDto dto,int seq) {
		 comdao.updatebbs(dto,seq);
	}
	@Override
	public void delbbs(int seq) {
		comdao.delbbs(seq);
	}
	@Override
	public void likecountplus(int seq) {
		comdao.likecountplus(seq);
	}
	@Override
	public void dislikecount(int seq) {
		comdao.dislikecount(seq);
	}
	@Override
	public boolean checkcomment(int seq) {
		return comdao.checkcomment(seq);
	}
	@Override
	public boolean getparent(int seq) {
		
		return comdao.getparent(seq);
	}
	@Override
	public boolean checkjoiner(int seq) {
		return comdao.checkjoiner(seq);
	}
	@Override
	public void commentdiscount(int seq) {
		comdao.commentdiscount(seq);
		
	}
	@Override
	public int getSeq() {
		return comdao.getSeq();
	}
	@Override
	public boolean writecalendar(CombbsDto dto, int child) {
		
		return comdao.writecalendar(dto, child);
	}
	
	
	
	
	
	
}
