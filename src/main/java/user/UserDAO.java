package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	public UserDAO() {
		try {
			String dbURL = "jdbc:mysql://localhost:3306/bbs";
			String dbID = "root";
			String dbPassword = "1234";
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<User> getAllUsers() {
		String SQL = "SELECT * FROM user";
		List<User> userList = new ArrayList<>();

		try {
			pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				User user = new User();
				user.setUserID(rs.getString("userID"));
				user.setUserPassword(rs.getString("userPassword"));
				user.setUserName(rs.getString("userName"));
				user.setUserGender(rs.getString("userGender"));
				user.setUserEmail(rs.getString("userEmail"));
				user.setUserAddress(rs.getString("userAddress"));
				user.setUserPhone(rs.getString("userPhone"));

				userList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return userList; // 사용자 정보가 없을 경우 빈 리스트 반환
	}

	// 회원가입 메서드
	public int join(User user) {
		String SQL = "INSERT INTO user (userID, userPassword, userName, userGender, userEmail, userAddress, userPhone) VALUES (?, ?, ?, ?, ?, ?, ?)";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, user.getUserID());
			pstmt.setString(2, user.getUserPassword());
			pstmt.setString(3, user.getUserName());
			pstmt.setString(4, user.getUserGender());
			pstmt.setString(5, user.getUserEmail());
			pstmt.setString(6, user.getUserAddress());
			pstmt.setString(7, user.getUserPhone());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1; // Database error
	}

	// 로그인 메서드
	public int login(String userID, String userPassword) {
		String SQL = "SELECT userPassword FROM USER WHERE userID = ?";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				String resultPassword = rs.getString(1);

				if (resultPassword != null && resultPassword.equals(userPassword))
					return 1; // 로그인 성공

				else
					return 0; // 비밀번호 불일치 or password is NULL in DB.

			}
			return -1; // ID가 없음.

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return -2; // DB 오류.
	}

	
	// 사용자 이름 조회 메서드
	public String getUserName(String userID) {
		String SQL = "SELECT userName FROM user WHERE userID = ?";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String userName = rs.getString("userName");
				return userName;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; // 사용자 이름이 없을 경우 null 반환
	}
	public boolean deleteAllUserRelatedData(String userID) {
	    try {
	        // Turn off auto-commit
	        conn.setAutoCommit(false);

	        // Delete all posts written by the user from the 'bbs' table
	        String SQL1 = "DELETE FROM bbs WHERE userID = ?";
	        PreparedStatement pstmt1 = conn.prepareStatement(SQL1);
	        pstmt1.setString(1, userID);
	        pstmt1.executeUpdate();

	        // Delete all products posted by the user from the 'product' table
	        String SQL2 = "DELETE FROM product WHERE userID = ?";
	        PreparedStatement pstmt2 = conn.prepareStatement(SQL2);
	        pstmt2.setString(1, userID);
	        pstmt2.executeUpdate();

	       // Delete all help requests made by the user from the 'help' table
	       String SQL3 = "DELETE FROM help WHERE userID = ?";
	       PreparedStatement pstmt3 = conn.prepareStatement(SQL3);
	       pstmt3.setString(1, userID);
	       pstmt3.executeUpdate();

	      // Delete all orders made by the user from the 'orders' table
	      String SQL4= "DELETE FROM orders WHERE userID=?";
	      PreparedStatement stmt4=conn.prepareStatement(SQL4); 
	      stmt4.setString(1,userID); 
	      int rowsAffectedOrdersTable=0; 
	      rowsAffectedOrdersTable+=stmt4.executeUpdate(); 

	     // Delete user from the 'user' table
	     String SQL5= "DELETE FROM user WHERE userID=?";
	     PreparedStatement stmt5=conn.prepareStatement(SQL5); 
	     stmt5.setString(1,userID); 
	     int rowsAffectedUserTable=0; 
	     rowsAffectedUserTable+=stmt5.executeUpdate(); 

	    if(rowsAffectedOrdersTable > 0 && rowsAffectedUserTable > 0){
	         return true;
	    }
	   else{
	         return false;
	   }

	    } catch (SQLException e) {
	      try {
	          if (conn != null) {
	              // An error occurred. Roll back all changes made in this transaction.
	              conn.rollback();
	          }
	      } catch (SQLException e2) {
	          e.printStackTrace();
	      }
	      
	    } finally {
	      
		  try {	
			if(conn != null) {	
				// Turn auto-commit back on.
				conn.setAutoCommit(true);	
			}	
		  } catch(SQLException e){	
			  e.printStackTrace();	
		  }
		  
		  return false;  // Return false if an exception was thrown
		
	    }

	}

	// 사용자 이메일 조회 메서드
	public String getUserEmail(String userID) {
		String SQL = "SELECT userEmail FROM user WHERE userID = ?";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getString("userEmail");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; // 사용자 이메일이 없을 경우 null 반환
	}

	// 사용자 정보 조회 메서드
	public User getUserByID(String userID) {
		String SQL = "SELECT * FROM user WHERE userID = ?";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				User user = new User();
				user.setUserID(rs.getString("userID"));
				user.setUserPassword(rs.getString("userPassword"));
				user.setUserName(rs.getString("userName"));
				user.setUserGender(rs.getString("userGender"));
				user.setUserEmail(rs.getString("userEmail"));
				user.setUserAddress(rs.getString("userAddress"));
				user.setUserPhone(rs.getString("userPhone"));
				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; // 사용자 정보가 없을 경우 null 반환
	}

	// 자원 해제 메서드
	public void close() {
		try {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 사용자 정보 수정 메서드
	public boolean updateUserProfile(User updatedUser) {
		String SQL = "UPDATE user SET userPassword = ?, userName = ?, userGender = ?, userEmail = ?, userAddress = ?, userPhone = ? WHERE userID = ?";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, updatedUser.getUserPassword());
			pstmt.setString(2, updatedUser.getUserName());
			pstmt.setString(3, updatedUser.getUserGender());
			pstmt.setString(4, updatedUser.getUserEmail());
			pstmt.setString(5, updatedUser.getUserAddress());
			pstmt.setString(6, updatedUser.getUserPhone());
			pstmt.setString(7, updatedUser.getUserID());

			int result = pstmt.executeUpdate();
			return result > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false; // Update failed
	}

	// 사용자 정보 일부 수정 메서드
	public boolean updateUser(User user) {
		String SQL = "UPDATE user SET userName = ?, userEmail = ?, userAddress = ?, userPhone = ? WHERE userID = ?";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, user.getUserName());
			pstmt.setString(2, user.getUserEmail());
			pstmt.setString(3, user.getUserAddress());
			pstmt.setString(4, user.getUserPhone());
			pstmt.setString(5, user.getUserID());

			int result = pstmt.executeUpdate();
			return result > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false; // Update failed
	}

	// 사용자의 주소를 조회하는 메서드
	public String getUserAddress(String userID) {
		String SQL = "SELECT userAddress FROM user WHERE userID = ?";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getString("userAddress");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; // 사용자 주소가 없을 경우 null 반환
	}

	// 사용자의 전화번호를 조회하는 메서드
	public String getUserPhone(String userID) {
		String SQL = "SELECT userPhone FROM user WHERE userID = ?";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getString("userPhone");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; // 사용자 전화번호가 없을 경우 null 반환
	}
}
