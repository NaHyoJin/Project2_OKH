package studysrc;

import java.util.List;

public interface ICombbsService {
	public List<CombbsDto> getComList();
	public void readcount(int seq);
	public String[] getTagName(String tagname);
	public List<comment_bbsDto> detailbbs(int seq);
	public boolean writeBbs(CombbsDto dto);
	public List<CombbsDto> getpagingComList(PagingBean paging, String searchWord, int search);
	public List<CombbsDto> commentnull(int seq);
	public void commentcount(int seq);
	public void updatebbs(CombbsDto dto,int seq);
	public void delbbs(int seq);
	public boolean checkcomment(int seq);
	public void likecountplus(int seq);
	public void dislikecount(int seq);
	public boolean getparent(int seq);
	public boolean checkjoiner(int seq);
	public void commentdiscount(int seq);
	public int getSeq();
	public boolean writecalendar(CombbsDto dto,int child);
}
