package ntattuan.vvhieu.cuoikyltdd02.Model;

public class User {
    private String UserName;
    private String Pass;
    private String SDT;
    private int isActive;
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

    public User(String userName, String pass, String SDT, int isActive, int role) {
        UserName = userName;
        Pass = pass;
        this.SDT = SDT;
        this.isActive = isActive;
        Role = role;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
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
