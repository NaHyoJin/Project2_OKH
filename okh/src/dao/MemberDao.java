package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import db.DBClose;
import db.DBConnection;
import dto.MemberDto;

public class MemberDao implements MemberDaoImpl {//프로젝트1 인간 다오 부분 그냥 참고 할려고 한거임. 필요없음.

	/*
	ID VARCHAR2(50) PRIMARY KEY,
	PWD VARCHAR2(50) NOT NULL,
	NAME VARCHAR2(50) NOT NULL,
	EMAIL VARCHAR2(50) NOT NULL,
	JOINDATE DATE NOT NULL,
	AUTH NUMBER(1) NOT NULL,	--사용자(3) 관리자(1) 임의컬럼.
	MDEL NUMBER(1) NOT NULL,		-- 삭제번호. 기본은 1번 삭제되면 0번.
	LDEL NUMBER(1) NOT NULL	
	 */
	
	//회원가입 부분.
	@Override
	public boolean addMember(MemberDto dto) {		
		String sql = " INSERT INTO SNSMEMBER "
				+ " (ID, PWD, NAME, EMAIL, JOINDATE, AUTH, MDEL, LDEL) "
				+ " VALUES(?, ?, ?, ?, SYSDATE, 3, 0, 1) ";
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		int count = 0;
						
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 addSNSMember Success");
			
			stmt = conn.prepareStatement(sql);
			System.out.println("2/6 addSNSMember Success");
			
			// ? -> setting
			stmt.setString(1, dto.getId());
			stmt.setString(2, dto.getPwd());
			stmt.setString(3, dto.getName());
			stmt.setString(4, dto.getEmail());
			
			count = stmt.executeUpdate();
			System.out.println("3/6 addSNSMember Success");
			
		} catch (SQLException e) {
			System.out.println("addSNSMember fail");
		} finally {
			DBClose.close(stmt, conn, null);			
		}
		
		return count>0?true:false;
	}

	
	//아이디 체크 부분.
	@Override
	public boolean idCheck(String id) {
		String sql = " SELECT ID FROM SNSMEMBER "
				+ " WHERE ID = '" + id + "'";
		
		boolean findId = false;
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
				
		try {			
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);			
			rs = psmt.executeQuery(sql);
			
			while(rs.next()){			
				findId = true;			
			}
			
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally{			
			DBClose.close(psmt, conn, rs);			
		}
		
		return findId;
	}//////////////////idCheck

	
	
	//로그인 할 때 아이디, 암호 
	@Override
	public MemberDto login(MemberDto dto) {
		
		MemberDto mem = null;
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		String sql = " SELECT ID, NAME, EMAIL, JOINDATE, AUTH, MDEL, LDEL "
				+ " FROM SNSMEMBER "
				+ " WHERE ID = ? AND PWD = ? ";
		
		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			
			System.out.println("1/6 login Success");
			
			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getPwd());			
			
			System.out.println("2/6 login Success");
			
			rs = psmt.executeQuery();
			
			while(rs.next()){
				String id = rs.getString(1);
				String name = rs.getString(2);
				String email = rs.getString(3);
				String joindate = rs.getString(4);
				int auth = rs.getInt(5);
				int mdel = rs.getInt(6);
				int ldel = rs.getInt(7);
				
				mem = new MemberDto(id, null, name, email, joindate, auth, mdel, 0);
//									
			}	
			System.out.println("3/6 login Success");
			
		} catch (SQLException e) {			
			System.out.println("login Fail");
		} finally{
			DBClose.close(psmt, conn, rs);		
		}	
		
		return mem;
	}
	
	@Override
	public void addfollow(String myid, String followid) {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		String sql = " INSERT INTO SNSFOLLOW "
				+ " (MYID, FOLLOWID, CONNECTN) "
				+ " VALUES(?, ?, 0) ";
		
		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			
			System.out.println("1/6 addfollow Success");
			
			psmt.setString(1, myid);
			psmt.setString(2, followid);			
			
			System.out.println("2/6 addfollow Success");
			
			rs = psmt.executeQuery();
			
		} catch (SQLException e) {			
			System.out.println("addfollow Fail");
		} finally{
			DBClose.close(psmt, conn, rs);		
		}	
	}
	
	
	@Override
	public MemberDto updateMypage(String myid) {
		
		MemberDto mem = null;
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		String sql = " SELECT ID, NAME, EMAIL, JOINDATE, AUTH, MDEL, LDEL "
				+ " FROM SNSMEMBER "
				+ " WHERE ID = '" + myid + "' ";
		
		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			
			System.out.println("1/6 update Success");
			
			System.out.println("2/6 update Success");
			
			rs = psmt.executeQuery();
			
			while(rs.next()){
				String id = rs.getString(1);
				String name = rs.getString(2);
				String email = rs.getString(3);
				String joindate = rs.getString(4);
				int auth = rs.getInt(5);
				int mdel = rs.getInt(6);
				int ldel = rs.getInt(7);
				
				mem = new MemberDto(id, null, name, email, joindate, auth, mdel, ldel);			
			}	
			System.out.println("3/6 update Success");
			
		} catch (SQLException e) {			
			System.out.println("update Fail");
		} finally{
			DBClose.close(psmt, conn, rs);		
		}	
		
		return mem;
		
	}


	@Override
	public boolean updateMember(String id, String name, String email, String pw) {
		
		String sql = " UPDATE SNSMEMBER SET "
				+ " NAME=?, EMAIL=?, PWD=? "
				+ " WHERE ID = '" + id + "' ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();	
			System.out.println("2/6 S updateBbs");
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, name);
			psmt.setString(2, email);
			psmt.setString(3, pw);
			
			System.out.println("3/6 S updateBbs");
			
			count = psmt.executeUpdate();
			System.out.println("4/6 S updateBbs");
			
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally{
			DBClose.close(psmt, conn, null);	
			System.out.println("5/6 S updateBbs");
		}		
		
		return count>0?true:false;
		
	}
	
	public List<MemberDto> getSelectName(String selname){
		List<MemberDto> list = new ArrayList<MemberDto>();
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		String sql = " SELECT ID, NAME, EMAIL, MDEL "
				+ " FROM SNSMEMBER "
				+ " WHERE NAME LIKE '%" + selname + "%' "
						+ " AND MDEL = 0 ";
		
		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			
			System.out.println("1/6 update Success");
			
			System.out.println("2/6 update Success");
			
			rs = psmt.executeQuery();
			
			while(rs.next()){
				String id = rs.getString(1);
				String name = rs.getString(2);
				String email = rs.getString(3);
				int mdel = rs.getInt(4);
				
				MemberDto dto = new MemberDto(id, null, name, email, null, 0, mdel, 1);
				list.add(dto);
			}	
			System.out.println("3/6 update Success");
			
		} catch (SQLException e) {			
			System.out.println("update Fail");
		} finally{
			DBClose.close(psmt, conn, rs);		
		}	
		
		return list;
	}

	@Override
	public boolean dropMember(String id) {
		
		String sql = " UPDATE SNSMEMBER SET "
				+ " MDEL=1, LDEL=1 "
				+ " WHERE ID = '" + id + "' ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();	
			System.out.println("2/6 S dropMember");
			
			psmt = conn.prepareStatement(sql);
			
			System.out.println("3/6 S dropMember");
			
			count = psmt.executeUpdate();
			System.out.println("4/6 S dropMember");
			
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally{
			DBClose.close(psmt, conn, null);	
			System.out.println("5/6 S dropMember");
		}		
		
		return count>0?true:false;
	}
	
	@Override
	public int loginMdel(String id) {
		
		String sql = " SELECT MDEL "
				+ " FROM SNSMEMBER "
				+ " WHERE ID = '" + id + "' ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		int mdel = 0;
		
		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);			
			rs = psmt.executeQuery(sql);
			
			System.out.println("2/6 S loginMdel");
			while(rs.next()){			
				mdel = rs.getInt(1);			
			}
			
			
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally{
			DBClose.close(psmt, conn, null);	
			System.out.println("5/6 S loginMdel");
		}		
		
		return mdel;
		
	}


	@Override
	public boolean loginLdel(String id) {
		String sql = " UPDATE SNSMEMBER SET "
				+ " LDEL=0 "
				+ " WHERE ID = '" + id + "' ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();	
			System.out.println("2/6 S loginLdel");
			
			psmt = conn.prepareStatement(sql);
			
			System.out.println("3/6 S loginLdel");
			
			count = psmt.executeUpdate();
			System.out.println("4/6 S loginLdel");
			
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally{
			DBClose.close(psmt, conn, null);	
			System.out.println("5/6 S loginLdel");
		}		
		
		return count>0?true:false;
	}


	@Override
	public boolean logoutLdel(String id) {
		String sql = " UPDATE SNSMEMBER SET "
				+ " LDEL=1 "
				+ " WHERE ID = '" + id + "' ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();	
			System.out.println("2/6 S logoutLdel");
			
			psmt = conn.prepareStatement(sql);
			
			System.out.println("3/6 S logoutLdel");
			
			count = psmt.executeUpdate();
			System.out.println("4/6 S logoutLdel");
			
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally{
			DBClose.close(psmt, conn, null);	
			System.out.println("5/6 S logoutLdel");
		}		
		
		return count>0?true:false;
	}
	
	
	
	// ㅎㅈ
	//아이디 찾기 부분.
		@Override
		public String idRecover(String name, String email) {
			String recoverID = null;
			
			Connection conn = null;
			PreparedStatement psmt = null;
			ResultSet rs = null;
			
			String sql = " SELECT ID "
					+ " FROM SNSMEMBER "
					+ " WHERE name = ? AND email = ? ";
			
			try {
				conn = DBConnection.getConnection();
				psmt = conn.prepareStatement(sql);
				
				System.out.println("1/6 idRecover Success");
				
				psmt.setString(1, name);
				psmt.setString(2, email);			
				
				System.out.println("2/6 idRecover Success");
				
				//쿼리 작동 후 결과값 rs로 전달.
				rs = psmt.executeQuery();
				
				while(rs.next()){
					String id = rs.getString(1);
					
					recoverID = id;
				}	
				System.out.println("3/6 idRecover Success");
				
			} catch (SQLException e) {			
				System.out.println("idRecover Fail");
			} finally{
				DBClose.close(psmt, conn, rs);		
			}	
			
			return recoverID;
		}////////////////////////idRecover
		
		
		//암호 찾기 부분.
		@Override
		public String passwordRecover(String name, String email) {
			//찾은 암호 넣어둘 변수.
			String recoverPW = null;
			
			Connection conn = null;
			PreparedStatement psmt = null;
			ResultSet rs = null;
			
			String sql = " SELECT PWD "
					+ " FROM SNSMEMBER "
					+ " WHERE name = ? AND email = ? ";
			
			try {
				conn = DBConnection.getConnection();
				psmt = conn.prepareStatement(sql);
				
				System.out.println("1/6 passwordRecover Success");
				
				psmt.setString(1, name);
				psmt.setString(2, email);			
				
				System.out.println("2/6 passwordRecover Success");
				
				//쿼리 작동 후 결과값 rs로 전달.
				rs = psmt.executeQuery();
				
				while(rs.next()){
					String pwd = rs.getString(1);
					recoverPW = pwd;
				}	
				System.out.println("3/6 passwordRecover Success");
				
			} catch (SQLException e) {			
				System.out.println("passwordRecover Fail");
			} finally{
				DBClose.close(psmt, conn, rs);		
			}	
			
			return recoverPW;
		}/////////////////////////passwordRecover
			
		
		
		//무작위 암호 만드는 부분.
		
		public static String tempPass(int len) {//len은 뭐냐? 몇 자리 로 만들것인가.
			
			//변경될 암호 코드들.
			char[] charaters = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
	                'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
	                'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7',
	                '8', '9' };

			StringBuilder sb = new StringBuilder("");
			
	        Random rd = new Random();
	        for (int i = 0; i < len; i++) {
	               sb.append(charaters[rd.nextInt(charaters.length)]);
	         }
	        return sb.toString();
		}
		
		
		//임시 암호로 DB바꿔주는부분.
		public String tempPassChange(String name, String email) {
			
			//변경된 암호 저장 변수.
			String tempPwd;
			
			tempPwd = tempPass(4);// 4글자 임시 암호.
			
			// 직접 DB수정 부분.
			String sql = " UPDATE SNSMEMBER SET "
					+ " PWD = ? "//기존 암호, 임시 암호로 바꾸는 부분.
					+ " WHERE name = ? AND email = ? ";//조건 부분.
			
			Connection conn = null;
			PreparedStatement psmt = null;
			int count = 0;
			
			try {
				conn = DBConnection.getConnection();	
				System.out.println("2/6 tempPassChange");
				
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, tempPwd);
				psmt.setString(2, name);
				psmt.setString(3, email);
				
				System.out.println("3/6 tempPassChange");
				
				//실행.
				count = psmt.executeUpdate();
				System.out.println("4/6 tempPassChange");
				
			} catch (SQLException e) {			
				e.printStackTrace();
			} finally{
				DBClose.close(psmt, conn, null);	
				System.out.println("5/6 tempPassChange");
			}		
			
			return tempPwd;
		}
	
	

}
