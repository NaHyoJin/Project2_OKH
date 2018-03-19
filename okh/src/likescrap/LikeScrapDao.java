package likescrap;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import db.DBConnection;
import techbbs.TechbbsDto;
import techpds.PdsDto;
import db.DBClose;

public class LikeScrapDao implements iLikeScrapDao {
	public LikeScrapDao() {
		DBConnection.initConnection();
	}
	
	@Override
	public boolean addLikeID(TechbbsDto bbs) {
		String sql = " UPDATE TECHBBS SET LIKEID=? "
				+ " WHERE SEQ=? ";
		Connection conn = null;
		PreparedStatement psmt = null;
		
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();			
			System.out.println("1/6 addLikeID Success");
			
			psmt = conn.prepareStatement(sql);
			System.out.println("2/6 addLikeID Success");
			
			psmt.setString(1, bbs.getLikeid());
			psmt.setInt(2, bbs.getSeq());
			count = psmt.executeUpdate();
			
			System.out.println("3/6 addLikeID Success");
			
		} catch (SQLException e) {
			System.out.println("addLikeID fail");
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, null);			
		}
		
		return count>0?true:false;
	}


	@Override
	public boolean addDisLikeID(TechbbsDto bbs) {
		String sql = " UPDATE TECHBBS SET DISLIKEID=? "
				+ " WHERE SEQ=? ";
		Connection conn = null;
		PreparedStatement psmt = null;
		
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();			
			System.out.println("1/6 addDisLikeID Success");
			
			psmt = conn.prepareStatement(sql);
			System.out.println("2/6 addDisLikeID Success");
			
			psmt.setString(1, bbs.getDislikeid());
			psmt.setInt(2, bbs.getSeq());
			count = psmt.executeUpdate();
			
			System.out.println("3/6 addDisLikeID Success");
			
		} catch (SQLException e) {
			System.out.println("addDisLikeID fail");
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, null);			
		}
		
		return count>0?true:false;
	}
	
	

	@Override
	public String[] getids(String serchid) {
		StringTokenizer st= new StringTokenizer(serchid, "-");
		
		int len=st.countTokens();	//split.length
		System.out.println(len); 
		String[] serchids=new String[len];
		for (int i = 0; i < len; i++) {
			serchids[i]=st.nextToken();
		}
		
		return serchids;
	}


	@Override
	public String getLikeID(int seq) {
		String sql = " SELECT LIKEID "
				+ " FROM TECHBBS "
				+ " WHERE SEQ=? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		String likeid = "";
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 getLikeID Success");
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			
			System.out.println("2/6 getLikeID Success");
			
			rs = psmt.executeQuery();
			System.out.println("3/6 getLikeID Success");
			
			if(rs.next()) {
				likeid = rs.getString(1);	//regdate
			}	
			System.out.println("4/6 getLikeID Success");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("getLikeID fail");
			e.printStackTrace();			
		} finally {
			DBClose.close(psmt, conn, rs);	
			System.out.println("5/6 getLikeID Success");
		}		
		
		return likeid;
	}


	@Override
	public String getDisLikeID(int seq) {
		String sql = " SELECT DISLIKEID "
				+ " FROM TECHBBS "
				+ " WHERE SEQ=? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		String dislikeid = "";
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 getDisLikeID Success");
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			
			System.out.println("2/6 getDisLikeID Success");
			
			rs = psmt.executeQuery();
			System.out.println("3/6 getDisLikeID Success");
			
			if(rs.next()) {
				dislikeid = rs.getString(1);	//regdate
			}	
			System.out.println("4/6 getDisLikeID Success");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("getDisLikeID fail");
			e.printStackTrace();			
		} finally {
			DBClose.close(psmt, conn, rs);	
			System.out.println("5/6 getDisLikeID Success");
		}		
		
		return dislikeid;
	}


	@Override
	public boolean isitlikeid(int seq,String serchlikeid) {
		Connection conn=null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		TechbbsDto dto=null;
		
		String sql1 = " SELECT SEQ,ID, TITLE,TAGNAME,CONTENT,WDATE,DEL,  "
				+ " READCOUNT,LIKECOUNT,LIKEID,DISLIKEID,COMMENTCOUNT,SCRAPCOUNT "
				+ "  FROM TECHBBS "
				+ " WHERE SEQ=? AND LIKEID LIKE '%-" + serchlikeid.trim() + "-%' ";
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 getdetail Success");			
		psmt = conn.prepareStatement(sql1);	
		
		psmt.setInt(1, seq);			
		rs = psmt.executeQuery();
		System.out.println("2/6 getdetail Success");		
		while(rs.next()){		
			int seq1=rs.getInt(1);
			String id = rs.getString(2);
			String title = rs.getString(3);
			String tagname = rs.getString(4);
			String content = rs.getString(5);
			String wdate = rs.getString(6);	
			int del = rs.getInt(7);
			int readcount = rs.getInt(8);
			int likecount = rs.getInt(9);
			String likeid = rs.getString(10);	
			String dislikeid = rs.getString(11);	
			int commentcount = rs.getInt(12);
			int scrapcount = rs.getInt(13);
			int likeidyn=0;
			likeidyn=dto!=null?1:2;	//id있으면 1(취소) id없으면2(추가)
			dto = new TechbbsDto(seq1, id, title, tagname, content, wdate, del, readcount, likecount, likeid,dislikeid, likeidyn);
			
			
		}
		System.out.println("3/6 getdetail Success");		
		} catch (SQLException e) {	
			System.out.println("getdetail fail");		
			e.printStackTrace();
		} finally{
			DBClose.close(psmt, conn, rs);	
		}
		return dto!=null?true:false;
	}


	@Override
	public boolean isitdislikeid(int seq,String serchlikeid) {
		Connection conn=null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		TechbbsDto dto=null;
		
		String sql1 = " SELECT SEQ,ID, TITLE,TAGNAME,CONTENT,WDATE,  "
				+ " READCOUNT,LIKECOUNT,LIKEID,DISLIKEID,COMMENTCOUNT,SCRAPCOUNT "
				+ "  FROM TECHBBS "
				+ " WHERE SEQ=? AND DISLIKEID LIKE '%-" + serchlikeid.trim() + "-%' ";
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 isitdislikeid Success");			
		psmt = conn.prepareStatement(sql1);	
		
		psmt.setInt(1, seq);			
		rs = psmt.executeQuery();
		System.out.println("2/6 isitdislikeid Success");		
		while(rs.next()){		
			int seq1=rs.getInt(1);
			String id = rs.getString(2);
			String title = rs.getString(3);
			String tagname = rs.getString(4);
			String content = rs.getString(5);
			String wdate = rs.getString(6);	
			int readcount = rs.getInt(7);
			int likecount = rs.getInt(8);
			String likeid = rs.getString(9);	
			String dislikeid = rs.getString(10);	
			int commentcount = rs.getInt(11);
			int scrapcount = rs.getInt(12);
			int dislikeidyn=0;
			dislikeidyn=dto!=null?1:2;	//id있으면 1 id없으면2
			dto = new TechbbsDto(seq1, id, title, tagname, content, wdate, readcount, likecount, likeid, dislikeid, dislikeidyn);
			
			
		}
		System.out.println("3/6 isitdislikeid Success");		
		} catch (SQLException e) {	
			System.out.println("isitdislikeid fail");		
			e.printStackTrace();
		} finally{
			DBClose.close(psmt, conn, rs);	
		}
		return dto!=null?true:false;
	}


	@Override
	public boolean deleteLikeID(String deleteid, int seq) {
		String getalllikeid=getLikeID(seq);
		String[] ids=getids(getalllikeid);
		String up="-";
		for (int i = 0; i < ids.length; i++) {
			if (ids[i].equals(deleteid)) {
				
			}else {
				up=up+ids[i];
			}
		}
		System.out.println(up);
		String sql = " UPDATE TECHBBS SET LIKEID=? "
				+ " WHERE SEQ=? ";
		Connection conn = null;
		PreparedStatement psmt = null;
		
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();			
			System.out.println("1/6 deleteLikeID Success");
			
			psmt = conn.prepareStatement(sql);
			System.out.println("2/6 deleteLikeID Success");
			
			psmt.setString(1, up);
			psmt.setInt(2, seq);
			count = psmt.executeUpdate();
			
			System.out.println("3/6 deleteLikeID Success");
			
		} catch (SQLException e) {
			System.out.println("deleteLikeID fail");
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, null);			
		}
		
		return count>0?true:false;
	}


	@Override
	public boolean deleteaddDisLikeID(String deleteid, int seq) {
		System.out.println(deleteid+"이건..?"+seq);
		String getall_dislikeid=getDisLikeID(seq);
		String[] ids=getids(getall_dislikeid);
		String up="-";
		for (int i = 0; i < ids.length; i++) {
			if (ids[i].equals(deleteid)) {
				
			}else {
				up=up+ids[i];
			}
		}
		
		String sql = " UPDATE TECHBBS SET DISLIKEID=? "
				+ " WHERE SEQ=? ";
		Connection conn = null;
		PreparedStatement psmt = null;
		
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();			
			System.out.println("1/6 deleteLikeID Success");
			
			psmt = conn.prepareStatement(sql);
			System.out.println("2/6 deleteLikeID Success");
			
			psmt.setString(1, up);
			psmt.setInt(2, seq);
			count = psmt.executeUpdate();
			
			System.out.println("3/6 deleteLikeID Success");
			
		} catch (SQLException e) {
			System.out.println("deleteLikeID fail");
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, null);			
		}
		
		return count>0?true:false;
	}


	
	
	
}

