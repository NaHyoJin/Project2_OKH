package dao;

import java.util.List;

import dto.PdsDto;

public interface iPdsDao {
	public boolean writePds(PdsDto pds);
	
	public PdsDto getPdsBbs(int seq);
	public boolean pdsdelete(int seq);

	public boolean pdsupdate(int seq, String filename);
	public int getSeq();
}
