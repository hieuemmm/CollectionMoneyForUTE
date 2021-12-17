package ntattuan.vvhieu.cuoikyltdd02.Fragment;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ntattuan.vvhieu.cuoikyltdd02.Adapter.DoanVienAdapter;
import ntattuan.vvhieu.cuoikyltdd02.App;
import ntattuan.vvhieu.cuoikyltdd02.MainActivity;
import ntattuan.vvhieu.cuoikyltdd02.Model.Candidate;
import ntattuan.vvhieu.cuoikyltdd02.Data.CadidateDAO;
import ntattuan.vvhieu.cuoikyltdd02.Model.User;
import ntattuan.vvhieu.cuoikyltdd02.R;

public class DoanVienFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private ListView doanvien_listview;
    private TextView doanvien_textSearch;
    private ImageView doanvien_SearchButton,doanvien_SearchClose;
    private CadidateDAO candidateDAO;
    private DoanVienAdapter doanVienAdapter;

    public DoanVienFragment() {
    }

    public static DoanVienFragment newInstance(String param1, String param2) {
        DoanVienFragment fragment = new DoanVienFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doan_vien, container, false);
        candidateDAO = new CadidateDAO(this.getActivity());
        doanvien_listview = (ListView) view.findViewById(R.id.doanvien_listview);
        doanvien_textSearch = (TextView) view.findViewById(R.id.doanvien_textSearch);
        doanvien_SearchButton = (ImageView) view.findViewById(R.id.doanvien_SearchButton);
        doanvien_SearchClose = (ImageView) view.findViewById(R.id.doanvien_SearchClose);
        TaoDuLieuMau();
        LoadListView_DoanVien();

        //Tìm kiếm đoàn viên
        doanvien_textSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0){
                    doanvien_SearchClose.setBackground(getResources().getDrawable(R.drawable.ic_baseline_close_24));
                    Search_DoanVien(String.valueOf(s));
                }
                else{
                    doanvien_SearchClose.setBackgroundResource(0);
                    LoadListView_DoanVien();
                }
            }
        });
        doanvien_SearchClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doanvien_textSearch.setText("");
                App.hideKeyboard(getActivity());
            }
        });
        return view;
    }

    private void LoadListView_DoanVien() {
        candidateDAO = new CadidateDAO(this.getActivity());
        List<Candidate> candidates = candidateDAO.getAllCandidate();
        doanVienAdapter = new DoanVienAdapter(this.getActivity(), candidates);
        doanvien_listview.setAdapter(doanVienAdapter);
    }

    public void Search_DoanVien(String name) {
        List<Candidate> candidates = candidateDAO.getCandidateByName(name);
        doanVienAdapter.setListCandidate(candidates);
        doanvien_listview.setAdapter(doanVienAdapter);
    }

    private void TaoDuLieuMau() {
        List<Candidate> candidates = new ArrayList<Candidate>();
        Candidate candidate1 = new Candidate("Hà Hoài", "293847563", App.GENDER_NAM, App.DrawableToByteArray(R.drawable.candidate_avatar, this.getActivity()));
        Candidate candidate2 = new Candidate("Hà Hồng", "293847564", App.GENDER_NAM, App.DrawableToByteArray(R.drawable.candidate_avatar, this.getActivity()));
        Candidate candidate3 = new Candidate("Hà Hiền", "293847565", App.GENDER_NAM, App.DrawableToByteArray(R.drawable.candidate_avatar, this.getActivity()));
        Candidate candidate4 = new Candidate("Hà Đào", "293847566", App.GENDER_NAM, App.DrawableToByteArray(R.drawable.candidate_avatar, this.getActivity()));
        candidates.add(candidate1);
        candidates.add(candidate2);
        candidates.add(candidate3);
        candidates.add(candidate4);
        for (Candidate candidate : candidates) {
            if (!candidateDAO.CheckCandidateExits(candidate.getCMND())) {
                candidateDAO.addCandidate(candidate);
            }
        }
    }
}