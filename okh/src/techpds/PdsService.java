package techpds;

import java.util.List;


public class PdsService implements PdsServiceImpl{
	private static PdsService pdsService=new PdsService();
	public iPdsDao pdsdao;
	private PdsService() {
		pdsdao=new PdsDao();
	}
	public static PdsService getInstance() {
		return pdsService;
	}
	@Override
	public boolean writePds(PdsDto pds) {
		// TODO Auto-generated method stub
		return pdsdao.writePds(pds);
	}

	@Override
	public PdsDto getPdsBbs(int seq) {
		// TODO Auto-generated method stub
		return pdsdao.getPdsBbs(seq);
	}


	@Override
	public boolean pdsdelete(int seq) {
		// TODO Auto-generated method stub
		return pdsdao.pdsdelete(seq);
	}

	@Override
	public boolean pdsupdate(int seq, String filename) {
		// TODO Auto-generated method stub
		return pdsdao.pdsupdate(seq, filename);
	}
	@Override
	public int getSeq() {
		// TODO Auto-generated method stub
		return pdsdao.getSeq();
	}
	@Override
	public List<PdsDto> getpdsList(int parent) {
		// TODO Auto-generated method stub
		return pdsdao.getpdsList(parent);
	}

}
