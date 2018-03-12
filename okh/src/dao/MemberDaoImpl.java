package dao;

import java.util.List;

import dto.MemberDto;

public interface MemberDaoImpl {
	
	public boolean addMember(MemberDto dto);	
	
	public boolean idCheck(String id);
	
	public MemberDto login(MemberDto dto);
	
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
