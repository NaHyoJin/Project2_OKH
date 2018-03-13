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
		}else if(search == 1) {	// tagname
			sWord = " WHERE TAGNAME='" + searchWord.trim() + "' ";
		}else if(search == 2) {	// 내용
			sWord = " WHERE CONTENT LIKE '%" + searchWord.trim() + "%' ";
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
						+ "WHERE ROWNUM <=" + paging.getCountPerPage();
			
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
						rs.getInt(11)
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
		String sql = " SELECT SEQ, ID, TITLE,TAGNAME, CONTENT, WDATE, DEL, READCOUNT, LIKECOUNT,COMMENTCOUNT, SCRAPCOUNT "
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
						rs.getInt(11)
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
				+ " DEL, READCOUNT,LIKECOUNT,COMMENTCOUNT,SCRAPCOUNT ) "
				+ " VALUES(SEQ_TECHBBS.NEXTVAL, ?, ?, ?,?, "
				+ " SYSDATE, 0,0,0,0,0) ";
		
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


}
