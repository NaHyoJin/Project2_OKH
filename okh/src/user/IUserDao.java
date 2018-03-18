package user;

public interface IUserDao {
	
	public UserDto login(String userID, String userPassword);
	public int registerCheck(String userID);
	public boolean addMember(String userID, String userPassword, String userName, String age, String gender, String email, String auth, String profile);
	
	public int getScore(String userID);
	public boolean updateScore(String userID, int score);

}
