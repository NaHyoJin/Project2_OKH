package jobs_BBS5;

import java.util.List;

public interface jobsBbs5MaterialsServiceImpl {

	//무조건 일단 다 가지고 온다....................향후 검색 부분 추가해보자.
	public List<BbsMaterialsBeanDtoVO> getPdsList();
	public List<BbsMaterialsBeanDtoVO> getPdsList(int parent);//부모글 있는것.
	
	//파일 넣는 부분. DB에 넣는 부분.
	public boolean writePds(BbsMaterialsBeanDtoVO pds);
	
	//다운로드수 증가 부분.
	public boolean downloadcount(int seq);

	//검색 부분
	public List<BbsMaterialsBeanDtoVO> getPdsPagingList(PagingBean paging, String searchWord, int search);
	
	//디테일 하나 가지고 오는 부분.
	public BbsMaterialsBeanDtoVO getPds(int seq);
	
	//조회수 부분.
	public void readcount(int seq);
	
	//글 수정.
	public boolean updateBbs(BbsMaterialsBeanDtoVO bbs);
	//글 삭제.
	public boolean deleteMaterials(int seq);
	
}
