package techpds;

import java.util.List;


public interface PdsServiceImpl {
	public boolean writePds(PdsDto pds);
	public List<PdsDto> getpdsList(int parent);
	public PdsDto getPdsBbs(int seq);
	public boolean pdsdelete(int seq);

	public boolean pdsupdate(int seq, String filename);
	public int getSeq();
}
