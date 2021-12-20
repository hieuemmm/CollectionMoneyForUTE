package ntattuan.vvhieu.cuoikyltdd02.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.PopupMenu;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

import ntattuan.vvhieu.cuoikyltdd02.App;
import ntattuan.vvhieu.cuoikyltdd02.EditCandidateActivity;
import ntattuan.vvhieu.cuoikyltdd02.Model.Candidate;
import ntattuan.vvhieu.cuoikyltdd02.Model.Round;
import ntattuan.vvhieu.cuoikyltdd02.R;

public class RoundAdapter extends BaseAdapter {

    private List<Round> ListRound;
    private LayoutInflater layoutInflater;
    private Context context;

    public RoundAdapter(Context aContext) {
        this.context = aContext;
        layoutInflater = LayoutInflater.from(aContext);
    }

    public RoundAdapter(Context aContext, List<Round> ListRound) {
        this.context = aContext;
        this.ListRound = ListRound;
        layoutInflater = LayoutInflater.from(aContext);
    }
    public void setListRound(List<Round> ListRound) {
        this.ListRound = ListRound;
    }
    @Override
    public int getCount() {
        return ListRound.size();
    }

    @Override
    public Object getItem(int position) {
        return ListRound.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_round, null);
            holder = new ViewHolder();
            holder.Round_menu = (ImageView) convertView.findViewById(R.id.Round_menu);
            holder.Round_STT = (TextView) convertView.findViewById(R.id.Round_STT);
            holder.Round_Name = (TextView) convertView.findViewById(R.id.Round_Name);
            holder.Round_CreateTime = (TextView) convertView.findViewById(R.id.Round_CreateTime);
            holder.Round_Price = (TextView) convertView.findViewById(R.id.Round_Price);
            holder.Round_IsShowRadio = (RadioButton) convertView.findViewById(R.id.Round_IsShowRadio);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Round round = this.ListRound.get(position);
        holder.Round_STT.setText(String.valueOf(position + 1));
        holder.Round_Name.setText(round.getName());
        holder.Round_CreateTime.setText("Ngày tạo: "+ round.getCreateTime());
        holder.Round_Price.setText(App.CurrencytoVN(round.getPrice()));
        if (round.getIsShow()==App.SHOW){
            holder.Round_IsShowRadio.setChecked(true);
        }else{
            holder.Round_IsShowRadio.setChecked(false);
        }
        holder.Round_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(v.getContext(), v.findViewById(R.id.candidate_luachon));
                popup.getMenuInflater().inflate(R.menu.round_menu, popup.getMenu());
                popup.show();
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.Roundchecked:

                                break;
                            case R.id.Roundedit:

                                break;
                            case R.id.Rounddelete:

                                break;
                            default:
                                break;
                        }
                        return true;
                    }
                });
            }
        });
        return convertView;
    }

    static class ViewHolder {
        ImageView Round_menu;
        TextView Round_STT, Round_Name, Round_CreateTime, Round_Price;
        RadioButton Round_IsShowRadio;
    }
}
