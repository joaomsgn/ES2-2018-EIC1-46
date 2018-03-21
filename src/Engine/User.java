package Engine;

public class User {
	private String Mail;
	private String Password;
	private boolean isOnline=false;
	
	public User(String n, String p){
		Mail= n;
		Password=p;
	}

	public String getMail() {
		return Mail;
	}

	public void setMail(String M) {
		Mail = M;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public boolean isOnline() {
		return isOnline;
	}

	public void setOnline(boolean isOnline) {
		this.isOnline = isOnline;
	}
}
