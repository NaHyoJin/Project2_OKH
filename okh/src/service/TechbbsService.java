package service;
import java.util.List;


import dto.*;
import dao.*;

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
	
	
}
