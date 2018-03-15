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
	
	
	
	
	
}
