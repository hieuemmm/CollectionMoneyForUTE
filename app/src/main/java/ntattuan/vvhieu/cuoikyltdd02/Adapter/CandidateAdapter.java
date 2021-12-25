package ntattuan.vvhieu.cuoikyltdd02.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.PopupMenu;

import java.util.List;

import ntattuan.vvhieu.cuoikyltdd02.App;
import ntattuan.vvhieu.cuoikyltdd02.Data.DoneMoneyDAO;
import ntattuan.vvhieu.cuoikyltdd02.Data.CandidateDAO;
import ntattuan.vvhieu.cuoikyltdd02.EditCandidateActivity;
import ntattuan.vvhieu.cuoikyltdd02.CustomEvent.OnChangeAdapter;
import ntattuan.vvhieu.cuoikyltdd02.HistoryActivity;
import ntattuan.vvhieu.cuoikyltdd02.Model.Candidate;
import ntattuan.vvhieu.cuoikyltdd02.Model.DoneMoney;
import ntattuan.vvhieu.cuoikyltdd02.R;

public class CandidateAdapter extends BaseAdapter {
    private static OnChangeAdapter onChangeAdapter;
    public List<Candidate> listCandidate;
    private ViewHolder holder;
    private DoneMoneyDAO doneMoneyDAO;
    private CandidateDAO candidateDAO;

    private LayoutInflater layoutInflater;
    private Context context;

    // START CustomEvent
    public void setOnChangeAdapter(OnChangeAdapter eventListener) {
        onChangeAdapter = eventListener;
    }

    public static void Change() {
        if (onChangeAdapter != null) {
            onChangeAdapter.onChange();
        }
    }

    //END CustomEvent
    public CandidateAdapter(Context aContext) {
        this.context = aContext;
        layoutInflater = LayoutInflater.from(aContext);
        doneMoneyDAO = new DoneMoneyDAO(this.context);
        candidateDAO = new CandidateDAO(this.context);
    }

    public void setListCandidate(List<Candidate> listCandidate) {
        this.listCandidate = Candidate.Sort(listCandidate);
    }

    @Override
    public int getCount() {
        return listCandidate.size();
    }

    @Override
    public Object getItem(int position) {
        return listCandidate.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("ResourceAsColor")
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_candidate, null);
            holder = new ViewHolder();
            holder.candidate_avatar = (ImageView) convertView.findViewById(R.id.candidate_avatar);
            holder.candidate_luachon = (ImageView) convertView.findViewById(R.id.candidate_luachon);
            holder.candidate_Is_Active = (ImageView) convertView.findViewById(R.id.candidate_Is_Active);
            holder.candidate_Name = (TextView) convertView.findViewById(R.id.candidate_Name);
            holder.candidate_CMND = (TextView) convertView.findViewById(R.id.candidate_CMND);
            holder.candidate_ID = (TextView) convertView.findViewById(R.id.candidate_ID);
            holder.candidate_Gender = (TextView) convertView.findViewById(R.id.candidate_Gender);
            holder.Candidate_Button_DoanPhi = (Button) convertView.findViewById(R.id.Candidate_Button_DoanPhi);
            holder.Candidate_Button_HoiPhi = (Button) convertView.findViewById(R.id.Candidate_Button_HoiPhi);
            holder.Candidate_Button_DuyetNgay = (Button) convertView.findViewById(R.id.Candidate_Button_DuyetNgay);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Candidate candidate = this.listCandidate.get(position);
        holder.candidate_avatar.setImageBitmap(candidate.getAvatarToBitMap());
        holder.candidate_Name.setText("Tên: " + candidate.getName());
        holder.candidate_CMND.setText("CMND/CCCD: " + candidate.getCMND());
        holder.candidate_ID.setText(String.valueOf(candidate.getId()));
        holder.candidate_Gender.setText("Giới tính: " + candidate.getGenderToString());
        hiddenShowButton(candidate);
        holder.candidate_luachon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(v.getContext(), v.findViewById(R.id.candidate_luachon));
                popup.getMenuInflater().inflate(R.menu.candidate_menu, popup.getMenu());
                popup.show();
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Intent intent;
                        Bundle bundle;
                        switch (item.getItemId()) {
                            case R.id.view:
                                intent = new Intent(context, HistoryActivity.class);
                                bundle = new Bundle();
                                bundle.putInt("CandidateID", candidate.getId());
                                intent.putExtra("CandidateCurrent", bundle);
                                context.startActivity(intent);
                                break;
                            case R.id.edit:
                                intent = new Intent(context, EditCandidateActivity.class);
                                bundle = new Bundle();
                                bundle.putInt("CandidateID", candidate.getId());
                                intent.putExtra("CandidateCurrent", bundle);
                                context.startActivity(intent);
                                break;
                            case R.id.delete:
                                if (App.CheckIsAdministrator()) {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                                    builder.setTitle("Xóa đoàn viên");
                                    builder.setMessage("Bạn muốn xóa mọi dữ liệu của Đoàn viên " + candidate.getName() + " ?");
                                    builder.setPositiveButton("Xóa ngay", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            candidateDAO.deleteCandidate(candidate);
                                            listCandidate.remove(candidate);
                                            Change();//Lan tỏa sự kiện Change ra bên ngoài
                                            App.ToastShow(v.getContext().getApplicationContext(), "Xóa thành công");
                                            dialog.dismiss();
                                        }
                                    });
                                    builder.setNegativeButton("Quay lại", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    });
                                    AlertDialog alert = builder.create();
                                    alert.show();
                                } else {
                                    App.ToastShow(v.getContext().getApplicationContext(), App.STRING_NO_ROLE_ADMIN);
                                }
                                break;
                            default:
                                break;
                        }
                        return true;
                    }
                });
            }
        });
        holder.Candidate_Button_DoanPhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Đoàn phí");
                if (!candidate.isDoanPhi()) {
                    builder.setMessage("Thu hộ " + App.CurrencytoVN(App.Price_DoanPhi_Current) + " ?");
                    builder.setPositiveButton("Thu Tiền", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            //Thêm vào bảng đã nạp tiền
                            DoneMoney doneMoney = new DoneMoney(
                                    candidate.getId(),
                                    App.DotNopTienDoanPhi_Current,
                                    App.ACTIVE,
                                    App.UserLogined.getUserName(),
                                    App.GetTimeCurrent(),
                                    "",
                                    ""
                            );
                            doneMoneyDAO.addDoneMoney(doneMoney);
                            candidate.setDoanPhi(true);
                            App.ToastShow(v.getContext().getApplicationContext(), "Đã thu " + App.CurrencytoVN(App.Price_DoanPhi_Current) + " từ " + candidate.getName());
                            v.findViewById(R.id.Candidate_Button_DoanPhi).setBackground(v.getResources().getDrawable(R.drawable.btn_green));
                            dialog.dismiss();
                        }
                    });
                } else {
                    builder.setMessage("Hủy thu tiền ?");
                    builder.setPositiveButton("Hủy Thu " + App.CurrencytoVN(App.Price_DoanPhi_Current), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            DoneMoney doneMoney = doneMoneyDAO.getDoneMoneyByID(candidate.getId(), App.DotNopTienDoanPhi_Current);
                            doneMoney.setIsActive(App.NO_ACTIVE);
                            doneMoney.setDelete_by(App.UserLogined.getUserName());
                            doneMoney.setDelete_time(App.GetTimeCurrent());
                            doneMoneyDAO.updateDoneMoney(doneMoney);
                            candidate.setDoanPhi(false);
                            App.ToastShow(v.getContext().getApplicationContext(), "Đã thu " + App.CurrencytoVN(App.Price_DoanPhi_Current) + " của " + candidate.getName());
                            v.findViewById(R.id.Candidate_Button_DoanPhi).setBackground(v.getResources().getDrawable(R.drawable.btn_red_border));
                            dialog.dismiss();
                        }
                    });
                }
                builder.setNegativeButton("Quay lại", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
        holder.Candidate_Button_HoiPhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Hội phí");
                if (!candidate.isHoiPhi()) {
                    builder.setMessage("Thu hộ " + App.CurrencytoVN(App.Price_HoiPhi_Current) + " ?");
                    builder.setPositiveButton("Thu Tiền", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            //Thêm vào bảng đã nạp tiền
                            DoneMoney doneMoney = new DoneMoney(
                                    candidate.getId(),
                                    App.DotNopTienHoiPhi_Current,
                                    App.ACTIVE,
                                    App.UserLogined.getUserName(),
                                    App.GetTimeCurrent(),
                                    "",
                                    ""
                            );
                            doneMoneyDAO.addDoneMoney(doneMoney);
                            candidate.setHoiPhi(true);
                            App.ToastShow(v.getContext().getApplicationContext(), "Đã thu " + App.CurrencytoVN(App.Price_HoiPhi_Current) + " từ " + candidate.getName());
                            v.findViewById(R.id.Candidate_Button_HoiPhi).setBackground(v.getResources().getDrawable(R.drawable.btn_green));
                            dialog.dismiss();
                        }
                    });
                } else {
                    builder.setMessage("Hủy thu tiền ?");
                    builder.setPositiveButton("Hủy Thu " + App.CurrencytoVN(App.Price_HoiPhi_Current), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            DoneMoney doneMoney = doneMoneyDAO.getDoneMoneyByID(candidate.getId(), App.DotNopTienHoiPhi_Current);
                            doneMoney.setIsActive(App.NO_ACTIVE);
                            doneMoney.setDelete_by(App.UserLogined.getUserName());
                            doneMoney.setDelete_time(App.GetTimeCurrent());
                            doneMoneyDAO.updateDoneMoney(doneMoney);
                            candidate.setHoiPhi(false);
                            App.ToastShow(v.getContext().getApplicationContext(), "Đã thu " + App.CurrencytoVN(App.Price_HoiPhi_Current) + " của " + candidate.getName());
                            v.findViewById(R.id.Candidate_Button_HoiPhi).setBackground(v.getResources().getDrawable(R.drawable.btn_red_border));
                            dialog.dismiss();
                        }
                    });
                }
                builder.setNegativeButton("Quay lại", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing
                        holder.Candidate_Button_DoanPhi.setBackgroundResource(0);
                        dialog.dismiss();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
        holder.Candidate_Button_DuyetNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Duyệt đoàn viên");
                builder.setMessage("Bạn muốn duyệt " + candidate.getName() + " ?");
                builder.setPositiveButton("Duyệt ngay", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        candidateDAO.ActiveCandidate(candidate);
                        App.ToastShow(v.getContext().getApplicationContext(), "Duyệt thành công");
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
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
        return convertView;
    }

    private void hiddenShowButton(Candidate candidate) {
        holder.Candidate_Button_DoanPhi.setVisibility(View.VISIBLE);
        holder.Candidate_Button_HoiPhi.setVisibility(View.VISIBLE);
        holder.Candidate_Button_DuyetNgay.setVisibility(View.GONE);
        if (candidate.getIsActive() == App.NO_ACTIVE) {
            holder.candidate_Is_Active.setBackground(this.context.getDrawable(R.drawable.no_active));
            holder.Candidate_Button_DoanPhi.setVisibility(View.GONE);
            holder.Candidate_Button_HoiPhi.setVisibility(View.GONE);
            if (App.CheckIsAdministrator()) {
                holder.Candidate_Button_DuyetNgay.setVisibility(View.VISIBLE);
            }
        } else {
            holder.candidate_Is_Active.setBackgroundResource(0);
            holder.Candidate_Button_DuyetNgay.setVisibility(View.GONE);
            if (App.DoanVien_Tab_Current == App.DOANVIEN_TAB_DOAN_PHI) {
                holder.Candidate_Button_HoiPhi.setVisibility(View.GONE);
            }
            if (App.DoanVien_Tab_Current == App.DOANVIEN_TAB_HOI_PHI) {
                holder.Candidate_Button_DoanPhi.setVisibility(View.GONE);
            }
            if (candidate.isDoanPhi()) {
                if (candidate.getDoanPhiCreateBy().equals(App.UserLogined.getUserName())){
                    holder.Candidate_Button_DoanPhi.setBackground(this.context.getDrawable(R.drawable.btn_green));
                }else{
                    holder.Candidate_Button_DoanPhi.setEnabled(false);
                    holder.Candidate_Button_DoanPhi.setBackground(this.context.getDrawable(R.drawable.btn_green_enable));
                }
            } else {
                holder.Candidate_Button_DoanPhi.setBackground(this.context.getDrawable(R.drawable.btn_red_border));
            }
            if (candidate.isHoiPhi()) {
                if (candidate.getHoiPhiCreateBy().equals(App.UserLogined.getUserName())){
                    holder.Candidate_Button_HoiPhi.setBackground(this.context.getDrawable(R.drawable.btn_green));
                }else{
                    holder.Candidate_Button_HoiPhi.setEnabled(false);
                    holder.Candidate_Button_HoiPhi.setBackground(this.context.getDrawable(R.drawable.btn_green_enable));
                }
            } else {
                holder.Candidate_Button_HoiPhi.setBackground(this.context.getDrawable(R.drawable.btn_red_border));
            }
        }
    }

    static class ViewHolder {
        ImageView candidate_avatar, candidate_luachon, candidate_Is_Active;
        TextView candidate_Name;
        TextView candidate_Gender;
        TextView candidate_CMND;
        TextView candidate_ID;
        Button Candidate_Button_DoanPhi, Candidate_Button_HoiPhi, Candidate_Button_DuyetNgay;
    }
}
