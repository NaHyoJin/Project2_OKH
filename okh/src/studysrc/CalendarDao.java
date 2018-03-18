package studysrc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBClose;
import db.DBConnection;


public class CalendarDao implements iCalendar{
	
	private static CalendarDao calendarDao = new CalendarDao();
	
	private CalendarDao() {}
	
	public static CalendarDao getInstance() {
		return calendarDao;
	}

	
	

	@Override
	public boolean updatecalendar(String title, String content, String rdate, int seq) {
		int count = 0;
		String sql = " UPDATE CALENDAR "
				+ " SET TITLE = ?, CONTENT = ?, RDATE = ? "
				+ " WHERE SEQ =? ";
			
		Connection conn = null;
		PreparedStatement psmt = null;
		try {
			
			
		conn = DBConnection.getConnection();
		System.out.println("1/6 S updatecalendar");
		
		
		psmt= conn.prepareStatement(sql);
		psmt.setString(1, title);
		psmt.setString(2, content);
		psmt.setString(3, rdate);
		psmt.setInt(4, seq);
		System.out.println("2/6 S updatecalendar");
		count = psmt.executeUpdate();
		System.out.println("3/6 S updatecalendar");
		} catch (SQLException e) {
			System.out.println("F updatecalendar");
			e.printStackTrace();
		}finally {
			DBClose.close(psmt, conn, null);
		}
		
		return count>0?true:false;
	}

	@Override
	public boolean deletecalendar(int seq) {
		boolean b = false;
		String sql = " DELETE FROM CALENDAR WHERE SEQ=?  ";
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			System.out.println("1/6 deletecalendar Success");
			psmt.setInt(1, seq);
			System.out.println("2/6 deletecalendar Success");
			rs= psmt.executeQuery();
			System.out.println("3/6 deletecalendar Success");
			b = true;
		} catch (SQLException e) {
			System.out.println(" deletecalendar fail");
			e.printStackTrace();
		}finally {
			DBClose.close(psmt, conn, rs);
		}
		
		return b;
	}

	@Override
	public CalendarDto getDay(int seq) {
		String sql = " SELECT SEQ, ID, TITLE, CONTENT, RDATE, WDATE FROM CALENDAR WHERE SEQ=? ";
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		CalendarDto dto = new CalendarDto();
		boolean b = false;
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 getDay Success");
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			
			System.out.println("2/6 getDay Success");
			
			rs= psmt.executeQuery();
			System.out.println("3/6 getDay Success");
			
			while(rs.next()) {
				
				dto.setSeq(rs.getInt(1));
				dto.setId(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setRdate(rs.getString(5));
				dto.setWdate(rs.getString(6));
				
				System.out.println("4/6 getDay Success");
				b = true;
				
			}
			
			
			
			} catch (SQLException e) {
				System.out.println("getDay Fail");
				e.printStackTrace();
			}
			finally {
				DBClose.close(psmt, conn, rs);
				System.out.println("5/6 getDay success");
			}
		return dto;
			
			
			
	}

	@Override
	public List<CalendarDto> getDayList(String id, String rdate) {
		String sql = " SELECT SEQ, ID, TITLE, CONTENT, RDATE, WDATE FROM CALENDAR WHERE ID=? AND RDATE LIKE '%"+rdate+"%' ";
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		List<CalendarDto> list = new ArrayList<CalendarDto>();
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 getDayList Success");
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id.trim());
			
			System.out.println("2/6 getDayList Success");
			
			rs= psmt.executeQuery();
			System.out.println("3/6 getDayList Success");
			
			while(rs.next()) {
				CalendarDto dto = new CalendarDto();
				dto.setSeq(rs.getInt(1));
				dto.setId(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setRdate(rs.getString(5));
				dto.setWdate(rs.getString(6));
				list.add(dto);
				System.out.println("4/6 getDayList Success");
				
				
			}
			
			
			
			} catch (SQLException e) {
				System.out.println("getDayList Fail");
				e.printStackTrace();
			}
			finally {
				DBClose.close(psmt, conn, rs);
				System.out.println("5/6 getDayList success");
			}
			
			
			return list;
	}

	

	@Override
	public List<CalendarDto> getCalendarList(String id, String yyyyMM) {

		String sql = " SELECT SEQ, ID, TITLE, CONTENT, RDATE, WDATE "
				+ " FROM ( SELECT ROW_NUMBER() OVER(PARTITION BY SUBSTR(RDATE, 1, 8) ORDER BY RDATE ASC ) RN, "
				+ " 		SEQ, ID, TITLE, CONTENT, RDATE, WDATE "
				+ " 		FROM CALENDAR "
				+ " 		WHERE ID=? AND SUBSTR(RDATE,1,6)=?) "
				+ " WHERE RN BETWEEN 1 AND 5 ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		List<CalendarDto> list = new ArrayList<CalendarDto>();
		try {
		conn = DBConnection.getConnection();
		System.out.println("1/6 getCalendarList Success");
		psmt = conn.prepareStatement(sql);
		psmt.setString(1, id.trim());
		psmt.setString(2, yyyyMM.trim());
		System.out.println("2/6 getCalendarList Success");
		
		rs= psmt.executeQuery();
		System.out.println("3/6 getCalendarList Success");
		
		while(rs.next()) {
			CalendarDto dto = new CalendarDto();
			dto.setSeq(rs.getInt(1));
			dto.setId(rs.getString(2));
			dto.setTitle(rs.getString(3));
			dto.setContent(rs.getString(4));
			dto.setRdate(rs.getString(5));
			dto.setWdate(rs.getString(6));
			list.add(dto);
			System.out.println("4/6 getCalendarList Success");
			
			
		}
		
		
		
		} catch (SQLException e) {
			System.out.println("getCalendarList Fail");
			e.printStackTrace();
		}
		finally {
			DBClose.close(psmt, conn, rs);
			System.out.println("5/6 getCalendarList success");
		}
		
		
		return list;
	}
	
	@Override
	public boolean writecalendar(CalendarDto cal) {
		
		String sql = " INSERT INTO CALENDAR(SEQ, ID, TITLE, CONTENT, RDATE, WDATE) "
				+ " VALUES(SEQ_CAL.NEXTVAL, ?, "
				+ " ?, ?, ?, SYSDATE) ";
				
		
		Connection conn = null;
		PreparedStatement psmt = null;
		
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();			
			System.out.println("1/6 writecalendar Success");
			
			psmt = conn.prepareStatement(sql);
			System.out.println("2/6 writecalendar Success");
			
			psmt.setString(1, cal.getId());
			psmt.setString(2, cal.getTitle());
			psmt.setString(3, cal.getContent());
			psmt.setString(4, cal.getRdate());
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
