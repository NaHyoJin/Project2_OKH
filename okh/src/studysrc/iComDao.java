package studysrc;

import java.util.List;

public interface iComDao {
	public List<CombbsDto> getComList();
	public void readcount(int seq);
	public String[] getTagName(String tagname);
	public boolean writeBbs(CombbsDto dto);
	public List<comment_bbsDto> detailbbs(int seq);
	public List<CombbsDto> getpagingComList(PagingBean paging, String searchWord, int search);
	public void commentdiscount(int seq);
	public List<CombbsDto> commentnull(int seq);
	public void commentcount(int seq);
	public void delbbs(int seq);
	public void updatebbs(CombbsDto dto,int seq);
	public int getSeq();
	public boolean checkcomment(int seq);
	public void likecountplus(int seq);
	public void dislikecount(int seq);
	public boolean getparent(int seq);
	public boolean checkjoiner(int seq);
	public boolean writecalendar(CombbsDto dto,int child);
	public boolean deletecalendar(int child);
}
