package ntattuan.vvhieu.cuoikyltdd02.Data;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import ntattuan.vvhieu.cuoikyltdd02.App;
import ntattuan.vvhieu.cuoikyltdd02.Model.Candidate;
import ntattuan.vvhieu.cuoikyltdd02.Model.DoneMoney;
import ntattuan.vvhieu.cuoikyltdd02.Model.History;
import ntattuan.vvhieu.cuoikyltdd02.Model.User;

public class HistoryDAO extends DBManager {
    private final String ACTION_COLLECT = "Thu";
    private final String ACTION_UN_COLLECT = "Hủy thu";

    public HistoryDAO(Context context) {
        super(context);
    }

    public List<History> getHistoryOfCandidate(int Candidate_ID, int Type) {
        String nameType = Type == App.TYPE_ROUND_DOAN_PHI ? " Đoàn phí" : " Hội phí";
        //Lấy các trường donemoney ra
        List<History> histories = new ArrayList<History>();
        List<DoneMoney> doneMoneyList = new DoneMoneyDAO(context).getListDoneMoneyByID(Candidate_ID,Type);
        if (doneMoneyList!=null){
            for (DoneMoney doneMoney : doneMoneyList) {
                History historyCollect = new History();
                historyCollect.setBy(doneMoney.getCreate_by());
                historyCollect.setTime(doneMoney.getCreate_time());
                historyCollect.setAction(ACTION_COLLECT + nameType);
                historyCollect.setType(App.ACTIONT_COLLECT);
                histories.add(historyCollect);
                if (doneMoney.getIsActive() == App.NO_ACTIVE) {
                    History historyUnCollect = new History();
                    historyUnCollect.setBy(doneMoney.getDelete_by());
                    historyUnCollect.setTime(doneMoney.getDelete_time());
                    historyUnCollect.setAction(ACTION_UN_COLLECT + nameType);
                    historyUnCollect.setType(App.ACTIONT_UN_COLLECT);
                    histories.add(historyUnCollect);
                }
            }
        }
        return histories;
    }
}
