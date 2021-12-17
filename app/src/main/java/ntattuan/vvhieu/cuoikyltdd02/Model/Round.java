package ntattuan.vvhieu.cuoikyltdd02.Model;

public class Round {
    private int Id;
    private String Name;
    private String CreateTime;
    private int IsShow;
    private int Type; // 1 là đoàn phí, 0 là hội phí

    public Round() {
    }

    public Round(String name, String createTime, int isShow) {
        Name = name;
        CreateTime = createTime;
        IsShow = isShow;
    }

    public Round(int id, String name, String createTime, int isShow) {
        Id = id;
        Name = name;
        CreateTime = createTime;
        IsShow = isShow;
    }

    public Round(int id, String name, String createTime, int isShow, int type) {
        Id = id;
        Name = name;
        CreateTime = createTime;
        IsShow = isShow;
        Type = type;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    public int getIsShow() {
        return IsShow;
    }

    public void setIsShow(int isShow) {
        IsShow = isShow;
    }
    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getType() {
        return Type;
    }

    public void setType(int type) {
        Type = type;
    }
}
