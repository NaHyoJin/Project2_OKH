package lifeBbs;

public class LifeBbsPagingUtil {
	
	public static LifeBbsPagingDto setPagingInfo(LifeBbsPagingDto paging) {
		paging.setCountPerPage(10);
		paging.setBlockCount(10);
		
		paging.setStartNum(
				paging.getTotalCount() - (paging.getNowPage() - 1) * paging.getCountPerPage()
			);
		
		return paging;
	}

}
