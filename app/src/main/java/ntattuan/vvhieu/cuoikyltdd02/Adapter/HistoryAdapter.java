package ntattuan.vvhieu.cuoikyltdd02.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import ntattuan.vvhieu.cuoikyltdd02.App;
import ntattuan.vvhieu.cuoikyltdd02.Data.HistoryDAO;
import ntattuan.vvhieu.cuoikyltdd02.Model.History;
import ntattuan.vvhieu.cuoikyltdd02.R;

public class HistoryAdapter extends BaseAdapter {
    private List<History> ListHistory;
    private LayoutInflater layoutInflater;
    private Context context;

    public HistoryAdapter(Context aContext) {
        this.context = aContext;
        layoutInflater = LayoutInflater.from(aContext);
    }

    public void setListHistory(List<History> LH) {
        this.ListHistory = LH;
    }

    @Override
    public int getCount() {
        return ListHistory.size();
    }

    @Override
    public Object getItem(int position) {
        return ListHistory.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_history, null);
            holder = new ViewHolder();
            holder.HistorySTT = (TextView) convertView.findViewById(R.id.HistorySTT);
            holder.History_By = (TextView) convertView.findViewById(R.id.History_By);
            holder.History_Time = (TextView) convertView.findViewById(R.id.History_Time);
            holder.History_Action = (TextView) convertView.findViewById(R.id.History_Action);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        History history = this.ListHistory.get(position);
        holder.HistorySTT.setText(position + 1 + ".");
        holder.History_By.setText(history.getBy());
        holder.History_Time.setText("Vào lúc: " + history.getTime());
        holder.History_Action.setText(history.getAction());
        if (history.getType()== App.ACTIONT_COLLECT){
            holder.History_Action.setTextColor(Color.rgb(88,183,63));
        }else{
            holder.History_Action.setTextColor(Color.RED);
        }
        return convertView;
    }

    static class ViewHolder {
        TextView History_By, History_Time, History_Action, HistorySTT;
    }
}