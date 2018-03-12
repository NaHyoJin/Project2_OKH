package modelService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.CustUserBean;

public class CustUserManager implements iCustUserManager {

	
	
	public CustUserManager() {
		// TODO Auto-generated constructor stub
		
		try {
			
			//오라클 드라이버 설정.
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	//인간 등록하는 부분.
		public int custAdd(String id, String name, String address) {
			
			
			String sql = " INSERT INTO custuser "
					+ " (id, name, address) "
					+ " VALUES(?, ?, ?) ";
			
			
			Connection conn = null;
			PreparedStatement psmt = null;
			ResultSet rs = null;//이건 사실 필요없다.
			
			
			int count = 0;
			
							
			try {
				
				conn = getConnection();
				System.out.println("1/6 custAdd Success");
				
				psmt = conn.prepareStatement(sql);
				System.out.println("2/6 custAdd Success");
							
				
				// ? -> setting
				psmt.setString(1, id.trim());//.trim()센스로 넣어주자.
				psmt.setString(2, name.trim());
				psmt.setString(3, address.trim());
				
				
				count = psmt.executeUpdate();//직접 실행 부분.
				
				System.out.println("3/6 custAdd Success");
				
			} catch (SQLException e) {
				System.out.println("custAdd fail");
			} finally {
				close(conn, psmt, null);
			}
			
//			return count>0?true:false;
			return count;
			
		}
		
		
		
		//전체 삭제 할시.
		public boolean deleteCustUser(String[] ids) {
			
			String sql = " delete from custuser "
					+ " where id = ? ";
			
			Connection conn = null;
			PreparedStatement psmt = null;
			
			int count[] = new int[ids.length];
			
			try {
				
				conn = getConnection();
				
				//이클립스는 자동 커밋이 되어있다.
				
				conn.setAutoCommit(false);//자동 커밋 꺼주는것. off
				
				psmt = conn.prepareStatement(sql);
				
				
				//psmt addBatch
				for (int i = 0; i < ids.length; i++) {
					
					psmt.setString(1, ids[i].trim());//배열로 넘어오니.
					psmt.addBatch();//묶어 놓는것.
					
				}
				
			count = psmt.executeBatch();//다수의 update 정상 작동 안되면 catch부분으로 이동됨.
			conn.commit();//수동 커밋.
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
				//문제 생길시 원상복구. count = psmt.executeBatch(); 문제 생길시
				try {
					conn.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}finally {
				try {
					conn.setAutoCommit(true);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				close(conn, psmt, null);
			}
			
			
			boolean isSuc = true;
			
			for (int i = 0; i < count.length; i++) {
				System.out.println("count [ " + i +" ] = " + count[i]);
				
				if(count[i] != -2) {//-2라는것은 정상.
					isSuc = false;
					break;
				}
			}

			return isSuc;
		}
		
		
	
		//아이디 찾기 부분.
		public CustUserBean getCustUser(String id) {
			
			String sql = " select id, name, address "
					+ " from custuser " //form 이라고 적어놨었어 미쳤어....
					+ " where id = ? ";
			
			Connection conn = null;
			PreparedStatement psmt = null;
			ResultSet rs = null;
			
			CustUserBean dto = null;
			
			try {
				conn = getConnection();
				
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, id.trim());
				
				rs = psmt.executeQuery();
				
				while (rs.next()) {
					dto = new CustUserBean();
					dto.setId(rs.getString("id"));
					dto.setName(rs.getString("name"));
					dto.setAddress(rs.getString("address"));
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				close(conn, psmt, rs);
			}
			
			return dto;
		}
		
		
		
	
	//전체 리스트 가지고 오는 부분.
	@Override
	public List<CustUserBean> getCustUserList() {
		
		
		String sql = " select id, name, address "
				+ " from custuser "
				+ " order by id desc ";
		
		Connection conn = null;		
		PreparedStatement psmt = null;		
		ResultSet rs = null;
		
		List<CustUserBean> list = new ArrayList<CustUserBean>();
		
		
		try {
			conn = getConnection();
		
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
		
		
		while(rs.next()) {
			
			CustUserBean cust = new CustUserBean();
			cust.setId(rs.getString("id"));
			cust.setName(rs.getString("name"));
			cust.setAddress(rs.getString("address"));
			
			list.add(cust);//리스트에 집어넣는.
			
		}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, psmt, rs);
		}
		
		
		return list;
	}
	
	
	
	//접속하는 부분.
		public Connection getConnection() throws SQLException{
			
			String url = "jdbc:oracle:thin:@192.168.10.57:1521:xe";
			String user = "hr";
			String pass = "hr";
			
			
			Connection conn = DriverManager.getConnection(url, user, pass);//접속 준비 부분.
			
			return conn;
		}
		
		
		
		//접속 종료 부분.
		public void close(Connection conn, PreparedStatement psmt, ResultSet rs) {
			
			try {
				
				if(rs != null) {			
					rs.close();
				}
				if(psmt != null) {
					psmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	

}
