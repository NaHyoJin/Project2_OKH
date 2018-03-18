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
	public void commentcount(int seq) {
		String sql = " UPDATE COMBBS "
				+ " SET COMMENTCOUNT=COMMENTCOUNT+1 "
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
			e.printStackTrace();
			System.out.println("readcount Fail");
		} finally {
			DBClose.close(psmt, conn, null);			
		}		
	}
	
	
	
	@Override
	public List<CombbsDto> commentnull(int seq) {
		String sql = " SELECT ID, TITLE, CONTENT, TAGNAME, JOINDATE FROM COMBBS WHERE SEQ = ? ";
		CombbsDto dto = null;
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		List<CombbsDto> list = new ArrayList<CombbsDto>();
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
		
				dto = new CombbsDto(rs.getString(i++),//id,
									rs.getString(i++),//title,
									rs.getString(i++),	//String content,			
									rs.getString(i++),	//String tagname,	
									rs.getString(i++));	//String joindate)		
													
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

	@Override
	public List<comment_bbsDto> detailbbs(int seq) {
		System.out.println("seq?"+seq);
		
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
				
						System.out.println("dto?" +dto);
						
						list.add(dto);
				
			}
			
			System.out.println("detailbbs 4/6 S");
		} catch (SQLException e) {
			System.out.println("detailbbs F");
			e.printStackTrace();
		}finally {
			DBClose.close(psmt, conn, rs);
		}
		System.out.println("listdel?:"+list.get(0).getCommentdel());
		
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
	
	String sql = " INSERT INTO CALENDAR(SEQ, ID, TITLE, CONTENT, RDATE, WDATE) "
				+ " VALUES(SEQ_CAL.NEXTVAL, ?, "
				+ " ?, ?, ?, SYSDATE) ";
	
	
	
	 */
	String sql = " INSERT INTO COMBBS(SEQ, ID, "
			+ " TITLE, TAGNAME, CONTENT,JOINDATE,WDATE, "
			+ " DEL, READCOUNT,JOINERCOUNT,COMMENTCOUNT,PARENT ) "
			+ " VALUES(SEQ_COMBBS.NEXTVAL, ?, ?, ?,?,?, "
			+ " SYSDATE, 0,0,0,0,SEQ_COMBBS.NEXTVAL) ";
	
	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;
	int count = 0;
	
	try {
		conn = DBConnection.getConnection();			
		
		
		String sql1 = " INSERT INTO CALENDAR(SEQ, ID, TITLE, CONTENT, RDATE, WDATE) "
				+ " VALUES(SEQ_CAL.NEXTVAL, ?, "
				+ " ?, ?, ?, SYSDATE) ";
		psmt = conn.prepareStatement(sql1);
		System.out.println("1/6 cal success");
		psmt.setString(1, dto.getId());
		psmt.setString(2, dto.getTitle());
		psmt.setString(3, dto.getContent());
		psmt.setString(4, dto.getJoindate());
		rs = psmt.executeQuery();
		psmt.close();
		rs.close();
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
		DBClose.close(psmt, conn, rs);			
	}
	
	return count>0?true:false;
}

@Override
public List<CombbsDto> getpagingComList(PagingBean paging, String searchWord, int search) {
	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;
	
	List<CombbsDto> list = new ArrayList<CombbsDto>();
	
	System.out.println("searchWord=" +searchWord+"  search = " + search);
	
	String sWord = "";		
	if(search == 0) {	// 제목
		sWord = " WHERE TITLE LIKE '%" + searchWord.trim() + "%' ";
	}else if(search == 1) {	// 작성자
		sWord = " WHERE ID=' " + searchWord.trim() + " ' ";
	}else if(search == 2) {	// 내용
		sWord = " WHERE = CONTENT LIKE '%" +searchWord.trim() +"%' ";
	} 
	
	try {
		conn = DBConnection.getConnection();
		System.out.println("1/6 gettechBbsPagingList Success");
		
		// 글의 총수
		String totalSql = " SELECT COUNT(SEQ) FROM COMBBS " + sWord;
		psmt = conn.prepareStatement(totalSql);
		rs = psmt.executeQuery();
		System.out.println("2/6 gettechBbsPagingList Success");
		
		int totalCount = 0;
		rs.next();
		totalCount = rs.getInt(1);	// row의 총 갯수
		System.out.println("total:" +totalCount);
		paging.setTotalCount(totalCount);
		
		paging = PagingUtil.setPagingInfo(paging);
		
		psmt.close();
		rs.close();
		
		// row를 취득
		String sql = " SELECT * FROM "
				+ " (SELECT * FROM (SELECT * FROM COMBBS " + sWord + " ORDER BY SEQ ASC) "
				+ "  WHERE ROWNUM <=" + paging.getStartNum() + " ORDER BY SEQ DESC) "
				+ " WHERE ROWNUM <=" + paging.getCountPerPage();
		
		System.out.println("paging.getStartNum() = " + paging.getStartNum());
		
		psmt = conn.prepareStatement(sql);
		System.out.println("3/6 getpagingComList Success");
		
		rs = psmt.executeQuery();
		System.out.println("4/6 getpagingComList Success");
		
		while(rs.next()) {
			CombbsDto dto = new CombbsDto(rs.getInt(1),//	int seq;
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
		}
		System.out.println("5/6 getpagingComList Success");			
		
	} catch (SQLException e) {
		e.printStackTrace();
		System.out.println("getpagingComList Fail");
	} finally {
		DBClose.close(psmt, conn, rs);
		System.out.println("6/6 getpagingComList Success");	
	}
	
	return list;
}



@Override
public void updatebbs(CombbsDto dto, int seq) {
	
	String sql = " UPDATE COMBBS SET "
			+ " TITLE = ?, CONTENT = ?, TAGNAME = ?, JOINDATE = ? WHERE SEQ = ? ";
	
	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;
	try {
		conn = DBConnection.getConnection();
		System.out.println("1/6 updatebbs Success");
		String sql1 = " UPDATE CALENDAR SET "
				+ " TITLE = ?, CONTENT = ?, RDATE = ? WHERE SEQ=? ";
		psmt = conn.prepareStatement(sql1);
		System.out.println("1/6 cal success");
		psmt.setString(1, dto.getTitle());
		psmt.setString(2, dto.getContent());
		psmt.setString(3, dto.getJoindate());
		psmt.setInt(4, seq);
		rs = psmt.executeQuery();
		psmt.close();
		rs.close();
		
		
		
		psmt = conn.prepareStatement(sql);
		psmt.setString(1, dto.getTitle());
		psmt.setString(2, dto.getContent());
		psmt.setString(3, dto.getTagname());
		psmt.setString(4, dto.getJoindate());
		psmt.setInt(5, seq);
		System.out.println("2/6 updatebbs Success");
		
		psmt.executeUpdate();
		System.out.println("3/6 updatebbs Success");
		
	} catch (SQLException e) {
		e.printStackTrace();
		System.out.println("updatebbs Fail");
	} finally {
		DBClose.close(psmt, conn, rs);			
	}		
}



@Override
public void delbbs(int seq) {
	String sql = " UPDATE COMBBS SET "
			+ " DEL = 1 WHERE SEQ = ? ";
	
	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;
	try {
		conn = DBConnection.getConnection();
		System.out.println("1/6 updatebbs Success");
		
		String sql1 = " DELETE FROM CALENDAR WHERE SEQ=?  ";
		psmt = conn.prepareStatement(sql1);
		System.out.println("1/6 cal success");
		
		psmt.setInt(1, seq);
		rs = psmt.executeQuery();
		psmt.close();
		rs.close();
		
		
		
		psmt = conn.prepareStatement(sql);
		
		psmt.setInt(1, seq);
		System.out.println("2/6 updatebbs Success");
		
		psmt.executeUpdate();
		System.out.println("3/6 updatebbs Success");
		
	} catch (SQLException e) {
		e.printStackTrace();
		System.out.println("updatebbs Fail");
	} finally {
		DBClose.close(psmt, conn, rs);			
	}		
}
	
	






}
