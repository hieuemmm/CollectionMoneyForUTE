package ntattuan.vvhieu.cuoikyltdd02.Model;

import ntattuan.vvhieu.cuoikyltdd02.App;

public class Statistic {
    private Round roundCurent;
    private int collect_you;
    private int collect_another;
    private int collect_no;
    private int Total;

    public Statistic() {
    }

    public Statistic(Round roundCurent, int collect_you, int collect_another, int collect_no, int total) {
        this.roundCurent = roundCurent;
        this.collect_you = collect_you;
        this.collect_another = collect_another;
        this.collect_no = collect_no;
        Total = total;
    }

    public Round getRoundCurent() {
        return roundCurent;
    }

    public void setRoundCurent(Round roundCurent) {
        this.roundCurent = roundCurent;
    }

    public int getCollect_you() {
        return collect_you;
    }

    public int getMoneyCollect_you() {
        return collect_you * roundCurent.getPrice();
    }

    public void setCollect_you(int collect_you) {
        this.collect_you = collect_you;
    }

    public int getCollect_another() {
        return collect_another;
    }

    public int getMoneyCollect_another() {
        return collect_another * roundCurent.getPrice();
    }

    public void setCollect_another(int collect_another) {
        this.collect_another = collect_another;
    }

    public int getCollect_no() {
        return Total - (collect_you + collect_another);
    }

    public int getMoneyCollect_no() {
        return (Total - (collect_you + collect_another)) * roundCurent.getPrice();
    }

    public void setCollect_no(int collect_no) {
        this.collect_no = collect_no;
    }

    public int getTotal() {
        return Total;
    }

    public int getMoney_Total() {
        return Total * roundCurent.getPrice();
    }

    public void setTotal(int total) {
        Total = total;
    }
    public String getPriceToString() {
        return "("+App.CurrencytoK(roundCurent.getPrice()) + "/người)";
    }

}
