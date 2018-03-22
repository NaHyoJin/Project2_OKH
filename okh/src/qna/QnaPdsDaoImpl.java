package qna;

import java.util.List;



public interface QnaPdsDaoImpl {
	public boolean writePds(QnaPdsDto pds);
	public List<QnaPdsDto> getpdsList(int parent);
	public QnaPdsDto getPdsBbs(int seq);
	public boolean pdsdelete(int seq);

	public boolean pdsupdate(int seq, String filename);
	public int getPdsSeq();
	
	
	

}
