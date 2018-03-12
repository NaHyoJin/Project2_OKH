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

}
