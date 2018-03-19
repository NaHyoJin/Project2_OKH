package techreplebbs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import db.DBConnection;
import db.DBClose;

public class TechRepbbsDao implements iTechRepbbsDao {
	public TechRepbbsDao() {
		DBConnection.initConnection();
	}
	@Override
	public List<TechRepbbsDto> getRepBbsList(int seq) {
		String sql = " SELECT SEQ, ID, "
				+ " CONTENT, WDATE, PARENT,"
				+ " DEL "
				+ " FROM TECHREPBBS "
				+ " WHERE PARENT=? AND DEL=0 "
				+ " ORDER BY WDATE ASC ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		List<TechRepbbsDto> list = new ArrayList<TechRepbbsDto>();
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 getRepBbsList Success");
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			System.out.println("2/6 getRepBbsList Success");
			
			rs = psmt.executeQuery();
			System.out.println("3/6 getRepBbsList Success");
			
			while (rs.next()) {
				TechRepbbsDto dto = new TechRepbbsDto(rs.getInt(1), // seq, 
										rs.getString(2), // id, 
										rs.getString(3), //ref, 
										rs.getString(4),	//step, 
										rs.getInt(5), // depth, 
										rs.getInt(6)); // readcount
				list.add(dto);
			}
			System.out.println("4/6 getBbsList Success");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("getBbsList fail");
		} finally {
			DBClose.close(psmt, conn, rs);			
		}
		
		return list;
	}

	
	@Override
	public boolean writeBbs(TechRepbbsDto bbs) {
		String sql = " INSERT INTO TECHREPBBS(SEQ, ID, "
				+ " CONTENT, WDATE,PARENT,DEL) "
				+ " VALUES(SEQ_TECHREPBBS.NEXTVAL, ?, "
				+ "  ?, SYSDATE, ?, "
				+ " 0) ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();			
			System.out.println("1/6 writeBbs Success");
			
			psmt = conn.prepareStatement(sql);
			System.out.println("2/6 writeBbs Success");
			
			psmt.setString(1, bbs.getId());
			psmt.setString(2, bbs.getContent());
			psmt.setInt(3, bbs.getParent());
			count = psmt.executeUpdate();
			
			System.out.println("3/6 writeBbs Success");
			
		} catch (SQLException e) {
			System.out.println("writeBbs fail");
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, null);			
		}
		
		return count>0?true:false;
	}

	@Override
	public boolean repupdate(int seq, String content) {
		String sql = " UPDATE TECHREPBBS SET "
				+ " CONTENT=? "
				+ " WHERE SEQ=? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		int count = 0;
		System.out.println(seq+content);
		try {
			conn = DBConnection.getConnection();	
			System.out.println("2/6 S repupdate");
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, content);
			psmt.setInt(2, seq);
			
			System.out.println("3/6 S repupdate");
			
			count = psmt.executeUpdate();
			System.out.println("4/6 S repupdate");
			
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally{
			DBClose.close(psmt, conn, null);	
			System.out.println("5/6 S repupdate");
		}		
		
		return count>0?true:false;
	}

	@Override
	public boolean repdelete(int seq) {
		String sql=" UPDATE TECHREPBBS SET "
				+ " DEL=1 "
				+ " WHERE SEQ=? ";
		
		int count = 0;
		Connection conn=null;
		PreparedStatement psmt=null;
		
		try {
			conn = DBConnection.getConnection();			
			psmt=conn.prepareStatement(sql);
			psmt.setInt(1, seq);			
			count = psmt.executeUpdate();
			
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally{
			DBClose.close(psmt, conn, null);			
		}
				
		return count>0?true:false;
	}

	


	
}

