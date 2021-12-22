package ntattuan.vvhieu.cuoikyltdd02;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import java.io.InputStream;

import ntattuan.vvhieu.cuoikyltdd02.Data.CandidateDAO;
import ntattuan.vvhieu.cuoikyltdd02.Model.Candidate;

public class AddCandidateActivity extends AppCompatActivity {
    private ImageView DoanVien_Add_Avatar, DoanVien_Add_ButtonCamera, DoanVien_Add_ButtonLiblary, Doanvien_Add_ButtonBack;
    private TextView Doanvien_Add_Name, Doanvien_Add_CMND, Doanvien_Add_SDT, Doanvien_Add_CMNDError, Doanvien_Add_SDTError;
    private RadioButton Doanvien_Add_GenderNam;
    private Button DoanVien_Add_ButtonAdd;
    private boolean FromErrorCMND = true;
    private CandidateDAO candidateDAO;
    private MainActivity mainActivity = new MainActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_candidate);
        candidateDAO = new CandidateDAO(this);
        DoanVien_Add_Avatar = (ImageView) findViewById(R.id.DoanVien_Add_Avatar);
        DoanVien_Add_ButtonCamera = (ImageView) findViewById(R.id.DoanVien_Add_ButtonCamera);
        DoanVien_Add_ButtonLiblary = (ImageView) findViewById(R.id.DoanVien_Add_ButtonLiblary);
        Doanvien_Add_ButtonBack = (ImageView) findViewById(R.id.Doanvien_Add_ButtonBack);
        Doanvien_Add_Name = (TextView) findViewById(R.id.Doanvien_Add_Name);
        Doanvien_Add_CMND = (TextView) findViewById(R.id.Doanvien_Add_CMND);
        Doanvien_Add_CMNDError = (TextView) findViewById(R.id.Doanvien_Add_CMNDError);
        Doanvien_Add_SDT = (TextView) findViewById(R.id.Doanvien_Add_SDT);
        Doanvien_Add_SDTError = (TextView) findViewById(R.id.Doanvien_Add_SDTError);
        Doanvien_Add_GenderNam = (RadioButton) findViewById(R.id.DoanVien_Add_GenderNam);
        DoanVien_Add_ButtonAdd = (Button) findViewById(R.id.DoanVien_Add_ButtonAdd);

        //Bắt sự kiện
        DoanVien_Add_ButtonCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectImageCamera();
            }
        });
        DoanVien_Add_ButtonLiblary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectImageFolder();
            }
        });
        Doanvien_Add_ButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Doanvien_Add_CMND.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @SuppressLint("ResourceAsColor")
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                FromErrorCMND = true;
                if (s.length() != 0) {
                    Doanvien_Add_CMNDError.setText(s.length() + "/9 kí tự");
                    if (s.length() == 9) {
                        Doanvien_Add_CMNDError.setTextColor(Color.BLACK);
                        Doanvien_Add_CMND.setTextColor(Color.BLACK);
                        if (candidateDAO.CheckCandidateExits(s.toString())) {
                            Doanvien_Add_CMNDError.setText("[ Bị trùng lặp ] " + s.length() + "/9 kí tự");
                            Doanvien_Add_CMND.setTextColor(Color.RED);
                            Doanvien_Add_CMNDError.setTextColor(Color.RED);
                        } else {
                            FromErrorCMND = false;
                            Doanvien_Add_SDT.requestFocus();
                        }
                    } else {
                        Doanvien_Add_CMND.setTextColor(Color.RED);
                        Doanvien_Add_CMNDError.setTextColor(Color.RED);
                    }
                }
            }
        });
        Doanvien_Add_SDT.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @SuppressLint("ResourceAsColor")
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0) {
                    Doanvien_Add_SDTError.setText(s.length() + "/10 kí tự");
                    if (s.length() == 10 || s.length() == 11) {
                        Doanvien_Add_SDTError.setTextColor(Color.BLACK);
                        Doanvien_Add_SDT.setTextColor(Color.BLACK);
                        if (true) {
                            Doanvien_Add_SDTError.setText("[ Đã tồn tại ] " + s.length() + "/10 kí tự");
                            Doanvien_Add_SDT.setTextColor(Color.RED);
                            Doanvien_Add_SDT.setTextColor(Color.RED);
                        }
                    } else {
                        Doanvien_Add_SDT.setTextColor(Color.RED);
                        Doanvien_Add_SDT.setTextColor(Color.RED);
                    }
                }
            }
        });
        Doanvien_Add_Name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                //sự kiện rời forcus
                if (!hasFocus)
                    if (Doanvien_Add_Name.getText().length() > 0)
                        Doanvien_Add_Name.setText(App.chuannHoaHoTen(String.valueOf(Doanvien_Add_Name.getText())));
            }
        });
        DoanVien_Add_ButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Doanvien_Add_Name.getText().toString().replace(" ", "").length() == 0) {
                    Doanvien_Add_Name.requestFocus();
                } else if (FromErrorCMND == false) {
                    //Đủ điển kiện thêm đoàn viên và không trùng CMND
                    String SDT = "";
                    if (Doanvien_Add_SDT.getText().toString().replace(" ", "").length() == 10||Doanvien_Add_SDT.getText().toString().replace(" ", "").length() == 11) {
                        SDT = String.valueOf(Doanvien_Add_SDT.getText());
                    }
                    Candidate candidate = new Candidate(
                            Doanvien_Add_Name.getText().toString(),
                            Doanvien_Add_CMND.getText().toString(),
                            SDT,
                            Doanvien_Add_GenderNam.isChecked() ? App.GENDER_NAM : App.GENDER_NU,
                            App.getImageBitmap(DoanVien_Add_Avatar),
                            App.CheckIsAdministrator() ? App.ACTIVE : App.NO_ACTIVE
                    );
                    candidateDAO.addCandidate(candidate);
                    setResult(Activity.RESULT_CANCELED, new Intent());
                    App.ToastShow(getBaseContext(),"Thêm thành công");
                    finish();
                } else {
                    Doanvien_Add_CMND.requestFocus();
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == App.REQUEST_CODE_CAMERA && resultCode == RESULT_OK && data != null) {
            try {
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                DoanVien_Add_Avatar.setBackgroundResource(0);
                DoanVien_Add_Avatar.setImageBitmap(bitmap);
            } catch (Exception e) {
            }
        } else if (requestCode == App.REQUEST_CODE_FOLDER && resultCode == RESULT_OK && data != null) {
            try {
                Uri uri = data.getData();
                InputStream inputStream = this.getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                DoanVien_Add_Avatar.setBackgroundResource(0);
                DoanVien_Add_Avatar.setImageBitmap(bitmap);
            } catch (Exception e) {
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void SelectImageFolder() {
        Intent gallery = new Intent();
        gallery.setType("image/*");
        gallery.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(gallery, App.REQUEST_CODE_FOLDER);
    }

    private void SelectImageCamera() {
        Intent gallery = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(gallery, App.REQUEST_CODE_CAMERA);
    }
}