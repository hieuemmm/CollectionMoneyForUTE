package ntattuan.vvhieu.cuoikyltdd02.Model;

public class DoneMoney {
    private int Cadidate_id;
    private int moneyround_id;
    private String Create_by;

    public DoneMoney() {

    }

    public DoneMoney(int cadidate_id, int moneyround_id, String create_by) {
        Cadidate_id = cadidate_id;
        this.moneyround_id = moneyround_id;
        Create_by = create_by;
    }

    public int getCadidate_id() {
        return Cadidate_id;
    }

    public void setCadidate_id(int cadidate_id) {
        Cadidate_id = cadidate_id;
    }

    public int getMoneyround_id() {
        return moneyround_id;
    }

    public void setMoneyround_id(int moneyround_id) {
        this.moneyround_id = moneyround_id;
    }

    public String getCreate_by() {
        return Create_by;
    }

    public void setCreate_by(String create_by) {
        Create_by = create_by;
    }
}
