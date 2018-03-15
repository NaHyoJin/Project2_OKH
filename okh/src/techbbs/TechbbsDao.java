package techbbs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import db.DBConnection;
import db.DBClose;

public class TechbbsDao implements iTechbbsDao {

	@Override
	public List<TechbbsDto> gettechBbsPagingList(PagingBean paging, String searchWord, int search) {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		List<TechbbsDto> bbslist = new ArrayList<TechbbsDto>();
		
		System.out.println("search = " + search);
		
		String sWord = "";		
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
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 gettechBbsPagingList Success");
			
			// 글의 총수
			String totalSql = " SELECT COUNT(SEQ) FROM TECHBBS " + sWord;
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
			
			// row를 취득
			String sql = " SELECT * FROM "
						+ " (SELECT * FROM (SELECT * FROM TECHBBS " + sWord + " ORDER BY SEQ ASC)"
						+ "  WHERE ROWNUM <=" + paging.getStartNum() + " ORDER BY SEQ DESC) "
						+ " WHERE ROWNUM <=" + paging.getCountPerPage()+" AND DEL=0 ";
			
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
						rs.getInt(10),
						rs.getInt(11),
						rs.getInt(12)
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
		String sql = " SELECT SEQ, ID, TITLE,TAGNAME, CONTENT, WDATE, DEL, READCOUNT, LIKECOUNT,COMMENTCOUNT,POINT, SCRAPCOUNT "
				+ " FROM TECHBBS "
				+ " ORDER BY SEQ DESC";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		List<TechbbsDto> list=new ArrayList<TechbbsDto>();
		
		try {
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
						rs.getInt(10),
						rs.getInt(11),
						rs.getInt(12)
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
				+ " DEL, READCOUNT,LIKECOUNT,COMMENTCOUNT,POINT,SCRAPCOUNT ) "
				+ " VALUES(SEQ_TECHBBS.NEXTVAL, ?, ?, ?,?, "
				+ " SYSDATE, 0,0,0,0,0,0) ";
		
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
				+ " READCOUNT,LIKECOUNT,COMMENTCOUNT,POINT,SCRAPCOUNT "
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
			int commentcount = rs.getInt(10);
			int point = rs.getInt(11);
			int scrapcount = rs.getInt(12);
			int pdsyn=2;
			
			dto = new TechbbsDto(seq1, id, title, tagname, content, wdate,del, readcount, commentcount, point, likecount, scrapcount,pdsyn);
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
				+ " B.READCOUNT,B.LIKECOUNT,B.COMMENTCOUNT,B.POINT,B.SCRAPCOUNT,P.SEQ,P.FILENAME,P.PARENT "
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
				int commentcount = rs.getInt(9);
				int point = rs.getInt(10);
				int scrapcount = rs.getInt(11);
				int pdsseq=rs.getInt(12);
				String filename = rs.getString(13);	
				int parent=rs.getInt(14);
				int pdsyn=1;
				
				dto = new TechbbsDto(seq1, id, title, tagname, content, wdate, readcount, commentcount, point, likecount, scrapcount, filename, parent, pdsseq,pdsyn);
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
				+ " B.READCOUNT,B.LIKECOUNT,B.COMMENTCOUNT,B.POINT,B.SCRAPCOUNT,P.SEQ,P.FILENAME,P.PARENT "
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
				int commentcount = rs.getInt(9);
				int point = rs.getInt(10);
				int scrapcount = rs.getInt(11);
				int pdsseq=rs.getInt(12);
				String filename = rs.getString(13);	
				int parent=rs.getInt(14);
				int pdsyn=0;
				pdsyn=dto!=null?1:2;	//자료있으면 1 자료없으면2
				
				dto = new TechbbsDto(seq1, id, title, tagname, content, wdate, readcount, commentcount, point, likecount, scrapcount, filename, parent, pdsseq,pdsyn);
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
	public void countplus(int seq,String whatcount) {
		String sql = " UPDATE TECHBBS SET ?=?+1 "
				+ " WHERE SEQ=? ";
		Connection conn=null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		
		try {
			conn = DBConnection.getConnection();	
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, whatcount);
			psmt.setString(2, whatcount);
			psmt.setInt(3, seq);
			
			psmt.executeUpdate();			
			
		} catch (SQLException e) {
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
			
			psmt.executeUpdate();			
			
		} catch (SQLException e) {
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
	
	
}

