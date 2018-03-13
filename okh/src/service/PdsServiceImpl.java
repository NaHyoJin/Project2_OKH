package service;

import java.util.List;

import dto.PdsDto;

public interface PdsServiceImpl {
	public boolean writePds(PdsDto pds);
	
	public PdsDto getPdsBbs(int seq);
	public boolean pdsdelete(int seq);

	public boolean pdsupdate(int seq, String filename);
	public int getSeq();
}
