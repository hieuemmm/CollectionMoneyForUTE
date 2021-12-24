package ntattuan.vvhieu.cuoikyltdd02.Model;

public class History {
    private String By;
    private String Time;
    private String Action;
    private int Type;

    public History() {
    }

    public History(String by, String time, String action) {
        By = by;
        Time = time;
        Action = action;
    }

    public int getType() {
        return Type;
    }

    public void setType(int type) {
        Type = type;
    }

    public String getBy() {
        return By;
    }

    public void setBy(String by) {
        By = by;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getAction() {
        return Action;
    }

    public void setAction(String action) {
        Action = action;
    }
}
