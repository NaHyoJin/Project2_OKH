package service;

import java.util.List;

import dao.TechbbsDao;
import dao.iTechbbsDao;
import dto.PagingBean;
import dto.TechbbsDto;

public interface TechbbsServiceImpl {
	public List<TechbbsDto> gettechBbsList();
	public List<TechbbsDto> gettechBbsPagingList(PagingBean paging, String searchWord, int search);
	public String[] getTagName(String tagname);
	public boolean writeBbs(TechbbsDto bbs);
}
