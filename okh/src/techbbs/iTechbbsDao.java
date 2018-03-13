package techbbs;

import java.util.List;


public interface iTechbbsDao {
	public List<TechbbsDto> gettechBbsList();
	public List<TechbbsDto> gettechBbsPagingList(PagingBean paging, String searchWord, int search);
	public String[] getTagName(String tagname);
	public boolean writeBbs(TechbbsDto bbs);
	
}
