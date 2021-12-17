package ntattuan.vvhieu.cuoikyltdd02.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import ntattuan.vvhieu.cuoikyltdd02.LoginActivity;
import ntattuan.vvhieu.cuoikyltdd02.MainActivity;
import ntattuan.vvhieu.cuoikyltdd02.R;

public class CaNhanFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private Button logoutButton;

    public CaNhanFragment() {

    }
    public static CaNhanFragment newInstance(String param1, String param2) {
        CaNhanFragment fragment = new CaNhanFragment();
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
        View view = inflater.inflate(R.layout.fragment_ca_nhan, container, false);
        logoutButton = (Button) view.findViewById(R.id.logoutButton);

        //Bắt sự kiện
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PressLogout();
            }
        });
        return view;
    }

    private void PressLogout(){
        LoginActivity.UserLogined = null;
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
    }
}