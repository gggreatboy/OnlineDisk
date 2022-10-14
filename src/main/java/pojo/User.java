package pojo;

public class User {
	private String username;

	private String userpwd;



	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserpwd() {
		return userpwd;
	}
	public void setUserpwd(String userpwd) {
		this.userpwd = userpwd;
	}

	public User(String username,String userpwd){

		this.username=username;
		this.userpwd=userpwd;
	}
	
}
