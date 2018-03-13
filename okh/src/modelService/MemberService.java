package modelService;

import java.util.List;

import dao.TechbbsDao;
import dao.MemberDaoImpl;
import dto.MemberDto;

public class MemberService implements MemberServiceImpl {

	MemberDaoImpl dao = new TechbbsDao();

	
	@Override
	public boolean addMember(MemberDto dto) {		
		return dao.addMember(dto);		
	}

	
	@Override
	public boolean idCheck(String id) {		
		return dao.idCheck(id);		
	}

	
	@Override
	public MemberDto login(MemberDto dto) {		
		return dao.login(dto);
	}


	@Override
	public void addfollow(String myid, String followid) {
		dao.addfollow(myid, followid);
	}
	
	@Override
	public MemberDto updateMypage(String myid) {
		return dao.updateMypage(myid);
	}


	@Override
	public boolean updateMember(String id, String name, String email, String pw) {
		return dao.updateMember(id, name, email, pw);
	}
	
	@Override
	public List<MemberDto> getSelectName(String selname){
		return dao.getSelectName(selname);
	}
	
	@Override
	public boolean dropMember(String id) {
		return dao.dropMember(id);
	}


	@Override
	public int loginMdel(String id) {
		return dao.loginMdel(id);
	}


	@Override
	public boolean loginLdel(String id) {
		return dao.loginLdel(id);
	}


	@Override
	public boolean logoutLdel(String id) {
		return dao.logoutLdel(id);
	}
	
	// ㅎㅈ
	//아이디 찾기 부분.
	public String idRecover(String name, String email) {
		return dao.idRecover(name, email);
	}
	
	//암호 찾기 부분.
	public String passwordRecover(String name, String email) {
		return dao.passwordRecover(name, email);
	}
	
	//암호 임시 암호로 바꿔주는부분.
	public String tempPassChange(String name, String email) {
		return dao.tempPassChange(name, email);
	}
	
}
