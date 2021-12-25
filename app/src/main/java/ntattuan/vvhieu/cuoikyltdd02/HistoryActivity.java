package ntattuan.vvhieu.cuoikyltdd02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import java.util.List;

import ntattuan.vvhieu.cuoikyltdd02.Adapter.HistoryAdapter;
import ntattuan.vvhieu.cuoikyltdd02.Data.CandidateDAO;
import ntattuan.vvhieu.cuoikyltdd02.Data.HistoryDAO;
import ntattuan.vvhieu.cuoikyltdd02.Model.Candidate;
import ntattuan.vvhieu.cuoikyltdd02.Model.History;

public class HistoryActivity extends AppCompatActivity {
    private HistoryDAO historyDAO;
    private CandidateDAO candidateDAO;
    private ImageView History_ButtonBack,History_Candidate_Avatar;
    private TextView History_Candidate_Name, History_Candidate_CMND,History_Candidate_SDT,History_Candidate_Gender,History_notifi;
    private ListView History_listview;
    private TabLayout History_TabLayout;
    private HistoryAdapter historyAdapter;
    private Candidate candidateCurrent;
    private int TypeCurrent = App.TYPE_ROUND_DOAN_PHI;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        historyDAO = new HistoryDAO(this);
        candidateDAO = new CandidateDAO(this);
        historyAdapter = new HistoryAdapter(this);

        History_ButtonBack = (ImageView) findViewById(R.id.History_ButtonBack);
        History_Candidate_Avatar = (ImageView) findViewById(R.id.History_Candidate_Avatar);

        History_Candidate_Name = (TextView) findViewById(R.id.History_Candidate_Name);
        History_Candidate_CMND = (TextView) findViewById(R.id.History_Candidate_CMND);
        History_Candidate_SDT = (TextView) findViewById(R.id.History_Candidate_SDT);
        History_Candidate_Gender = (TextView) findViewById(R.id.History_Candidate_Gender);
        History_notifi = (TextView) findViewById(R.id.History_notifi);
        History_listview = (ListView) findViewById(R.id.History_listview);
        History_TabLayout = (TabLayout) findViewById(R.id.History_TabLayout);

        //Lấy dữ liệu từ Intent về set
        Intent callerIntent = getIntent();
        Bundle packageFromCaller = callerIntent.getBundleExtra("CandidateCurrent");
        int CandidateID = packageFromCaller.getInt("CandidateID");
        candidateCurrent = candidateDAO.getCandidateByID(CandidateID);
        candidateCurrent = candidateDAO.getCandidateByID(CandidateID);
        History_Candidate_Avatar.setImageBitmap(candidateCurrent.getAvatarToBitMap());
        History_Candidate_Name.setText("Tên: " + candidateCurrent.getName());
        History_Candidate_CMND.setText("CMND/CCCD: " + candidateCurrent.getCMND());
        History_Candidate_SDT.setText("SĐT: "+ candidateCurrent.getSDT());
        History_Candidate_Gender.setText("Giới tính: " + candidateCurrent.getGenderToString());
        LoadListView_History();
        History_ButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        History_TabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        TypeCurrent = App.TYPE_ROUND_DOAN_PHI;
                        break;
                    case 1:
                        TypeCurrent = App.TYPE_ROUND_HOI_PHI;
                        break;
                    default:
                        TypeCurrent = App.TYPE_ROUND_DOAN_PHI;
                        break;
                }
                LoadListView_History();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    public void LoadListView_History() {
        List<History> histories = historyDAO.getHistoryOfCandidate(candidateCurrent.getId(),TypeCurrent);
        historyAdapter.setListHistory(histories);
        History_listview.setAdapter(historyAdapter);
        if (histories.size()<1){
            History_notifi.setVisibility(View.VISIBLE);
        }else{
            History_notifi.setVisibility(View.GONE);
        }
    }
}