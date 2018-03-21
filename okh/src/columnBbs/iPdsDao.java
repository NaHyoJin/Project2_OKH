package columnBbs;

import java.util.List;

import columnBbs.ColumnBbsDto;
import columnBbs.PdsDto;

public interface iPdsDao {
	
	public List<PdsDto> getPdsList();	
	public boolean writePds(PdsDto pds);
	
	public boolean downloadcount(int seq);

	
	public PdsDto getDetailBbs(int seq);
}
