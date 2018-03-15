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

	@Override
	public List<TechRepbbsDto> getRepBbsList(int seq) {
		String sql = " SELECT SEQ, ID, REF, STEP, DEPTH, "
				+ " CONTENT, WDATE, PARENT,"
				+ " DEL, LIKECOUNT "
				+ " FROM TECHREPBBS "
				+ " WHERE PARENT=? AND DEL=0 "
				+ " ORDER BY REF DESC, STEP ASC ";
		
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
										rs.getInt(3), //ref, 
										rs.getInt(4),	//step, 
										rs.getInt(5), // depth, 
										rs.getString(6), //title, 
										rs.getString(7), //content, 
										rs.getInt(8), //parent, 
										rs.getInt(9), //del, 
										rs.getInt(10)); // readcount
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
	public boolean answer(int seq, TechRepbbsDto bbs) {
		// update : 기존의 댓글들을 한칸(STEP)씩 진행시키는 작업
				String sql1 = " UPDATE TECHREPBBS "
						+ " SET STEP=STEP+1 "
						+ " WHERE REF=(SELECT REF FROM BBS WHERE SEQ=?) "
						+ "	  AND STEP > (SELECT STEP FROM BBS WHERE SEQ=?) ";
				/*
				 * 		3-0-0 부모글
				 * 		댓글  3-1-1	-> 3-2-1	
				 * 			3-2-1	-> 3-3-1
				 */		
				
				// insert
				String sql2 = " INSERT INTO TECHREPBBS "
						+ " (SEQ, ID, REF, STEP, DEPTH, "
						+ " CONTENT, WDATE, PARENT, DEL, LIKECOUNT) "
						+ " VALUES(SEQ_TECHREPBBS.NEXTVAL, ?, "
						+ "		(SELECT REF FROM BBS WHERE SEQ=? ), "	// REF
						+ " 	(SELECT STEP FROM BBS WHERE SEQ=? )+1, "
						+ "		(SELECT DEPTH FROM BBS WHERE SEQ=? )+1, "
						+ "		 ?, SYSDATE, ?, 0, 0) ";

					/*
					 * 	3-0-0 부모
					 * 		3-1-1 댓글
					 */
				
				Connection conn = null;
				PreparedStatement psmt = null;
				
				int count = 0;
				
				try {
					conn = DBConnection.getConnection();
					conn.setAutoCommit(false);
					System.out.println("1/6 answer Success");
					
					psmt = conn.prepareStatement(sql1);	// update
					psmt.setInt(1, seq);
					psmt.setInt(2, seq);
					System.out.println("2/6 answer Success");
					
					count = psmt.executeUpdate();
					System.out.println("3/6 answer Success");
					
					psmt.clearParameters();
					
					psmt = conn.prepareStatement(sql2); // insert
					psmt.setString(1, bbs.getId());
					psmt.setInt(2, seq);
					psmt.setInt(3, seq);
					psmt.setInt(4, seq);
					psmt.setString(5, bbs.getContent());
					psmt.setInt(6, seq);
					
					System.out.println("4/6 answer Success");
					
					count = psmt.executeUpdate();
					conn.commit();		
					System.out.println("5/6 answer Success");
					
				} catch (SQLException e) {
					System.out.println("answer fail");			
					try {
						conn.rollback();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}			
					e.printStackTrace();
				} finally {
					try {
						conn.setAutoCommit(true);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					DBClose.close(psmt, conn, null);
					System.out.println("6/6 answer Success");			
				}
						
				return count>0?true:false;
	}

	@Override
	public boolean writeBbs(TechRepbbsDto bbs) {
		String sql = " INSERT INTO TECHREPBBS(SEQ, ID, "
				+ " REF, STEP, DEPTH, "
				+ " CONTENT, WDATE,PARENT,DEL, LIKECOUNT) "
				+ " VALUES(SEQ_TECHREPBBS.NEXTVAL, ?, "
				+ " (SELECT NVL(MAX(REF), 0)+1 FROM TECHREPBBS), 0, 0, "
				+ "  ?, SYSDATE, ?, "
				+ " 0, 0) ";
		
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
	public void likecountplus(int seq) {
		String sql = " UPDATE TECHREPBBS "
				+ " SET LIKECOUNT=LIKECOUNT+1 "
				+ " WHERE SEQ=? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 likecountplus Success");
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			System.out.println("2/6 likecountplus Success");
			
			psmt.executeUpdate();
			System.out.println("3/6 likecountplus Success");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("likecountplus Fail");
		} finally {
			DBClose.close(psmt, conn, null);			
		}		
	}

	@Override
	public boolean repupdate(int seq, String content) {
		String sql = " UPDATE TECHREPBBS SET "
				+ " CONTENT=? "
				+ " WHERE PARENT=? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		int count = 0;
		
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
				+ " DEL=1"
				+ " WHERE PARENT=? ";
		
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

