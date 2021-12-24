package ntattuan.vvhieu.cuoikyltdd02.MainFragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import java.util.List;

import ntattuan.vvhieu.cuoikyltdd02.Adapter.CandidateAdapter;
import ntattuan.vvhieu.cuoikyltdd02.Adapter.UserAdapter;
import ntattuan.vvhieu.cuoikyltdd02.AddCandidateActivity;
import ntattuan.vvhieu.cuoikyltdd02.AddUserActivity;
import ntattuan.vvhieu.cuoikyltdd02.App;
import ntattuan.vvhieu.cuoikyltdd02.CustomEvent.OnChangeAdapter;
import ntattuan.vvhieu.cuoikyltdd02.Data.CandidateDAO;
import ntattuan.vvhieu.cuoikyltdd02.Data.UserDAO;
import ntattuan.vvhieu.cuoikyltdd02.Model.Candidate;
import ntattuan.vvhieu.cuoikyltdd02.Model.User;
import ntattuan.vvhieu.cuoikyltdd02.R;

public class UserFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private TextView User_textSearch;
    private ImageView User_ButtonAdd, User_SearchClose;
    private Button User_Button_isLock;
    private int isLock = App.ACTIVE;
    private ListView User_listview;
    private UserDAO userDAO;
    private UserAdapter userAdapter;
    private FragmentActivity myfragmentActivity = this.getActivity();
    public UserFragment() {
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
        View view = inflater.inflate(R.layout.fragment_user, container, false);

        userDAO = new UserDAO(this.getActivity());
        User_textSearch = (TextView) view.findViewById(R.id.User_textSearch);
        User_ButtonAdd = (ImageView) view.findViewById(R.id.User_ButtonAdd);
        User_SearchClose = (ImageView) view.findViewById(R.id.User_SearchClose);
        User_Button_isLock = (Button) view.findViewById(R.id.User_Button_isLock);
        User_listview = (ListView) view.findViewById(R.id.User_listview);
        userAdapter = new UserAdapter(this.getActivity());
        LoadListView_User();

        //Tìm kiếm đoàn viên
        User_textSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0) {
                    User_SearchClose.setBackground(getResources().getDrawable(R.drawable.ic_baseline_close_24));
                    Search_User(String.valueOf(s));
                } else {
                    App.hideKeyboard(getActivity());
                    User_SearchClose.setBackgroundResource(0);
                    LoadListView_User();
                }
            }
        });
        User_ButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), AddUserActivity.class);
                startActivityForResult(i, App.LAUNCH_SECOND_ACTIVITY);
            }
        });
        User_SearchClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User_textSearch.setText("");
                App.hideKeyboard(getActivity());
            }
        });
        User_Button_isLock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isLock==App.ACTIVE){
                    User_Button_isLock.setBackgroundColor(Color.rgb(0,122,255));
                    User_Button_isLock.setTextColor(Color.rgb(255,255,255));
                    isLock = App.NO_ACTIVE;
                }else{
                    User_Button_isLock.setBackgroundColor(Color.rgb(245,245,245));
                    User_Button_isLock.setTextColor(Color.rgb(113,113,113));
                    isLock = App.ACTIVE;
                }
                LoadListView_User();
            }
        });
        userAdapter.setOnChangeAdapter(new OnChangeAdapter() {
            @Override
            public void onChange() {
                LoadListView_User();
            }

            @Override
            public void onEdit(int id) {

            }
        });
        return view;
    }
    public void LoadListView_User() {
        List<User> users = userDAO.getAllUser(isLock);
        userAdapter.setListUser(users);
        User_listview.setAdapter(userAdapter);
    }

    private void Search_User(String key) {
        List<User> users = userDAO.searchUser(key,isLock);
        userAdapter.setListUser(users);
        User_listview.setAdapter(userAdapter);
    }
}