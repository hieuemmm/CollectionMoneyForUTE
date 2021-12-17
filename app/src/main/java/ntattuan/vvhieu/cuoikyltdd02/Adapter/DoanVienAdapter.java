package ntattuan.vvhieu.cuoikyltdd02.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ntattuan.vvhieu.cuoikyltdd02.Model.Candidate;
import ntattuan.vvhieu.cuoikyltdd02.R;

public class DoanVienAdapter extends BaseAdapter {
    public List<Candidate> listCandidate;

    public void setListCandidate(List<Candidate> listCandidate) {
        this.listCandidate = listCandidate;
    }

    private LayoutInflater layoutInflater;
    private Context context;

    public DoanVienAdapter(Context aContext) {
        this.context = aContext;
        layoutInflater = LayoutInflater.from(aContext);
    }

    public DoanVienAdapter(Context aContext, List<Candidate> listCandidate) {
        this.context = aContext;
        this.listCandidate = listCandidate;
        layoutInflater = LayoutInflater.from(aContext);
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

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_doanvien, null);
            holder = new ViewHolder();
            holder.candidate_avatar = (ImageView) convertView.findViewById(R.id.candidate_avatar);
            holder.candidate_Name = (TextView) convertView.findViewById(R.id.candidate_Name);
            holder.candidate_CMND = (TextView) convertView.findViewById(R.id.candidate_CMND);
            holder.candidate_Gender = (TextView) convertView.findViewById(R.id.candidate_Gender);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Candidate candidate = this.listCandidate.get(position);
        holder.candidate_avatar.setImageBitmap(candidate.getAvatarToBitMap());
        holder.candidate_Name.setText("Tên: " + candidate.getName());
        holder.candidate_CMND.setText("CMND: " +candidate.getCMND());
        holder.candidate_Gender.setText("Giới tính: " +candidate.getGenderToString());
        return convertView;
    }

    static class ViewHolder {
        ImageView candidate_avatar;
        TextView candidate_Name;
        TextView candidate_Gender;
        TextView candidate_CMND;
    }
}
