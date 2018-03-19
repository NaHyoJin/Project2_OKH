package jobs_BBS5;

import java.util.List;

public interface newbbs5HWCodingPDSServiceImpl {

	public boolean writePds(newbbs5HWCodingPDSVO pds);
	public List<newbbs5HWCodingPDSVO> getpdsList(int parent);
	public newbbs5HWCodingPDSVO getPdsBbs(int seq);
	public boolean pdsdelete(int seq);

	public boolean pdsupdate(int seq, String filename);
	public int getSeq();
}
