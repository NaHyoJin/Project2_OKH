package columnBbs;

import java.util.List;

import columnBbs.ColumnBbsDto;
import columnBbs.PagingBean;

public interface iColumnBbsDao {
	
	public boolean writeBbs(ColumnBbsDto bbs);
	public boolean updateBbs(ColumnBbsDto bbs);
		
	public ColumnBbsDto getBbs(int seq);
	public void readcount(int seq);
	public boolean answerBbs(int seq, ColumnBbsDto bbs);
	
	public boolean dellBbs(int seq);
	
	public List<ColumnBbsDto> getBbsList();
	public List<ColumnBbsDto> getBbsPagingList(PagingBean paging, String searchWord, int search);
	
	
	
	
	public ColumnBbsDto getdelltailBbs(int seq);


}




