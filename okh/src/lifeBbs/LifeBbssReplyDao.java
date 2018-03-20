package lifeBbs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBClose;
import db.DBConnection;

public class LifeBbssReplyDao implements ILifeBbssReplyDao {
	
	private static LifeBbssReplyDao lifebbsreplydao = new LifeBbssReplyDao();
	
	public LifeBbssReplyDao() {
		DBConnection.initConnection();
	}
	
	public static LifeBbssReplyDao getInstance() {
		return lifebbsreplydao;
	}

	@Override
	public boolean writeBbsReply(LifeBbssReplyDto bbs) {
		String sql = " INSERT INTO LIFEBBSREPLY(SEQ, ID, "
				+ " REF, STEP, DEPTH, "
				+ " CONTENT, UP, DOWN, UPID, DOWNID, WDATE, PARENT, "
				+ " DEL, BBSSEQ) "
				+ " VALUES(SEQ_LIFEBBSREPLY.NEXTVAL, ?, "
				+ " (SELECT NVL(MAX(REF), 0)+1 FROM LIFEBBSREPLY), 0, 0, "
				+ " ?, 0, 0, NULL, NULL, SYSDATE, 0, "
				+ " 0, ?) ";
		
		System.out.println("bbs in dao = " + bbs.toString());
		
		Connection conn = null;
		PreparedStatement psmt = null;
		
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("3/6 writeBbsReply Success");
			
			psmt = conn.prepareStatement(sql);
			System.out.println("4/6 writeBbsReply Success");
			
			psmt.setString(1, bbs.getId());
			psmt.setString(2, bbs.getContent());
			psmt.setInt(3, bbs.getBbsseq());
			
			count = psmt.executeUpdate();
			System.out.println("5/6 writeBbsReply Success");
			
		} catch (SQLException e) {
			System.out.println("writeBbsReply Fail");
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, null);
		}
		
		return count>0;
	}


	@Override
	public boolean deleteBbsReply(int seq) {
		String sql = " UPDATE LIFEBBSREPLY SET DEL=1 WHERE SEQ=? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 deleteBbsReply Success");
			
			psmt = conn.prepareStatement(sql);
			System.out.println("2/6 deleteBbsReply Success");
			
			psmt.setInt(1, seq);
			
			count = psmt.executeUpdate();
			System.out.println("3/6 deleteBbsReply Success");
			
		} catch (SQLException e) {
			System.out.println("deleteBbsReply Fail");
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, null);
		}
		
		return count>0;
	}

	@Override
	public boolean updateBbsReply(LifeBbssReplyDto bbs) {
		String sql = " UPDATE LIFEBBSREPLY SET CONTENT=? WHERE SEQ=? ";
		
		System.out.println("bbs in dao : " + bbs.toString());
		
		Connection conn = null;
		PreparedStatement psmt = null;
		
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 updateBbsReply Success");
			
			psmt = conn.prepareStatement(sql);
			System.out.println("2/6 updateBbsReply Success");
			
			psmt.setString(1, bbs.getContent());
			psmt.setInt(2, bbs.getSeq());
			
			count = psmt.executeUpdate();
			System.out.println("3/6 updateBbsReply Success");
			
		} catch (SQLException e) {
			System.out.println("updateBbsReply Fail");
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, null);
			System.out.println("4/6 updateBbsReply Success");
		}
		
		return count>0;
	}

	@Override
	public boolean ReplyAnswer(int seq, LifeBbssReplyDto bbs) {
		String sql1 = " UPDATE LIFEBBSREPLY "
				+ " SET STEP=+1 "
				+ " WHERE REF=(SELECT REF FROM LIFEBBSREPLY WHERE SEQ=?) "
				+ "   AND STEP>(SELECT STEP FROM LIFEBBSREPLY WHERE SEQ=?) ";
		
		String sql2 = " INSERT INTO LIFEBBSREPLY "
				+ " (SEQ, ID, REF, STEP, DEPTH, "
				+ "  CONTENT, UP, DOWN, UPID, DOWNID, WDATE, PARENT, DEL, BBSSEQ) "
				+ " VALUES(SEQ_LIFEBBSREPLY.NEXTVAL, ?, "
				+ " 	   (SELECT REF FROM LIFEBBSREPLY WHERE SEQ=? ), "
				+ " 	   (SELECT STEP FROM LIFEBBSREPLY WHERE SEQ=? )+1, "
				+ " 	   (SELECT DEPTH FROM LIFEBBSREPLY WHERE SEQ=? )+1, "
				+ " 	   ?, 0, 0, NULL, NULL, SYSDATE, 0, 0, ?) ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);
			System.out.println("1/6 ReplyAnswer Success");
			
			psmt = conn.prepareStatement(sql1);
			psmt.setInt(1, seq);
			psmt.setInt(2, seq);
			System.out.println("2/6 ReplyAnswer Success");
			
			count = psmt.executeUpdate();
			System.out.println("3/6 ReplyAnswer Success");
			
			psmt.clearParameters();
			
			psmt = conn.prepareStatement(sql2);
			psmt.setString(1, bbs.getId());
			psmt.setInt(2, seq);
			psmt.setInt(3, seq);
			psmt.setInt(4, seq);
			psmt.setString(5, bbs.getContent());
			psmt.setInt(8, bbs.getBbsseq());
			System.out.println("4/6 ReplyAnswer Success");
			
			count = psmt.executeUpdate();
			conn.commit();
			System.out.println("5/6 ReplyAnswer Success");
			
		} catch (SQLException e) {
			
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
			e.printStackTrace();
		} finally {
			try {
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			DBClose.close(psmt, conn, null);
			System.out.println("6/6 ReplyAnswer Success");
		}
		
		return count>0;
	}

	@Override
	public List<LifeBbssReplyDto> getBbsReplyList(int bbsseq) {
		String sql = " SELECT SEQ, ID, REF, STEP, DEPTH, "
				+ " CONTENT, UP, DOWN, UPID, DOWNID, WDATE, PARENT, "
				+ " DEL, BBSSEQ "
				+ " FROM LIFEBBSREPLY "
				+ " WHERE BBSSEQ=? "
				+ " ORDER BY REF ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		List<LifeBbssReplyDto> list = new ArrayList<LifeBbssReplyDto>();
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("3/6 getBbsList Success");
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, bbsseq);
			System.out.println("4/6 getBbsList Success");
			
			rs = psmt.executeQuery();
			System.out.println("5/6 getBbsList Success");
			
			while(rs.next()) {
				LifeBbssReplyDto dto = new LifeBbssReplyDto(rs.getInt(1),			// seq, 
															rs.getString(2),		// id, 
															rs.getInt(3),			// ref, 
															rs.getInt(4),			// step, 
															rs.getInt(5),			// depth, 
															rs.getString(6),		// content, 
															rs.getInt(7),			// up, 
															rs.getInt(8),			// down, 
															rs.getString(9),		// upid, 
															rs.getString(10),		// downid, 
															rs.getString(11),		// wdate, 
															rs.getInt(12),			// parent, 
															rs.getInt(13),			// del, 
															rs.getInt(14));			// bbsseq);
				list.add(dto);
			}
			System.out.println("6/6 getBbsList Success");
			
		} catch (SQLException e) {
			System.out.println("getBbsList Fail");
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		
		return list;
	}
	
	@Override
	public int getCountReply(int bbsseq) {
		String sql = " select count(*) from lifebbsreply where bbsseq=? and del=0 ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		int countreply = 0;

		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 getCountReply Success");
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, bbsseq);
			System.out.println("2/6 getCountReply Success");

			rs = psmt.executeQuery();
			System.out.println("3/6 getCountReply Success");
			
			if(rs.next()) {
				countreply = rs.getInt(1);
			}
			System.out.println("4/6 getCountReply Success");
			
			
		} catch (SQLException e) {
			System.out.println("getCountReply Fail");
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		
		
		return countreply;
	}

	@Override
	public List<LifeBbssReplyDto> reply() {
		String sql = " SELECT BBSSEQ, DEL FROM LIFEBBSREPLY " + 
				" WHERE ROWID IN " + 
				"		( SELECT MAX(ROWID) " + 
				"			FROM LIFEBBSREPLY " + 
				"		   GROUP BY DEL, BBSSEQ " + 
				"		   HAVING DEL=0) ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		List<LifeBbssReplyDto> list = new ArrayList<LifeBbssReplyDto>();
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 reply Success");
			
			psmt = conn.prepareStatement(sql);
			System.out.println("2/6 reply Success");

			rs = psmt.executeQuery();
			System.out.println("3/6 reply Success");
			
			while(rs.next()) {
				LifeBbssReplyDto dto = new LifeBbssReplyDto(rs.getInt(1), rs.getInt(1));
				list.add(dto);
			}
			System.out.println("4/6 reply Success");
			
		} catch (SQLException e) {
			System.out.println("reply Fail");
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		
		return list;
	}

}
