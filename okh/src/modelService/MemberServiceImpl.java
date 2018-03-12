package modelService;

import java.util.List;

import dto.MemberDto;

public interface MemberServiceImpl {

	
	//sns 회원 추가.
	public boolean addMember(MemberDto dto);
	
	
	//아이디 체크
	public boolean idCheck(String id);
	
	
	//로그인.
	public MemberDto login(MemberDto dto);
	
	// 내 자신 팔로우
	public void addfollow(String myid, String followid);
	
	public MemberDto updateMypage(String myid);
	
	public boolean updateMember(String id, String name, String email, String pw);
	
	public List<MemberDto> getSelectName(String selname);
	
	public boolean dropMember(String id);
	
	public int loginMdel(String id);
	
	
	public boolean loginLdel(String id);
	
	public boolean logoutLdel(String id);
	
	// ㅎㅈ
	//아이디 찾기 부분.
	public String idRecover(String name, String email);
	
	//암호 찾기 부분.
	public String passwordRecover(String name, String email);
	
	//암호 임시 암호로 바꿔주는부분.
	public String tempPassChange(String name, String email);
}
