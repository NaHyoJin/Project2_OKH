package studysrc;

import java.util.List;

public interface ICombbsService {
	public List<CombbsDto> getComList();
	public void readcount(int seq);
	public String[] getTagName(String tagname);
	public List<comment_bbsDto> detailbbs(int seq);
	public boolean writeBbs(CombbsDto dto);
	
}
