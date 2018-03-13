package qna.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBClose;
import db.DBConnection;
import qna.dto.QnaDto;

public class qnaBbsDao implements qnaBbsDaoImpl {

	private static qnaBbsDao qNaDao = new qnaBbsDao();
	
	private qnaBbsDao() {}
	
	public static qnaBbsDao getInstance() {
		return qNaDao;
	}
	
	
	@Override
	public boolean writeQnaBbs(QnaDto dto) {
		
		String sql = " INSERT INTO QNA"
				+ " (SEQ, ID, REF, STEP, DEPTH, "
				+ " TITLE, CONTENT, TAG, WDATE, PARENT,"
				+ " DEL, READCOUNT, FAVOR, LVPOINT) "
				+ " VALUES"
				+ " (SEQ_QNA.NEXTVAL, ?, (SELECT NVL(MAX(REF),0)+1 FROM QNA), 0, 0, "
				+ " ?, ?, ?, SYSDATE, 0, "
				+ " 0, 0, 0, 0) ";
		
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
				+ " DEL, READCOUNT, FAVOR, LVPOINT "
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
					rs.getInt(14));		//lvpoint)
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
