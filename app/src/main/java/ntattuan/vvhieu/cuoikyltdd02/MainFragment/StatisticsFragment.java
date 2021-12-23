package ntattuan.vvhieu.cuoikyltdd02.MainFragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import ntattuan.vvhieu.cuoikyltdd02.App;
import ntattuan.vvhieu.cuoikyltdd02.Data.CandidateDAO;
import ntattuan.vvhieu.cuoikyltdd02.Data.StatisticDAO;
import ntattuan.vvhieu.cuoikyltdd02.Data.UserDAO;
import ntattuan.vvhieu.cuoikyltdd02.Model.Candidate;
import ntattuan.vvhieu.cuoikyltdd02.Model.Statistic;
import ntattuan.vvhieu.cuoikyltdd02.R;

public class StatisticsFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private TextView Statistics_Count_CandidateActive, Statistics_Count_CandidateNoActive, Statistics_Count_CandidateTotal;

    private TextView Statistics_DoanPhi_NameRound, Statistics_DoanPhi_MoneyRound, Statistics_DoanPhi_Candidate_You, Statistics_DoanPhi_Candidate_Another, Statistics_DoanPhi_Candidate_No, Statistics_DoanPhi_Candidate_Total;
    private TextView Statistics_DoanPhi_Money_You, Statistics_DoanPhi_Money_Another, Statistics_DoanPhi_Money_No, Statistics_DoanPhi_Money_Total;

    private TextView Statistics_HoiPhi_NameRound, Statistics_HoiPhi_MoneyRound, Statistics_HoiPhi_Candidate_You, Statistics_HoiPhi_Candidate_Another, Statistics_HoiPhi_Candidate_No, Statistics_HoiPhi_Candidate_Total;
    private TextView Statistics_HoiPhi_Money_You, Statistics_HoiPhi_Money_Another, Statistics_HoiPhi_Money_No, Statistics_HoiPhi_Money_Total;
    private TextView Statistic_ViewRole;
    private AutoCompleteTextView Statistics_TextSearch;
    private ImageView Statistics_Search_ButtonClose;
    private CandidateDAO candidateDAO;
    private PieChart Candidate_piechart;
    private PieChart DoanPhi_piechart;
    private PieChart HoiPhi_piechart;
    private PieChart piechartLengend;
    private String[] Statistic_SearchKey;

    public StatisticsFragment() {
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
        View view = inflater.inflate(R.layout.fragment_statistics, container, false);
        candidateDAO = new CandidateDAO(this.getActivity());
        Candidate_piechart = (PieChart) view.findViewById(R.id.Candidate_piechart);
        DoanPhi_piechart = (PieChart) view.findViewById(R.id.DoanPhi_piechart);
        HoiPhi_piechart = (PieChart) view.findViewById(R.id.HoiPhi_piechart);
        piechartLengend = (PieChart) view.findViewById(R.id.piechartLengend);
        Statistics_Count_CandidateActive = (TextView) view.findViewById(R.id.Statistics_Count_CandidateActive);
        Statistics_Count_CandidateNoActive = (TextView) view.findViewById(R.id.Statistics_Count_CandidateNoActive);
        Statistics_Count_CandidateTotal = (TextView) view.findViewById(R.id.Statistics_Count_CandidateTotal);

        Statistics_DoanPhi_NameRound = (TextView) view.findViewById(R.id.Statistics_DoanPhi_NameRound);
        Statistics_DoanPhi_MoneyRound = (TextView) view.findViewById(R.id.Statistics_DoanPhi_MoneyRound);
        Statistics_DoanPhi_Candidate_You = (TextView) view.findViewById(R.id.Statistics_DoanPhi_Candidate_You);
        Statistics_DoanPhi_Candidate_Another = (TextView) view.findViewById(R.id.Statistics_DoanPhi_Candidate_Another);
        Statistics_DoanPhi_Candidate_No = (TextView) view.findViewById(R.id.Statistics_DoanPhi_Candidate_No);
        Statistics_DoanPhi_Candidate_Total = (TextView) view.findViewById(R.id.Statistics_DoanPhi_Candidate_Total);
        Statistics_DoanPhi_Money_You = (TextView) view.findViewById(R.id.Statistics_DoanPhi_Money_You);
        Statistics_DoanPhi_Money_Another = (TextView) view.findViewById(R.id.Statistics_DoanPhi_Money_Another);
        Statistics_DoanPhi_Money_No = (TextView) view.findViewById(R.id.Statistics_DoanPhi_Money_No);
        Statistics_DoanPhi_Money_Total = (TextView) view.findViewById(R.id.Statistics_DoanPhi_Money_Total);

        Statistics_HoiPhi_NameRound = (TextView) view.findViewById(R.id.Statistics_HoiPhi_NameRound);
        Statistics_HoiPhi_MoneyRound = (TextView) view.findViewById(R.id.Statistics_HoiPhi_MoneyRound);
        Statistics_HoiPhi_Candidate_You = (TextView) view.findViewById(R.id.Statistics_HoiPhi_Candidate_You);
        Statistics_HoiPhi_Candidate_Another = (TextView) view.findViewById(R.id.Statistics_HoiPhi_Candidate_Another);
        Statistics_HoiPhi_Candidate_No = (TextView) view.findViewById(R.id.Statistics_HoiPhi_Candidate_No);
        Statistics_HoiPhi_Candidate_Total = (TextView) view.findViewById(R.id.Statistics_HoiPhi_Candidate_Total);
        Statistics_HoiPhi_Money_You = (TextView) view.findViewById(R.id.Statistics_HoiPhi_Money_You);
        Statistics_HoiPhi_Money_Another = (TextView) view.findViewById(R.id.Statistics_HoiPhi_Money_Another);
        Statistics_HoiPhi_Money_No = (TextView) view.findViewById(R.id.Statistics_HoiPhi_Money_No);
        Statistics_HoiPhi_Money_Total = (TextView) view.findViewById(R.id.Statistics_HoiPhi_Money_Total);

        //Chức năng tìm kiếm
        Statistic_ViewRole = (TextView) view.findViewById(R.id.Statistic_ViewRole);
        Statistics_Search_ButtonClose = (ImageView) view.findViewById(R.id.Statistics_Search_ButtonClose);
        Statistic_SearchKey = new UserDAO(this.getActivity()).getAllUserName();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getContext(),
                android.R.layout.simple_list_item_1, Statistic_SearchKey
        );
        AutoCompleteTextView Statistics_TextSearch = (AutoCompleteTextView) view.findViewById(R.id.Statistics_TextSearch);
        Statistics_TextSearch.setAdapter(adapter);
        Statistics_TextSearch.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        //Lấy dữ liệu từ DB
                        loadPieChartData(DoanPhi_piechart, App.TYPE_ROUND_DOAN_PHI, String.valueOf(Statistics_TextSearch.getText()));
                        loadPieChartData(HoiPhi_piechart, App.TYPE_ROUND_HOI_PHI, String.valueOf(Statistics_TextSearch.getText()));
                        App.hideKeyboard(getActivity());
                    }
                }
        );
        Statistics_Search_ButtonClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadPieChartData(DoanPhi_piechart, App.TYPE_ROUND_DOAN_PHI, App.UserLogined.getUserName());
                loadPieChartData(HoiPhi_piechart, App.TYPE_ROUND_HOI_PHI, App.UserLogined.getUserName());
                Statistics_TextSearch.setText("");
            }
        });
        if (!App.CheckIsAdministrator()){
            Statistics_Search_ButtonClose.setEnabled(false);
            Statistics_TextSearch.setEnabled(false);
            Statistics_TextSearch.setHint("Chức năng chỉ dành cho Administrator");
        }
        //Lấy dữ liệu từ DB
        DoanVien_setupPieChart();
        DoanVien_loadPieChartData();
        setupPieChart(DoanPhi_piechart, false, App.TYPE_ROUND_DOAN_PHI);
        setupPieChart(HoiPhi_piechart, false, App.TYPE_ROUND_HOI_PHI);
        setupPieChart(piechartLengend, true, -1);
        //Lấy dữ liệu từ DB
        loadPieChartData(DoanPhi_piechart, App.TYPE_ROUND_DOAN_PHI, App.UserLogined.getUserName());
        loadPieChartData(HoiPhi_piechart, App.TYPE_ROUND_HOI_PHI, App.UserLogined.getUserName());
        loadPieChartDataLengend(piechartLengend);
        return view;
    }

    private void DoanVien_setupPieChart() {
        Candidate_piechart.setDrawHoleEnabled(true);
        Candidate_piechart.setUsePercentValues(true);
        Candidate_piechart.setEntryLabelTextSize(12);
        Candidate_piechart.setEntryLabelColor(Color.BLACK);
        Candidate_piechart.setCenterText("Đoàn viên");
        Candidate_piechart.setCenterTextSize(24);
        Candidate_piechart.getDescription().setEnabled(false);

        Legend l = Candidate_piechart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setEnabled(true);
    }

    private void DoanVien_loadPieChartData() {
        int CountActive = candidateDAO.Count(App.ACTIVE);
        int CountNoActive = candidateDAO.Count(App.NO_ACTIVE);
        int CountTotal = CountActive + CountNoActive;
        Statistics_Count_CandidateActive.setText(String.valueOf(CountActive));
        Statistics_Count_CandidateNoActive.setText(String.valueOf(CountNoActive));
        Statistics_Count_CandidateTotal.setText(String.valueOf(CountTotal));
        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();
        entries.add(new PieEntry(CountActive, "Đã Duyệt"));
        entries.add(new PieEntry(CountNoActive, "Chờ Duyệt"));

        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.rgb(53, 152, 219));
        colors.add(Color.rgb(241, 196, 15));
        PieDataSet dataSet = new PieDataSet(entries, "");
        dataSet.setColors(colors);
        PieData data = new PieData(dataSet);
        data.setDrawValues(true);
        data.setValueFormatter(new PercentFormatter(Candidate_piechart)); //Add += "%"
        data.setValueTextSize(12f);//kich thức chữ
        data.setValueTextColor(Color.BLACK);
        Candidate_piechart.setData(data);
        Candidate_piechart.invalidate();
        Candidate_piechart.animateY(1000, Easing.EaseInOutQuad);
        Candidate_piechart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                Candidate_piechart.setCenterText(App.StringtoPeople(String.valueOf(h.getY())));
            }

            @Override
            public void onNothingSelected() {
                Candidate_piechart.setCenterText("Đoàn viên");
            }
        });
    }

    private void setupPieChart(PieChart pieChart, boolean LegendEnable, int Type) {
        pieChart.setDrawHoleEnabled(true);
        pieChart.setUsePercentValues(true);
        pieChart.setEntryLabelTextSize(12);
        pieChart.setDrawEntryLabels(false);
        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.setCenterText(Type == App.TYPE_ROUND_DOAN_PHI ? "Đoàn phí" : "Hội phí");
        pieChart.setCenterTextSize(18);
        pieChart.getDescription().setEnabled(false);

        Legend l = pieChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.CENTER);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);

        l.setDrawInside(true);
        l.setEnabled(LegendEnable);
    }

    private void loadPieChartData(PieChart pieChart, int Type, String UserName) {
        Statistic_ViewRole.setText("Bạn đang xem thống kê với tư cách : " + UserName);
        Statistic statistic = new StatisticDAO(this.getActivity()).getStatistics(Type, UserName);
        if (Type == App.TYPE_ROUND_DOAN_PHI) {
            Statistics_DoanPhi_NameRound.setText(statistic.getRoundCurent().getName());
            Statistics_DoanPhi_MoneyRound.setText(statistic.getPriceToString());
            Statistics_DoanPhi_Candidate_You.setText("Bạn thu" + " (" + statistic.getCollect_you() + "):");
            Statistics_DoanPhi_Candidate_Another.setText("N.Khác thu" + " (" + statistic.getCollect_another() + "):");
            Statistics_DoanPhi_Candidate_No.setText("Chưa thu" + " (" + statistic.getCollect_no() + "):");
            Statistics_DoanPhi_Candidate_Total.setText("Tổng" + " (" + statistic.getTotal() + "):");
            Statistics_DoanPhi_Money_You.setText(App.CurrencytoK(statistic.getMoneyCollect_you()));
            Statistics_DoanPhi_Money_Another.setText(App.CurrencytoK(statistic.getMoneyCollect_another()));
            Statistics_DoanPhi_Money_No.setText(App.CurrencytoK(statistic.getMoneyCollect_no()));
            Statistics_DoanPhi_Money_Total.setText(App.CurrencytoK(statistic.getMoney_Total()));
        } else {
            Statistics_HoiPhi_NameRound.setText(statistic.getRoundCurent().getName());
            Statistics_HoiPhi_MoneyRound.setText(statistic.getPriceToString());
            Statistics_HoiPhi_Candidate_You.setText("Bạn thu" + " (" + statistic.getCollect_you() + "):");
            Statistics_HoiPhi_Candidate_Another.setText("Khác thu" + " (" + statistic.getCollect_another() + "):");
            Statistics_HoiPhi_Candidate_No.setText("Chưa thu" + " (" + statistic.getCollect_no() + "):");
            Statistics_HoiPhi_Candidate_Total.setText("Tổng" + " (" + statistic.getTotal() + "):");
            Statistics_HoiPhi_Money_You.setText(App.CurrencytoK(statistic.getMoneyCollect_you()));
            Statistics_HoiPhi_Money_Another.setText(App.CurrencytoK(statistic.getMoneyCollect_another()));
            Statistics_HoiPhi_Money_No.setText(App.CurrencytoK(statistic.getMoneyCollect_no()));
            Statistics_HoiPhi_Money_Total.setText(App.CurrencytoK(statistic.getMoney_Total()));
        }
        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();
        entries.add(new PieEntry(statistic.getCollect_you(), "Bạn thu"));
        entries.add(new PieEntry(statistic.getCollect_another(), "Người khác thu"));
        entries.add(new PieEntry(statistic.getCollect_no(), "Chưa thu"));

        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.rgb(45, 204, 112));
        colors.add(Color.rgb(192, 255, 140));
        colors.add(Color.rgb(227, 55, 36));

        PieDataSet dataSet = new PieDataSet(entries, "");
        dataSet.setColors(colors);
        PieData data = new PieData(dataSet);
        data.setDrawValues(true);
        data.setValueFormatter(new PercentFormatter(pieChart)); //Add += "%"
        data.setValueTextSize(12f);//kich thức %
        data.setValueTextColor(Color.BLACK);
        pieChart.setData(data);
        pieChart.invalidate();
        pieChart.animateY(500, Easing.EaseInOutQuad);
        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                pieChart.setCenterText(App.CurrencytoK(String.valueOf(h.getY())));
            }

            @Override
            public void onNothingSelected() {
                pieChart.setCenterText(Type == App.TYPE_ROUND_DOAN_PHI ? "Đoàn phí" : "Hội phí");
            }
        });
    }

    private void loadPieChartDataLengend(PieChart pieChart) {
        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();
        entries.add(new PieEntry(0, "Bạn thu"));
        entries.add(new PieEntry(0, "Người khác thu"));
        entries.add(new PieEntry(0, "Chưa thu"));
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.rgb(45, 204, 112));
        colors.add(Color.rgb(192, 255, 140));
        colors.add(Color.rgb(227, 55, 36));
        PieDataSet dataSet = new PieDataSet(entries, "");
        dataSet.setColors(colors);
        PieData data = new PieData(dataSet);
        pieChart.setData(data);
    }
}