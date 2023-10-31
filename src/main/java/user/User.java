package user;

public class User {
	private String userID;
	private String userPassword;
	private String userName;
	private String userGender;
	private String userEmail;
	private String userAddress;
	private String userPhone;
	private boolean isAdmin;

	public User(String userID, String userPassword, String userName, String userGender, String userEmail,
			String userAddress, String userPhone) {
		this.userID = userID;
		this.userPassword = userPassword;
		this.userName = userName;
		this.userGender = userGender;
		this.userEmail = userEmail;
		this.userAddress = userAddress;
		this.userPhone = userPhone;

		// 기본값으로 일반 사용자로 초기화
		this.isAdmin = false;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String password) {
		this.userPassword = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserGender() {
		return userGender;
	}

	public void setUserGender(String gender) {
		this.userGender = gender;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String email) {
		this.userEmail = email;
	}

	public String getUserAddress() {
		return userAddress;

	}

	public void setUserAddress(String address) {
		this.userAddress = address;
	}

	public String getUserPhone() {
		return userPhone;

	}

	public void setUserPhone(String phone) {
		this.userPhone = phone;
	}

	public User() {
		// 초기화 코드...
	}

}
