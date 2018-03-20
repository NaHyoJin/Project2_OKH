package user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.DBClose;
import db.DBConnection;

public class UserDao implements IUserDao {
	
	public UserDao() {
		DBConnection.initConnection();
	}

	@Override
	public UserDto login(String userID, String userPassword) {
		UserDto getDto = null;
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		String sql = " SELECT ID, NAME, AGE, GENDER, EMAIL, AUTH, PROFILE, SCORE FROM OKHMEM WHERE ID=? AND PWD=? ";
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("2/6 login Success");
			
			psmt = conn.prepareStatement(sql);
			System.out.println("3/6 login Success");
			
			psmt.setString(1, userID);
			psmt.setString(2, userPassword);
			System.out.println("4/6 login Success");
			
			rs = psmt.executeQuery();
			System.out.println("5/6 login Success");
			
			while(rs.next()) {
				String id = rs.getString(1);
				String name = rs.getString(2);
				int age = rs.getInt(3);
				String gender = rs.getString(4);
				String email = rs.getString(5);
				int auth = rs.getInt(6);
				String profile = rs.getString(7);
				int score = rs.getInt(8);
				
				getDto = new UserDto(id, null, name, age, gender, email, auth, profile, score);
			}
			System.out.println("6/6 login Success");
			
		} catch (SQLException e) {
			System.out.println("Login Fail");
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		
		return getDto;
	}

	@Override
	public int registerCheck(String userID) {
		String sql = " SELECT ID FROM OKHMEM WHERE ID = '" + userID + "'";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		int findId = 1;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("4/6 registerCheck Success");
			
			psmt = conn.prepareStatement(sql);
			System.out.println("5/6 registerCheck Success");

			rs = psmt.executeQuery(sql);
			System.out.println("6/6 registerCheck Success");
			
			if(rs.next() || userID.equals("")) {
				findId = 0;
			}
			
		} catch (SQLException e) {
			System.out.println("registerCheck Fail");
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		
		return findId;
	}

	@Override
	public boolean addMember(String userID, String userPassword, String userName, String age, String gender,
			String email, String auth, String profile) {
		String sql = " INSERT INTO OKHMEM "
				+ " (ID, PWD, NAME, AGE, GENDER, EMAIL, AUTH, PROFILE, SCORE) "
				+ " VALUES(?, ?, ?, ?, ?, ?, 1, ?, 0) ";
		System.out.println("profile in dao = " + profile);
		
		Connection conn = null;
		PreparedStatement psmt = null;
		
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("4/6 addMember Success");
			
			psmt = conn.prepareStatement(sql);
			System.out.println("5/6 addMember Success");
			
			psmt.setString(1, userID);
			psmt.setString(2, userPassword);
			psmt.setString(3, userName);
			psmt.setInt(4, Integer.parseInt(age));
			psmt.setString(5, gender);
			psmt.setString(6, email);
			psmt.setString(7, profile);
			
			count = psmt.executeUpdate();
			System.out.println("6/6 addMember Success");
			
		} catch (SQLException e) {
			System.out.println("addMember Fail");
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, null);
		}
		
		return count>0;
	}

	@Override
	public boolean update(String userID, String userPassword, String userName, String age, String gender, String email,
			String auth, String profile) {
		String sql = " UPDATE OKHMEM SET "
				+ " PWD=?, NAME=?, AGE=?, GENDER=?, EMAIL=?, PROFILE=? "
				+ " WHERE ID=? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("4/6 update Success");
			
			psmt = conn.prepareStatement(sql);
			System.out.println("5/6 update Success");
			
			psmt.setString(1, userPassword);
			psmt.setString(2, userName);
			psmt.setInt(3, Integer.parseInt(age));
			psmt.setString(4, gender);
			psmt.setString(5, email);
			psmt.setString(6, profile);
			psmt.setString(7, userID);
			
			count = psmt.executeUpdate();
			System.out.println("6/6 update Success");
			
		} catch (SQLException e) {
			System.out.println("update Fail");
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, null);
		}
		
		return count>0;
	}

	@Override
	public boolean profile(String userID, String userProfile) {
		String sql = " UPDATE OKHMEM SET "
				+ " PROFILE=? "
				+ " WHERE ID=? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("4/6 update Success");
			
			psmt = conn.prepareStatement(sql);
			System.out.println("5/6 update Success");
			
			psmt.setString(1, userProfile);
			psmt.setString(2, userID);
			
			count = psmt.executeUpdate();
			System.out.println("6/6 update Success");
			
		} catch (SQLException e) {
			System.out.println("update Fail");
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, null);
		}
		
		return count>0;
	}

	@Override
	public String getProfile(String userID) {
		String sql = " SELECT PROFILE FROM OKHMEM "
				+ " WHERE ID=? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 getProfile Success");
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, userID);
			System.out.println("2/6 getProfile Success");
			
			rs = psmt.executeQuery();
			System.out.println("3/6 getProfile Success");
			
			if(rs.next()) {
				if(rs.getString(1).equals(" ")) {
					return "http://localhost:8090/okh_1/image/muser.png";
				}
				return "http://localhost:8090/okh_1/upload/" + rs.getString(1);
			}
			
		} catch (SQLException e) {
			System.out.println("getProfile Fail");
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		
		return "http://localhost:8090/okh_1/image/muser.png";
	}

	@Override
	public int getScore(String userID) {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		String sql = " SELECT SCORE FROM OKHMEM WHERE ID=? ";
		
		int score = 0;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 getScore Success");
			
			psmt = conn.prepareStatement(sql);
			System.out.println("2/6 getScore Success");
			
			psmt.setString(1, userID);
			System.out.println("3/6 getScore Success");
			
			rs = psmt.executeQuery();
			System.out.println("4/6 getScore Success");
			
			if(rs.next()) {
				score = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			System.out.println("getScore Fail");
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		
		return score;
	}

	@Override
	public boolean updateScore(String userID, int score) {
		String sql = " UPDATE OKHMEM SET SCORE=? WHERE ID=? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 updateScore Success");
			
			psmt = conn.prepareStatement(sql);
			System.out.println("2/6 updateScore Success");
			
			psmt.setInt(1, score);
			psmt.setString(2, userID);
			
			count = psmt.executeUpdate();
			System.out.println("3/6 updateScore Success");
			
		} catch (SQLException e) {
			System.out.println("updateScore Fail");
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, null);
		}
		
		return count>0;
	}

	@Override
	public UserDto getUser(String userID) {
		String sql = " SELECT ID, NAME, AGE, GENDER, EMAIL, AUTH, PROFILE, SCORE FROM OKHMEM WHERE ID=? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		UserDto getDto = null;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("4/6 getUser Success");
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, userID);
			System.out.println("5/6 getUser Success");

			rs = psmt.executeQuery();
			System.out.println("6/6 getUser Success");
			
			if(rs.next()) {
				String id = rs.getString(1);
				String name = rs.getString(2);
				int age = rs.getInt(3);
				String gender = rs.getString(4);
				String email = rs.getString(5);
				int auth = rs.getInt(6);
				String profile = rs.getString(7);
				int score = rs.getInt(8);
				
				getDto = new UserDto(id, null, name, age, gender, email, auth, profile, score);
			}
			
		} catch (SQLException e) {
			System.out.println("getUser Fail");
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		
		return getDto;
	}

}
