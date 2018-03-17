package jobs_BBS5;

import java.util.List;

import techpds.PdsDao;
import techpds.PdsService;
import techpds.iPdsDao;

public class jobsBbs5MaterialsService implements jobsBbs5MaterialsServiceImpl {

	//싱글톤 생성 부분.
	private static jobsBbs5MaterialsService pdsService = new jobsBbs5MaterialsService();
	
	public jobsBbs5MaterialsDaoImpl pdsdao;
	
	private jobsBbs5MaterialsService() {
		pdsdao = new jobsBbs5MaterialsDao();
	}
	
	public static jobsBbs5MaterialsService getInstance() {
		return pdsService;
	}
	
	
	//모든글 가지고 오는것.
	@Override
	public List<BbsMaterialsBeanDtoVO> getPdsList() {
		// TODO Auto-generated method stub
		return pdsdao.getPdsList();
	}
	//부모글 있는것.
	@Override
	public List<BbsMaterialsBeanDtoVO> getPdsList(int parent) {
		// TODO Auto-generated method stub
		return pdsdao.getPdsList(parent);
	}

	@Override
	public boolean writePds(BbsMaterialsBeanDtoVO pds) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean downloadcount(int seq) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<BbsMaterialsBeanDtoVO> getPdsPagingList(PagingBean paging, String searchWord, int search) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BbsMaterialsBeanDtoVO getPds(int seq) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void readcount(int seq) {
		
	}

}
