package jobs_BBS5;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import db.DBConnection;
import db.DBClose;

public class HWRepbbsDao implements HWRepbbsDaoImpl {

	@Override
	public List<HWRepbbsDto> getRepBbsList(int seq) {
		String sql = " SELECT SEQ, ID, "
				+ " CONTENT, WDATE, PARENT,"
				+ " DEL "
				+ " FROM HWRepbbsDto "
				+ " WHERE PARENT=? AND DEL=0 "
				+ " ORDER BY WDATE ASC ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		List<HWRepbbsDto> list = new ArrayList<HWRepbbsDto>();
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 getRepBbsList Success");
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			System.out.println("2/6 getRepBbsList Success");
			
			rs = psmt.executeQuery();
			System.out.println("3/6 getRepBbsList Success");
			
			while (rs.next()) {
				HWRepbbsDto dto = new HWRepbbsDto(rs.getInt(1), // seq, 
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
	public boolean writeBbs(HWRepbbsDto bbs) {
		String sql = " INSERT INTO HWRepbbsDto(SEQ, ID, "
				+ " CONTENT, WDATE, PARENT, DEL) "
				+ " VALUES(SEQ_HWRepbbsDto.NEXTVAL, ?, "
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
	
	//글 작성시 인간 쪽 점수 올라가는 것.
		public boolean writeBbsMemSCORE(byte score, String writescoreid) {
			String sql = " UPDATE OKHMEM "
					+ " SET score=score+? "
					+ " WHERE id=? ";
			Connection conn = null;
			PreparedStatement psmt = null;
			
			int count = 0;
			
			System.out.println(sql);
			try {
				conn = DBConnection.getConnection();	
				psmt=conn.prepareStatement(sql);
				System.out.println("1/6 HWRepbbsDao writeBbsMemSCORE Success");	
				psmt.setByte(1, score);
				psmt.setString(2, writescoreid);
				
				System.out.println(sql);
				System.out.println("2/6 HWRepbbsDao writeBbsMemSCORE Success");	
				count = psmt.executeUpdate();			
				
			} catch (SQLException e) {
				System.out.println("HWRepbbsDao writeBbsMemSCORE fail");	
				e.printStackTrace();
			}finally{
				DBClose.close(psmt, conn, null);
				System.out.println("3/6 finally HWRepbbsDao writeBbsMemSCORE Success");	
			}
			
			return count>0?true:false;
		}

	@Override
	public boolean repupdate(int seq, String content) {
		String sql = " UPDATE HWRepbbsDto SET "
				+ " CONTENT=? "
				+ " WHERE SEQ=? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		int count = 0;
		System.out.println(seq+content);
		try {
			conn = DBConnection.getConnection();	
			System.out.println("2/6 S repupdate HWRepbbsDto");
			
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
		String sql=" UPDATE HWRepbbsDto SET "
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

