package ntattuan.vvhieu.cuoikyltdd02.Model;

public class DoneMoney {
    private int id;
    private int Cadidate_id;
    private int Moneyround_id;
    private int IsActive;
    private String Create_by;
    private String Create_time;
    private String Delete_by;
    private String Delete_time;

    public DoneMoney() {

    }

    public DoneMoney(int cadidate_id, int moneyround_id, int isActive, String create_by, String create_time, String delete_by, String delete_time) {
        Cadidate_id = cadidate_id;
        Moneyround_id = moneyround_id;
        IsActive = isActive;
        Create_by = create_by;
        Create_time = create_time;
        Delete_by = delete_by;
        Delete_time = delete_time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCadidate_id() {
        return Cadidate_id;
    }

    public void setCadidate_id(int cadidate_id) {
        Cadidate_id = cadidate_id;
    }

    public int getMoneyround_id() {
        return Moneyround_id;
    }

    public void setMoneyround_id(int moneyround_id) {
        Moneyround_id = moneyround_id;
    }

    public int getIsActive() {
        return IsActive;
    }

    public void setIsActive(int isActive) {
        IsActive = isActive;
    }

    public String getCreate_by() {
        return Create_by;
    }

    public void setCreate_by(String create_by) {
        Create_by = create_by;
    }

    public String getCreate_time() {
        return Create_time;
    }

    public void setCreate_time(String create_time) {
        Create_time = create_time;
    }

    public String getDelete_by() {
        return Delete_by;
    }

    public void setDelete_by(String delete_by) {
        Delete_by = delete_by;
    }

    public String getDelete_time() {
        return Delete_time;
    }

    public void setDelete_time(String delete_time) {
        Delete_time = delete_time;
    }
}
