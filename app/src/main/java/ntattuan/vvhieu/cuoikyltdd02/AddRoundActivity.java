package ntattuan.vvhieu.cuoikyltdd02;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import ntattuan.vvhieu.cuoikyltdd02.CustomEvent.OnChangeAdapter;
import ntattuan.vvhieu.cuoikyltdd02.Data.RoundDAO;
import ntattuan.vvhieu.cuoikyltdd02.Model.Round;

public class AddRoundActivity extends AppCompatActivity {
    private ImageView Round_Add_ButtonBack, Round_Add_NameHint_Paste;
    private TextView Round_Add_Name,Round_Add_NameError, Round_Add_Price, Round_Add_PriceShow,Round_Add_Tittle;
    private RadioButton Round_Add_DoanPhi, Round_Add_HoiPhi;
    private Button Round_Add_ButtonAdd;
    private RoundDAO roundDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_round);
        roundDAO = new RoundDAO(this);
        Round_Add_ButtonBack = (ImageView) findViewById(R.id.Round_Add_ButtonBack);
        Round_Add_NameHint_Paste = (ImageView) findViewById(R.id.Round_Add_NameHint_Paste);
        Round_Add_Tittle = (TextView) findViewById(R.id.Round_Add_Tittle);
        Round_Add_Name = (TextView) findViewById(R.id.Round_Add_Name);
        Round_Add_NameError = (TextView) findViewById(R.id.Round_Add_NameError);
        Round_Add_Price = (TextView) findViewById(R.id.Round_Add_Price);
        Round_Add_PriceShow = (TextView) findViewById(R.id.Round_Add_PriceShow);
        Round_Add_DoanPhi = (RadioButton) findViewById(R.id.Round_Add_DoanPhi);
        Round_Add_HoiPhi = (RadioButton) findViewById(R.id.Round_Add_HoiPhi);
        Round_Add_ButtonAdd = (Button) findViewById(R.id.Round_Add_ButtonAdd);
        Round_Add_NameError.setVisibility(View.GONE);

        Round_Add_ButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Round_Add_DoanPhi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Round_Add_Name.setText("Đợt Đoàn phí X");
            }
        });
        Round_Add_HoiPhi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Round_Add_Name.setText("Đợt Hội phí X");
            }
        });
        Round_Add_NameHint_Paste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Round_Add_HoiPhi.isChecked()) {
                    Round_Add_Name.setText("Đợt Hội phí X");
                } else {
                    Round_Add_Name.setText("Đợt Đoàn phí X");
                }
            }
        });
        Round_Add_Name.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count != 0 && roundDAO.CheckRoundExits(s.toString())) {
                    Round_Add_NameError.setVisibility(View.VISIBLE);
                } else{
                    Round_Add_NameError.setVisibility(View.GONE);
                }
            }
        });
        Round_Add_Price.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0) {
                    Round_Add_PriceShow.setText(App.CurrencytoVN(Integer.parseInt(s.toString())));
                } else {
                    Round_Add_PriceShow.setText("0 VNĐ");
                }
            }
        });
        Round_Add_ButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (String.valueOf(Round_Add_Name.getText()).replace(" ", "").length() > 0) {
                    if (Round_Add_Price.getText().length() > 0) {
                        if (Integer.parseInt(String.valueOf(Round_Add_Price.getText())) < 1000) {
                            Round_Add_Price.requestFocus();
                            App.ToastShow(getBaseContext(), "Số tiền quá nhỏ");
                        } else {
                            Round round = new Round(
                                    String.valueOf(Round_Add_Name.getText()),
                                    App.GetTimeCurrent(),
                                    Integer.parseInt(String.valueOf(Round_Add_Price.getText())),
                                    App.NO_SHOW,
                                    Round_Add_DoanPhi.isChecked() ? App.TYPE_ROUND_DOAN_PHI : App.TYPE_ROUND_HOI_PHI
                            );
                            if (roundDAO.CheckRoundExits(round.getName())){
                                roundDAO.addRound(round);
                                setResult(Activity.RESULT_CANCELED, new Intent());
                                App.ToastShow(getBaseContext(), "Thêm đợt thành công");
                                finish();
                            }else{
                                Round_Add_Name.requestFocus();
                            }
                        }
                    } else {
                        Round_Add_Price.requestFocus();
                        App.ToastShow(getBaseContext(), "Cần nhập số tiền");
                    }
                } else {
                    Round_Add_Name.requestFocus();
                    App.ToastShow(getBaseContext(), "Cần nhập tên đợt");
                }
            }
        });
    }
}