package ntattuan.vvhieu.cuoikyltdd02.Adapter;

import android.app.Activity;
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

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.PopupMenu;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

import ntattuan.vvhieu.cuoikyltdd02.App;
import ntattuan.vvhieu.cuoikyltdd02.CustomEvent.OnChangeAdapter;
import ntattuan.vvhieu.cuoikyltdd02.Data.RoundDAO;
import ntattuan.vvhieu.cuoikyltdd02.EditCandidateActivity;
import ntattuan.vvhieu.cuoikyltdd02.EditRoundActivity;
import ntattuan.vvhieu.cuoikyltdd02.Model.Candidate;
import ntattuan.vvhieu.cuoikyltdd02.Model.Round;
import ntattuan.vvhieu.cuoikyltdd02.CustomEvent.OnChangeAdapter;
import ntattuan.vvhieu.cuoikyltdd02.R;

public class RoundAdapter extends BaseAdapter {
    private static OnChangeAdapter onChangeAdapter;
    private List<Round> ListRound;
    private RoundDAO roundDAO;
    private LayoutInflater layoutInflater;
    private Context context;

    public RoundAdapter(Context aContext) {
        this.context = aContext;
        layoutInflater = LayoutInflater.from(aContext);
    }

    // START CustomEvent
    public void setOnChangeAdapter(OnChangeAdapter eventListener) {
        onChangeAdapter = eventListener;
    }

    public static void Change() {
        if (onChangeAdapter != null) {
            onChangeAdapter.onChange();
        }
    }

    public void Edit(int id) {
        if (onChangeAdapter != null) {
            onChangeAdapter.onEdit(id);
        }
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
            roundDAO = new RoundDAO(this.context);
            holder.Round_menu = (ImageView) convertView.findViewById(R.id.Round_menu);
            holder.Round_IsShow = (ImageView) convertView.findViewById(R.id.Round_IsShow);
            holder.Round_Icon = (ImageView) convertView.findViewById(R.id.Round_Icon);
            holder.Round_STT = (TextView) convertView.findViewById(R.id.Round_STT);
            holder.Round_Name = (TextView) convertView.findViewById(R.id.Round_Name);
            holder.Round_CreateTime = (TextView) convertView.findViewById(R.id.Round_CreateTime);
            holder.Round_Price = (TextView) convertView.findViewById(R.id.Round_Price);
            //holder.Round_IsShowRadio = (RadioButton) convertView.findViewById(R.id.Round_IsShowRadio);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Round round = this.ListRound.get(position);
        holder.Round_STT.setText(ListRound.size()-(position) + ".");
        holder.Round_Name.setText(round.getName());
        holder.Round_CreateTime.setText("Ngày tạo: " + round.getCreateTime());
        holder.Round_Price.setText(App.CurrencytoVN(round.getPrice()));
        if (round.getIsShow() == App.SHOW) {
            holder.Round_IsShow.setVisibility(View.VISIBLE);
            holder.Round_Icon.setBackground(context.getResources().getDrawable(R.drawable.ic_baseline_folder_special_24));
        } else {
            holder.Round_IsShow.setVisibility(View.GONE);
            holder.Round_Icon.setBackground(context.getResources().getDrawable(R.drawable.ic_baseline_folder_open_24));
        }
        holder.Round_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(v.getContext(), v.findViewById(R.id.Round_menu));
                popup.getMenuInflater().inflate(R.menu.round_menu, popup.getMenu());
                popup.show();
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        AlertDialog.Builder builder;
                        AlertDialog alert;
                        switch (item.getItemId()) {
                            case R.id.Roundchecked:
                                builder = new AlertDialog.Builder(v.getContext());
                                String typeRound = (round.getType() == App.TYPE_ROUND_DOAN_PHI ? "Đoàn phí" : "Hội phí");
                                builder.setTitle("Đặt đợt " + typeRound);
                                builder.setMessage("Bạn muốn đặt " + round.getName() + " làm đợt thu tiền hiện tại ?");
                                builder.setPositiveButton("Đặt ngay", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        roundDAO.UnSetShowAll();
                                        roundDAO.SetIsShow(round);
                                        App.ToastShow(v.getContext().getApplicationContext(), "Đã đặt " + round.getName() + " làm đợt thu tiền " + typeRound);
                                        Change();
                                        dialog.dismiss();
                                    }
                                });
                                builder.setNegativeButton("Quay lại", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                                alert = builder.create();
                                alert.show();
                                break;
                            case R.id.Roundedit:
                                Edit(round.getId());

                                break;
                            case R.id.Rounddelete:
                                builder = new AlertDialog.Builder(v.getContext());
                                builder.setTitle("Xóa đợt thu tiền");
                                builder.setMessage("Bạn muốn xóa mọi dữ liệu của " + round.getName() + " ?");
                                builder.setPositiveButton("Xóa ngay", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        roundDAO.deleteRound(round);
                                        Change();//Lan tỏa sự kiện Change ra bên ngoài
                                        App.ToastShow(v.getContext().getApplicationContext(), "Đã xóa " + round.getName());
                                        dialog.dismiss();
                                    }
                                });
                                builder.setNegativeButton("Quay lại", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                                alert = builder.create();
                                alert.show();
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
        ImageView Round_menu, Round_IsShow, Round_Icon;
        TextView Round_STT, Round_Name, Round_CreateTime, Round_Price;
    }
}
