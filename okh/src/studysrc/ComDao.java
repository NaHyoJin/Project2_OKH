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
	public boolean checkjoiner(int seq) {
		String sql = " SELECT JOINERCOUNT FROM COMBBS "
				+ " WHERE SEQ=? ";
		Connection conn=null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		int joinercount=0;
		try {
			conn = DBConnection.getConnection();	
			psmt=conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			rs = psmt.executeQuery();
			System.out.println("2/6 checkcomment Success");
			
			rs.next();
			joinercount = rs.getInt(1);	// row의 총 갯수
			
		} catch (SQLException e) {
			System.out.println(" checkcomment fail");	
			e.printStackTrace();
		}finally{
			
			DBClose.close(psmt, conn, rs);	
		}		
		return joinercount>0?true:false;
	}

	
	@Override
	public boolean getparent(int seq) {
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
								int bbsseq = rs.getInt(1);//	bbsseq,
									String bbsid = rs.getString(2);//bbsid, 
									String bbstitle = rs.getString(3);//bbstitle, 
									String bbscontent = rs.getString(3);//bbscontent,
									String bbswdate = rs.getString(4);//bbswdate, 
									int bbsdel = rs.getInt(5);//bbsdel,
									int bbsreadcount = rs.getInt(6);//bbsreadcount, 
									int bbscommentcount = rs.getInt(7);//bbscommentcount,
									String bbstagname = rs.getString(8);//bbstagname, 
									int bbsparent = rs.getInt(9);//bbsparent, 
									int bbsjoinercount = rs.getInt(10);//bbsjoinercount, 
									String bbsjoindate = rs.getString(11);//bbsjoindate,
									String bbsjoinner = rs.getString(12); //bbsjoiner
									int commentseq = rs.getInt(13);//commentseq, 
									String commentid = rs.getString(14);//commentid, 
									String commentcontent=rs.getString(15);//commentcontent, 
									String commentwdate=rs.getString(16);//commentwdate, 
									int commentdel = rs.getInt(17);//commentdel, 
									int commentchild=rs.getInt(18);//commentchild);
						//seq id title content wdate del readcount commentcount tagname parent joindate joiner
						// seq id content wdate del child
						
						int commentinorout=0;
						commentinorout=dto!=null?1:2;
						
						dto = new comment_bbsDto(bbsseq, bbsid, bbstitle, bbscontent, bbswdate, bbsdel, bbsreadcount, bbscommentcount, bbstagname, bbsparent, bbsjoinercount, bbsjoindate, bbsjoinner, commentseq, commentid, commentcontent, commentwdate, commentdel, commentchild, commentinorout);
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
		return dto!=null?true:false;
	
	}
	
	@Override
	public boolean checkcomment(int seq) {
		String sql = " SELECT COMMENTCOUNT FROM COMBBS "
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
			System.out.println("2/6 checkcomment Success");
			
			rs.next();
			commentcount = rs.getInt(1);	// row의 총 갯수
			
		} catch (SQLException e) {
			System.out.println(" checkcomment fail");	
			e.printStackTrace();
		}finally{
			
			DBClose.close(psmt, conn, rs);	
		}		
		return commentcount>0?true:false;
	}

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
	public void commentdiscount(int seq) {
		String sql = " UPDATE COMBBS "
				+ " SET COMMENTCOUNT=COMMENTCOUNT-1 "
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
		String sql = " SELECT ID, TITLE, CONTENT, TAGNAME, JOINDATE, WDATE,COMMENTCOUNT,READCOUNT,JOINERCOUNT FROM COMBBS WHERE SEQ = ? ";
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
									rs.getString(i++),	//String joindate)		
									rs.getString(i++),
									rs.getInt(i++),
									rs.getInt(i++),
									rs.getInt(i++));				
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
				+" WHERE B.PARENT=S.CHILD AND B.SEQ=? AND B.SEQ=S.CHILD AND S.DEL = 0 AND B.DEL = 0 ";
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
									rs.getString(i++), //bbsjoiner
									rs.getInt(i++),//commentseq, 
									rs.getString(i++),//commentid, 
									rs.getString(i++),//commentcontent, 
									rs.getString(i++),//commentwdate, 
									rs.getInt(i++),//commentdel, 
									rs.getInt(i++));//commentchild);
						//seq id title content wdate del readcount commentcount tagname parent joindate joiner
						// seq id content wdate del child
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
				+ " READCOUNT, COMMENTCOUNT, TAGNAME, PARENT, JOINERCOUNT, JOINDATE, JOINNER"
				+ " FROM COMBBS "
				+ " WHERE DEL = 0 ORDER BY SEQ DESC ";
		
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
										rs.getString(12),
										rs.getString(13));		//	String joindate;
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
public int getSeq() {
	String sql = " SELECT MAX(SEQ) "
			+ " FROM COMBBS ";
	
	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;
	
	int seq=0;
	try {
		conn=DBConnection.getConnection();
		System.out.println("1/6 getSeq Success");
		psmt=conn.prepareStatement(sql);
		System.out.println("2/6 getSeq Success");
		rs = psmt.executeQuery();
		rs.next();
		System.out.println("3/6 getSeq Success");
		seq = rs.getInt(1);
		System.out.println("4/6 getSeq Success"+seq);
	} catch (SQLException e) {
		System.out.println("getSeq fail");
		e.printStackTrace();
	}finally {
		DBClose.close(psmt, conn, null);			
	}
	
	
	return seq;
}

	
@Override
public boolean writeBbs(CombbsDto dto) {
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
	private int joinercount;	// 좋아요카운트
	private String joindate;	// 모임날
	private String joinner;	// 좋아요 누른사람
	 */
	String sql = " INSERT INTO COMBBS(SEQ, ID, "
			+ " TITLE, TAGNAME, CONTENT,JOINDATE,WDATE, "
			+ " DEL, READCOUNT,JOINERCOUNT,COMMENTCOUNT,PARENT,JOINNER ) "
			+ " VALUES(SEQ_COMBBS.NEXTVAL, ?, ?, ?,?,?, "
			+ " SYSDATE, 0,0,0,0,SEQ_COMBBS.NEXTVAL,?) ";
	
	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;
	int count = 0;
	
	try {
		conn = DBConnection.getConnection();			
		
		
		psmt = conn.prepareStatement(sql);
		System.out.println("2/6 writeBbs Success");
		System.out.println(dto.getParent()+"asdsadcvkjj부모");
		psmt.setString(1, dto.getId());
		psmt.setString(2, dto.getTitle());
		psmt.setString(3, dto.getTagname());
		psmt.setString(4, dto.getContent());
		psmt.setString(5, dto.getJoindate());
		psmt.setString(6, "-admin");
		
		
		
		
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
		String totalSql = " SELECT COUNT(SEQ) FROM COMBBS " + sWord + " AND DEL=0 ";
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
				+ " WHERE ROWNUM <=" + paging.getCountPerPage() +" AND DEL=0 ";
		
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

@Override
public void likecountplus(int seq) {
	String sql = " UPDATE COMBBS SET JOINERCOUNT=JOINERCOUNT+1 "
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
	String sql = " UPDATE COMBBS SET JOINERCOUNT=JOINERCOUNT-1 "
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
public boolean writecalendar(CombbsDto dto,int child) {
	
	String sql = " INSERT INTO CALENDAR(SEQ, ID, TITLE, CONTENT, RDATE, WDATE,CHILD) "
			+ " VALUES(SEQ_CAL.NEXTVAL, ?, "
			+ " ?, ?, ?, SYSDATE,?) ";
			
	
	Connection conn = null;
	PreparedStatement psmt = null;
	
	int count = 0;
	
	try {
		conn = DBConnection.getConnection();			
		System.out.println("1/6 writecalendar Success");
		
		psmt = conn.prepareStatement(sql);
		System.out.println("2/6 writecalendar Success");
		
		psmt.setString(1, dto.getId());
		psmt.setString(2, dto.getTitle());
		psmt.setString(3, dto.getContent());
		psmt.setString(4, dto.getJoindate());
		psmt.setInt(5, child);
		count = psmt.executeUpdate();
		
		System.out.println("3/6 writecalendar Success");
		
	} catch (SQLException e) {
		System.out.println("writecalendar fail");
		e.printStackTrace();
	} finally {
		DBClose.close(psmt, conn, null);			
	}
	
	return count>0?true:false;
}
	






}
