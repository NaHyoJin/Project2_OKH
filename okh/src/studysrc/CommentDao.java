package studysrc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DBClose;
import db.DBConnection;

public class CommentDao implements iCommentDao {

	@Override
	public boolean writecomment(ComCommentDto dto) {
		String sql = " INSERT INTO SCOMMENT(SEQ, ID, "
				+ " CONTENT, WDATE, DEL,CHILD,JOINER) "
				+ " VALUES(SEQ_SCOMMENT.NEXTVAL, ?, ?, SYSDATE,0,?,0) ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		int count = 0;
		try {
			conn = DBConnection.getConnection();			
			System.out.println("1/6 writecomment Success");
			
			psmt = conn.prepareStatement(sql);
			System.out.println("2/6 writecomment Success");
			
			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getContent());
			psmt.setInt(3, dto.getChild());
			count = psmt.executeUpdate();
			
			System.out.println("3/6 writecomment Success");
			
		} catch (SQLException e) {
			System.out.println("writecomment fail");
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, null);			
		}
		
		return count>0?true:false;
	}
		
		
	
	
}
