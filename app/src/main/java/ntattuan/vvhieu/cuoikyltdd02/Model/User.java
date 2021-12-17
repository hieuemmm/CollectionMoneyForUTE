package ntattuan.vvhieu.cuoikyltdd02.Model;

public class User {
    private String UserName;
    private String Pass;
    private int Role;
    public User() {}
    public User(String userName, String pass) {
        UserName = userName;
        Pass = pass;
    }
    public User(String userName, String pass, int role) {
        UserName = userName;
        Pass = pass;
        Role = role;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPass() {
        return Pass;
    }

    public void setPass(String pass) {
        Pass = pass;
    }

    public int getRole() {
        return Role;
    }

    public void setRole(int role) {
        Role = role;
    }
}
