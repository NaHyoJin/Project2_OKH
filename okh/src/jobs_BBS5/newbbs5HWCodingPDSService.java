package jobs_BBS5;

import java.util.List;

import techpds.PdsDto;

public class newbbs5HWCodingPDSService implements newbbs5HWCodingPDSServiceImpl {

	private static newbbs5HWCodingPDSService pdsService = new newbbs5HWCodingPDSService();
	
	public newbbs5HWCodingPDSDaoImpl pdsdao;
	
	private newbbs5HWCodingPDSService() {
		pdsdao = new newbbs5HWCodingPDSDao();
	}
	public static newbbs5HWCodingPDSService getInstance() {
		return pdsService;
	}
	
	@Override
	public boolean writePds(newbbs5HWCodingPDSVO pds) {
		// TODO Auto-generated method stub
		return pdsdao.writePds(pds);
	}

	@Override
	public newbbs5HWCodingPDSVO getPdsBbs(int seq) {
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
	public List<newbbs5HWCodingPDSVO> getpdsList(int parent) {
		// TODO Auto-generated method stub
		return pdsdao.getpdsList(parent);
	}

}
