package techbbs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import db.DBConnection;
import totalbbs.ColumnBbsDto;
import totalbbs.CombbsDto;
import totalbbs.LifeBbsDto;
import totalbbs.QnaDto;
import totalbbs.newbbs5HWCodingVO;
import totalbbs.totalbbsdto;
import db.DBClose;

public class TechbbsDao implements iTechbbsDao {
	
	public TechbbsDao() {
		DBConnection.initConnection();
	}
	@Override
	public List<TechbbsDto> gettechBbssortPagingList(PagingBean paging, String whatsort) {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		List<TechbbsDto> bbslist = new ArrayList<TechbbsDto>();
		
		
		String sWord = "";		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 gettechBbssortPagingList Success");
			
			// 글의 총수
			String totalSql = " SELECT COUNT(SEQ) FROM TECHBBS WHERE DEL=0 ";
			psmt = conn.prepareStatement(totalSql);
			rs = psmt.executeQuery();
			System.out.println("2/6 gettechBbssortPagingList Success");
			
			int totalCount = 0;
			rs.next();
			totalCount = rs.getInt(1);	// row의 총 갯수
			
			paging.setTotalCount(totalCount);
			paging = PagingUtil.setPagingInfo(paging);
			
			psmt.close();
			rs.close();
			if(whatsort.equals("wdate")) {	// 날짜
				sWord = " SEQ ";
			}else if(whatsort.equals("likecount")) {	// ID
				sWord = " LIKECOUNT ";
			}else if(whatsort.equals("contentcount")) {	// ID
				sWord = " COMMENTCOUNT ";
			}
			else if(whatsort.equals("scrapcount")) {	// ID
				sWord = " SCRAPCOUNT ";
			}
			else if(whatsort.equals("readcount")) {	// ID
				sWord = " READCOUNT ";
			}
			
			// row를 취득
			String sql = " SELECT * FROM "
						+ " (SELECT * FROM (SELECT * FROM TECHBBS ORDER BY "+ sWord +" ASC)  "
						+ "  WHERE ROWNUM <= " + paging.getStartNum() + " AND DEL=0  ORDER BY "+ sWord +" DESC) "
						+ " WHERE ROWNUM <= " + paging.getCountPerPage() + " AND DEL=0 ";
			
			System.out.println("paging.getStartNum() = " + paging.getStartNum());
			
			psmt = conn.prepareStatement(sql);
			System.out.println("3/6 gettechBbssortPagingList Success");
			
			rs = psmt.executeQuery();
			System.out.println("4/6 gettechBbssortPagingList Success");
			
			while(rs.next()) {
				TechbbsDto dto = new TechbbsDto(rs.getInt(1),
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
			System.out.println("5/6 gettechBbssortPagingList Success");			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("gettechBbssortPagingList Fail");
		} finally {
			DBClose.close(psmt, conn, rs);
			System.out.println("6/6 gettechBbsPagingList Success");	
		}
		
		return bbslist;
	}
	
	
	@Override
	public List<TechbbsDto> gettechBbsPagingList(PagingBean paging, String searchWord, int search) {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		List<TechbbsDto> bbslist = new ArrayList<TechbbsDto>();
		
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
			sWord = " AND TAGNAME LIKE '%-" + searchWord.trim() + "-%' ";
		} 
		else if(search == 4) {	// 태그네임
			sWord = " AND TAGNAME LIKE '%" + searchWord.trim() + "%' ";
		} 
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 gettechBbsPagingList Success");
			
			// 글의 총수
			String totalSql = " SELECT COUNT(SEQ) FROM TECHBBS WHERE DEL=0 " + sWord;
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
				sWord = " WHERE TAGNAME LIKE '%-" + searchWord.trim() + "-%' ";
			} 
			else if(search == 4) {	// 태그네임
				sWord = " WHERE TAGNAME LIKE '%" + searchWord.trim() + "%' ";
			} 
			// row를 취득
			String sql = " SELECT * FROM "
						+ " (SELECT * FROM (SELECT * FROM TECHBBS " + sWord + " ORDER BY SEQ ASC) "
						+ "  WHERE ROWNUM <= " + paging.getStartNum() + " AND DEL=0 ORDER BY SEQ DESC) "
						+ " WHERE ROWNUM <= " + paging.getCountPerPage() + " AND DEL=0 ";
			
			System.out.println("paging.getStartNum() = " + paging.getStartNum());
			
			psmt = conn.prepareStatement(sql);
			System.out.println("3/6 gettechBbsPagingList Success");
			
			rs = psmt.executeQuery();
			System.out.println("4/6 gettechBbsPagingList Success");
			
			while(rs.next()) {
				TechbbsDto dto = new TechbbsDto(rs.getInt(1),
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
			System.out.println("gettechBbsPagingList Fail");
		} finally {
			DBClose.close(psmt, conn, rs);
			System.out.println("6/6 gettechBbsPagingList Success");	
		}
		
		return bbslist;
	}

	@Override
	public List<TechbbsDto> gettechBbsList() {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		List<TechbbsDto> list=new ArrayList<TechbbsDto>();
		int totalCount = 0;
		try {
		String sql = " SELECT * FROM  "
				+ " (SELECT * FROM (SELECT * FROM TECHBBS ORDER BY SEQ ASC) "
				+ "  WHERE ROWNUM >= 1 AND DEL=0 ORDER BY SEQ DESC) "
				+ " WHERE ROWNUM <= 6 AND DEL=0 ";
		
		
			conn=DBConnection.getConnection();
			System.out.println(" (1/6) gettechBbsList Success");
			psmt = conn.prepareStatement(sql);
			
			System.out.println(" (2/6) gettechBbsList Success");
				
		
			
			rs = psmt.executeQuery();
			System.out.println(" (3/6) gettechBbsList Success");
			while(rs.next()){
				
				TechbbsDto dto = new TechbbsDto(rs.getInt(1),
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
	@Override
	public boolean writeBbs(TechbbsDto bbs) {
		
		String sql = " INSERT INTO TECHBBS(SEQ, ID, "
				+ " TITLE, TAGNAME, CONTENT, WDATE, "
				+ " DEL, READCOUNT,LIKECOUNT,LIKEID,DISLIKEID,COMMENTCOUNT,SCRAPCOUNT ) "
				+ " VALUES(SEQ_TECHBBS.NEXTVAL, ?, ?, ?,?, "
				+ " SYSDATE, 0,0,0,?,?,0,0) ";
		
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
			psmt.setString(5, "-admin-");
			psmt.setString(6, "-admin-");
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
	public List<TechbbsDto> getdetail(int seq) {
		Connection conn=null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		TechbbsDto dto=null;
		List<TechbbsDto> list=new ArrayList<>();
		String sql1 = " SELECT SEQ,ID, TITLE,TAGNAME,CONTENT,WDATE,DEL,  "
				+ " READCOUNT,LIKECOUNT,LIKEID,DISLIKEID,COMMENTCOUNT,SCRAPCOUNT "
				+ "  FROM TECHBBS "
				+ " WHERE SEQ=? ";
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 getdetail Success");			
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
			
			dto = new TechbbsDto(seq1, id, title, tagname, content, wdate, del, readcount, commentcount, likecount, scrapcount, pdsyn);
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
	public List<TechbbsDto> getpdsdetail(int seq) {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		List<TechbbsDto> list=new ArrayList<>();
		TechbbsDto dto=null;
		String sql = " SELECT B.SEQ,B.ID, B.TITLE,B.TAGNAME,B.CONTENT,B.WDATE, "
				+ " B.READCOUNT,B.LIKECOUNT,B.LIKEID,B.DISLIKEID,B.COMMENTCOUNT,B.SCRAPCOUNT,P.SEQ,P.FILENAME,P.PARENT "
				+ " FROM TECHBBS B,TECH_PDS P  "
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
				int pdsseq=rs.getInt(13);
				String filename = rs.getString(14);	
				int parent=rs.getInt(15);
				int pdsyn=1;
				
				dto = new TechbbsDto(seq1, id, title, tagname, content, wdate, readcount,likecount, likeid,dislikeid,commentcount, scrapcount, filename, parent, pdsseq,pdsyn);
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
		List<TechbbsDto> list=new ArrayList<>();
		TechbbsDto dto=null;
		String sql = " SELECT B.SEQ,B.ID, B.TITLE,B.TAGNAME,B.CONTENT,B.WDATE, "
				+ " B.READCOUNT,B.LIKECOUNT,B.LIKEID,B.DISLIKEID,B.COMMENTCOUNT,B.SCRAPCOUNT,P.SEQ,P.FILENAME,P.PARENT "
				+ " FROM TECHBBS B,TECH_PDS P  "
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
				
				dto = new TechbbsDto(seq1, id, title, tagname, content, wdate, readcount,likecount, likeid,dislikeid,commentcount, scrapcount, filename, parent, pdsseq,pdsyn);
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
		return dto!=null?true:false;
	}
	@Override
	public void likecountplus(int seq) {
		String sql = " UPDATE TECHBBS SET LIKECOUNT=LIKECOUNT+1 "
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
		String sql = " UPDATE TECHBBS SET LIKECOUNT=LIKECOUNT-1 "
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
		String sql = " UPDATE TECHBBS SET "
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
		String sql=" UPDATE TECHBBS SET "
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

	@Override
	public boolean pdsdelete(int seq) {
		String sql=" DELETE  "
				+ " FROM TECH_PDS "
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
	@Override
	public boolean repAlldelete(int seq) {
		String sql=" DELETE  "
				+ " FROM TECHREPBBS "
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

	@Override
	public void readcountplus(int seq) {
		String sql = " UPDATE TECHBBS SET READCOUNT=READCOUNT+1 "
				+ " WHERE SEQ=? ";
		Connection conn=null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
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
		String sql = " UPDATE TECHBBS SET SCRAPCOUNT=SCRAPCOUNT+1 "
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
		String sql = " UPDATE TECHBBS SET COMMENTCOUNT=COMMENTCOUNT+1 "
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
		String sql = " UPDATE TECHBBS SET SCRAPCOUNT=SCRAPCOUNT-1 "
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
		String sql = " UPDATE TECHBBS SET COMMENTCOUNT=COMMENTCOUNT-1 "
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
		String sql = " SELECT COMMENTCOUNT FROM TECHBBS "
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
	@Override
	public List<LifeBbsDto> getlifeBbsList() {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		List<LifeBbsDto> list=new ArrayList<LifeBbsDto>();
		int totalCount = 0;
		try {
		String sql = " SELECT * FROM  "
				+ " (SELECT * FROM (SELECT * FROM LIFEBBS ORDER BY SEQ ASC) "
				+ "  WHERE ROWNUM >= 1 AND DEL=0 ORDER BY SEQ DESC) "
				+ " WHERE ROWNUM <= 6 AND DEL=0 ";
		
		
			conn=DBConnection.getConnection();
			System.out.println(" (1/6) getlifeBbsList Success");
			psmt = conn.prepareStatement(sql);
			
			System.out.println(" (2/6) getlifeBbsList Success");
				
		
			
			rs = psmt.executeQuery();
			System.out.println(" (3/6) getlifeBbsList Success");
			while(rs.next()){
				
				LifeBbsDto dto = new LifeBbsDto
						(rs.getInt(1),
						rs.getString(2),
						rs.getInt(3),
						rs.getInt(4),
						rs.getInt(5),
						rs.getString(6),
						rs.getString(7),
						rs.getString(8),
						rs.getString(9),
						rs.getInt(10),
						rs.getString(11),
						rs.getString(12),
						rs.getString(13),
						rs.getInt(14),
						rs.getInt(15),
						rs.getInt(16),
						rs.getInt(17),
						rs.getInt(18)
						);
		// seq, id, title, content, wdate, del, readcount, likecount, scrapcount)
				list.add(dto);
			}	
			System.out.println(" (4/6) getlifeBbsList Success");
			
		} catch (SQLException e) {
			System.out.println("getlifeBbsList fail");
			e.printStackTrace();
		}finally {
			DBClose.close(psmt, conn, rs);
		}
		
		return list;
	}
	@Override
	public List<newbbs5HWCodingVO> getbbs5BbsList() {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		List<newbbs5HWCodingVO> list=new ArrayList<newbbs5HWCodingVO>();
		int totalCount = 0;
		try {
		String sql = " SELECT * FROM  "
				+ " (SELECT * FROM (SELECT * FROM newbbs5HWCodingVO ORDER BY SEQ ASC) "
				+ "  WHERE ROWNUM >= 1 AND DEL=0 ORDER BY SEQ DESC) "
				+ " WHERE ROWNUM <= 6 AND DEL=0 ";
		
		
			conn=DBConnection.getConnection();
			System.out.println(" (1/6) getbbs5BbsList Success");
			psmt = conn.prepareStatement(sql);
			
			System.out.println(" (2/6) getbbs5BbsList Success");
				
		
			
			rs = psmt.executeQuery();
			System.out.println(" (3/6) getbbs5BbsList Success");
			while(rs.next()){
				
				newbbs5HWCodingVO dto = new newbbs5HWCodingVO
						(rs.getInt(1),
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
			System.out.println(" (4/6) getbbs5BbsList Success");
			
		} catch (SQLException e) {
			System.out.println("getbbs5BbsList fail");
			e.printStackTrace();
		}finally {
			DBClose.close(psmt, conn, rs);
		}
		
		return list;
	}
	@Override
	public List<QnaDto> getqnaBbsList() {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		List<QnaDto> list=new ArrayList<QnaDto>();
		int totalCount = 0;
		try {
		String sql = " SELECT * FROM  "
				+ " (SELECT * FROM (SELECT * FROM QNA ORDER BY SEQ ASC) "
				+ "  WHERE ROWNUM >= 1 AND DEL=0 ORDER BY SEQ DESC) "
				+ " WHERE ROWNUM <= 6 AND DEL=0 ";
		
		
			conn=DBConnection.getConnection();
			System.out.println(" (1/6) getqnaBbsList Success");
			psmt = conn.prepareStatement(sql);
			
			System.out.println(" (2/6) getqnaBbsList Success");
				
		
			
			rs = psmt.executeQuery();
			System.out.println(" (3/6) getqnaBbsList Success");
			while(rs.next()){
				
				QnaDto dto = new QnaDto
						(rs.getInt(1),
						rs.getString(2),
						rs.getInt(3),
						rs.getInt(4),
						rs.getInt(5),
						rs.getString(6),
						rs.getString(7),
						rs.getString(8),
						rs.getString(9),
						rs.getInt(10),
						rs.getInt(11),
						rs.getInt(12),
						rs.getInt(13),
						rs.getInt(14),
						rs.getInt(15)
						);
		// seq, id, title, content, wdate, del, readcount, likecount, scrapcount)
				list.add(dto);
			}	
			System.out.println(" (4/6) getqnaBbsList Success");
			
		} catch (SQLException e) {
			System.out.println("getqnaBbsList fail");
			e.printStackTrace();
		}finally {
			DBClose.close(psmt, conn, rs);
		}
		
		return list;
	}
	@Override
	public List<TechbbsDto> alltechBbsList() {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		List<TechbbsDto> list=new ArrayList<TechbbsDto>();
		int totalCount = 0;
		try {
			String sql = " SELECT * FROM TECHBBS "
					+ "  WHERE DEL=0 ORDER BY SEQ DESC ";
		
		
			conn=DBConnection.getConnection();
			System.out.println(" (1/6) gettechBbsList Success");
			psmt = conn.prepareStatement(sql);
			
			System.out.println(" (2/6) gettechBbsList Success");
				
		
			
			rs = psmt.executeQuery();
			System.out.println(" (3/6) gettechBbsList Success");
			while(rs.next()){
				
				TechbbsDto dto = new TechbbsDto(rs.getInt(1),
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
	public List<LifeBbsDto> alllifeBbsList() {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		List<LifeBbsDto> list=new ArrayList<LifeBbsDto>();
		int totalCount = 0;
		try {
			String sql = " SELECT * FROM LIFEBBS "
					+ "  WHERE DEL=0 ORDER BY SEQ DESC ";
		
		
			conn=DBConnection.getConnection();
			System.out.println(" (1/6) getlifeBbsList Success");
			psmt = conn.prepareStatement(sql);
			
			System.out.println(" (2/6) getlifeBbsList Success");
				
		
			
			rs = psmt.executeQuery();
			System.out.println(" (3/6) getlifeBbsList Success");
			while(rs.next()){
				
				LifeBbsDto dto = new LifeBbsDto
						(rs.getInt(1),
						rs.getString(2),
						rs.getInt(3),
						rs.getInt(4),
						rs.getInt(5),
						rs.getString(6),
						rs.getString(7),
						rs.getString(8),
						rs.getString(9),
						rs.getInt(10),
						rs.getString(11),
						rs.getString(12),
						rs.getString(13),
						rs.getInt(14),
						rs.getInt(15),
						rs.getInt(16),
						rs.getInt(17),
						rs.getInt(18)
						);
		// seq, id, title, content, wdate, del, readcount, likecount, scrapcount)
				list.add(dto);
			}	
			System.out.println(" (4/6) getlifeBbsList Success");
			
		} catch (SQLException e) {
			System.out.println("getlifeBbsList fail");
			e.printStackTrace();
		}finally {
			DBClose.close(psmt, conn, rs);
		}
		
		return list;
	}
	@Override
	public List<newbbs5HWCodingVO> allbbs5BbsList() {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		List<newbbs5HWCodingVO> list=new ArrayList<newbbs5HWCodingVO>();
		int totalCount = 0;
		try {
		String sql = " SELECT * FROM newbbs5HWCodingVO "
					+ "  WHERE DEL=0 ORDER BY SEQ DESC ";
			
		
			conn=DBConnection.getConnection();
			System.out.println(" (1/6) getbbs5BbsList Success");
			psmt = conn.prepareStatement(sql);
			
			System.out.println(" (2/6) getbbs5BbsList Success");
				
		
			
			rs = psmt.executeQuery();
			System.out.println(" (3/6) getbbs5BbsList Success");
			while(rs.next()){
				
				newbbs5HWCodingVO dto = new newbbs5HWCodingVO
						(rs.getInt(1),
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
			System.out.println(" (4/6) getbbs5BbsList Success");
			
		} catch (SQLException e) {
			System.out.println("getbbs5BbsList fail");
			e.printStackTrace();
		}finally {
			DBClose.close(psmt, conn, rs);
		}
		
		return list;
	}
	@Override
	public List<QnaDto> allqnaBbsList() {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		List<QnaDto> list=new ArrayList<QnaDto>();
		int totalCount = 0;
		try {
		String sql = " SELECT * FROM QNA "
				+ "  WHERE DEL=0 ORDER BY SEQ DESC ";
		
		
			conn=DBConnection.getConnection();
			System.out.println(" (1/6) allqnaBbsList Success");
			psmt = conn.prepareStatement(sql);
			
			System.out.println(" (2/6) allqnaBbsList Success");
				
		
			
			rs = psmt.executeQuery();
			System.out.println(" (3/6) allqnaBbsList Success");
			while(rs.next()){
				
				QnaDto dto = new QnaDto
						(rs.getInt(1),
						rs.getString(2),
						rs.getInt(3),
						rs.getInt(4),
						rs.getInt(5),
						rs.getString(6),
						rs.getString(7),
						rs.getString(8),
						rs.getString(9),
						rs.getInt(10),
						rs.getInt(11),
						rs.getInt(12),
						rs.getInt(13),
						rs.getInt(14),
						rs.getInt(15)
						);
		// seq, id, title, content, wdate, del, readcount, likecount, scrapcount)
				list.add(dto);
			}	
			System.out.println(" (4/6) allqnaBbsList Success");
			
		} catch (SQLException e) {
			System.out.println("allqnaBbsList fail");
			e.printStackTrace();
		}finally {
			DBClose.close(psmt, conn, rs);
		}
		
		return list;
	}
	@Override
	public List<CombbsDto> allcomBbsList() {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		List<CombbsDto> list=new ArrayList<CombbsDto>();
		int totalCount = 0;
		try {
		String sql = " SELECT * FROM COMBBS "
				+ "  WHERE DEL=0 ORDER BY SEQ DESC ";
		
		
			conn=DBConnection.getConnection();
			System.out.println(" (1/6) allcomBbsList Success");
			psmt = conn.prepareStatement(sql);
			
			System.out.println(" (2/6) allcomBbsList Success");
				
		
			
			rs = psmt.executeQuery();
			System.out.println(" (3/6) allcomBbsList Success");
			while(rs.next()){
				
				CombbsDto dto = new CombbsDto
						(rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getInt(6),
						rs.getInt(7),
						rs.getInt(8),
						rs.getString(9),
						rs.getInt(10),
						rs.getInt(11),
						rs.getString(12),
						rs.getString(13)
						);
		// seq, id, title, content, wdate, del, readcount, likecount, scrapcount)
				list.add(dto);
			}	
			System.out.println(" (4/6) allcomBbsList Success");
			
		} catch (SQLException e) {
			System.out.println("allcomBbsList fail");
			e.printStackTrace();
		}finally {
			DBClose.close(psmt, conn, rs);
		}
		
		return list;
	}
	@Override
	public List<totalbbsdto> gettotalBbsList() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<ColumnBbsDto> allcolBbsList() {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		List<ColumnBbsDto> list=new ArrayList<ColumnBbsDto>();
		int totalCount = 0;
		try {
		String sql = " SELECT * FROM BBS "
					+ "  WHERE DEL=0 ORDER BY SEQ DESC ";
			
		
			conn=DBConnection.getConnection();
			System.out.println(" (1/6) getbbs5BbsList Success");
			psmt = conn.prepareStatement(sql);
			
			System.out.println(" (2/6) getbbs5BbsList Success");
				
		
			
			rs = psmt.executeQuery();
			System.out.println(" (3/6) getbbs5BbsList Success");
			while(rs.next()){
				
				ColumnBbsDto dto = new ColumnBbsDto
						(rs.getInt(1),
						rs.getString(2),
						rs.getInt(3),
						rs.getInt(4),
						rs.getInt(5),
						rs.getString(6),
						rs.getString(7),
						rs.getString(8),
						rs.getInt(9),
						rs.getInt(10),
						rs.getInt(11)
						);
		// seq, id, title, content, wdate, del, readcount, likecount, scrapcount)
				list.add(dto);
			}	
			System.out.println(" (4/6) getbbs5BbsList Success");
			
		} catch (SQLException e) {
			System.out.println("getbbs5BbsList fail");
			e.printStackTrace();
		}finally {
			DBClose.close(psmt, conn, rs);
		}
		
		return list;
	}
	@Override
	public boolean checkqnacomment(int seq) {
		String sql = " SELECT ANSWERCOUNT FROM QNA "
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
	@Override
	public boolean checklifecomment(int seq) {
		String sql = " SELECT COUNTREPLY FROM LIFEBBS "
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
	@Override
	public List<TechbbsDto> getliketechBbsList(String likeid) {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		List<TechbbsDto> list=new ArrayList<TechbbsDto>();
		
		try {
			String sql = " SELECT * FROM TECHBBS "
					+ "  WHERE DEL=0 AND LIKEID LIKE '%-"+likeid+"-%' ";

		
			conn=DBConnection.getConnection();
			System.out.println(" (1/6) gettechBbsList Success");
			psmt = conn.prepareStatement(sql);
			
			System.out.println(" (2/6) gettechBbsList Success");
				
		
			
			rs = psmt.executeQuery();
			System.out.println(" (3/6) gettechBbsList Success");
			while(rs.next()){
				
				TechbbsDto dto = new TechbbsDto(rs.getInt(1),
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
	public List<LifeBbsDto> getlikealllifeBbsList(String likeid) {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		List<LifeBbsDto> list=new ArrayList<LifeBbsDto>();
		int totalCount = 0;
		try {
			String sql = " SELECT * FROM LIFEBBS "
					+ "  WHERE DEL=0 AND UPID LIKE '%"+likeid+",%' ";

			conn=DBConnection.getConnection();
			System.out.println(" (1/6) getlifeBbsList Success");
			psmt = conn.prepareStatement(sql);
			
			System.out.println(" (2/6) getlifeBbsList Success");
				
		
			
			rs = psmt.executeQuery();
			System.out.println(" (3/6) getlifeBbsList Success");
			while(rs.next()){
				
				LifeBbsDto dto = new LifeBbsDto
						(rs.getInt(1),
						rs.getString(2),
						rs.getInt(3),
						rs.getInt(4),
						rs.getInt(5),
						rs.getString(6),
						rs.getString(7),
						rs.getString(8),
						rs.getString(9),
						rs.getInt(10),
						rs.getString(11),
						rs.getString(12),
						rs.getString(13),
						rs.getInt(14),
						rs.getInt(15),
						rs.getInt(16),
						rs.getInt(17),
						rs.getInt(18)
						);
		// seq, id, title, content, wdate, del, readcount, likecount, scrapcount)
				list.add(dto);
			}	
			System.out.println(" (4/6) getlifeBbsList Success");
			
		} catch (SQLException e) {
			System.out.println("getlifeBbsList fail");
			e.printStackTrace();
		}finally {
			DBClose.close(psmt, conn, rs);
		}
		
		return list;
	}
	@Override
	public List<newbbs5HWCodingVO> getlikeallbbs5BbsList(String likeid) {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		List<newbbs5HWCodingVO> list=new ArrayList<newbbs5HWCodingVO>();
		int totalCount = 0;
		try {
		String sql = " SELECT * FROM newbbs5HWCodingVO "
				+ "  WHERE DEL=0 AND LIKEID LIKE '%"+likeid+"-%' ";

		
			conn=DBConnection.getConnection();
			System.out.println(" (1/6) getbbs5BbsList Success");
			psmt = conn.prepareStatement(sql);
			
			System.out.println(" (2/6) getbbs5BbsList Success");
				
		
			
			rs = psmt.executeQuery();
			System.out.println(" (3/6) getbbs5BbsList Success");
			while(rs.next()){
				
				newbbs5HWCodingVO dto = new newbbs5HWCodingVO
						(rs.getInt(1),
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
			System.out.println(" (4/6) getbbs5BbsList Success");
			
		} catch (SQLException e) {
			System.out.println("getbbs5BbsList fail");
			e.printStackTrace();
		}finally {
			DBClose.close(psmt, conn, rs);
		}
		
		return list;
	}



	
	
}

