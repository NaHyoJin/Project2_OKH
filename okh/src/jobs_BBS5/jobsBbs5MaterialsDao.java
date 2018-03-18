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

	//DB연결부분이 없었다. 정병찬 디버그 180316
	public jobsBbs5MaterialsDao() {
		DBConnection.initConnection();
	}
	
	//모든글 다 가지고 오는것.
	@Override
	public List<BbsMaterialsBeanDtoVO> getPdsList() {
		// TODO Auto-generated method stub
		
		String sql = " select * "
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
										rs.getInt(3),//up
										rs.getInt(4),//up
										rs.getInt(5),//up
										rs.getString(6),//title, 
										rs.getString(7),//content, 
										rs.getString(8),//tag
										rs.getString(9),//filename, 
										rs.getInt(10),//up
										rs.getInt(11),//down 반대
										rs.getString(12),//작성일.
										rs.getInt(13),//부모글.
										rs.getInt(14),//del 삭제 번호.
										rs.getInt(15),//readcount, 
										rs.getInt(16),//downcount, 
										rs.getString(17)//regdate
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
	
	@Override
	public List<BbsMaterialsBeanDtoVO> getPdsList(int parent) {
		// TODO Auto-generated method stub
		
		String sql = " select * "
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
						rs.getInt(3),//up
						rs.getInt(4),//up
						rs.getInt(5),//up
						rs.getString(6),//title, 
						rs.getString(7),//content, 
						rs.getString(8),//tag
						rs.getString(9),//filename, 
						rs.getInt(10),//up
						rs.getInt(11),//down 반대
						rs.getString(12),//작성일.
						rs.getInt(13),//부모글.
						rs.getInt(14),//del 삭제 번호.
						rs.getInt(15),//readcount, 
						rs.getInt(16),//downcount, 
						rs.getString(17)//regdate
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
		
		//글 작성 들어오나 확인 코드
		System.out.println("jobsBbs5MaterialsDao writePds");
		
		String sql = " INSERT INTO BbsMaterialsBeanDtoVO(SEQ, ID, "
				+ " REF, STEP, DEPTH, "
				+ " TITLE, CONTENT, TAG, FILENAME, UP, DOWN, WDATE, PARENT, "
				+ " DEL, READCOUNT, downcount, regdate) "
				+ " VALUES(SEQ_BbsMaterialsBeanDtoVO.NEXTVAL, ?, "//시퀀스 이름이 틀렸다...십할...
				+ " (SELECT NVL(MAX(REF), 0)+1 FROM BbsMaterialsBeanDtoVO), 0, 0, "
				+ " ?, ?, ?, ?, 0, 0, SYSDATE, 0, "
				+ " 0, 0, 0, SYSDATE) ";
		
		int count = 0;
		
		Connection conn = null;
		PreparedStatement psmt = null;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 S writePds");

			psmt = conn.prepareStatement(sql);
			psmt.setString(1, pds.getId());
			System.out.println("pds.getid : " + pds.getId());
			psmt.setString(2, pds.getTitle());
			System.out.println("pds.getTitle : " + pds.getTitle());
			psmt.setString(3, pds.getContent());
			System.out.println("pds.getContent : " + pds.getContent());
			psmt.setString(4, pds.getTag());
			System.out.println("pds.getTag : " + pds.getTag());
			psmt.setString(5, pds.getFilename());
			System.out.println("pds.getFilename : " + pds.getFilename());
			System.out.println("2/6 S writePds");

			count = psmt.executeUpdate();
			System.out.println("3/6 S writePds");

		} catch (SQLException e) {
			System.out.println("F writePds");
		} finally {
			DBClose.close(psmt, conn, null);
			System.out.println("4/6 finally S writePds");
		}

		return count>0?true:false;
	}
	
	
	//다운로드 수
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
	public List<BbsMaterialsBeanDtoVO> getPdsPagingList(PagingBean paging, 
														String searchWord, 
														int search) {
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		//담을 리스트 준비
		//자료실 게시판이 테이블 별도로 필요. 
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
			String totalSql = " SELECT COUNT(SEQ) FROM BbsMaterialsBeanDtoVO " + sWord;
			
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
			System.out.println("3/6 BbsMaterialsBeanDtoVO getPdsPagingList Success");
			
			rs = psmt.executeQuery();
			System.out.println("4/6 getPdsPagingList Success");
			
			while(rs.next()) {
				
				BbsMaterialsBeanDtoVO pdsdto = new BbsMaterialsBeanDtoVO(rs.getInt(1),//seq, 
						rs.getString(2),//id, 
						rs.getInt(3),//up
						rs.getInt(4),//up
						rs.getInt(5),//up
						rs.getString(6),//title, 
						rs.getString(7),//content, 
						rs.getString(8),//tag
						rs.getString(9),//filename, 
						rs.getInt(10),//up
						rs.getInt(11),//down 반대
						rs.getString(12),//작성일.
						rs.getInt(13),//부모글.
						rs.getInt(14),//del 삭제 번호.
						rs.getInt(15),//readcount, 
						rs.getInt(16),//downcount, 
						rs.getString(17)//regdate
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
		
		String sql = " SELECT * "
					+ " FROM BbsMaterialsBeanDtoVO "
					+ " WHERE SEQ=? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		BbsMaterialsBeanDtoVO dto = null;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 BbsMaterialsBeanDtoVO getPds Success");
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			
			System.out.println("2/6 BbsMaterialsBeanDtoVO getPds Success");
			
			rs = psmt.executeQuery();
			System.out.println("3/6 BbsMaterialsBeanDtoVO getPds Success");
			
			if(rs.next()) {
				int i = 1;//i++ 이런식으로 사용할려고 준비한 변수.
				
				dto = new BbsMaterialsBeanDtoVO(rs.getInt(1),//seq, 
						rs.getString(2),//id, 
						rs.getInt(3),
						rs.getInt(4),
						rs.getInt(5),
						rs.getString(6),//title, 
						rs.getString(7),//content, 
						rs.getString(8),//tag
						rs.getString(9),//filename, 
						rs.getInt(10),//up
						rs.getInt(11),//down 반대
						rs.getString(12),//작성일.
						rs.getInt(13),//부모글.
						rs.getInt(14),//del 삭제 번호.
						rs.getInt(15),//readcount, 
						rs.getInt(16),//downcount, 
						rs.getString(17)//regdate
						);			
			}	
			System.out.println("4/6 BbsMaterialsBeanDtoVO getPds Success");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("BbsMaterialsBeanDtoVO getPds fail");
			e.printStackTrace();			
		} finally {
			DBClose.close(psmt, conn, rs);
			System.out.println("5/6 BbsMaterialsBeanDtoVO getPds Success");
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
	}/////////////readcount
	
	
	@Override
	public boolean updateBbs(BbsMaterialsBeanDtoVO bbs) {
		//seq, id, title, content, tag, filename
		String sql = " UPDATE BbsMaterialsBeanDtoVO "
				+ "SET TITLE=?, CONTENT=?, TAG=?, filename=? "
				+ "WHERE SEQ=? ";
		
		System.out.println("BbsMaterialsBeanDtoVO updateBbs bbs in dao : " + bbs.toString());
		
		Connection conn = null;
		PreparedStatement psmt = null;
		
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 BbsMaterialsBeanDtoVO updateBbs Success");
			
			psmt = conn.prepareStatement(sql);
			System.out.println("2/6 BbsMaterialsBeanDtoVO updateBbs Success");
			
			System.out.println("bbs.getTitle() : " + bbs.getTitle());
			psmt.setString(1, bbs.getTitle().trim());
			psmt.setString(2, bbs.getContent().trim());
			System.out.println("bbs.getTag() : " + bbs.getTag());
			psmt.setString(3, bbs.getTag().trim());
			// filename 잘 가지고 오는지 확인 부분.
			System.out.println("bbs.getFilename() : " + bbs.getFilename());
			psmt.setString(4, bbs.getFilename().trim());
			psmt.setInt(5, bbs.getSeq());
			
			count = psmt.executeUpdate();
			System.out.println("3/6 BbsMaterialsBeanDtoVO updateBbs Success");
			System.out.println("count in dao executeUpdate : " + count);
			
		} catch (SQLException e) {
			System.out.println("updateBbs Fail");
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, null);
			System.out.println("4/6 updateBbs Success finally");
		}
		
		return count>0?true:false;
	}
	
	//일반 게시판 삭제 부분.
	@Override
	public boolean deleteMaterials(int seq) {
		String sql = " UPDATE BbsMaterialsBeanDtoVO SET DEL=1 WHERE SEQ=? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 deleteMaterials Success");
			
			psmt = conn.prepareStatement(sql);
			System.out.println("2/6 deleteMaterials Success");
			
			psmt.setInt(1, seq);
			
			count = psmt.executeUpdate();
			System.out.println("3/6 deleteMaterials Success");
			
		} catch (SQLException e) {
			System.out.println("deleteMaterials Fail");
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, null);
		}
		
		return count>0?true:false;
	}
}

