package ntattuan.vvhieu.cuoikyltdd02;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import ntattuan.vvhieu.cuoikyltdd02.Data.RoundDAO;
import ntattuan.vvhieu.cuoikyltdd02.Model.Candidate;
import ntattuan.vvhieu.cuoikyltdd02.Model.Round;

public class EditRoundActivity extends AppCompatActivity {
    private ImageView Round_Edit_ButtonBack, Round_Edit_NameHint_Paste;
    private TextView Round_Edit_Name, Round_Edit_NameError, Round_Edit_Price, Round_Edit_PriceShow, Round_Edit_Tittle;
    private RadioButton Round_Edit_DoanPhi, Round_Edit_HoiPhi;
    private Button Round_Edit_ButtonEdit;
    private RoundDAO roundDAO;
    private Round roundCurrent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_round);
        roundDAO = new RoundDAO(this);
        Round_Edit_ButtonBack = (ImageView) findViewById(R.id.Round_Edit_ButtonBack);
        Round_Edit_NameHint_Paste = (ImageView) findViewById(R.id.Round_Edit_NameHint_Paste);
        Round_Edit_Tittle = (TextView) findViewById(R.id.Round_Edit_Tittle);
        Round_Edit_Name = (TextView) findViewById(R.id.Round_Edit_Name);
        Round_Edit_NameError = (TextView) findViewById(R.id.Round_Edit_NameError);
        Round_Edit_Price = (TextView) findViewById(R.id.Round_Edit_Price);
        Round_Edit_PriceShow = (TextView) findViewById(R.id.Round_Edit_PriceShow);
        Round_Edit_DoanPhi = (RadioButton) findViewById(R.id.Round_Edit_DoanPhi);
        Round_Edit_HoiPhi = (RadioButton) findViewById(R.id.Round_Edit_HoiPhi);
        Round_Edit_ButtonEdit = (Button) findViewById(R.id.Round_Edit_ButtonEdit);
        Round_Edit_NameError.setVisibility(View.GONE);

        //Lấy dữ liệu từ Intent về set
        Intent callerIntent = getIntent();
        Bundle packageFromCaller = callerIntent.getBundleExtra("RoundCurrent");
        int RoundID = packageFromCaller.getInt("RoundID");
        roundCurrent = roundDAO.getRoundByID(RoundID);
        if (roundCurrent.getType()==App.TYPE_ROUND_DOAN_PHI){
            Round_Edit_DoanPhi.setChecked(true);
            Round_Edit_HoiPhi.setVisibility(View.GONE);
        }else{
            Round_Edit_HoiPhi.setChecked(true);
            Round_Edit_DoanPhi.setVisibility(View.GONE);
        }
        Round_Edit_Name.setText(roundCurrent.getName());
        Round_Edit_Price.setText(String.valueOf(roundCurrent.getPrice()));
        Round_Edit_PriceShow.setText(App.CurrencytoVN(roundCurrent.getPrice()));

        Round_Edit_ButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Round_Edit_DoanPhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Round_Edit_Name.setText("Đợt Đoàn phí X");
            }
        });
        Round_Edit_HoiPhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Round_Edit_Name.setText("Đợt Hội phí X");
            }
        });
        Round_Edit_NameHint_Paste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Round_Edit_HoiPhi.isChecked()) {
                    Round_Edit_Name.setText("Đợt Hội phí X");
                } else {
                    Round_Edit_Name.setText("Đợt Đoàn phí X");
                }
            }
        });
        Round_Edit_Name.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count != 0 && roundDAO.CheckRoundExits(s.toString(),roundCurrent.getName())) {
                    Round_Edit_NameError.setVisibility(View.VISIBLE);
                } else {
                    Round_Edit_NameError.setVisibility(View.GONE);
                }
            }
        });
        Round_Edit_Price.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0) {
                    Round_Edit_PriceShow.setText(App.CurrencytoVN(Integer.parseInt(s.toString())));
                } else {
                    Round_Edit_PriceShow.setText("0 VNĐ");
                }
            }
        });
        Round_Edit_ButtonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (String.valueOf(Round_Edit_Name.getText()).replace(" ", "").length() > 0) {
                    if (Round_Edit_Price.getText().length() > 0) {
                        if (Integer.parseInt(String.valueOf(Round_Edit_Price.getText())) < 1000) {
                            Round_Edit_Name.requestFocus();
                            App.ToastShow(getBaseContext(), "Số tiền quá nhỏ");
                        } else {
                            Round round = new Round(
                                    roundCurrent.getId(),
                                    String.valueOf(Round_Edit_Name.getText()),
                                    Integer.parseInt(String.valueOf(Round_Edit_Price.getText()))
                            );
                            if (!roundDAO.CheckRoundExits(round.getName(), roundCurrent.getName())){
                                roundDAO.UpdateRound(round);
                                setResult(Activity.RESULT_CANCELED, new Intent());
                                App.ToastShow(getBaseContext(), "Sửa đợt thành công");
                                finish();
                            }else{
                                Round_Edit_Name.requestFocus();
                            }
                        }
                    } else {
                        Round_Edit_Price.requestFocus();
                        App.ToastShow(getBaseContext(), "Cần nhập số tiền");
                    }
                } else {
                    Round_Edit_Name.requestFocus();
                    App.ToastShow(getBaseContext(), "Cần nhập tên đợt");
                }
            }
        });
    }
}