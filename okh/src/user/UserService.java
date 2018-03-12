package user;

public class UserService implements IUserService {
	
	private static IUserDao userDao = new UserDao();

	@Override
	public UserDto login(String userID, String userPassword) {
		return userDao.login(userID, userPassword);
	}

	@Override
	public int registerCheck(String userID) {
		return userDao.registerCheck(userID);
	}

	@Override
	public boolean addMember(String userID, String userPassword, String userName, String age, String gender,
			String email, String auth, String profile) {
		return userDao.addMember(userID, userPassword, userName, age, gender, email, auth, profile);
	}

}
