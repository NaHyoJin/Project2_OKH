package jobs_BBS5;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBClose;
import db.DBConnection;

public class jobsBbs5MaterialsDao implements jobsBbs5MaterialsDaoImpl {
	
	//싱글톤 부분.
	private static jobsBbs5MaterialsDao pdsDao = new jobsBbs5MaterialsDao();
	
	private jobsBbs5MaterialsDao() {
		// TODO Auto-generated constructor stub
	}
	
	public static jobsBbs5MaterialsDao getInstance() {
		return pdsDao;
	}
	
	
	
	@Override
	public List<BbsMaterialsBeanDtoVO> getPdsList() {
		// TODO Auto-generated method stub
		
		String sql = " select seq, id, title, content, filename, readcount, downcount, regdate "
				+ " from BbsMaterialsBeanDtoVO "
				+ " order by seq desc ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		List<BbsMaterialsBeanDtoVO> list = new ArrayList<BbsMaterialsBeanDtoVO>();
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 getPdsList Success");
			
			psmt = conn.prepareStatement(sql);
			System.out.println("2/6 getPdsList Success");
			
			rs = psmt.executeQuery();
			System.out.println("3/6 getPdsList Success");
			
			while (rs.next()) {
				BbsMaterialsBeanDtoVO dto = new BbsMaterialsBeanDtoVO(rs.getInt(1),//seq, 
										rs.getString(2),//id, 
										rs.getString(3),//title, 
										rs.getString(4),//content, 
										rs.getString(5),//filename, 
										rs.getInt(6),//readcount, 
										rs.getInt(7),//downcount, 
										rs.getString(8)//regdate
										);
				list.add(dto);
			}
			System.out.println("4/6 getPdsList Success");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("getPdsList fail");
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
			System.out.println("5/6 getPdsList Success");
		}

		return list;
	}

	
	//파일 DB에 집어넣는 부분.
	@Override
	public boolean writePds(BbsMaterialsBeanDtoVO pds) {

		String sql = " INSERT INTO BbsMaterialsBeanDtoVO( "
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
	
	
	
	public boolean downloadcount(int seq) {
		
		String sql = " update BbsMaterialsBeanDtoVO "
				+ " set downcount=downcount+1 "
				+ " where seq=? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 downloadcount");
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			System.out.println("2/6 downloadcount");
			
			count = psmt.executeUpdate();
			System.out.println("3/6 downloadcount");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("downloadcount 실패");
			e.printStackTrace();
		}finally {
			System.out.println("4/6 downloadcount");
			DBClose.close(psmt, conn, null);
		}
		
		return count>0?true:false;
	}
	
	
	//검색 부분.
	@Override
	public List<BbsMaterialsBeanDtoVO> getPdsPagingList(PagingBean paging, String searchWord, int search) {
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		//담을 리스트 준비
		//자료실 게시판이 테이블 별도로 필요. 
		List<BbsMaterialsBeanDtoVOTable> bbslist = new ArrayList<BbsMaterialsBeanDtoVOTable>();
		List<BbsMaterialsBeanDtoVO> pdslist = new ArrayList<BbsMaterialsBeanDtoVO>();
		
		//찾는 번호 제목인지 작성자인지 내용인지 구분하는 int 번호
		System.out.println("search = " + search);
		
		String sWord = "";
		
		if(search == 0) {	// 제목
			sWord = " WHERE TITLE LIKE '%" + searchWord.trim() + "%' ";
		}else if(search == 1) {	// 작성자
			sWord = " WHERE ID='" + searchWord.trim() + "' ";
		}else if(search == 2) {	// 내용
			//0308추가 코드 부분.
			sWord = " where content like '%" + searchWord.trim() + "%' ";
		}else if(search == 3) {	//제목 + 내용으로 검색.
			//0308추가 코드 부분.
			sWord = " where title like '%" + searchWord.trim() + "%' "
					+ " 	or content like '%" + searchWord.trim() + "%' ";
		}
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 getPdsPagingList Success");
			
			// 글의 총수
			String totalSql = " SELECT COUNT(SEQ) FROM BbsMaterialsBeanDtoVOTable " + sWord;
			
			psmt = conn.prepareStatement(totalSql);
			rs = psmt.executeQuery();
			System.out.println("2/6 getPdsPagingList Success");
			
			int totalCount = 0;
			rs.next();
			totalCount = rs.getInt(1);	// row의 총 갯수
			
			paging.setTotalCount(totalCount);
			paging = PagingUtil.setPagingInfo(paging);
			
			psmt.close();
			rs.close();
			
			// row를 취득
			String sql = " SELECT * FROM "
						+ " (SELECT * FROM (SELECT * FROM BbsMaterialsBeanDtoVO " + sWord + " ORDER BY seq ASC)"
						+ "  WHERE ROWNUM <=" + paging.getStartNum() + " ORDER BY seq DESC) "
						+ " WHERE ROWNUM <=" + paging.getCountPerPage();
			
			System.out.println("paging.getStartNum() = " + paging.getStartNum());
			
			psmt = conn.prepareStatement(sql);
			System.out.println("3/6 getPdsPagingList Success");
			
			rs = psmt.executeQuery();
			System.out.println("4/6 getPdsPagingList Success");
			
			while(rs.next()) {
				
				BbsMaterialsBeanDtoVO pdsdto = 
						new BbsMaterialsBeanDtoVO(rs.getInt(1),//seq, 
											rs.getString(2),//id, 
											rs.getString(3),//title, 
											rs.getString(4),//content, 
											rs.getString(5),//filename, 
											rs.getInt(6),//readcount, 
											rs.getInt(7),//downcount, 
											rs.getString(8)//regdate
											);
				pdslist.add(pdsdto);				
			}
			System.out.println("5/6 getPdsPagingList Success");			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("getPdsPagingList Fail");
		} finally {
			DBClose.close(psmt, conn, rs);
			System.out.println("6/6 getPdsPagingList Success");	
		}
		
		return pdslist;
	}

	
	//pds 디테일 하나 가지고 오는 부분.
	@Override
	public BbsMaterialsBeanDtoVO getPds(int seq) {
		/*
		 	CREATE TABLE PDS(
				SEQ NUMBER(8) PRIMARY KEY,
				ID VARCHAR2(50) NOT NULL,
				TITLE VARCHAR2(200) NOT NULL,
				CONTENT VARCHAR2(4000) NOT NULL,
				FILENAME VARCHAR2(50) NOT NULL,
				READCOUNT NUMBER(8) NOT NULL,
				DOWNCOUNT NUMBER(8) NOT NULL,
				REGDATE DATE NOT NULL
			);
		 */
		String sql = " SELECT SEQ, ID, TITLE, CONTENT, "
						+ " FILENAME, READCOUNT, DOWNCOUNT, REGDATE "
					+ " FROM BbsMaterialsBeanDtoVO "
					+ " WHERE SEQ=? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		BbsMaterialsBeanDtoVO dto = null;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 getPds Success");
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			
			System.out.println("2/6 getPds Success");
			
			rs = psmt.executeQuery();
			System.out.println("3/6 getPds Success");
			
			if(rs.next()) {
				int i = 1;
				dto = new BbsMaterialsBeanDtoVO(rs.getInt(i++), // seq,
						rs.getString(i++), // id,
						rs.getString(i++), //title, 
						rs.getString(i++), //content, 
						rs.getString(i++), //FILENAME, 
						rs.getInt(i++), //READCOUNT, 
						rs.getInt(i++), //DOWNCOUNT, 
						rs.getString(i++)); // REGDATE				
			}	
			System.out.println("4/6 getPds Success");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("getPds fail");
			e.printStackTrace();			
		} finally {
			DBClose.close(psmt, conn, rs);	
			System.out.println("5/6 getPds Success");
		}		
		
		return dto;
	}
	
	
	//조회수 부분.
	@Override
	public void readcount(int seq) {
		String sql = " UPDATE BbsMaterialsBeanDtoVO "
				+ " SET READCOUNT=READCOUNT+1 "
				+ " WHERE SEQ=? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 readcount Success");
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			System.out.println("2/6 readcount Success");
			
			psmt.executeUpdate();
			System.out.println("3/6 readcount Success");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("readcount Fail");
		} finally {
			DBClose.close(psmt, conn, null);			
		}		
	}
}

