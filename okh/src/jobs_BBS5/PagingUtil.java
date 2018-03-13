package jobs_BBS5;


public class PagingUtil {
	
	public static PagingBean setPagingInfo(PagingBean paging) {

		paging.setCountPerPage(10);	// 페이지에서 보여줄 글의 갯수
		paging.setBlockCount(10);	//[1][2][3][4][5]~[10]
		
		/*
			올린 글의 갯수: 12
			
			getTotalCount()
						1페이지
				12 - (1 - 1) * 10 = 12	--> startNum	
						2페이지
				12 - (2 - 1) * 10 = 2	--> startNum		
		*/
		paging.setStartNum(
					paging.getTotalCount()
					 - (paging.getNowPage() - 1) * paging.getCountPerPage());
		
		return paging;		
	}
}