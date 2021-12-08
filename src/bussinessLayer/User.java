package bussinessLayer;
import java.io.Serializable;

public class User implements Serializable {
    private String userName;
    private String password;
    private String type;
    private int id=0;
    
    public User() {
    	
    }
    
    public User(String name,String pass,String type)
    {
        this.userName=name;
        this.password=pass;
        this.type=type;
    }
    public User(int id, String name,String pass,String type)
    {
        this.userName=name;
        this.password=pass;
        this.type=type;
        this.id=id;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
    
}

