package ntattuan.vvhieu.cuoikyltdd02.Data;

import android.content.Context;

import ntattuan.vvhieu.cuoikyltdd02.App;
import ntattuan.vvhieu.cuoikyltdd02.Model.Statistic;

public class StatisticDAO extends DBManager {

    public StatisticDAO(Context context) {
        super(context);
    }

    public Statistic getStatistics(int Type, String UserName) {
        Statistic statistic = new Statistic();
        statistic.setCollect_you(new DoneMoneyDAO(context).CountIsActive(UserName, true, Type));
        statistic.setCollect_another(new DoneMoneyDAO(context).CountIsActive(UserName, false, Type));
        statistic.setTotal(new CandidateDAO(context).Count(App.ACTIVE));
        if (Type == App.TYPE_ROUND_DOAN_PHI) {
            statistic.setRoundCurent(new RoundDAO(context).getRoundByID(App.DotNopTienDoanPhi_Current));
        } else {
            statistic.setRoundCurent(new RoundDAO(context).getRoundByID(App.DotNopTienHoiPhi_Current));
        }
        return statistic;
    }
}
