package studysrc;

import java.util.List;

public interface iComDao {
	public List<CombbsDto> getComList();
	public void readcount(int seq);
	public String[] getTagName(String tagname);
	public boolean writeBbs(CombbsDto dto);
	public List<comment_bbsDto> detailbbs(int seq);
	public List<CombbsDto> getpagingComList(PagingBean paging, String searchWord, int search);
	
	public List<CombbsDto> commentnull(int seq);
	public void commentcount(int seq);
	public void delbbs(int seq);
	public void updatebbs(CombbsDto dto,int seq);
}
