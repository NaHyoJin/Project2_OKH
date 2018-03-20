package jobs_BBS5;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import db.DBClose;
import db.DBConnection;

public class newbbs5HWCodingDao implements newbbs5HWCodingDaoImpl {

	//DB연결 까먹지 마라.
	public newbbs5HWCodingDao() {
		DBConnection.initConnection();
	}
	
	@Override
	public List<newbbs5HWCodingVO> gettechBbsPagingList(PagingBean paging, 
			String searchWord, int search) {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		List<newbbs5HWCodingVO> bbslist = new ArrayList<newbbs5HWCodingVO>();
		
		System.out.println("search = " + search);
		
		String sWord = "";		
		if(search == 0) {	// 제목
			sWord = " AND TITLE LIKE '%" + searchWord.trim() + "%' ";
		}else if(search == 1) {	// ID
			sWord = " AND ID='" + searchWord.trim() + "' ";
		}else if(search == 2) {	// 내용
			sWord = " AND CONTENT LIKE '%" + searchWord.trim() + "%' ";
		} 
		else if(search == 3) {	// 태그네임
			sWord = " AND TAGNAME LIKE '%" + searchWord.trim() + "%' ";
		} 
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 gettechBbsPagingList Success");
			
			// 글의 총수
			String totalSql = " SELECT COUNT(SEQ) FROM newbbs5HWCodingVO WHERE DEL=0 " + sWord;
			psmt = conn.prepareStatement(totalSql);
			rs = psmt.executeQuery();
			System.out.println("2/6 gettechBbsPagingList Success");
			
			int totalCount = 0;
			rs.next();
			totalCount = rs.getInt(1);	// row의 총 갯수
			
			paging.setTotalCount(totalCount);
			paging = PagingUtil.setPagingInfo(paging);
			
			psmt.close();
			rs.close();
			if(search == 0) {	// 제목
				sWord = " WHERE TITLE LIKE '%" + searchWord.trim() + "%' ";
			}else if(search == 1) {	// ID
				sWord = " WHERE ID='" + searchWord.trim() + "' ";
			}else if(search == 2) {	// 내용
				sWord = " WHERE CONTENT LIKE '%" + searchWord.trim() + "%' ";
			} 
			else if(search == 3) {	// 태그네임
				sWord = " WHERE TAGNAME LIKE '%" + searchWord.trim() + "%' ";
			} 
			// row를 취득
			String sql = " SELECT * FROM "
						+ " (SELECT * FROM (SELECT * FROM newbbs5HWCodingVO " + sWord + " ORDER BY SEQ ASC) "
						+ "  WHERE ROWNUM <= " + paging.getStartNum() + " AND DEL=0 ORDER BY SEQ DESC) "
						+ " WHERE ROWNUM <= " + paging.getCountPerPage() + " AND DEL=0 ";
			
			System.out.println("paging.getStartNum() = " + paging.getStartNum());
			
			psmt = conn.prepareStatement(sql);
			System.out.println("3/6 gettechBbsPagingList Success");
			
			rs = psmt.executeQuery();
			System.out.println("4/6 gettechBbsPagingList Success");
			
			while(rs.next()) {
				newbbs5HWCodingVO dto = new newbbs5HWCodingVO(rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getString(6),
						rs.getInt(7),
						rs.getInt(8),
						rs.getInt(9),
						rs.getString(10),
						rs.getString(11),
						rs.getInt(12),
						rs.getInt(13)
						);
						// seq, id, title, content, wdate, del, readcount, likecount, scrapcount)
				bbslist.add(dto);				
			}
			System.out.println("5/6 gettechBbsPagingList Success");			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("newbbs5HWCodingDao gettechBbsPagingList Fail");
		} finally {
			DBClose.close(psmt, conn, rs);
			System.out.println("6/6 gettechBbsPagingList Success");	
		}
		
		return bbslist;
	}

	@Override
	public List<newbbs5HWCodingVO> gettechBbsList() {
		String sql = " SELECT SEQ, ID, TITLE, TAGNAME, CONTENT, WDATE, "
				+ " DEL, READCOUNT, LIKECOUNT, LIKEID, DISLIKEID, COMMENTCOUNT, SCRAPCOUNT "
				+ " FROM newbbs5HWCodingVO "
				+ " ORDER BY SEQ DESC";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		List<newbbs5HWCodingVO> list = new ArrayList<newbbs5HWCodingVO>();
		
		try {
			conn=DBConnection.getConnection();
			System.out.println(" (1/6) gettechBbsList Success");
			psmt = conn.prepareStatement(sql);
			
			System.out.println(" (2/6) gettechBbsList Success");
				
		
			
			rs = psmt.executeQuery();
			System.out.println(" (3/6) gettechBbsList Success");
			while(rs.next()){
				
				newbbs5HWCodingVO dto = new newbbs5HWCodingVO(rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getString(6),
						rs.getInt(7),
						rs.getInt(8),
						rs.getInt(9),
						rs.getString(10),
						rs.getString(11),
						rs.getInt(12),
						rs.getInt(13)
						);
		// seq, id, title, content, wdate, del, readcount, likecount, scrapcount)
				list.add(dto);
			}	
			System.out.println(" (4/6) gettechBbsList Success");
			
		} catch (SQLException e) {
			System.out.println("gettechBbsList fail");
			e.printStackTrace();
		}finally {
			DBClose.close(psmt, conn, rs);
		}
		
		return list;
	}

	@Override
	public String[] getTagName(String tagname) {
		StringTokenizer st= new StringTokenizer(tagname, "-");
		
		int len=st.countTokens();	//split.length
		System.out.println(len); 
		String[] tagnames=new String[len];
		for (int i = 0; i < len; i++) {
			tagnames[i]=st.nextToken();
		}
		
		return tagnames;
	}
	
	//글 작성 하는 부분.
	@Override
	public boolean writeBbs(newbbs5HWCodingVO bbs) {
		
		String sql = " INSERT INTO newbbs5HWCodingVO(SEQ, ID, "
				+ " TITLE, TAGNAME, CONTENT, WDATE, "
				+ " DEL, READCOUNT, LIKECOUNT, LIKEID, DISLIKEID, COMMENTCOUNT, SCRAPCOUNT ) "
				+ " VALUES(SEQ_newbbs5HWCodingVO.NEXTVAL, ?, ?, ?, ?, "
				+ " SYSDATE, 0, 0, 0, ?, ?, 0, 0) ";
		
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
			psmt.setString(3, bbs.getTagname());
			psmt.setString(4, bbs.getContent());
			psmt.setString(5, "-admin");
			psmt.setString(6, "-admin");
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
	
	//글 잘 작성 하면 인간에게 점수 주는 부분.
	//글 작성시 인간 쪽 점수 올라가는 것. byte 형으로 점수 10점 들어온다.
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
			System.out.println("1/6 writeBbsMemSCORE Success");	
			psmt.setByte(1, score);
			psmt.setString(2, writescoreid);
			
			System.out.println(sql);
			System.out.println("2/6 writeBbsMemSCORE Success");	
			count = psmt.executeUpdate();			
			
		} catch (SQLException e) {
			System.out.println("writeBbsMemSCORE fail");	
			e.printStackTrace();
		}finally{
			DBClose.close(psmt, conn, null);
			System.out.println("3/6 finally writeBbsMemSCORE Success");	
		}
		
		return count>0?true:false;
	}
	@Override
	public List<newbbs5HWCodingVO> getdetail(int seq) {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		newbbs5HWCodingVO dto = null;
		List<newbbs5HWCodingVO> list = new ArrayList<>();
		String sql1 = " SELECT SEQ, ID, TITLE, TAGNAME, CONTENT, WDATE, DEL, "
				+ " READCOUNT, LIKECOUNT, LIKEID, DISLIKEID, COMMENTCOUNT, SCRAPCOUNT "
				+ " FROM newbbs5HWCodingVO "
				+ " WHERE SEQ=? ";
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 getdetail newbbs5HWCodingVO Success");			
		psmt = conn.prepareStatement(sql1);	
		
		psmt.setInt(1, seq);			
		rs = psmt.executeQuery();
		System.out.println("2/6 getdetail Success");		
		while(rs.next()){		
			int seq1=rs.getInt(1);
			String id = rs.getString(2);
			String title = rs.getString(3);
			String tagname = rs.getString(4);
			String content = rs.getString(5);
			String wdate = rs.getString(6);	
			int del = rs.getInt(7);
			int readcount = rs.getInt(8);
			int likecount = rs.getInt(9);
			String likeid = rs.getString(10);	
			String dislikeid = rs.getString(11);	
			int commentcount = rs.getInt(12);
			int scrapcount = rs.getInt(13);
			int pdsyn=2;
			
			dto = new newbbs5HWCodingVO(seq1, id, title, tagname, content, wdate, 
					del, readcount, commentcount, likecount, scrapcount, pdsyn);
			list.add(dto);
			
		}
		System.out.println("3/6 getdetail Success");		
		} catch (SQLException e) {	
			System.out.println("getdetail fail");		
			e.printStackTrace();
		} finally{
			DBClose.close(psmt, conn, rs);	
		}
		return list;
	}
	
	@Override
	public List<newbbs5HWCodingVO> getpdsdetail(int seq) {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		List<newbbs5HWCodingVO> list=new ArrayList<>();
		newbbs5HWCodingVO dto=null;
		String sql = " SELECT B.SEQ, B.ID, B.TITLE, B.TAGNAME, B.CONTENT, B.WDATE, "
				+ " B.READCOUNT, B.LIKECOUNT, B.LIKEID, B.DISLIKEID, B.COMMENTCOUNT, B.SCRAPCOUNT, "
				+ " P.SEQ, P.FILENAME, P.PARENT "
				+ " FROM newbbs5HWCodingVO B, newbbs5HWCodingPDSVO P  "
				+ " WHERE B.ID=P.ID AND B.SEQ=? AND B.SEQ=P.PARENT ";
		
		try {			
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);	
			System.out.println("1/6 getpdsdetail Success");	
			psmt.setInt(1, seq);			
			rs = psmt.executeQuery();
			System.out.println("2/6 getpdsdetail Success");	
			while(rs.next()){		
				int seq1=rs.getInt(1);
				String id = rs.getString(2);
				String title = rs.getString(3);
				String tagname = rs.getString(4);
				String content = rs.getString(5);
				String wdate = rs.getString(6);	
				int readcount = rs.getInt(7);
				int likecount = rs.getInt(8);
				String likeid = rs.getString(9);	
				String dislikeid = rs.getString(10);
				int commentcount = rs.getInt(11);
				int scrapcount = rs.getInt(12);
				int pdsseq = rs.getInt(13);
				String filename = rs.getString(14);	
				int parent = rs.getInt(15);
				int pdsyn = 1;
				
				dto = new newbbs5HWCodingVO(seq1, id, title, tagname, content, wdate, readcount,likecount, likeid,dislikeid,commentcount, scrapcount, filename, parent, pdsseq,pdsyn);
				list.add(dto);
					
				System.out.println("5/6 getdetail Success");			
				
				
			}
			System.out.println("3/6 getpdsdetail Success");	
		} catch (SQLException e) {	
			System.out.println("getpdsdetail fail");	
			e.printStackTrace();
		} finally{
			DBClose.close(psmt, conn, rs);	
		}
		return list;
	}
	
	@Override
	public boolean getparent(int seq) {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		List<newbbs5HWCodingVO> list = new ArrayList<>();
		newbbs5HWCodingVO dto = null;
		String sql = " SELECT B.SEQ,B.ID, B.TITLE, B.TAGNAME, B.CONTENT, B.WDATE, "
				+ " B.READCOUNT, B.LIKECOUNT, B.LIKEID, B.DISLIKEID,"
				+ " B.COMMENTCOUNT, B.SCRAPCOUNT, P.SEQ, P.FILENAME, P.PARENT "
				+ " FROM newbbs5HWCodingVO B, newbbs5HWCodingPDSVO P  "
				+ " WHERE B.ID=P.ID AND B.SEQ=? AND B.SEQ=P.PARENT ";
		
		try {			
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);	
			System.out.println("1/6 getparent Success");		
			psmt.setInt(1, seq);			
			rs = psmt.executeQuery();
			
			while(rs.next()){		
				int seq1=rs.getInt(1);
				String id = rs.getString(2);
				String title = rs.getString(3);
				String tagname = rs.getString(4);
				String content = rs.getString(5);
				String wdate = rs.getString(6);	
				int readcount = rs.getInt(7);
				int likecount = rs.getInt(8);
				String likeid = rs.getString(9);	
				String dislikeid = rs.getString(10);
				int commentcount = rs.getInt(11);
				int scrapcount = rs.getInt(12);
				int pdsseq=rs.getInt(13);
				String filename = rs.getString(14);	
				int parent=rs.getInt(15);
				int pdsyn=0;
				pdsyn=dto!=null?1:2;	//자료있으면 1 자료없으면2
				
				dto = new newbbs5HWCodingVO(seq1, id, title, tagname, content, wdate, readcount,likecount, likeid,dislikeid,commentcount, scrapcount, filename, parent, pdsseq,pdsyn);
				list.add(dto);
					
				System.out.println("5/6 getBbsPagingList Success");			
				
				
			}
			System.out.println("2/6 getparent Success");	
		} catch (SQLException e) {		
			System.out.println("getparent fail");	
			e.printStackTrace();
		} finally{
			DBClose.close(psmt, conn, rs);	
		}
		System.out.println(dto!=null?true:false);	
		return dto != null ? true : false;
	}
	
	@Override
	public void likecountplus(int seq) {
		String sql = " UPDATE newbbs5HWCodingVO SET LIKECOUNT=LIKECOUNT+1 "
				+ " WHERE SEQ=? ";
		Connection conn=null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		System.out.println(sql);
		try {
			conn = DBConnection.getConnection();	
			psmt=conn.prepareStatement(sql);
			System.out.println("1/6 countplus Success");	
			psmt.setInt(1, seq);
			System.out.println(sql);
			System.out.println("2/6 countplus Success");	
			psmt.executeUpdate();			
			
		} catch (SQLException e) {
			System.out.println("countplus fail");	
			e.printStackTrace();
		}finally{
			DBClose.close(psmt, conn, rs);	
		}
	}
	@Override
	public void dislikecount(int seq) {
		String sql = " UPDATE newbbs5HWCodingVO SET LIKECOUNT=LIKECOUNT-1 "
				+ " WHERE SEQ=? ";
		Connection conn=null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		
		try {
			conn = DBConnection.getConnection();	
			psmt=conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			System.out.println("2/6 dislikecount Success");	
			psmt.executeUpdate();			
			
		} catch (SQLException e) {
			System.out.println(" dislikecount fail");	
			e.printStackTrace();
		}finally{
			
			DBClose.close(psmt, conn, rs);	
		}		
	}
	
	@Override
	public boolean update(int seq, String title, String content) {
		String sql = " UPDATE newbbs5HWCodingVO SET "
				+ " TITLE=?, CONTENT=? "
				+ " WHERE SEQ=? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();	
			System.out.println("2/6 S updateBbs");
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, title);
			psmt.setString(2, content);
			psmt.setInt(3, seq);
			
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

	@Override
	public boolean delete(int seq) {
		String sql = " UPDATE newbbs5HWCodingVO SET "
				+ " DEL=1"
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
	//글 삭제시 인간 쪽 점수 빼는 것. 나중에 시간 되면 만들어보자.
		public boolean deleteBbsMemSCORE(byte score, int seq) {
		//글 삭제 하면 -로. 쿼리문은 잘 작동한다.
		String sql = " UPDATE OKHMEM "
				+ " SET score=score-? "
				+ " WHERE id=(select id from newbbs5HWCodingVO where seq=?) ";
		Connection conn = null;
		PreparedStatement psmt = null;
		
		int count = 0;
		
		System.out.println(sql);
		try {
			conn = DBConnection.getConnection();	
			psmt = conn.prepareStatement(sql);
			System.out.println("1/6 deleteBbsMemSCORE Success");
			
			System.out.println("score : " + score);
			psmt.setByte(1, score);
			System.out.println("seq : " + seq);
			psmt.setInt(2, seq);
			
			System.out.println(sql);
			System.out.println("2/6 deleteBbsMemSCORE Success");	
			count = psmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("deleteBbsMemSCORE fail");	
			e.printStackTrace();
		}finally{
			DBClose.close(psmt, conn, null);
			System.out.println("3/6 finally deleteBbsMemSCORE Success");	
		}
		
		return count > 0 ? true : false;
	}

	@Override
	public boolean pdsdelete(int seq) {
		String sql=" DELETE  "
				+ " FROM newbbs5HWCodingPDSVO "
				+ " WHERE PARENT = ? ";
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
		} finally {DBClose.close(pstmt, conn, null);}
		
		return count>0?true:false;
	}
	
	//리플 부분.
	@Override
	public boolean repAlldelete(int seq) {
		String sql=" DELETE  "
				+ " FROM TECHREPBBS "
				+ " WHERE PARENT = ? ";
		Connection conn = null;
		PreparedStatement pstmt = null;//sql문에서?썻을때이렇게불러와야한다
		
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
		} finally {DBClose.close(pstmt, conn, null);}
		
		return count>0?true:false;
	}

	//조회수 부분.
	@Override
	public void readcountplus(int seq) {
		String sql = " UPDATE newbbs5HWCodingVO SET READCOUNT=READCOUNT+1 "
				+ " WHERE SEQ=? ";
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		System.out.println(sql);
		try {
			conn = DBConnection.getConnection();	
			psmt=conn.prepareStatement(sql);
			System.out.println("1/6 readcountplus Success");	
			psmt.setInt(1, seq);
			System.out.println(sql);
			System.out.println("2/6 readcountplus Success");	
			psmt.executeUpdate();			
			
		} catch (SQLException e) {
			System.out.println("readcountplus fail");	
			e.printStackTrace();
		}finally{
			DBClose.close(psmt, conn, rs);	
		}		
	}

	@Override
	public void scrapcountplus(int seq) {
		String sql = " UPDATE newbbs5HWCodingVO SET SCRAPCOUNT=SCRAPCOUNT+1 "
				+ " WHERE SEQ=? ";
		Connection conn=null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		System.out.println(sql);
		try {
			conn = DBConnection.getConnection();	
			psmt=conn.prepareStatement(sql);
			System.out.println("1/6 scrapcountplus Success");	
			psmt.setInt(1, seq);
			System.out.println(sql);
			System.out.println("2/6 scrapcountplus Success");	
			psmt.executeUpdate();			
			
		} catch (SQLException e) {
			System.out.println("scrapcountplus fail");	
			e.printStackTrace();
		}finally{
			DBClose.close(psmt, conn, rs);	
		}		
	}

	@Override
	public void commentcountplus(int seq) {
		String sql = " UPDATE newbbs5HWCodingVO SET COMMENTCOUNT=COMMENTCOUNT+1 "
				+ " WHERE SEQ=? ";
		Connection conn=null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		System.out.println(sql);
		try {
			conn = DBConnection.getConnection();	
			psmt=conn.prepareStatement(sql);
			System.out.println("1/6 commentcountplus Success");	
			psmt.setInt(1, seq);
			System.out.println(sql);
			System.out.println("2/6 commentcountplus Success");	
			psmt.executeUpdate();			
			
		} catch (SQLException e) {
			System.out.println("commentcountplus fail");	
			e.printStackTrace();
		}finally{
			DBClose.close(psmt, conn, rs);	
		}		
	}

	@Override
	public void scrapcountminus(int seq) {
		String sql = " UPDATE newbbs5HWCodingVO SET SCRAPCOUNT=SCRAPCOUNT-1 "
				+ " WHERE SEQ=? ";
		Connection conn=null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		System.out.println(sql);
		try {
			conn = DBConnection.getConnection();	
			psmt=conn.prepareStatement(sql);
			System.out.println("1/6 scrapcountplus Success");	
			psmt.setInt(1, seq);
			System.out.println(sql);
			System.out.println("2/6 scrapcountplus Success");	
			psmt.executeUpdate();			
			
		} catch (SQLException e) {
			System.out.println("scrapcountplus fail");	
			e.printStackTrace();
		}finally{
			DBClose.close(psmt, conn, rs);	
		}		
	}

	@Override
	public void commentcountminus(int seq) {
		String sql = " UPDATE newbbs5HWCodingVO SET COMMENTCOUNT=COMMENTCOUNT-1 "
				+ " WHERE SEQ=? ";
		Connection conn=null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		try {
			conn = DBConnection.getConnection();	
			psmt=conn.prepareStatement(sql);
			System.out.println("1/6 commentcountminus Success");	
			psmt.setInt(1, seq);
			System.out.println(sql);
			System.out.println("2/6 commentcountminus Success");	
			psmt.executeUpdate();			
			
		} catch (SQLException e) {
			System.out.println("commentcountminus fail");	
			e.printStackTrace();
		}finally{
			DBClose.close(psmt, conn, rs);	
		}		
	}

	@Override
	public boolean checkcomment(int seq) {
		String sql = " SELECT COMMENTCOUNT FROM newbbs5HWCodingVO "
				+ " WHERE SEQ=? ";
		Connection conn=null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		int commentcount=0;
		try {
			conn = DBConnection.getConnection();	
			psmt=conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			rs = psmt.executeQuery();
			System.out.println("2/6 gettechBbsPagingList Success");
			
			rs.next();
			commentcount = rs.getInt(1);	// row의 총 갯수
			
		} catch (SQLException e) {
			System.out.println(" dislikecount fail");	
			e.printStackTrace();
		}finally{
			
			DBClose.close(psmt, conn, rs);	
		}		
		return commentcount>0?true:false;
	}

}