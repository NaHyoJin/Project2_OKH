package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;
import db.DBclose;
import dto.PdsDto;

public class PdsDao implements iPdsDao {

	@Override
	public boolean writePds(PdsDto pds) {
		String sql = " INSERT INTO TECH_PDS( "
				+ " SEQ, ID, FILENAME, REGDATE) "
				+ " VALUES(SEQ_TECH_PDS.NEXTVAL, "
				+ " ?, ?, SYSDATE) ";
		
		int count = 0;
		
		Connection conn = null;
		PreparedStatement psmt = null;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 S writePds");
				
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, pds.getId());
			psmt.setString(2, pds.getFilename());
			System.out.println("2/6 S writePds");
			
			count = psmt.executeUpdate();
			System.out.println("3/6 S writePds");
			
		} catch (SQLException e) {
			System.out.println("F writePds");
		} finally {
			DBclose.close(psmt, conn, null);
			System.out.println("4/6 S writePds");
		}		
		
		return count>0?true:false;
	}

	

	@Override
	public PdsDto getPdsBbs(int parent) {
		String sql = " SELECT SEQ, ID, FILENAME,PARENT, REGDATE "
				+ " FROM TECH_PDS "
				+ " WHERE PARENT=? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		PdsDto dto = null;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 getPdsBbs Success");
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, parent);
			
			System.out.println("2/6 getPdsBbs Success");
			
			rs = psmt.executeQuery();
			System.out.println("3/6 getPdsBbs Success");
			
			if(rs.next()) {
				dto = new PdsDto(rs.getInt(1),		//seq
						rs.getString(2),	//id
						rs.getString(3),	
						rs.getInt(4),
						rs.getString(5));	//regdate
			}	
			System.out.println("4/6 getPdsBbs Success");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("getPdsBbs fail");
			e.printStackTrace();			
		} finally {
			DBclose.close(psmt, conn, rs);	
			System.out.println("5/6 getBbs Success");
		}		
		
		return dto;
	}

	

	@Override
	public boolean pdsupdate(int seq, String filename) {
		String sql = " UPDATE TECH_PDS SET "
				+ " FILENAME=? "
				+ " WHERE SEQ=? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();	
			System.out.println("2/6 S pdsupdate");
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, filename);
			psmt.setInt(2, seq);
			
			System.out.println("3/6 S pdsupdate");
			
			count = psmt.executeUpdate();
			System.out.println("4/6 S pdsupdate");
			
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally{
			DBclose.close(psmt, conn, null);	
			System.out.println("5/6 S pdsupdate");
		}		
		
		return count>0?true:false;
	}

	@Override
	public boolean pdsdelete(int seq) {
		String sql=" DELETE  "
				+ " FROM TECH_PDS "
				+ " WHERE SEQ = ? ";
		Connection conn=null;
		PreparedStatement pstmt=null;//sql문에서?썻을때이렇게불러와야한다
		
		int count=0;
		
		try {
			conn=DBConnection.getConnection();
			pstmt=conn.prepareStatement(sql);
			System.out.println("1/6 pdsdelete Success");
			pstmt.setInt(1, seq);
			System.out.println("2/6 pdsdelete Success");
			count=pstmt.executeUpdate();
			System.out.println("3/6 pdsdelete Success");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {DBclose.close(pstmt, conn, null);}
		
		return count>0?true:false;
	}



	@Override
	public int getSeq() {
		String sql = " SELECT MAX(SEQ) "
				+ " FROM TECHBBS ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		int seq=0;
		try {
			conn=DBConnection.getConnection();
			System.out.println("1/6 getSeq Success");
			psmt=conn.prepareStatement(sql);
			System.out.println("2/6 getSeq Success");
			rs = psmt.executeQuery();
			rs.next();
			System.out.println("3/6 getSeq Success");
			seq = rs.getInt(1);
			System.out.println("4/6 getSeq Success"+seq);
		} catch (SQLException e) {
			System.out.println("getSeq fail");
			e.printStackTrace();
		}finally {
			DBclose.close(psmt, conn, null);			
		}
		
		
		return seq;
	}

}
