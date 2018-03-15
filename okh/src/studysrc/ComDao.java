package studysrc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import db.DBClose;
import db.DBConnection;

public class ComDao implements iComDao {
	
	
	@Override
	public List<comment_bbsDto> detailbbs(int seq) {
	String sql =" SELECT * FROM COMBBS B,SCOMMENT S  "
				+" WHERE B.PARENT=S.CHILD AND B.SEQ=? AND B.SEQ=S.CHILD ";
	comment_bbsDto dto = null;
	List<comment_bbsDto> list = new ArrayList<comment_bbsDto>();
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			conn = DBConnection.getConnection();
			System.out.println("detailbbs 1/6 S");
			psmt= conn.prepareStatement(sql);
			System.out.println("detailbbs 2/6 S");
			psmt.setInt(1, seq);
		
			rs = psmt.executeQuery();
			System.out.println("detailbbs 3/6 S");
			
			while(rs.next()) {
				int i = 1;
						dto = new comment_bbsDto(
								rs.getInt(i++),//	bbsseq,
									rs.getString(i++),//bbsid, 
									rs.getString(i++),//bbstitle, 
									rs.getString(i++),//bbscontent,
									rs.getString(i++),//bbswdate, 
									rs.getInt(i++),//bbsdel,
									rs.getInt(i++),//bbsreadcount, 
									rs.getInt(i++),//bbscommentcount,
									rs.getString(i++),//bbstagname, 
									rs.getInt(i++),//bbsparent, 
									rs.getInt(i++),//bbsjoinercount, 
									rs.getString(i++),//bbsjoindate, 
									rs.getInt(i++),//commentseq, 
									rs.getString(i++),//commentid, 
									rs.getString(i++),//commentcontent, 
									rs.getString(i++),//commentwdate, 
									rs.getInt(i++),//commentjoiner, 
									rs.getInt(i++),//commentchild, 
									rs.getInt(i++));//commentdel);
				list.add(dto);
				
			}
			
			System.out.println("detailbbs 4/6 S");
		} catch (SQLException e) {
			System.out.println("detailbbs F");
			e.printStackTrace();
		}finally {
			DBClose.close(psmt, conn, rs);
		}
		
		
		return list;
	
	}

	/*
 	private int seq;		//시퀀스
	private String id;		// 아이디
	private String title;	//제목
	private String content;	//내용
	private String wdate;	//작성일
	private int del;	//삭제 0,1
	private int readcount;	//조회수
	private int commentcount;		//댓글수 
	private String tagname;	//지역테그
	private int parent;		//부모글
	private int joinercount;	// 좋아요
	private String joindate;	// 모임날
				 */
	@Override
	public List<CombbsDto> getComList() {
		String sql = " SELECT SEQ, ID, "
				+ " TITLE, CONTENT, WDATE, DEL,"
				+ " READCOUNT, COMMENTCOUNT, TAGNAME, PARENT, JOINERCOUNT, JOINDATE"
				+ " FROM COMBBS "
				+ " ORDER BY SEQ DESC ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		List<CombbsDto> list = new ArrayList<CombbsDto>();
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("getComList 1/6 S");
			psmt= conn.prepareStatement(sql);
			System.out.println("getComList 2/6 S");
			rs = psmt.executeQuery();
			System.out.println("getComList 3/6 S");
			
			while(rs.next()) {
				CombbsDto dto = new CombbsDto(
										rs.getInt(1),//	int seq;
										rs.getString(2),//	String id;
										rs.getString(3),//String title;
										rs.getString(4),//	String content
										rs.getString(5),//	String wdate;
										rs.getInt(6),//	int del
										rs.getInt(7),//	int readcount;
										rs.getInt(8),//	int commentcount,
										rs.getString(9),//  String tagname;
										rs.getInt(10),//	int parent;
										rs.getInt(11),//	int joinercount;
										rs.getString(12));		//	String joindate;
				list.add(dto);
				/*
 	private int seq;		//시퀀스
	private String id;		// 아이디
	private String title;	//제목
	private String content;	//내용
	private String wdate;	//작성일
	private int del;	//삭제 0,1
	private int readcount;	//조회수
	private int commentcount;		//댓글수 
	private String tagname;	//지역테그
	private int parent;		//부모글
	private int joinercount;	// 좋아요
	private String joindate;	// 모임날
				 */
			}
			System.out.println("getComList 4/6 S");
		} catch (SQLException e) {
			System.out.println("getComList F");
			e.printStackTrace();
		}finally {
			DBClose.close(psmt, conn, rs);
		}
		
		
		return list;
	}
	
	@Override
	public void readcount(int seq) {
		String sql = " UPDATE COMBBS "
				+ " SET READCOUNT=READCOUNT+1 "
				+ " WHERE SEQ=? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 readcount Success");
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);
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
public boolean writeBbs(CombbsDto dto) {
	/*
	 SEQ NUMBER(8) PRIMARY KEY,
	ID VARCHAR2(50) NOT NULL,
	TITLE VARCHAR2(200) NOT NULL,
	CONTENT VARCHAR2(4000) NOT NULL,
	WDATE DATE NOT NULL,
	DEL NUMBER(1) NOT NULL,
	READCOUNT NUMBER(8) NOT NULL,
	COMMENTCOUNT NUMBER(8) NOT NULL,
	TAGNAME VARCHAR2(50) NOT NULL,
	PARENT NUMBER(8) NOT NULL,
	JOINERCOUNT NUMBER(8),
	JOINDATE VARCHAR2 (50) NOT NULL 
	 */
	String sql = " INSERT INTO COMBBS(SEQ, ID, "
			+ " TITLE, TAGNAME, CONTENT,JOINDATE,WDATE, "
			+ " DEL, READCOUNT,JOINERCOUNT,COMMENTCOUNT,PARENT ) "
			+ " VALUES(SEQ_COMBBS.NEXTVAL, ?, ?, ?,?,?, "
			+ " SYSDATE, 0,0,0,0,SEQ_COMBBS.NEXTVAL) ";
	
	Connection conn = null;
	PreparedStatement psmt = null;
	
	int count = 0;
	
	try {
		conn = DBConnection.getConnection();			
		System.out.println("1/6 writeBbs Success");
		
		psmt = conn.prepareStatement(sql);
		System.out.println("2/6 writeBbs Success");
		
		psmt.setString(1, dto.getId());
		psmt.setString(2, dto.getTitle());
		psmt.setString(3, dto.getTagname());
		psmt.setString(4, dto.getContent());
		psmt.setString(5, dto.getJoindate());
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
