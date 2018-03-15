package studysrc;

import java.util.List;

public interface iComDao {
	public List<CombbsDto> getComList();
	public void readcount(int seq);
	public String[] getTagName(String tagname);
	public boolean writeBbs(CombbsDto dto);
	public List<comment_bbsDto> detailbbs(int seq);
}
