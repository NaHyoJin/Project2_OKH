package modelService;

import java.util.List;

import dto.CustUserBean;

public interface iCustUserManager {
	
	
	//모든 인간 리스트 가지고 오는것.
	public List<CustUserBean> getCustUserList();
	
	//회원 인간 추가 부분.
	public int custAdd(String id, String name, String address);
	
	//전체 삭제.
	public boolean deleteCustUser(String[] ids);
	
	//아이디 찾기 부분.
	public CustUserBean getCustUser(String id);
	
	//단일 삭제 부분.
	
	//단일 수정 부분.

}
