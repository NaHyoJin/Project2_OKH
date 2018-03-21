package qna;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBClose;
import db.DBConnection;

//QnaAnswerDto(seq, id, content, wdate, child, del, likecount)
public class QnaAnswerDao implements QnaAnswerDaoImpl {

	@Override
	public boolean writeAnswer(QnaDto dto, int seq) {
		
		/*String sql2 = " UPDATE QNA "
				+ " SET DEPTH = DEPTH+1 "
				+ " WHERE SEQ = (SELECT CHILD FROM QNAANSWER WHERE CHILD=? )";
		
		
		
		String sql1 = " INSERT INTO QNAANSWER "
				+ " (SEQ, ID, "
				+ " COMMENT_NUM,"
				+ " CONTENT, WDATE, "
				+ " CHILD, DEL, LIKECOUNT)"
				+ " VALUES"
				+ " (SEQ_QNAANSWER.NEXTVAL, ?,"
				+ " (SELECT DEPTH FROM QNA WHERE SEQ=? )+1 "
				+ " , ?, SYSDATE, "
				+ " ?, 0, 0)";*/
/*	
		String sql1 = " UPDATE QNA "
				+ " SET STEP=STEP+1, ANSWERCOUNT=ANSWERCOUNT+1 "
				+ " WHERE REF=(SELECT REF FROM QNA WHERE SEQ=?) "
				+ "	  AND STEP > (SELECT STEP FROM QNA WHERE SEQ=?) ";
		
		
		// insert
		String sql2 = " INSERT INTO QNA "
				+ " (SEQ, ID, REF, STEP, DEPTH, "
				+ " TITLE, CONTENT, TAG, WDATE, PARENT, DEL, READCOUNT, "
				+ " FAVOR, LVPOINT, ANSWERCOUNT ) "
				+ " VALUES(SEQ_QNA.NEXTVAL, ?, "
				+ "		(SELECT REF FROM QNA WHERE SEQ=? ), "	// REF
				+ " 	(SELECT STEP FROM QNA WHERE SEQ=? )+1, "
				+ "		(SELECT DEPTH FROM QNA WHERE SEQ=? )+1, "
				+ "		?, ?, ?, SYSDATE, ?, 0, 0, "
				+ "		0, 0, ?) ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		
		System.out.println(dto.toString());
		int count = 0;
		try {
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);
			System.out.println("1/6 writeAnswer Success");
			
			psmt = conn.prepareStatement(sql1);
			System.out.println("2/6 writeAnswer Success");
			psmt.setInt(1, seq);
			psmt.setInt(2, seq);
				
			count = psmt.executeUpdate();
			System.out.println("3/6 writeAnswer Success");
			
			psmt.clearParameters();
			
			psmt = conn.prepareStatement(sql2);
			psmt.setString(1, dto.getId());
			psmt.setInt(2, seq);
			psmt.setInt(3, seq);
			psmt.setInt(4, seq);
			psmt.setString(5, seq+"타이틀");
			psmt.setString(6, dto.getContent());
			psmt.setString(7, seq+"테그");
			psmt.setInt(8, seq);
			psmt.setInt(9, dto.getCommentcount() + 1);
			System.out.println("4/6 writeAnswer Success");	
			
			count = psmt.executeUpdate();
			conn.commit();
			System.out.println("5/6 writeAnswer Success");
			
			
		} catch (SQLException e) {
			System.out.println("writeAnswer Fail");
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
			System.out.println("6/6 writeAnswer Success");
		}		
		
		return count>0;*/
		return false;
	}

	@Override
	public List<QnaAnswerDto> getCommentList(int seq) {
		
		String sql = " SELECT SEQ, ID, CONTENT, WDATE, "
				+ " PARENT, DEL"
				+ " FROM QNAANSWER"
				+ " WHERE PARENT=? AND DEL=0"
				+ " ORDER BY WDATE ASC ";
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		List<QnaAnswerDto> list = new ArrayList<QnaAnswerDto>();
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 getCommentList Success");
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			System.out.println("2/6 getCommentList Success");
			
			rs = psmt.executeQuery();
			System.out.println("3/6 getCommentList Success");
			QnaAnswerDto dto = null;
			while(rs.next()) {
				dto = new QnaAnswerDto(
					rs.getInt(1),		//seq, 
					rs.getString(2),	//id, 
					rs.getString(3),	//content, 
					rs.getString(4),	//wdate, 
					rs.getInt(5),		//parent, 
					rs.getInt(6));		//del)
				list.add(dto);				
			}
			System.out.println("4/6 getCommentList Success");
			
		} catch (SQLException e) {
			System.out.println("getCommentList Fail");
			e.printStackTrace();
		}finally {
			DBClose.close(psmt, conn, rs);
			System.out.println("5/6 getCommentList Success");
			
		}		
		return list;
	}

	@Override
	public int getSeq() {
		
		String sql = " SELECT MAX(SEQ) FROM QNAANSWER";
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

	
	

}
