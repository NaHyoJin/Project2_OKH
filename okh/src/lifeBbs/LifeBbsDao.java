package lifeBbs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBClose;
import db.DBConnection;

public class LifeBbsDao implements ILifeBbsDao {
	
	private static LifeBbsDao lifeBbsDao = new LifeBbsDao();
	
	public LifeBbsDao() {
		DBConnection.initConnection();
	}
	
	public static LifeBbsDao getInstance() {
		return lifeBbsDao;
	}

	@Override
	public boolean writeCountReply(int seq, int countreply) {
		String sql = " UPDATE LIFEBBS SET COUNTREPLY=? WHERE SEQ=? ";
		
		Connection conn = null; 
		PreparedStatement psmt = null;
		
		int count = 0;
		

		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 writeCountReply Success");
			
			psmt = conn.prepareStatement(sql);
			System.out.println("2/6 writeCountReply Success");
			
			psmt.setInt(1, countreply);
			psmt.setInt(2, seq);
			
			count = psmt.executeUpdate();
			System.out.println("3/6 writeCountReply Success");
			
		} catch (SQLException e) {
			System.out.println("writeCountReply Fail");
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, null);
		}
		
		return count>0;
	}

	@Override
	public boolean writeBbs(LifeBbsDto bbs) {
		String sql = " INSERT INTO LIFEBBS(SEQ, ID, "
				+ " REF, STEP, DEPTH, "
				+ " TITLE, CONTENT, TAG, FILENAME, UP, UPID, DOWNID, WDATE, PARENT, "
				+ " DEL, READCOUNT, DOWNCOUNT, COUNTREPLY) "
				+ " VALUES(SEQ_LIFEBBS.NEXTVAL, ?, "
				+ " (SELECT NVL(MAX(REF), 0)+1 FROM LIFEBBS), 0, 0, "
				+ " ?, ?, ?, ?, 0, ?, ?, SYSDATE, 0, "
				+ " 0, 0, 0, 0) ";
		
		Connection conn = null; 
		PreparedStatement psmt = null;
		
		System.out.println("bbs in dao : " + bbs.toString());
		
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("3/6 writeBbs Success");
			
			psmt = conn.prepareStatement(sql);
			System.out.println("4/6 writeBbs Success");
			
			psmt.setString(1, bbs.getId());
			psmt.setString(2, bbs.getTitle());
			psmt.setString(3, bbs.getContent());
			psmt.setString(4, bbs.getTag());
			psmt.setString(5, bbs.getFilename());
			psmt.setString(6, bbs.getUpid());
			psmt.setString(7, bbs.getDownid());
			
			count = psmt.executeUpdate();
			System.out.println("5/6 writeBbs Success");
			
		} catch (SQLException e) {
			System.out.println("writeBbs Fail");
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, null);
		}
		
		return count>0;
	}

	@Override
	public boolean deleteBbs(int seq) {
		String sql = " UPDATE LIFEBBS SET DEL=1 WHERE SEQ=? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 deleteBbs Success");
			
			psmt = conn.prepareStatement(sql);
			System.out.println("2/6 deleteBbs Success");
			
			psmt.setInt(1, seq);
			
			count = psmt.executeUpdate();
			System.out.println("3/6 deleteBbs Success");
			
		} catch (SQLException e) {
			System.out.println("deleteBbs Fail");
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, null);
		}
		
		return count>0;
	}

	@Override
	public boolean updateBbs(LifeBbsDto bbs) {
		String sql = " UPDATE LIFEBBS SET TITLE=?, CONTENT=?, TAG=?, FILENAME=? WHERE SEQ=? ";
		
		System.out.println("bbs in dao : " + bbs.toString());
		
		Connection conn = null;
		PreparedStatement psmt = null;
		
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 updateBbs Success");
			
			psmt = conn.prepareStatement(sql);
			System.out.println("2/6 updateBbs Success");
			
			psmt.setString(1, bbs.getTitle());
			psmt.setString(2, bbs.getContent());
			psmt.setString(3, bbs.getTag());
			psmt.setString(4, bbs.getFilename());
			psmt.setInt(5, bbs.getSeq());
			
			count = psmt.executeUpdate();
			System.out.println("3/6 updateBbs Success");
			
		} catch (SQLException e) {
			System.out.println("updateBbs Fail");
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, null);
			System.out.println("4/6 updateBbs Success");
		}
		
		return count>0;
	}

	@Override
	public boolean updatedownid(int seq, int up, String downid) {
		String sql = " UPDATE LIFEBBS SET UP=?, DOWNID=? WHERE SEQ=? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 updateupid Success");
			
			psmt = conn.prepareStatement(sql);
			System.out.println("2/6 updateupid Success");
			
			psmt.setInt(1, up);
			psmt.setString(2, downid);
			psmt.setInt(3, seq);
			
			count = psmt.executeUpdate();
			System.out.println("3/6 updateupid Success");
			
		} catch (SQLException e) {
			System.out.println("updateupid Fail");
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, null);
		}
		
		return count>0;
	}

	@Override
	public boolean updateupid(int seq, int up, String upid) {
		String sql = " UPDATE LIFEBBS SET UP=?, UPID=? WHERE SEQ=? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 updateupid Success");
			
			psmt = conn.prepareStatement(sql);
			System.out.println("2/6 updateupid Success");
			
			psmt.setInt(1, up);
			psmt.setString(2, upid);
			psmt.setInt(3, seq);
			
			count = psmt.executeUpdate();
			System.out.println("3/6 updateupid Success");
			
		} catch (SQLException e) {
			System.out.println("updateupid Fail");
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, null);
		}
		
		return count>0;
	}

	@Override
	public LifeBbsDto getupdownid(int seq) {
		LifeBbsDto dto = null;
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		String sql = " SELECT UP, UPID, DOWNID FROM LIFEBBS WHERE SEQ=? ";

		try {
			conn = DBConnection.getConnection();
			System.out.println("3/6 getupid Success");
			
			psmt = conn.prepareStatement(sql);
			System.out.println("4/6 getupid Success");
			
			psmt.setInt(1, seq);
			
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				dto = new LifeBbsDto(rs.getInt(1), rs.getString(2), rs.getString(3));
			}
			System.out.println("5/6 getupid Success");
		} catch (SQLException e) {
			System.out.println("getupid Fail");
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		
		return dto;
	}

	@Override
	public LifeBbsDto getDetailBbs(int seq) {
		LifeBbsDto dto = null;
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		String sql = " SELECT SEQ, ID, REF, STEP, DEPTH, "
				+ " TITLE, CONTENT, TAG, FILENAME, UP, UPID, DOWNID, WDATE, PARENT, DEL, READCOUNT, DOWNCOUNT, COUNTREPLY "
				+ " FROM LIFEBBS "
				+ " WHERE SEQ=? ";
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("3/6 getDetailBbs Success");
			
			psmt = conn.prepareStatement(sql);
			System.out.println("4/6 getDetailBbs Success");
			
			psmt.setInt(1, seq);
			
			rs = psmt.executeQuery();
			System.out.println("5/6 getDetailBbs Success");
			
			if(rs.next()) {
				dto = new LifeBbsDto(rs.getInt(1),			// seq
									rs.getString(2),		// id
									rs.getInt(3),			// ref
									rs.getInt(4),			// step
									rs.getInt(5),			// depth
									rs.getString(6),		// title
									rs.getString(7),		// content
									rs.getString(8),		// tag
									rs.getString(9),		// filename
									rs.getInt(10),			// up
									rs.getString(11),		// upid
									rs.getString(12),		// downid
									rs.getString(13),		// wdate
									rs.getInt(14),			// parent
									rs.getInt(15),			// del
									rs.getInt(16),			// readcount
									rs.getInt(17),			// downcount
									rs.getInt(18));			// countreply
			}
			System.out.println("6/6 getDetailBbs Success");
			
		} catch (SQLException e) {
			System.out.println("getDetailBbs Fail");
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		
		return dto;
	}

	@Override
	public void readcount(int seq) {
		String sql = " UPDATE LIFEBBS "
				+ " SET READCOUNT=READCOUNT+1 "
				+ " WHERE SEQ=? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("4/6 readcount Success");
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			System.out.println("5/6 readcount Success");
			
			psmt.executeUpdate();
			System.out.println("6/6 readcount Success");
			
		} catch (SQLException e) {
			System.out.println("readcount Fail");
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, null);
		}
	}

	@Override
	public boolean downloadcount(int seq) {
		String sql = " UPDATE LIFEBBS "
				+ " SET DOWNCOUNT=DOWNCOUNT+1 "
				+ " WHERE SEQ=? ";
		
		int count = 0;
		
		Connection conn = null;
		PreparedStatement psmt = null;

		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 downloadcount success");
	        
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			System.out.println("2/6 downloadcount success");
			
			count = psmt.executeUpdate();
			System.out.println("3/6 downloadcount success");
	         
	         
		} catch (SQLException e) {
			System.out.println("downloadcount Fail");
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, null);
		}
		
		return count>0;
	}

	@Override
	public boolean answer(int seq, LifeBbsDto bbs) {
		String sql1 = " UPDATE LIFEBBS "
				+ " SET STEP=+1 "
				+ " WHERE REF=(SELECT REF FROM LIFEBBS WHERE SEQ=?) "
				+ "   AND STEP>(SELECT STEP FROM LIFEBBS WHERE SEQ=?) ";
		
		String sql2 = " INSERT INTO LIFEBBS "
				+ " (SEQ, ID, REF, STEP, DEPTH, "
				+ "  TITLE, CONTENT, FILENAME, UP, UPID, DOWNID, WDATE, PARENT, DEL, READCOUNT, DOWNCOUNT, COUNTREPLY) "
				+ " VALUES(SEQ_LIFEBBS.NEXTVAL, ?, "
				+ " 	   (SELECT REF FROM LIFEBBS WHERE SEQ=? ), "
				+ " 	   (SELECT STEP FROM LIFEBBS WHERE SEQ=? )+1, "
				+ " 	   (SELECT DEPTH FROM LIFEBBS WHERE SEQ=? )+1, "
				+ " 	   ?, ?, ?, 0, ?, ?, SYSDATE, ?, 0, 0, 0, 0) ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);
			System.out.println("1/6 answer Success");
			
			psmt = conn.prepareStatement(sql1);
			psmt.setInt(1, seq);
			psmt.setInt(2, seq);
			System.out.println("2/6 answer Success");
			
			count = psmt.executeUpdate();
			System.out.println("3/6 answer Success");
			
			psmt.clearParameters();
			
			psmt = conn.prepareStatement(sql2);
			psmt.setString(1, bbs.getId());
			psmt.setInt(2, seq);
			psmt.setInt(3, seq);
			psmt.setInt(4, seq);
			psmt.setString(5, bbs.getTitle());
			psmt.setString(6, bbs.getContent());
			psmt.setString(7, bbs.getFilename());
			psmt.setString(8, bbs.getUpid());
			psmt.setString(9, bbs.getDownid());
			psmt.setInt(10, seq);
			System.out.println("4/6 answer Success");
			
			count = psmt.executeUpdate();
			conn.commit();
			System.out.println("5/6 answer Success");
			
		} catch (SQLException e) {
			System.out.println("answer Fail");
			
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
			System.out.println("6/6 answer Success");
		}
		
		return count>0;
	}

	@Override
	public List<LifeBbsDto> getBbsPagingList(LifeBbsPagingDto paging, String searchWord, int search) {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		List<LifeBbsDto> bbslist = new ArrayList<LifeBbsDto>();
		
		System.out.println("search = " + search);
		
		String sWord = "";		
		if(search == 0) {	// 제목
			sWord = " WHERE TITLE LIKE '%" + searchWord.trim() + "%' ";
		}else if(search == 1) {	// 작성자
			sWord = " WHERE ID='" + searchWord.trim() + "' ";
		}else if(search == 2) {	// 내용
			sWord = " WHERE CONTENT LIKE '%" + searchWord.trim() + "%' ";
		} 
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 getBbsPagingList Success");
			
			// 글의 총수
			String totalSql = " SELECT COUNT(SEQ) FROM LIFEBBS " + sWord + "AND DEL=0";
			psmt = conn.prepareStatement(totalSql);
			rs = psmt.executeQuery();
			System.out.println("2/6 getBbsPagingList Success");
			
			int totalCount = 0;
			rs.next();
			totalCount = rs.getInt(1);	// row의 총 갯수
			
			paging.setTotalCount(totalCount);
			paging = LifeBbsPagingUtil.setPagingInfo(paging);
			
			psmt.close();
			rs.close();
			
			// row를 취득
			String sql = " SELECT * FROM "
						+ " (SELECT * FROM (SELECT * FROM LIFEBBS " + sWord + " ORDER BY REF ASC, STEP DESC)"
						+ "  WHERE ROWNUM <=" + paging.getStartNum() + " ORDER BY REF DESC, STEP ASC) "
						+ "WHERE ROWNUM <=" + paging.getCountPerPage() + "AND DEL=0";
			
			psmt = conn.prepareStatement(sql);
			System.out.println("3/6 getBbsPagingList Success");
			
			rs = psmt.executeQuery();
			System.out.println("4/6 getBbsPagingList Success");
			
			while(rs.next()) {
				LifeBbsDto dto = new LifeBbsDto(rs.getInt(1),			// seq
												rs.getString(2),		// id
												rs.getInt(3),			// ref
												rs.getInt(4),			// step
												rs.getInt(5),			// depth
												rs.getString(6),		// title
												rs.getString(7),		// content
												rs.getString(8),		// tag
												rs.getString(9),		// filename
												rs.getInt(10),			// up
												rs.getString(11),		// upid
												rs.getString(12),		// downid
												rs.getString(13),		// wdate
												rs.getInt(14),			// parent
												rs.getInt(15),			// del
												rs.getInt(16),			// readcount
												rs.getInt(17),			// downcount
												rs.getInt(18));			// countreply
				bbslist.add(dto);				
			}
			System.out.println("5/6 getBbsPagingList Success");			
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("getBbsPagingList Fail");
		} finally {
			DBClose.close(psmt, conn, rs);
			System.out.println("6/6 getBbsPagingList Success");	
		}
		
		return bbslist;
	}

}
