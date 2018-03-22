package columnBbs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBClose;
import db.DBConnection;
import lifeBbs.LifeBbsDto;
import lifeBbs.LifeBbsPagingUtil;
import columnBbs.ColumnBbsDto;
import columnBbs.PagingBean;
import oracle.net.aso.r;

public class ColumnBbsDao implements iColumnBbsDao {
	
	private static ColumnBbsDao bbsDao = new ColumnBbsDao();
	
	private ColumnBbsDao() {
	}
	
	public static ColumnBbsDao getInstance() {
		return bbsDao;
	}	
	
	
	
	
	@Override
	public boolean updateBbs(ColumnBbsDto bbs) {
		String sql = " UPDATE BBS SET TITLE=?, CONTENT=? WHERE SEQ=?";
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
			psmt.setInt(3, bbs.getSeq());
			
			count = psmt.executeUpdate();
			System.out.println("3/6 updateBbs Success");
			System.out.println("count in dao :" + count);
		
		} catch (SQLException e) {
			System.out.println("updateBbs Fail");
			e.printStackTrace();
		}finally {
			DBClose.close(psmt, conn, null);
			System.out.println("4/6 updateBbs Success");
		}
		
		
		return count>0;
	}

	@Override
	public ColumnBbsDto getdelltailBbs(int intseq) {
		ColumnBbsDto dto = null;
		
		Connection conn = null;
		// 연결
		PreparedStatement psmt = null;
		// Connection객체를 PreparedStatement 통헤서 생성된다
		ResultSet rs = null;
		// DB에 쿼리를 보내기 위해 필요한 객체이다.
		
		String sql = " SELECT SEQ, ID, REF, STEP, DEPTH, "
				+ " TITLE, CONTENT, WDATE, PARENT, DEL, READCOUNT "
				+ " FROM BBS "
				+ " WHERE SEQ=? ";
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("3/6 getdeltailBbs Success");
			
			psmt = conn.prepareStatement(sql);
			System.out.println("4/6 getdeltailBbs Success");
			
			rs = psmt.executeQuery();
			System.out.println("5/6 getDeltailBbs Success");
			
			if(rs.next()) {
				int i = 1;
				
				dto = new ColumnBbsDto(rs.getInt(1),
								 rs.getString(2),
								 rs.getInt(3),
								 rs.getInt(4),
								 rs.getInt(5),
								 rs.getString(6),
								 rs.getString(7),
								 rs.getString(8),
								 rs.getInt(9),
								 rs.getInt(10),
								 rs.getInt(11));
				}			System.out.println("6/6 getDeltailBbs Success");
			
		} catch (SQLException e) {
			System.out.println("getDetailBbs Fail");
			e.printStackTrace();
		}finally {
			DBClose.close(psmt, conn, rs);
		}
		return dto;
	}

	@Override
	public boolean dellBbs(int seq) {
		String sql = " UPDATE BBS SET DEL=1 WHERE SEQ=? ";
		// seq에 "내용" 담는다 = 쿼리문  update bbs에 set del=1로 하고  seq=? 어디에 있는지;
		Connection conn = null;
		// 연결
		PreparedStatement psmt = null;
		// Connection객체를 PreparedStatement 통헤서 생성된다
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();
			// 		 파일명              /   파일내 부분
			System.out.println("1/6 dellBbs Success");
			// 확인 작업
			psmt = conn.prepareStatement(sql);
			System.out.println("2/6 dellBbs Success");
			
			psmt.setInt(1, seq);
			
			count = psmt.executeUpdate();
			System.out.println("3/6 dellBbs Success");
			System.out.println("count in dao : " + count);
		
		} catch (SQLException e) {
			// try 실행시 오류가 발생하면 catch실행된다. 오류 없을 시 패스
			System.out.println("dellBbs fail");
			// dellBbs fail 실행
			e.printStackTrace();
		}
		return count>0;
	}

	@Override
	public List<ColumnBbsDto> getBbsPagingList(PagingBean paging, String searchWord, int search) {
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		List<ColumnBbsDto> bbslist = new ArrayList<ColumnBbsDto>();
		
		String sWord = "";		
		if(search == 0) {	// 제목
			sWord = " WHERE TITLE LIKE '%" + searchWord.trim() + "%' ";
		}else if(search == 1) {	// 작성자
			sWord = " WHERE ID='" + searchWord.trim() + "' ";
		}else if(search == 2) {	// 내용
			
		} 
		System.out.println("search------------------ = " + search);
		System.out.println("sWord------------------ = " + sWord);
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 getBbsPagingList Success");
			
			// 글의 총수
			String totalSql = " SELECT COUNT(SEQ) FROM BBS " + sWord;
			psmt = conn.prepareStatement(totalSql);
			rs = psmt.executeQuery();
			System.out.println("2/6 getBbsPagingList Success");
			
			int totalCount = 0;
			rs.next();
			totalCount = rs.getInt(1);	// row의 총 갯수
			System.out.println("totalCount------------------ = " + totalCount);
			
			paging.setTotalCount(totalCount);
			paging = PagingUtil.setPagingInfo(paging);
			
			psmt.close();
			rs.close();
			
			// row를 취득
			String sql = " SELECT * FROM "
						+ " (SELECT * FROM (SELECT * FROM BBS " + sWord + " ORDER BY REF ASC, STEP DESC)"
						+ "  WHERE ROWNUM <=" + paging.getStartNum() + " ORDER BY REF DESC, STEP ASC) "
						+ "WHERE ROWNUM <=" + paging.getCountPerPage();
			
			System.out.println("paging.getStartNum() = " + paging.getStartNum());
			System.out.println("paging.getCountPerPage() = " + paging.getCountPerPage());
			
			psmt = conn.prepareStatement(sql);
			System.out.println("3/6 getBbsPagingList Success");
			
			rs = psmt.executeQuery();
			System.out.println("4/6 getBbsPagingList Success");
			
			while(rs.next()) {
				ColumnBbsDto dto = new ColumnBbsDto(rs.getInt(1), // seq, 
						rs.getString(2), // id, 
						rs.getInt(3), //ref, 
						rs.getInt(4),	//step, 
						rs.getInt(5), // depth, 
						rs.getString(6), //title, 
						rs.getString(7), //content, 
						rs.getString(8), //wdate, 
						rs.getInt(9), //parent, 
						rs.getInt(10), //del, 
						rs.getInt(11)); // readcount
				bbslist.add(dto);				
			}
			System.out.println("5/6 getBbsPagingList Success");			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("getBbsPagingList Fail");
		} finally {
			DBClose.close(psmt, conn, rs);
			System.out.println("6/6 getBbsPagingList Success");	
		}
		
		return bbslist;
	}

	@Override
	public boolean answerBbs(int seq, ColumnBbsDto bbs) {
		
		// update : 기존의 댓글들을 한칸(STEP)씩 진행시키는 작업
		String sql1 = " UPDATE BBS "
				+ " SET STEP=STEP+1 "
				+ " WHERE REF=(SELECT REF FROM BBS WHERE SEQ=?) "
				+ "	  AND STEP > (SELECT STEP FROM BBS WHERE SEQ=?) ";
		/*
		 * 		3-0-0 부모글
		 * 		댓글  3-1-1	-> 3-2-1	
		 * 			3-2-1	-> 3-3-1
		 */		
		
		// insert
		String sql2 = " INSERT INTO BBS "
				+ " (SEQ, ID, REF, STEP, DEPTH, "
				+ " TITLE, CONTENT, WDATE, PARENT, DEL, READCOUNT) "
				+ " VALUES(SEQ_BBS.NEXTVAL, ?, "
				+ "		(SELECT REF FROM BBS WHERE SEQ=? ), "	// REF
				+ " 	(SELECT STEP FROM BBS WHERE SEQ=? )+1, "
				+ "		(SELECT DEPTH FROM BBS WHERE SEQ=? )+1, "
				+ "		?, ?, SYSDATE, ?, 0, 0) ";

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
			psmt.setString(5, bbs.getTitle());
			psmt.setString(6, bbs.getContent());
			psmt.setInt(7, seq);
			
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
	public ColumnBbsDto getBbs(int seq) {
		String sql = " SELECT SEQ, ID, REF, STEP, DEPTH, "
				+ " TITLE, CONTENT, WDATE, PARENT, DEL, READCOUNT "
				+ " FROM BBS "
				+ " WHERE SEQ=? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		ColumnBbsDto dto = null;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 getBbs Success");
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			
			System.out.println("2/6 getBbs Success");
			
			rs = psmt.executeQuery();
			System.out.println("3/6 getBbs Success");
			
			if(rs.next()) {
				int i = 1;
				dto = new ColumnBbsDto(rs.getInt(i++), // seq, 
						rs.getString(i++), // id, 
						rs.getInt(i++), //ref, 
						rs.getInt(i++),	//step, 
						rs.getInt(i++), // depth, 
						rs.getString(i++), //title, 
						rs.getString(i++), //content, 
						rs.getString(i++), //wdate, 
						rs.getInt(i++), //parent, 
						rs.getInt(i++), //del, 
						rs.getInt(i++)); // readcount				
			}	
			System.out.println("4/6 getBbs Success");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("getBbs fail");
			e.printStackTrace();			
		} finally {
			DBClose.close(psmt, conn, rs);	
			System.out.println("5/6 getBbs Success");
		}		
		
		return dto;
	}

	@Override
	public void readcount(int sseq) {
		String sql = " UPDATE BBS "
				+ " SET READCOUNT=READCOUNT+1 "
				+ " WHERE SEQ=? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 readcount Success");
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, sseq);
			System.out.println("2/6 readcount Success");
			
			psmt.executeUpdate();
			System.out.println("3/6 readcount Success");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("readcount Fail");
		} finally {
			DBClose.close(psmt, conn, null);			
		}		
	}

	@Override
	public List<ColumnBbsDto> getBbsList() {
		
		String sql = " SELECT SEQ, ID, REF, STEP, DEPTH, "
				+ " TITLE, CONTENT, WDATE, PARENT,"
				+ " DEL, READCOUNT "
				+ " FROM BBS "
				+ " ORDER BY REF DESC, STEP ASC ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		List<ColumnBbsDto> list = new ArrayList<ColumnBbsDto>();
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 getBbsList Success");
			
			psmt = conn.prepareStatement(sql);
			System.out.println("2/6 getBbsList Success");
			
			rs = psmt.executeQuery();
			System.out.println("3/6 getBbsList Success");
			
			while (rs.next()) {
				ColumnBbsDto dto = new ColumnBbsDto(rs.getInt(1), // seq, 
										rs.getString(2), // id, 
										rs.getInt(3), //ref, 
										rs.getInt(4),	//step, 
										rs.getInt(5), // depth, 
										rs.getString(6), //title, 
										rs.getString(7), //content, 
										rs.getString(8), //wdate, 
										rs.getInt(9), //parent, 
										rs.getInt(10), //del, 
										rs.getInt(11)); // readcount
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
	public boolean writeBbs(ColumnBbsDto bbs) {
		
		String sql = " INSERT INTO BBS(SEQ, ID, "
				+ " REF, STEP, DEPTH, "
				+ " TITLE, CONTENT, WDATE, PARENT, "
				+ " DEL, READCOUNT) "
				+ " VALUES(SEQ_BBS.NEXTVAL, ?, "
				+ " (SELECT NVL(MAX(REF), 0)+1 FROM BBS), 0, 0, "
				+ " ?, ?, SYSDATE, 0, "
				+ " 0, 0) ";
		System.out.println(bbs.getId()+bbs.getTitle()+bbs.getContent());
		Connection conn = null;
		PreparedStatement psmt = null;
		
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();			
			System.out.println("1/6 writeBbs Success");
			
			psmt = conn.prepareStatement(sql);
			System.out.println("2/6 writeBbs Success");
			
			psmt.setString(1, bbs.getId());
			psmt.setString(2, bbs.getTitle());
			psmt.setString(3, bbs.getContent());
			
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

	

	

}


