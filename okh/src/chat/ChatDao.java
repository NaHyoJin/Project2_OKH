package chat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.DBClose;
import db.DBConnection;

public class ChatDao implements IChatDao {
	
	public ChatDao() {
		DBConnection.initConnection();
	}

	@Override
	public ArrayList<ChatDto> getChatListById(String fromid, String toid, String seq) {
		ArrayList<ChatDto> chatlist = null;
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		String sql = " SELECT SEQ, FROMID, TOID, CHATCONTENT, CHATTIME FROM CHAT WHERE ((FROMID=? AND TOID=?) OR (FROMID=? AND TOID=?)) AND SEQ > ? ORDER BY CHATTIME ";
		

		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 getChatListById Success");
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, fromid);
			psmt.setString(2, toid);
			psmt.setString(3, toid);
			psmt.setString(4, fromid);
			psmt.setInt(5, Integer.parseInt(seq));
			System.out.println("2/6 getChatListById Success");
			
			rs = psmt.executeQuery();
			System.out.println("3/6 getChatListById Success");
			
			chatlist = new ArrayList<ChatDto>();
			while(rs.next()) {
				ChatDto dto = new ChatDto();
				dto.setSeq(rs.getInt(1));
				dto.setFromid(rs.getString(2).replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"));
				dto.setToid(rs.getString(3).replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"));
				dto.setChatcontent(rs.getString(4).replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"));
				int chatTime = Integer.parseInt(rs.getString(5).substring(11, 13));
				String timeType = "오전";
				if(chatTime >= 12) {
					timeType = "오후";
					chatTime -= 12;
				}
				dto.setChattime(rs.getString(5).substring(0, 11) + " " + timeType + " " + chatTime + ":" +rs.getString(5).substring(14, 16) + "");
				chatlist.add(dto);
			}
			
		} catch (SQLException e) {
			System.out.println("getChatListById Fail");
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		
		return chatlist;
	}
	
	@Override
	public ArrayList<ChatDto> getBox(String userID) {
		ArrayList<ChatDto> chatlist = null;
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		String sql = " SELECT SEQ, FROMID, TOID, CHATCONTENT, CHATTIME, CHATREAD FROM CHAT WHERE SEQ IN (SELECT MAX(SEQ) FROM CHAT WHERE TOID=? OR FROMID=? GROUP BY FROMID, TOID) ";
 		

		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 getBox Success");
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, userID);
			psmt.setString(2, userID);
			System.out.println("2/6 getBox Success");
			
			rs = psmt.executeQuery();
			System.out.println("3/6 getBox Success");
			
			chatlist = new ArrayList<ChatDto>();
			while(rs.next()) {
				ChatDto dto = new ChatDto();
				dto.setSeq(rs.getInt(1));
				dto.setFromid(rs.getString(2).replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"));
				dto.setToid(rs.getString(3).replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"));
				dto.setChatcontent(rs.getString(4).replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"));
				int chatTime = Integer.parseInt(rs.getString(5).substring(11, 13));
				String timeType = "오전";
				if(chatTime >= 12) {
					timeType = "오후";
					chatTime -= 12;
				}
				dto.setChattime(rs.getString(5).substring(0, 11) + " " + timeType + " " + chatTime + ":" +rs.getString(5).substring(14, 16) + "");
				chatlist.add(dto);
			}
			for(int i = 0; i < chatlist.size(); i++) {
				ChatDto x = chatlist.get(i);
				for(int j = 0; j < chatlist.size(); j++) {
					ChatDto y = chatlist.get(j);
					if(x.getFromid().equals(y.getToid()) && x.getToid().equals(y.getFromid())) {
						if(x.getSeq() < y.getSeq()) {
							chatlist.remove(x);
							i--;
							break;
						}else {
							chatlist.remove(y);
							j--;
						}
					}
				}
			}
			
		} catch (SQLException e) {
			System.out.println("getBox  Fail");
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		
		return chatlist;
	}

	@Override
	public ArrayList<ChatDto> getChatListByRecent(String fromid, String toid, int number) {
		ArrayList<ChatDto> chatlist = null;
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		String sql = " SELECT SEQ, FROMID, TOID, CHATCONTENT, CHATTIME FROM CHAT WHERE ((FROMID=? AND TOID=?) OR (FROMID=? AND TOID=?)) AND SEQ > (SELECT MAX(SEQ) - ? FROM CHAT WHERE (FROMID=? AND TOID=?) OR (FROMID=? AND TOID=?)) ORDER BY CHATTIME ";
		

		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 getChatListByRecent Success");
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, fromid);
			psmt.setString(2, toid);
			psmt.setString(3, toid);
			psmt.setString(4, fromid);
			psmt.setInt(5, number);
			psmt.setString(6, fromid);
			psmt.setString(7, toid);
			psmt.setString(8, toid);
			psmt.setString(9, fromid);
			System.out.println("2/6 getChatListByRecent Success");
			
			rs = psmt.executeQuery();
			System.out.println("3/6 getChatListByRecent Success");
			
			chatlist = new ArrayList<ChatDto>();
			while(rs.next()) {
				ChatDto dto = new ChatDto();
				dto.setSeq(rs.getInt(1));
				dto.setFromid(rs.getString(2).replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"));
				dto.setToid(rs.getString(3).replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"));
				dto.setChatcontent(rs.getString(4).replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"));
				int chatTime = Integer.parseInt(rs.getString(5).substring(11, 13));
				String timeType = "오전";
				if(chatTime >= 12) {
					timeType = "오후";
					chatTime -= 12;
				}
				dto.setChattime(rs.getString(5).substring(0, 11) + " " + timeType + " " + chatTime + ":" +rs.getString(5).substring(14, 16) + "");
				chatlist.add(dto);
			}
			
		} catch (SQLException e) {
			System.out.println("getChatListByRecent Fail");
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		System.out.println("chatlist in dao----------------------------------------------" + chatlist);
		return chatlist;
	}
	
	@Override
	public int submit(String fromid, String toid, String chatcontent) {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		String sql = " INSERT INTO CHAT VALUES(SEQ_CHAT.NEXTVAL, ?, ?, ?, SYSDATE, 0) ";

		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 submit Success");
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, fromid);
			psmt.setString(2, toid);
			psmt.setString(3, chatcontent);
			System.out.println("2/6 submit Success");
			
			return psmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("submit Fail");
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		return -1;
	}

	@Override
	public boolean readChat(String fromid, String toid) {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		int count = 0;
		
		String sql = " UPDATE CHAT SET CHATREAD=1 WHERE (FROMID=? AND TOID=?) ";
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 readChat Success");

			psmt = conn.prepareStatement(sql);
			psmt.setString(1, toid);
			psmt.setString(2, fromid);
			System.out.println("2/6 readChat Success");

			count = psmt.executeUpdate();
			System.out.println("3/6 readChat Success");
			
		} catch (SQLException e) {
			System.out.println("readChat Fail");
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		
		return count>0;
	}

	@Override
	public int getAllUnreadChat(String userID) {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		String sql = " SELECT COUNT(SEQ) FROM CHAT WHERE TOID=? AND CHATREAD=0 ";
		
		int chatCount = 0;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 readChat Success");

			psmt = conn.prepareStatement(sql);
			psmt.setString(1, userID);
			System.out.println("2/6 readChat Success");
			
			rs = psmt.executeQuery();
			System.out.println("3/6 readChat Success");

			if(rs.next()) {
				chatCount = rs.getInt(1);
			}
			System.out.println("3/6 readChat Success");
			
		} catch (SQLException e) {
			System.out.println("readChat Fail");
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		
		return chatCount;
	}

}
