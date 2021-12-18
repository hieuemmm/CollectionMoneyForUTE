package ntattuan.vvhieu.cuoikyltdd02.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import ntattuan.vvhieu.cuoikyltdd02.App;
import ntattuan.vvhieu.cuoikyltdd02.Fragment.DoanVien.DoanVienAdd;
import ntattuan.vvhieu.cuoikyltdd02.Fragment.DoanVien.DoanVienView;
import ntattuan.vvhieu.cuoikyltdd02.R;

public class DoanVienFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private DoanVienView FDoanVienView;
    private DoanVienAdd FDoanVienAdd;
    private TextView doanvien_textSearch;
    private ImageView doanvien_ButtonAdd,doanvien_SearchClose,doanvien_ButtonBack;


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
        FDoanVienView = new DoanVienView();
        FDoanVienAdd = new DoanVienAdd();

        doanvien_textSearch = (TextView) view.findViewById(R.id.doanvien_textSearch);
        doanvien_ButtonAdd = (ImageView) view.findViewById(R.id.doanvien_ButtonAdd);
        doanvien_SearchClose = (ImageView) view.findViewById(R.id.doanvien_SearchClose);
        doanvien_ButtonBack = (ImageView) view.findViewById(R.id.doanvien_ButtonBack);
        //Mặc định là FramentView
        ReplaceFragment(FDoanVienView);
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
                    FDoanVienView.Search_DoanVien(String.valueOf(s));
                }
                else{
                    doanvien_SearchClose.setBackgroundResource(0);
                    FDoanVienView.LoadListView_DoanVien();
                }
            }
        });
        doanvien_ButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doanvien_ButtonBack.setBackground(getResources().getDrawable(R.drawable.ic_baseline_arrow_back_ios_24));
                ReplaceFragment(FDoanVienAdd);
            }
        });
        doanvien_SearchClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doanvien_textSearch.setText("");
                App.hideKeyboard(getActivity());
            }
        });
        doanvien_ButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReplaceFragment(FDoanVienView);
                doanvien_ButtonBack.setBackgroundResource(0);
            }
        });
        return view;
    }
    private void ReplaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.doanvien_frame, fragment);
        fragmentTransaction.commit();
    }
}