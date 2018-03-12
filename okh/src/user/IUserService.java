package user;

public interface IUserService {
	
	public UserDto login(String userID, String userPassword);
	public int registerCheck(String userID);
	public boolean addMember(String userID, String userPassword, String userName, String age, String gender, String email, String auth, String profile);

}
