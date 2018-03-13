package qna;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



import db.DBClose;
import db.DBConnection;


public class QnaBbsDao implements QnaBbsDaoImpl {

	/*private static qnaBbsDao qNaDao = new qnaBbsDao();
	
	private qnaBbsDao() {}
	
	public static qnaBbsDao getInstance() {
		return qNaDao;
	}*/
	
	/*@Override
	public List<QnaDto> getQnaPagingList(PagingBean paging, String searchWord, int search) {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		List<QnaDto> list = new ArrayList<QnaDto>();
		
		System.out.println("search = " + search);
		
		String sWord = "";		
		if(search == 0) {	// 제목
			sWord = " WHERE TITLE LIKE '%" + searchWord.trim() + "%' ";
		}else if(search == 1) {	// 작성자
			sWord = " WHERE ID='" + searchWord.trim() + "' ";
		}else if(search == 2) {	// 내용
			
		} 
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 getBbsPagingList Success");
			
			// 글의 총수알아내고 짤라주는 작업
			String totalSql = " SELECT COUNT(SEQ) FROM BBS " + sWord;
			psmt = conn.prepareStatement(totalSql);
			rs = psmt.executeQuery();
			System.out.println("2/6 getBbsPagingList Success");
			
			int totalCount = 0;
			// 1나만 구하기때문에
			rs.next();
			totalCount = rs.getInt(1);	// row의 총 갯수
			
			paging.setTotalCount(totalCount);			// total count들어가고
			paging = PagingUtil.setPagingInfo(paging);	// 계산한수치가 나온다
			
			
			psmt.close();
			rs.close();
			
			// row를 취득
			// REF 정순으로 STEP을 역순으로
			// REF 역순 STEP 정순
			// 시작위치를 정해주고 갯수10개(paging.getCountPerPage())를 가져온다
			String sql = " SELECT * FROM "
						+ " (SELECT * FROM (SELECT * FROM BBS " + sWord + " ORDER BY REF ASC, STEP DESC)"
						+ "  WHERE ROWNUM <=" + paging.getStartNum() + " ORDER BY REF DESC, STEP ASC) "
						+ "WHERE ROWNUM <=" + paging.getCountPerPage();
			
			System.out.println("paging.getStartNum() = " + paging.getStartNum());
			
			psmt = conn.prepareStatement(sql);
			System.out.println("3/6 getBbsPagingList Success");
			
			rs = psmt.executeQuery();
			System.out.println("4/6 getBbsPagingList Success");
			
			while(rs.next()) {	// 하나의 자료만 가져오므로 if로 해도된다
				QnaDto dto = new QnaDto(
						rs.getInt(1),		//seq,
						rs.getString(2),	//id,
						rs.getInt(3),		//ref,
						rs.getInt(4),		//step,
						rs.getInt(5),		//depth,
						rs.getString(6),	//title,
						rs.getString(7),	//content,
						rs.getString(8),	// tag
						rs.getString(9),	//wdate,
						rs.getInt(10),		//parent,
						rs.getInt(11),		//del,
						rs.getInt(12),		//readcount,
						rs.getInt(13),		//favor,
						rs.getInt(14),		//lvpoint,
						rs.getInt(15));		//answercount	
						
				list.add(dto);				
			}
			System.out.println("5/6 getBbsPagingList Success");			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("getBbsPagingList Fail");
		} finally {
			DBclose.close(psmt, conn, rs);
			System.out.println("6/6 getBbsPagingList Success");	
		}
		
		return list;
	}
	*/
	
	@Override
	public List<QnaDto> getBbsPagingList(PagingBean paging) {
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		List<QnaDto> bbslist = new ArrayList<QnaDto>();
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 getBbsPagingList Success");
			
			// 글의 총수알고 짤라주는 작업
			String totalSql = " SELECT COUNT(SEQ) FROM QNA ";
			psmt = conn.prepareStatement(totalSql);
			rs = psmt.executeQuery();	
			System.out.println("2/6 getBbsPagingList Success");
			
			int totalCount = 0;
			//1나만 구하므로
			rs.next();
			totalCount = rs.getInt(1); 	// row의 총 갯수
			
			paging.setTotalCount(totalCount);		// total count들어가고
			paging = PagingUtil.setPagingInfo(paging);	// 계산한수치가 나온다
			
			psmt.close();
			rs.close();
			
			// row를 취득부분 댓글과 같이 정순으로 가져온다음 엎어야한다
			// REF 정순으로 STEP을 역순으로
			// REF 역순 STEP 정순
			// 시작위치를 정해주고 갯수10개(paging.getCountPerPage())를 가져온다
			String sql = " SELECT * FROM "
					+ " (SELECT * FROM (SELECT*FROM QNA ORDER BY REF ASC, STEP DESC)"
					+ " WHERE ROWNUM <=" +paging.getStartNum() + " ORDER BY REF DESC, STEP ASC) "
					+ " WHERE ROWNUM <=" + paging.getCountPerPage();
			psmt = conn.prepareStatement(sql);
			System.out.println("3/6 getBbsPagingList Success");
			
			rs = psmt.executeQuery();
			System.out.println("4/6 getBbsPagingList Success");
			
			while (rs.next()) {							// 하나만 갖고오므로 while말고 조건문if한다
				int i = 1;
				QnaDto dto = new QnaDto(rs.getInt(1),		//seq,
						rs.getString(2),	//id,
						rs.getInt(3),		//ref,
						rs.getInt(4),		//step,
						rs.getInt(5),		//depth,
						rs.getString(6),	//title,
						rs.getString(7),	//content,
						rs.getString(8),	// tag
						rs.getString(9),	//wdate,
						rs.getInt(10),		//parent,
						rs.getInt(11),		//del,
						rs.getInt(12),		//readcount,
						rs.getInt(13),		//favor,
						rs.getInt(14),		//lvpoint)
						rs.getInt(15));		//answercount
				
				bbslist.add(dto);				
			}	
			System.out.println("5/6 getBbsPagingList Success");
			
		} catch (SQLException e) {
			System.out.println("getBbsPagingList Fail");
			e.printStackTrace();
		}finally {
			DBClose.close(psmt, conn, rs);
			System.out.println("6/6 getBbsPagingList Success");
		}
		
		return bbslist;
	}
	
	
	
	@Override
	public boolean writeQnaBbs(QnaDto dto) {
		
		String sql = " INSERT INTO QNA"
				+ " (SEQ, ID, REF, STEP, DEPTH, "
				+ " TITLE, CONTENT, TAG, WDATE, PARENT,"
				+ " DEL, READCOUNT, FAVOR, LVPOINT, ANSWERCOUNT) "
				+ " VALUES"
				+ " (SEQ_QNA.NEXTVAL, ?, (SELECT NVL(MAX(REF),0)+1 FROM QNA), 0, 0, "
				+ " ?, ?, ?, SYSDATE, 0, "
				+ " 0, 0, 0, 0, 0) ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 writeQnaBbs Success");
			
			psmt = conn.prepareStatement(sql);
			System.out.println("2/6 writeQnaBbs Success");
			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getTitle());
			psmt.setString(3, dto.getContent());
			psmt.setString(4,dto.getTag());
					
			count = psmt.executeUpdate();
			System.out.println("3/6 writeQnaBbs Success");
			
		} catch (SQLException e) {
			System.out.println("writeQnaBbs Fail");
			e.printStackTrace();
		}finally {
			System.out.println("4/6 writeQnaBbs Success");
			DBClose.close(psmt, conn, null);
		}
					
		return count>0?true:false;
	}	
	

	@Override
	public List<QnaDto> getQnaList() {
		
		String sql = " SELECT SEQ, ID, REF, STEP, DEPTH, TITLE, CONTENT, TAG, WDATE, PARENT, "
				+ " DEL, READCOUNT, FAVOR, LVPOINT, ANSWERCOUNT "
				+ " FROM QNA "
				+ " ORDER BY REF DESC, STEP ASC";
		
		List<QnaDto> list = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 getQnaList Success");
			
			psmt = conn.prepareStatement(sql);
			System.out.println("2/6 getQnaList Success");
			
			rs = psmt.executeQuery();
			System.out.println("3/6 getQnaList Success");
			
			while(rs.next()) {
				QnaDto dto = new QnaDto(
					rs.getInt(1),		//seq,
					rs.getString(2),	//id,
					rs.getInt(3),		//ref,
					rs.getInt(4),		//step,
					rs.getInt(5),		//depth,
					rs.getString(6),	//title,
					rs.getString(7),	//content,
					rs.getString(8),	// tag
					rs.getString(9),	//wdate,
					rs.getInt(10),		//parent,
					rs.getInt(11),		//del,
					rs.getInt(12),		//readcount,
					rs.getInt(13),		//favor,
					rs.getInt(14),		//lvpoint)
					rs.getInt(15));		//answercount
				list.add(dto);
				
			}
			System.out.println("4/6 getQnaList Success");
			
		} catch (SQLException e) {
			System.out.println("getQnaList Fail");
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
			System.out.println("5/6 getQnaList Success");
		}
		return list;
	}








	
	
	
}
