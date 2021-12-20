package ntattuan.vvhieu.cuoikyltdd02.MainFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import ntattuan.vvhieu.cuoikyltdd02.Adapter.CandidateAdapter;
import ntattuan.vvhieu.cuoikyltdd02.Adapter.RoundAdapter;
import ntattuan.vvhieu.cuoikyltdd02.App;
import ntattuan.vvhieu.cuoikyltdd02.Data.CandidateDAO;
import ntattuan.vvhieu.cuoikyltdd02.Data.RoundDAO;
import ntattuan.vvhieu.cuoikyltdd02.Model.Candidate;
import ntattuan.vvhieu.cuoikyltdd02.Model.Round;
import ntattuan.vvhieu.cuoikyltdd02.R;

public class RoundFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private TextView Round_textSearch;
    private ImageView Round_ButtonAdd, Round_SearchClose;
    private TabLayout Round_TabLayout;
    private ListView Round_listview;
    private RoundDAO roundDAO;
    private RoundAdapter roundAdapter;

    //private FragmentActivity myfragmentActivity = this.getActivity();
    public RoundFragment() {
    }

    public static RoundFragment newInstance(String param1, String param2) {
        RoundFragment fragment = new RoundFragment();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_round, container, false);
        Round_textSearch = (TextView) view.findViewById(R.id.Round_textSearch);
        Round_ButtonAdd = (ImageView) view.findViewById(R.id.Round_ButtonAdd);
        Round_SearchClose = (ImageView) view.findViewById(R.id.Round_SearchClose);
        Round_TabLayout = (TabLayout) view.findViewById(R.id.Round_TabLayout);
        Round_listview = (ListView) view.findViewById(R.id.doanvien_listview);
        roundDAO = new RoundDAO(this.getActivity());

        roundAdapter = new RoundAdapter(this.getActivity());
        TaoDuLieuMau();
        LoadListView_Round();
        Round_TabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case App.ROUND_TAB_DOAN_PHI:
                        App.Round_Tab_Current = App.ROUND_TAB_DOAN_PHI;
                        break;
                    case App.ROUND_TAB_HOI_PHI:
                        App.Round_Tab_Current = App.ROUND_TAB_HOI_PHI;
                        break;
                    default:
                        App.Round_Tab_Current = App.ROUND_TAB_DOAN_PHI;
                        break;
                }
                LoadListView_Round();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        return view;
    }

    public void LoadListView_Round() {
        List<Round> rounds = roundDAO.getAllRound();
//        roundAdapter.setListRound(rounds);
//        Round_listview.setAdapter(roundAdapter);
    }

    private void TaoDuLieuMau() {
        List<Round> rounds = new ArrayList<Round>();
        Round roundDoanPhi1 = new Round(
                "Đợt Đoàn Phí 1",
                App.GetTimeCurrent(),
                600000,
                App.SHOW,
                App.TYPE_ROUND_DOAN_PHI
        );
        Round roundDoanPhi2 = new Round(
                "Đợt Đoàn Phí 2",
                App.GetTimeCurrent(),
                600000,
                App.SHOW,
                App.TYPE_ROUND_DOAN_PHI
        );
        Round roundHoiPhi1 = new Round(
                "Đợt Hội Phí 1",
                App.GetTimeCurrent(),
                600000,
                App.SHOW,
                App.TYPE_ROUND_HOI_PHI
        );
        Round roundHoiPhi2 = new Round(
                "Đợt Hội Phí 1",
                App.GetTimeCurrent(),
                600000,
                App.SHOW,
                App.TYPE_ROUND_HOI_PHI
        );
        rounds.add(roundDoanPhi1);
        rounds.add(roundDoanPhi2);
        rounds.add(roundHoiPhi1);
        rounds.add(roundHoiPhi2);
        for (Round round : rounds) {
            if (!roundDAO.CheckRoundExits(round.getName())) {
                roundDAO.addRound(round);
            }
        }
    }
}