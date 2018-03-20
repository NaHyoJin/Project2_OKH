package user;

public interface IUserService {
	
	public UserDto login(String userID, String userPassword);
	public int registerCheck(String userID);
	public boolean addMember(String userID, String userPassword, String userName, String age, String gender, String email, String auth, String profile);
	
	public int getScore(String userID);
	public boolean updateScore(String userID, int score);

	public UserDto getUser(String userID);
	public boolean update(String userID, String userPassword, String userName, String age, String gender, String email, String auth, String profile);
	public boolean profile(String userID, String userProfile);
	public String getProfile(String userID);

}
