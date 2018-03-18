package user;

public class UserService implements IUserService {
	
	private static UserService userService = new UserService();
	private static IUserDao userDao = new UserDao();
	
	public UserService() {
	}
	public static UserService getInstance() {
		return userService;
	}

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

	@Override
	public int getScore(String userID) {
		return userDao.getScore(userID);
	}

	@Override
	public boolean updateScore(String userID, int score) {
		return userDao.updateScore(userID, score);
	}

}
