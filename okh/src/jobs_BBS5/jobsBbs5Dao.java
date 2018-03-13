package jobs_BBS5;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beanDtoVO.BbsHWCodingBeanDtoVO;
import db.DBClose;
import db.DBConnection;


public class jobsBbs5Dao implements jobsBbs5DaoImpl {//일반 게시판 DAO부분.
	
/*
	CREATE TABLE BBS5HWCoding(
			SEQ NUMBER(8) PRIMARY KEY,
			ID VARCHAR2(50) NOT NULL,
			CONTENT VARCHAR2(4000) NOT NULL,
			WDATE DATE NOT NULL,
			DEL NUMBER(1) NOT NULL,
			LIKECOUNT NUMBER(8) NOT NULL
		);
		
		
	CREATE TABLE SNSFOLLOW(
		MYID VARCHAR2(50),   
	    FOLLOWID VARCHAR2(50),
	    CONNECTN NUMBER(1),
	    CONSTRAINT PK_SNSFOLLOW_01 PRIMARY KEY(MYID, FOLLOWID)
	);
*/

	
	//게시판4. 하드웨어 코딩 부분 글 전체 가지고 오는것.
	public List<BbsHWCodingBeanDtoVO> getBbsHWCodingBeanList(){
		
		List<BbsHWCodingBeanDtoVO> list = new ArrayList<BbsHWCodingBeanDtoVO>();
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
/*		기존에 있던 코드 참고해서 만든것.
		String sql = " SELECT SEQ, BGROUP, SORTS, DEPTH, ID, CONTENT, TO_CHAR(WDATE, 'YY/MM/DD'), DEL, LIKECOUNT " 
				+ " FROM SNSBBS "  
				+ " WHERE ID IN (SELECT FOLLOWID "
				+ " 				FROM SNSFOLLOW " 
				+ " 				WHERE MYID = '" + myid + "' "
				+ "						AND CONNECTN = 0 ) "
				+ "		AND DEL = 0 "
				+ " ORDER BY BGROUP DESC, SORTS ASC ";
*/
		
		String sql = " SELECT SEQ, ID, REF, STEP, DEPTH, "
				+ " TITLE, CONTENT, WDATE, PARENT,"
				+ " DEL, READCOUNT "
				+ " FROM BBS5HWCoding "
				+ " ORDER BY REF DESC, STEP ASC ";
		try {
			conn = DBConnection.getConnection();
			System.out.println("2/6 getBbsHWCodingBeanList Success");
			
			psmt = conn.prepareStatement(sql);
			System.out.println("3/6 getBbsHWCodingBeanList Success");
			
			rs = psmt.executeQuery();
			System.out.println("4/6 getBbsHWCodingBeanList Success");
			
			/*
			 		private int seq;	//시퀀스 번호
					private String id;	//아이디
					
					private int ref;	// 그룹번호
					private int step;	// 열번호
					private int depth;	// 깊이
					
					private String title;
					private String content;
					private String wdate;//작성일
					private int parent;	// 부모글
					
					private int del;	// 삭제
					private int readcount;//조회수
			 */
			while(rs.next()){
				int i = 1;
				BbsHWCodingBeanDtoVO dto = new BbsHWCodingBeanDtoVO(
						rs.getInt(i++),//seq, 
						rs.getString(i++),//id, 
						rs.getInt(i++),//ref, 
						rs.getInt(i++),//step, 
						rs.getInt(i++),//depth, 
						rs.getString(i++),//title, 
						rs.getString(i++),//content, 
						rs.getString(i++),//wdate, 
						rs.getInt(i++),//parent, 
						rs.getInt(i++),//del, 
						rs.getInt(i++)//readcount
						);
						
				list.add(dto);
			}
			
			System.out.println("5/6 getBbsHWCodingBeanList Success");
			
		} catch (SQLException e) {
			System.out.println("getBbsHWCodingBeanList fail");
		} finally{
			DBClose.close(psmt, conn, rs);
			System.out.println("6/6 getBbsHWCodingBeanList Success");
		}
		return list;	
	}

/*
		@Override
		public boolean writeSns(SnsDto dto) {
			int count = 0;
			
			Connection conn = null;
			PreparedStatement psmt = null;
			ResultSet rs = null;
			
			String sql = "INSERT INTO SNSBBS(SEQ, BGROUP, SORTS, DEPTH, ID, CONTENT, WDATE, DEL, LIKECOUNT) "
					+ " VALUES(SEQ_SNSBBS.NEXTVAL, SEQ_SNSBBS.NEXTVAL, 0, 0, ?, ?, SYSDATE, 0, 0) ";
			
			try {
				conn = DBConnection.getConnection();
				System.out.println("2/6 writeSns Success");
				
				psmt = conn.prepareStatement(sql);
				System.out.println("3/6 writeSns Success");
				
				psmt.setString(1, dto.getId());
				psmt.setString(2, dto.getContent());
				
				count = psmt.executeUpdate();
				System.out.println("4/6 writeSns Success");
				
			} catch (SQLException e) {			
				System.out.println("writeSns fail");
			} finally{
				DBClose.close(psmt, conn, rs);			
			}
			
			return count>0?true:false;
		}

		@Override
		public boolean snsDelete(int seq) {
			String sql=" UPDATE SNSBBS SET  "
					+" DEL=1 "
					+" WHERE SEQ=? ";
			
			int count = 0;
			Connection conn=null;
			PreparedStatement psmt=null;
			
			try {
				conn = DBConnection.getConnection();			
				psmt=conn.prepareStatement(sql);
				psmt.setInt(1, seq);			
				count = psmt.executeUpdate();
				
			} catch (SQLException e) {			
				e.printStackTrace();
			} finally{
				DBClose.close(psmt, conn, null);			
			}
					
			return count>0?true:false;
		}

		@Override
		public boolean snsUpdate(int seq, String content) {
			String sql = " UPDATE SNSBBS SET "
					+ " CONTENT=? "
					+ " WHERE SEQ=? ";
			
			Connection conn = null;
			PreparedStatement psmt = null;
			int count = 0;
			
			try {
				conn = DBConnection.getConnection();	
				System.out.println("2/6 S snsUpdate");
				
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, content);
				psmt.setInt(2, seq);
				
				System.out.println("3/6 S snsUpdate");
				
				count = psmt.executeUpdate();
				System.out.println("4/6 S snsUpdate");
				
			} catch (SQLException e) {			
				e.printStackTrace();
			} finally{
				DBClose.close(psmt, conn, null);	
				System.out.println("5/6 S snsUpdate");
			}		
			return count>0?true:false;
		}

		//////
		@Override
		public SnsDto getSNS(int seq) {
			SnsDto dto = null;
			
			Connection conn = null;
			PreparedStatement psmt = null;
			ResultSet rs = null;
			
			String sql = " SELECT BGROUP, SORTS, DEPTH, ID, CONTENT, WDATE, LIKECOUNT "
					+ " FROM SNSBBS "
					+ " WHERE SEQ = ? " ;
			
			try {			
				conn = DBConnection.getConnection();
				psmt = conn.prepareStatement(sql);	
					
				psmt.setInt(1, seq);
				rs = psmt.executeQuery();
				
				while(rs.next()){
					int bgroup = rs.getInt(1);
					int sorts = rs.getInt(2);
					int depth = rs.getInt(3);
					String id = rs.getString(4);
					String content = rs.getString(5);	
					String wdate = rs.getString(6);
					int likecount = rs.getInt(7);
					
					dto = new SnsDto(seq, bgroup, sorts, depth, id, content, wdate, 0, likecount);	
				}
				
			} catch (SQLException e) {			
				e.printStackTrace();
			} finally{
				DBClose.close(psmt, conn, rs);	
			}
			return dto;// TODO Auto-generated method stub
		}

		//////
		@Override
		public List<SnsDto> getMyContent(String myid) {
			
			List<SnsDto> list = new ArrayList<SnsDto>();
			
			Connection conn = null;
			PreparedStatement psmt = null;
			ResultSet rs = null;
			
			String sql = " SELECT SEQ, BGROUP, SORTS, DEPTH, ID, CONTENT, TO_CHAR(WDATE, 'YY/MM/DD'), DEL, LIKECOUNT " 
					+ " FROM SNSBBS "  
					+ " WHERE ID = '" + myid + "' "
						+ "	AND DEL = 0 "
					+ " ORDER BY BGROUP DESC, SORTS ASC ";
			
			try {
				conn = DBConnection.getConnection();
				System.out.println("2/6 getMyContent Success");
				
				psmt = conn.prepareStatement(sql);
				System.out.println("3/6 getMyContent Success");
				
				rs = psmt.executeQuery();
				System.out.println("4/6 getMyContent Success");
				
				while(rs.next()){
					int i = 1;
					SnsDto dto = new SnsDto(rs.getInt(i++),			// SEQ
												rs.getInt(i++),
												rs.getInt(i++),
												rs.getInt(i++),
												rs.getString(i++), 	// ID
												rs.getString(i++), 	// CONTENT
												rs.getString(i++),	// WDATE
												rs.getInt(i++),		// DEL
												rs.getInt(i++));	// CONNECTN
					list.add(dto);
				}
				
				System.out.println("5/6 getMyContent Success");
				
			} catch (SQLException e) {
				System.out.println("getMyContent fail");
			} finally{
				DBClose.close(psmt, conn, rs);
				System.out.println("6/6 getMyContent Success");
			}
			return list;	
		}
		

		@Override
		public boolean likeCount(int seq) {
			String sql = " UPDATE SNSBBS SET "
					+ " LIKECOUNT=LIKECOUNT+1 "
					+ " WHERE SEQ=? ";
			
			Connection conn = null;
			PreparedStatement psmt = null;
			ResultSet rs=null;
			int count = 0;
			
			try {
				conn = DBConnection.getConnection();	
				System.out.println("2/6 S snsUpdate");
				
				psmt = conn.prepareStatement(sql);
				psmt.setInt(1, seq);
				System.out.println("3/6 S snsUpdate");
				
				count = psmt.executeUpdate();
				System.out.println("4/6 S snsUpdate");
				
				
				
			} catch (SQLException e) {			
				e.printStackTrace();
			} finally{
				DBClose.close(psmt, conn, rs);	
				System.out.println("5/6 S snsUpdate");
			}
			return count>0?true:false;
		}

		@Override
		public boolean dislikeCount(int seq) {
			
			String sql = " UPDATE SNSBBS SET "
					+ " LIKECOUNT=LIKECOUNT-1 "
					+ " WHERE SEQ=? ";
			
			Connection conn = null;
			PreparedStatement psmt = null;
			ResultSet rs=null;
			int count = 0;
			
			try {
				conn = DBConnection.getConnection();	
				System.out.println("2/6 S snsUpdate");
				
				psmt = conn.prepareStatement(sql);
				psmt.setInt(1, seq);
				System.out.println("3/6 S snsUpdate");
				
				count = psmt.executeUpdate();
				System.out.println("4/6 S snsUpdate");
				
				
				
			} catch (SQLException e) {			
				e.printStackTrace();
			} finally{
				DBClose.close(psmt, conn, rs);	
				System.out.println("5/6 S snsUpdate");
			}
			return count>0?true:false;
		}
		
		// bang
		// ***
		public int PrintconNum(String myid, String selid) {
			Connection conn = null;
			PreparedStatement psmt = null;
			ResultSet rs = null;
			
			int connectn = 0;
			
			String sql = " SELECT MYID, FOLLOWID, CONNECTN "
					+ " FROM SNSFOLLOW "
					+ " WHERE MYID = ? "
							+ " AND FOLLOWID = ? ";
			
			try {			
				conn = DBConnection.getConnection();
				psmt = conn.prepareStatement(sql);	
				
				psmt.setString(1, myid);
				psmt.setString(2, selid);	
				
				rs = psmt.executeQuery();
				
				while(rs.next()){
					connectn = rs.getInt("connectn");
				}
				
			} catch (SQLException e) {			
				e.printStackTrace();
			} finally{
				DBClose.close(psmt, conn, rs);	
			}
			return connectn;
		}
		
		public boolean confollow(String myid, String selid) {
			
			Connection conn = null;
			PreparedStatement psmt = null;
			
			int count = 0;
			
			String sql = " UPDATE SNSFOLLOW SET "
					+ " CONNECTN = 0 "
					+ " WHERE MYID=? "
						+ " AND FOLLOWID=? ";
			
			try {			
				conn = DBConnection.getConnection();
				System.out.println("2/6 S confollow");
				psmt = conn.prepareStatement(sql);	
				System.out.println("3/6 S confollow");
				
				psmt.setString(1, myid);	
				psmt.setString(2, selid);	
				
				count = psmt.executeUpdate();
				
				System.out.println("4/6 S confollow");
				
				System.out.println("5/6 S confollow");
			} catch (SQLException e) {			
				System.out.println(" F confollow");
			} finally{
				DBClose.close(psmt, conn, null);	
			}
			return count>0?true:false;
		}
		
		@Override
		public boolean nameSelect(String myid, String selid) {
			
			Connection conn = null;
			PreparedStatement psmt = null;
			ResultSet rs = null;
			
			int count = 0;
			
			String sql = " SELECT MYID, FOLLOWID, CONNECTN "
					+ " FROM SNSFOLLOW "
					+ " WHERE MYID = ? "
							+ " AND FOLLOWID = ? ";
			
			try {			
				conn = DBConnection.getConnection();
				psmt = conn.prepareStatement(sql);	
				
				psmt.setString(1, myid);
				psmt.setString(2, selid);	
				
				rs = psmt.executeQuery();
				
				while(rs.next()){	
					count = 1;
				}
				
			} catch (SQLException e) {			
				e.printStackTrace();
			} finally{
				DBClose.close(psmt, conn, rs);	
			}
			return count>0?true:false;
		}
		
		
		@Override
		public boolean following(String myid, String selid) {
			
			int count = 0;
			
			Connection conn = null;
			PreparedStatement psmt = null;
			ResultSet rs = null;
			
			String sql = "INSERT INTO SNSFOLLOW(MYID, FOLLOWID, CONNECTN) "
					+ " VALUES(?, ?, 0) ";
			
			try {
				conn = DBConnection.getConnection();
				System.out.println("2/6 following Success");
				
				psmt = conn.prepareStatement(sql);
				System.out.println("3/6 following Success");
				
				psmt.setString(1, myid);
				psmt.setString(2, selid);
				
				count = psmt.executeUpdate();
				System.out.println("4/6 following Success");
				
			} catch (SQLException e) {			
				System.out.println("following fail");
			} finally{
				DBClose.close(psmt, conn, rs);			
			}
			
			return count>0?true:false;
		}
		
		@Override
		public List<selectFollowDto> selectfollow(String myid){
			
			List<selectFollowDto> list = new ArrayList<selectFollowDto>();
			
			Connection conn = null;
			PreparedStatement psmt = null;
			ResultSet rs = null;
			
			String sql = " SELECT M.NAME, F.FOLLOWID, M.EMAIL, F.CONNECTN "
					+ " FROM SNSMEMBER M, SNSFOLLOW F"
					+ " WHERE M.ID = F.FOLLOWID "
							+ " AND F.MYID = ? "
							+ " AND F.FOLLOWID != ? "
							+ " AND F.CONNECTN = 0 "
							+ " AND M.MDEL = 0 ";
			
			try {			
				conn = DBConnection.getConnection();
				System.out.println("2/6 selectfollow Success");
				psmt = conn.prepareStatement(sql);	
				System.out.println("3/6 selectfollow Success");
				
				psmt.setString(1, myid);
				psmt.setString(2, myid);
				rs = psmt.executeQuery();
				
				System.out.println("4/6 selectfollow Success");
				
				while(rs.next()){	
					int i = 1;
					selectFollowDto dto = new selectFollowDto(rs.getString(i++),	// NAME
																rs.getString(i++), 	// FOLLOWID
																rs.getString(i++), 	// EMAIL
																rs.getInt(i++));	// CONNECTN
					list.add(dto);
				}
				System.out.println("5/6 selectfollow Success");
				
			} catch (SQLException e) {			
				e.printStackTrace();
			} finally{
				DBClose.close(psmt, conn, rs);	
			}
			return list;
		}
		
		@Override
		public boolean disconfollow(String myid, String followid) {
			String sql = " UPDATE SNSFOLLOW SET "
					+ " CONNECTN = 1 "
					+ " WHERE MYID=? "
						+ " AND FOLLOWID=?";
			
			Connection conn = null;
			PreparedStatement psmt = null;
			int count = 0;
			
			try {
				conn = DBConnection.getConnection();	
				System.out.println("2/6 S disconfollow");
				
				psmt = conn.prepareStatement(sql);
				
				psmt.setString(1, myid);
				psmt.setString(2, followid);
				
				System.out.println("3/6 S disconfollow");
				
				count = psmt.executeUpdate();
				
				System.out.println("4/6 S disconfollow");
				
			} catch (SQLException e) {			
				e.printStackTrace();
			} finally{
				DBClose.close(psmt, conn, null);	
				System.out.println("5/6 S disconfollow");
			}		
			return count>0?true:false;
		}
		
		// 18.01.22
		// BANG
		// login 한 사람 출력
		public List<MemberDto> chatList(String id){
			List<MemberDto> list = new ArrayList<MemberDto>();
			
			String sql = " SELECT ID, NAME "
						+ " FROM SNSMEMBER "
						+ " WHERE LDEL = 0 "
							+ " AND ID != ? ";
			
			Connection conn = null;
			PreparedStatement psmt = null;
			ResultSet rs = null;
			
			try {			
				conn = DBConnection.getConnection();
				System.out.println("2/6 chatList Success");
				psmt = conn.prepareStatement(sql);	
				System.out.println("3/6 chatList Success");
				
				psmt.setString(1, id);
				
				rs = psmt.executeQuery();
				
				System.out.println("4/6 chatList Success");
				
				while(rs.next()){	
					int i = 1;
					MemberDto dto = new MemberDto(rs.getString(i++),		// ID
													rs.getString(i++)); 	// NAME
					list.add(dto);
				}
				System.out.println("5/6 chatList Success");
				
			} catch (SQLException e) {			
				e.printStackTrace();
			} finally{
				DBClose.close(psmt, conn, rs);	
			}
			
			return list;
		}
		
		// chat 테이블에 추가
		public boolean addchat(ChatDto dto) {
			int count = 0;
			
			Connection conn = null;
			PreparedStatement psmt = null;
			ResultSet rs = null;
			
			String sql = "INSERT INTO CHATLIST(MYID, CHATID, MYIP, CDEL) "
					+ " VALUES(?, ?, ?, 1) ";
			
			try {
				conn = DBConnection.getConnection();
				System.out.println("2/6 addchat Success");
				
				psmt = conn.prepareStatement(sql);
				System.out.println("3/6 addchat Success");
				
				psmt.setString(1, dto.getMyid());
				psmt.setString(2, dto.getChatid());
				psmt.setString(3, dto.getMyip());
				
				count = psmt.executeUpdate();
				System.out.println("4/6 addchat Success");
				
			} catch (SQLException e) {			
				System.out.println("addchat fail");
			} finally{
				DBClose.close(psmt, conn, rs);			
			}
			
			return count>0?true:false;
		}
		
		// chat 가능상태 확인
		public boolean chatTrue(String myid, String chatid) {
			int count = 0;
			
			Connection conn = null;
			PreparedStatement psmt = null;
			ResultSet rs = null;
			
			
			String sql = " SELECT * "
					+ " FROM CHATLIST "
					+ " WHERE MYID = ? "
						+ " AND CHATID = ? "
						+ " AND CDEL = 1 ";
			
			try {
				conn = DBConnection.getConnection();
				System.out.println("2/6 chatTrue Success");
				
				psmt = conn.prepareStatement(sql);
				System.out.println("3/6 chatTrue Success");
				
				psmt.setString(1, myid);
				psmt.setString(2, chatid);
				
				count = psmt.executeUpdate();
				
				System.out.println("4/6 chatTrue Success");
				
			} catch (SQLException e) {			
				System.out.println("chatTrue fail");
			} finally{
				DBClose.close(psmt, conn, rs);			
			}
			
			return count>0?true:false;
		}
		
		public boolean chatIdTrue(String myid) {
			int count = 0;
			
			Connection conn = null;
			PreparedStatement psmt = null;
			ResultSet rs = null;
			
			
			String sql = " SELECT * "
					+ " FROM CHATLIST C, SNSMEMBER M  "
					+ " WHERE C.MYID = M.ID "
						+ " AND C.CHATID = ? "
						+ " AND C.CDEL = 0 "
						+ " AND M.LDEL = 0 ";
			
			try {
				conn = DBConnection.getConnection();
				System.out.println("2/6 chatIdTrue Success");
				
				psmt = conn.prepareStatement(sql);
				System.out.println("3/6 chatIdTrue Success");
				
				psmt.setString(1, myid);
				
				count = psmt.executeUpdate();
				System.out.println("4/6 chatIdTrue Success");
				
			} catch (SQLException e) {			
				System.out.println("chatIdTrue fail");
			} finally{
				DBClose.close(psmt, conn, rs);			
			}
			
			return count>0?true:false;
		}

		// list로 뿌려서 나한테 대화를 걸어온 상대를 볼수 있음.
		public List<ChatDto> acceptid(String myid) {
			List<ChatDto> list = new ArrayList<ChatDto>();
			
			Connection conn = null;
			PreparedStatement psmt = null;
			ResultSet rs = null;
			
			
			String sql = " SELECT MYID, MYIP "
					+ " FROM CHATLIST "
					+ " WHERE CHATID = ? "
						+ " AND CDEL = 0 ";
			
			try {
				conn = DBConnection.getConnection();
				System.out.println("2/6 acceptid Success");
				
				psmt = conn.prepareStatement(sql);
				System.out.println("3/6 acceptid Success");
				
				psmt.setString(1, myid);
				
				rs = psmt.executeQuery();
				
				while(rs.next()){
					ChatDto dto = new ChatDto(rs.getString(1), null, rs.getString(2), 0);	
					list.add(dto);
				}
				
				System.out.println("4/6 acceptid Success");
				
			} catch (SQLException e) {			
				System.out.println("acceptid fail");
			} finally{
				DBClose.close(psmt, conn, rs);			
			}
			
			return list;
		}
		
		// 1대 1 채팅이 시작되면 CDEL을 1로 변환
		public void startChat(String myid, String chatid) {
			String sql = " UPDATE CHATLIST SET "
					+ " CDEL = 0 "
					+ " WHERE MYID=? "
						+ " AND CHATID=?";
			
			Connection conn = null;
			PreparedStatement psmt = null;
			int count = 0;
			
			try {
				conn = DBConnection.getConnection();	
				System.out.println("2/6 S startChatting");
				
				psmt = conn.prepareStatement(sql);
				
				psmt.setString(1, myid);
				psmt.setString(2, chatid);
				
				System.out.println("3/6 S startChatting");
				
				count = psmt.executeUpdate();
				
				System.out.println("4/6 S startChatting");
				
			} catch (SQLException e) {			
				e.printStackTrace();
			} finally{
				DBClose.close(psmt, conn, null);	
				System.out.println("5/6 S startChatting");
			}		
		}
		
		// 1대 1 채팅이 끝나면 CDEL을 0로 변환
		public void finishChat(String myid, String chatid) {
			String sql = " UPDATE CHATLIST SET "
					+ " CDEL = 1 "
					+ " WHERE MYID=? "
						+ " AND CHATID=?";
			
			Connection conn = null;
			PreparedStatement psmt = null;
			int count = 0;
			
			try {
				conn = DBConnection.getConnection();	
				System.out.println("2/6 S finishChat");
				
				psmt = conn.prepareStatement(sql);
				
				psmt.setString(1, myid);
				psmt.setString(2, chatid);
				
				System.out.println("3/6 S finishChat");
				
				count = psmt.executeUpdate();
				
				System.out.println("4/6 S finishChat");
				
			} catch (SQLException e) {			
				e.printStackTrace();
			} finally{
				DBClose.close(psmt, conn, null);	
				System.out.println("5/6 S finishChat");
			}		
		}
		
		
		@Override
		public void sortsup(int bgroup, int sorts) {
			
			Singleton s = Singleton.getInstance();
			
			String sql = " UPDATE SNSBBS "
					+ " SET SORTS = SORTS + 1 "
					+ " WHERE BGROUP = " + bgroup + " "
							+ "AND SORTS > " + sorts + " "; 
			
			Connection conn = DBConnection.getConnection();
			PreparedStatement stmt = null;
			int count = 0;
			
			System.out.println("sql:" + sql);
			
			try {
				stmt = conn.prepareStatement(sql);
				
				count = stmt.executeUpdate();		
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBClose.close(stmt, conn, null);			
			}	
		}

		@Override
		public boolean replyBbs(SnsDto dto, int bgroup, int depth) {
			
			String sql = " INSERT INTO SNSBBS "
					+ " (SEQ, BGROUP, SORTS, DEPTH, ID, CONTENT, WDATE, DEL, LIKECOUNT) "
					+ " VALUES(SEQ_SNSBBS.NEXTVAL, ?, ?, ?, ?, ?, SYSDATE, 0, 0) ";
			
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			
			int count = 0;
			
			try {
				conn = DBConnection.getConnection();
				stmt = conn.prepareStatement(sql);
				
				System.out.println("1/6 replyBbs Success!!");
				
				
				// ? -> setting
				stmt.setInt(1, bgroup);
				stmt.setInt(2, dto.getSorts());
				stmt.setInt(3, depth + 1);
				stmt.setString(4, dto.getId());
				stmt.setString(5, dto.getContent());
				
				System.out.println("2/6 replyBbs Success!!");
				
				count = stmt.executeUpdate();
				
				System.out.println("3/6 replyBbs Success!!");
				
			} catch (SQLException e) {
				System.out.println("replyBbs fail!!");
			} finally {
				DBClose.close(stmt, conn, null);
			}
			
			return count>0?true:false;
			
		}
		
		// 아라
		@Override
		public boolean likeCheck(int seq, String id) {
			
			String sql = " SELECT SEQ , LIKEID FROM LIKELIST "
					+ " WHERE SEQ=? "
							+ "AND LIKEID=? ";
			
			boolean findlike = false;
			
			Connection conn = null;
			PreparedStatement psmt = null;
			ResultSet rs = null;
					
			try {			
				conn = DBConnection.getConnection();
				System.out.println("2/6 likech Success");
				
				psmt = conn.prepareStatement(sql);	
				System.out.println("3/6 likech Success");
				
				psmt.setInt(1, seq);
				psmt.setString(2, id);	
				
				rs = psmt.executeQuery();
				System.out.println("4/6 likech Success");
				
				while(rs.next()){			
					findlike = true;			
				}
				
			} catch (SQLException e) {			
				e.printStackTrace();
			} finally{			
				DBClose.close(psmt, conn, rs);
				System.out.println("5/6 likech Success");
			}
			
			return findlike;
		}
		
		
		@Override
		public boolean likelist(int seq, String id) {
			
			int count = 0;
			
			Connection conn = null;
			PreparedStatement psmt = null;
			ResultSet rs = null;
			
			String sql = "INSERT INTO LIKELIST(SEQ, LIKEID, LIKEDEL) "
					+ " VALUES(?, ?, 0) ";
			
			try {
				conn = DBConnection.getConnection();
				System.out.println("2/6 like Success");
				
				psmt = conn.prepareStatement(sql);
				System.out.println("3/6 like Success");
				
				psmt.setInt(1, seq);
				psmt.setString(2, id);
				
				
				count = psmt.executeUpdate();
				System.out.println("4/6 like Success");
				
			} catch (SQLException e) {			
				System.out.println("like fail");
			} finally{
				DBClose.close(psmt, conn, rs);			
			}
			
			return count>0?true:false;
		}

		@Override
		public boolean dislikelist(int seq, String id) {
			String sql=" UPDATE LIKELIST SET  "
					+" LIKEDEL=1 "
					+" WHERE SEQ=?" 
							+ " AND LIKEID=? ";
			
			int count = 0;
			Connection conn=null;
			PreparedStatement psmt=null;
			
			try {
				conn = DBConnection.getConnection();			
				psmt=conn.prepareStatement(sql);
				psmt.setInt(1, seq);
				psmt.setString(2, id);	
				
				count = psmt.executeUpdate();
				
			} catch (SQLException e) {			
				e.printStackTrace();
			} finally{
				DBClose.close(psmt, conn, null);			
			}
					
			return count>0?true:false;
		}
		
		public boolean dislikecheck(int seq, String id) {
			
			String sql = " SELECT SEQ , LIKEID FROM LIKELIST "
					+ " WHERE SEQ=? "
							+ "AND LIKEID=? AND LIKEDEL = 1";
			

			boolean dislike = false;
			
			Connection conn = null;
			PreparedStatement psmt = null;
			ResultSet rs = null;
					
			try {			
				conn = DBConnection.getConnection();
				System.out.println("2/6 dislikech Success");
				
				psmt = conn.prepareStatement(sql);	
				System.out.println("3/6 dislikech Success");
				
				psmt.setInt(1, seq);
				psmt.setString(2, id);	
				
				rs = psmt.executeQuery();
				System.out.println("4/6 dislikech Success");
				
				while(rs.next()){			
					dislike = true;			
				}
				
			} catch (SQLException e) {			
				e.printStackTrace();
			} finally{			
				DBClose.close(psmt, conn, rs);
				System.out.println("5/6 dislikech Success");
			}
			
			return dislike;
		}
		
		public boolean dislikedel(int seq, String id) {
			String sql=" UPDATE LIKELIST SET  "
					+" LIKEDEL=0 "
					+" WHERE SEQ=?" 
							+ " AND LIKEID=? ";
			
			int count = 0;
			Connection conn=null;
			PreparedStatement psmt=null;
			
			try {
				conn = DBConnection.getConnection();			
				psmt=conn.prepareStatement(sql);
				psmt.setInt(1, seq);
				psmt.setString(2, id);	
				
				count = psmt.executeUpdate();
				
			} catch (SQLException e) {			
				e.printStackTrace();
			} finally{
				DBClose.close(psmt, conn, null);			
			}
					
			return count>0?true:false;
		}

		
		// ㅅㅎ
		@Override
		public boolean shareBbs(SnsDto dto, String id) {
			int count = 0;
			
			Connection conn = null;
			PreparedStatement psmt = null;
			ResultSet rs = null;
			
			if(dto.getSorts() != 0) {
				return false;
			}
			
			String sql = "INSERT INTO SNSBBS(SEQ, BGROUP, SORTS, DEPTH, ID, CONTENT, WDATE, DEL, LIKECOUNT) "
					+ " VALUES(SEQ_SNSBBS.NEXTVAL, SEQ_SNSBBS.NEXTVAL, 0, 0, ?, ?, SYSDATE, 0, 0) ";
			
			try {
				conn = DBConnection.getConnection();
				System.out.println("2/6 shareBbs Success");
				
				psmt = conn.prepareStatement(sql);
				System.out.println("3/6 shareBbs Success");
				
				psmt.setString(1, id);
				psmt.setString(2, dto.getContent());
				
				count = psmt.executeUpdate();
				System.out.println("4/6 shareBbs Success");
				
			} catch (SQLException e) {			
				System.out.println("shareBbs fail");
			} finally{
				DBClose.close(psmt, conn, rs);			
			}
			
			return count>0?true:false;
		}
*/		
		
		
}////////////////////////////////////BbsBoardDao
	
	
	
	
