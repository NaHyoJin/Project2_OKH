package user;

import java.io.Serializable;

/*
DROP TABLE OKHMEM
CASCADE CONSTRAINTS;

CREATE TABLE OKHMEM(
	ID VARCHAR2(50) PRIMARY KEY,
	PWD VARCHAR2(50) NOT NULL,
	NAME VARCHAR2(50) NOT NULL,
	AGE NUMBER(3) NOT NULL,		--나이
	GENDER VARCHAR2(50) NOT NULL,	--성별
	EMAIL VARCHAR2(50) NOT NULL,
	AUTH NUMBER(1) NOT NULL,
	PROFILE VARCHAR2(50),
	SCORE NUMBER(10) NOT NULL	--글 작성시 점수 올라가는
);
*/

public class UserDto implements Serializable {
	
	private String id;
	private String pwd;
	private String name;
	private int age;
	private String gender;
	private String email;
	private int auth;
	private String profile;
	private int score;
	
	public UserDto() {
	}
	
	public UserDto(String id, String pwd, String name, int age, String gender, String email, int auth, String profile,
			int score) {
		super();
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.email = email;
		this.auth = auth;
		this.profile = profile;
		this.score = score;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getAuth() {
		return auth;
	}
	public void setAuth(int auth) {
		this.auth = auth;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
	@Override
	public String toString() {
		return "UserDTO [id=" + id + ", pwd=" + pwd + ", name=" + name + ", age=" + age + ", gender=" + gender
				+ ", email=" + email + ", auth=" + auth + ", profile=" + profile + ", score=" + score + "]";
	}
	
}
