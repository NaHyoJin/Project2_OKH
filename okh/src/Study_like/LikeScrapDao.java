package Study_like;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import db.DBConnection;
import studysrc.CombbsDto;
import db.DBClose;

public class LikeScrapDao implements iLikeScrapDao {

	@Override
	public boolean addLikeID(CombbsDto bbs) {
		String sql = " UPDATE COMBBS SET JOINNER=? "
				+ " WHERE SEQ=? ";
		
		
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();			
			System.out.println("1/6 addLikeID Success");
			
			
			
			psmt = conn.prepareStatement(sql);
			System.out.println("2/6 addLikeID Success");
			
			psmt.setString(1, bbs.getJoinner());
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
		String sql = " SELECT JOINNER "
				+ " FROM COMBBS "
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
	public boolean isitlikeid(int seq,String serchlikeid) {
		Connection conn=null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		CombbsDto dto=null;
		
		String sql1 = " SELECT SEQ,ID,TITLE,CONTENT,WDATE,DEL,READCOUNT, "
				+ " COMMENTCOUNT, TAGNAME,PARENT,JOINERCOUNT,JOINDATE,JOINNER "
				+ "  FROM COMBBS "
				+ " WHERE SEQ=? AND JOINNER LIKE '%-" + serchlikeid.trim() + "-%' ";
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 isitlikeid Success");			
		psmt = conn.prepareStatement(sql1);	
		
		psmt.setInt(1, seq);			
		rs = psmt.executeQuery();
		System.out.println("2/6 isitlikeid Success");		
		while(rs.next()){		
			int seq1=rs.getInt(1);
			String id = rs.getString(2);
			String title = rs.getString(3);
			String content =rs.getString(4);
			String wdate = rs.getString(5);
			int del = rs.getInt(6);
			int readcount = rs.getInt(7);
			int commentcount = rs.getInt(8);
			String tagname = rs.getString(9);
			int parent = rs.getInt(10);
			int joinercount = rs.getInt(11);
			String joindate = rs.getString(12);
			String joinner = rs.getString(13);
			int likeidyn=0;
			likeidyn=dto!=null?1:2;	//id있으면 1(취소) id없으면2(추가)
			dto = new CombbsDto(seq1,id,title,content,wdate,del,readcount,commentcount,tagname,parent,joinercount,joindate,joinner,likeidyn);
			
		}
		System.out.println("3/6 isitlikeid Success");		
		} catch (SQLException e) {	
			System.out.println("isitlikeid fail");		
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
		String sql = " UPDATE COMBBS SET JOINNER=? "
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

