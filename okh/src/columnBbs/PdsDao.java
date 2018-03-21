package columnBbs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBClose;
import db.DBConnection;
import columnBbs.ColumnBbsDto;
import columnBbs.PdsDto;

public class PdsDao implements iPdsDao {
	
	private static PdsDao pdsDao = new PdsDao(); 

	private PdsDao() {}
	
	public static PdsDao getInstance() {
		return pdsDao;
	}	
	
	
	
	
	@Override
	public List<PdsDto> getPdsList() {

		String sql = " SELECT SEQ, ID, TITLE, CONTENT, FILENAME, "
				+ " READCOUNT, DOWNCOUNT, REGDATE "
				+ " FROM PDS "
				+ " ORDER BY SEQ DESC ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		List<PdsDto> list = new ArrayList<PdsDto>();
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 getPdsList Success");
			
			psmt = conn.prepareStatement(sql);
			System.out.println("2/6 getPdsList Success");
			
			rs = psmt.executeQuery();
			System.out.println("3/6 getPdsList Success");
			
			while(rs.next()) {
				PdsDto dto = new PdsDto(rs.getInt(1), // seq, 
										rs.getString(2), // id, 
										rs.getString(3), // title, 
										rs.getString(4), // content, 
										rs.getString(5), // filename, 
										rs.getInt(6), // readcount, 
										rs.getInt(7), //downcount, 
										rs.getString(8) //regdate
										);
				list.add(dto);
			}
			System.out.println("4/6 getPdsList Success");
			
		} catch (SQLException e) {
			System.out.println("getPdsList Fail");
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
			System.out.println("5/6 getPdsList Success");
		}

		return list;
	}

	@Override
	public boolean writePds(PdsDto pds) {
		String sql = " INSERT INTO PDS( "
				+ " SEQ, ID, TITLE, CONTENT, FILENAME,"
				+ " READCOUNT, DOWNCOUNT, REGDATE) "
				+ " VALUES(SEQ_PDS.NEXTVAL, "
				+ " ?, ?, ?, ?, 0, 0, SYSDATE) ";
		
		int count = 0;
		
		Connection conn = null;
		PreparedStatement psmt = null;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 S writePds");
				
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, pds.getId());
			psmt.setString(2, pds.getTitle());
			psmt.setString(3, pds.getContent());
			psmt.setString(4, pds.getFilename());
			System.out.println("2/6 S writePds");
			
			count = psmt.executeUpdate();
			System.out.println("3/6 S writePds");
			
		} catch (SQLException e) {
			System.out.println("F writePds");
		} finally {
			DBClose.close(psmt, conn, null);
			System.out.println("4/6 S writePds");
		}		
		
		return count>0?true:false;
	}

	@Override
	public boolean downloadcount(int seq) {
		
		String sql = " UPDATE PDS "
				+ " SET DOWNCOUNT=DOWNCOUNT+1 "
				+ " WHERE SEQ=? "; 
		
		int count = 0;
		
		Connection conn = null;
		PreparedStatement psmt = null;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 S downloadcount");
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			System.out.println("2/6 S downloadcount");
			
			count = psmt.executeUpdate();
			System.out.println("3/6 S downloadcount");						
		} catch (SQLException e) {
			System.out.println("fail downloadcount");
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, null);			
		}		
		
		return count>0?true:false;
	}

	
	@Override
	public PdsDto getDetailBbs(int seq) {
		PdsDto dto = null;
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		String sql = " SELECT SEQ, ID, TITLE, CONTENT,"
				+ " FILENAME, READCOUNT, DOWNCOUNT,REGDATE, "
				+ " FROM LIFEBBS"
				+ " WHERE SEQ=? ";  
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("3/6 getDetailBbs Success");
			
			psmt = conn.prepareStatement(sql);
			System.out.println("4/6 getDetailBbs Success");
			
			psmt.setInt(1, seq);
			
			rs = psmt.executeQuery();
			System.out.println("5/6 getDetailBbs Success");
			
			if(rs.next()) {
				int i = 1;
				
				dto = new PdsDto(rs.getInt(1),
								 rs.getString(2),
								 rs.getString(3),
								 rs.getString(4),
								 rs.getString(5),
								 rs.getInt(6),
								 rs.getInt(7),
								 rs.getString(8));
			}         
			System.out.println("6/6 getDetailBbs Success");
		
		} catch (SQLException e) {
			System.out.println("getDetailBbs Fail");
			e.printStackTrace();
		}finally {
			DBClose.close(psmt, conn, rs);
		}
		
		return dto;
	}



	
	
	

}





