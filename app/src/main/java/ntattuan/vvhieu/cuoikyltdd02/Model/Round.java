package ntattuan.vvhieu.cuoikyltdd02.Model;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import ntattuan.vvhieu.cuoikyltdd02.App;

public class Round {
    private int Id;
    private String Name;
    private String CreateTime;
    private int price;
    private int IsShow;
    private int Type; // 1 là đoàn phí, 0 là hội phí

    public Round() {
    }

    public Round(int id, String name, int price) {
        Id = id;
        Name = name;
        this.price = price;
    }

    public Round(String name, String createTime, int price, int isShow, int type) {
        Name = name;
        CreateTime = createTime;
        this.price = price;
        IsShow = isShow;
        Type = type;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getIsShow() {
        return IsShow;
    }

    public void setIsShow(int isShow) {
        IsShow = isShow;
    }

    public int getType() {
        return Type;
    }

    public void setType(int type) {
        Type = type;
    }
}
