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

import ntattuan.vvhieu.cuoikyltdd02.Adapter.CandidateAdapter;
import ntattuan.vvhieu.cuoikyltdd02.Data.CandidateDAO;
import ntattuan.vvhieu.cuoikyltdd02.Model.Candidate;

public class EditCandidateActivity extends AppCompatActivity {
    private ImageView DoanVien_Edit_Avatar, DoanVien_Edit_ButtonCamera, DoanVien_Edit_ButtonLiblary, DoanVien_Edit_ButtonBack,DoanVien_Edit_ReloadButton;
    private TextView DoanVien_Edit_ID, DoanVien_Edit_Name, DoanVien_Edit_CMND, DoanVien_Edit_SDT, DoanVien_Edit_CMNDError, DoanVien_Edit_SDTError;
    private CandidateDAO candidateDAO;
    private Candidate candidateCurrent;
    private RadioButton DoanVien_Edit_GenderNam, Doanvien_Edit_GenderNu;
    private Button DoanVien_Edit_ButtonEdit;
    private boolean FromErrorCMND = false;
    private boolean FromErrorSDT = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_candidate);
        candidateDAO = new CandidateDAO(this);
        DoanVien_Edit_Avatar = (ImageView) findViewById(R.id.DoanVien_Edit_Avatar);
        DoanVien_Edit_ButtonCamera = (ImageView) findViewById(R.id.DoanVien_Edit_ButtonCamera);
        DoanVien_Edit_ButtonLiblary = (ImageView) findViewById(R.id.DoanVien_Edit_ButtonLiblary);
        DoanVien_Edit_ButtonBack = (ImageView) findViewById(R.id.DoanVien_Edit_ButtonBack);
        DoanVien_Edit_ReloadButton = (ImageView) findViewById(R.id.DoanVien_Edit_ReloadButton);
        DoanVien_Edit_ID = (TextView) findViewById(R.id.DoanVien_Edit_ID);
        DoanVien_Edit_Name = (TextView) findViewById(R.id.DoanVien_Edit_Name);
        DoanVien_Edit_CMND = (TextView) findViewById(R.id.DoanVien_Edit_CMND);
        DoanVien_Edit_CMNDError = (TextView) findViewById(R.id.DoanVien_Edit_CMNDError);
        DoanVien_Edit_SDT = (TextView) findViewById(R.id.DoanVien_Edit_SDT);
        DoanVien_Edit_SDTError = (TextView) findViewById(R.id.DoanVien_Edit_SDTError);
        DoanVien_Edit_ButtonEdit = (Button) findViewById(R.id.DoanVien_Edit_ButtonEdit);
        DoanVien_Edit_GenderNam = (RadioButton) findViewById(R.id.DoanVien_Edit_GenderNam);
        Doanvien_Edit_GenderNu = (RadioButton) findViewById(R.id.DoanVien_Edit_GenderNu);
        //Lấy dữ liệu từ Intent về set
        Intent callerIntent = getIntent();
        Bundle packageFromCaller = callerIntent.getBundleExtra("CandidateCurrent");
        int CandidateID = packageFromCaller.getInt("CandidateID");
        candidateCurrent = candidateDAO.getCandidateByID(CandidateID);
        Load_DoanVien();


        DoanVien_Edit_ReloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Load_DoanVien();
            }
        });
        DoanVien_Edit_ButtonCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectImageCamera();
            }
        });
        DoanVien_Edit_ButtonLiblary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectImageFolder();
            }
        });
        DoanVien_Edit_ButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        DoanVien_Edit_CMND.addTextChangedListener(new TextWatcher() {
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
                    DoanVien_Edit_CMNDError.setText(s.length() + "/9 kí tự");
                    if (s.length() == 9 || s.length() == 12) {
                        DoanVien_Edit_CMNDError.setText(s.length() + "/" + s.length() + " kí tự");
                        DoanVien_Edit_CMNDError.setTextColor(Color.BLACK);
                        DoanVien_Edit_CMND.setTextColor(Color.BLACK);
                        if (candidateDAO.CheckCandidateExits(s.toString(),candidateCurrent.getCMND())) {
                            DoanVien_Edit_CMNDError.setText("[ Bị trùng lặp ] " + s.length() + "/" + s.length() + " kí tự");
                            DoanVien_Edit_CMND.setTextColor(Color.RED);
                            DoanVien_Edit_CMNDError.setTextColor(Color.RED);
                        } else {
                            FromErrorCMND = false;
                            if (s.length() == 12) {
                                DoanVien_Edit_SDT.requestFocus();
                            }
                        }
                    } else if (s.length() > 9) {
                        DoanVien_Edit_CMNDError.setText(s.length() + "/12 kí tự");
                        DoanVien_Edit_CMND.setTextColor(Color.RED);
                        DoanVien_Edit_CMNDError.setTextColor(Color.RED);
                    } else {
                        DoanVien_Edit_CMND.setTextColor(Color.RED);
                        DoanVien_Edit_CMNDError.setTextColor(Color.RED);
                    }
                } else {
                    DoanVien_Edit_CMNDError.setText("");
                    DoanVien_Edit_CMND.setTextColor(Color.RED);
                    DoanVien_Edit_CMNDError.setTextColor(Color.RED);
                }
            }
        });
        DoanVien_Edit_SDT.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @SuppressLint("ResourceAsColor")
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                FromErrorSDT = true;
                if (s.length() != 0) {
                    if (App.CheckIsSDT(s.toString())) {
                        DoanVien_Edit_SDTError.setText(s.length() + "/10 kí tự");
                        if (s.length() == 10 || s.length() == 11) {
                            DoanVien_Edit_SDTError.setText(s.length() + "/" + s.length() + " kí tự");
                            DoanVien_Edit_SDTError.setTextColor(Color.BLACK);
                            DoanVien_Edit_SDT.setTextColor(Color.BLACK);
                            if (candidateDAO.CheckSDTExits(s.toString(),candidateCurrent.getSDT())) {
                                DoanVien_Edit_SDTError.setText("[ Đã tồn tại ] " + s.length() + "/" + s.length() + " kí tự");
                                DoanVien_Edit_SDTError.setTextColor(Color.RED);
                                DoanVien_Edit_SDT.setTextColor(Color.RED);
                            } else {
                                FromErrorSDT = false;
                            }
                        } else if (s.length() > 11) {
                            DoanVien_Edit_SDTError.setText(s.length() + "/11 kí tự");
                            DoanVien_Edit_SDTError.setTextColor(Color.RED);
                            DoanVien_Edit_SDT.setTextColor(Color.RED);
                        } else {
                            DoanVien_Edit_SDTError.setTextColor(Color.RED);
                            DoanVien_Edit_SDT.setTextColor(Color.RED);
                        }
                    } else {
                        DoanVien_Edit_SDTError.setText("[Không phải số điện thoại]");
                        DoanVien_Edit_SDTError.setTextColor(Color.RED);
                        DoanVien_Edit_SDT.setTextColor(Color.RED);
                    }
                } else {
                    DoanVien_Edit_SDTError.setText("");
                    DoanVien_Edit_SDTError.setTextColor(Color.BLACK);
                    DoanVien_Edit_SDT.setTextColor(Color.BLACK);
                    FromErrorSDT = false;
                }
            }
        });
        DoanVien_Edit_Name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                //sự kiện rời forcus
                if (!hasFocus)
                    if (DoanVien_Edit_Name.getText().length() > 0)
                        DoanVien_Edit_Name.setText(App.chuannHoaHoTen(String.valueOf(DoanVien_Edit_Name.getText())));
            }
        });
        DoanVien_Edit_ButtonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DoanVien_Edit_Name.getText().toString().replace(" ", "").length() == 0) {
                    DoanVien_Edit_Name.requestFocus();
                } else if (FromErrorCMND == false) {
                    //Đủ điển kiện thêm đoàn viên và không trùng CMND
                    String SDT = "";
                    if (DoanVien_Edit_SDT.getText().toString().replace(" ", "").length() == 10 || DoanVien_Edit_SDT.getText().toString().replace(" ", "").length() == 11) {
                        SDT = String.valueOf(DoanVien_Edit_SDT.getText());
                    } else if (DoanVien_Edit_SDT.getText().toString().replace(" ", "").length() != 0) {
                        DoanVien_Edit_SDT.requestFocus();
                    }
                    if (!FromErrorSDT) {
                        Candidate candidate = new Candidate(
                                candidateCurrent.getId(),
                                DoanVien_Edit_Name.getText().toString(),
                                DoanVien_Edit_CMND.getText().toString(),
                                SDT,
                                DoanVien_Edit_GenderNam.isChecked() ? App.GENDER_NAM : App.GENDER_NU,
                                App.getImageBitmap(DoanVien_Edit_Avatar),
                                App.CheckIsAdministrator() ? App.ACTIVE : candidateCurrent.getIsActive()
                        );
                        candidateDAO.UpdateCandidate(candidate);
                        App.ToastShow(getBaseContext(), "Sửa đoàn viên thành công");
                        CandidateAdapter.Change();
                        finish();
                    }
                } else {
                    DoanVien_Edit_CMND.requestFocus();
                }
            }
        });
    }
    private void Load_DoanVien(){
        FromErrorCMND = false;
        FromErrorSDT = false;
        DoanVien_Edit_CMNDError.setText("");
        DoanVien_Edit_SDTError.setText("");
        DoanVien_Edit_Avatar.setImageBitmap(candidateCurrent.getAvatarToBitMap());
        DoanVien_Edit_ID.setText(String.valueOf(candidateCurrent.getId()));
        DoanVien_Edit_Name.setText(candidateCurrent.getName());
        DoanVien_Edit_CMND.setText(candidateCurrent.getCMND());
        DoanVien_Edit_SDT.setText(candidateCurrent.getSDT());
        if (candidateCurrent.getGender() == App.GENDER_NAM) {
            DoanVien_Edit_GenderNam.setChecked(true);
        } else {
            Doanvien_Edit_GenderNu.setChecked(true);
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == App.REQUEST_CODE_CAMERA && resultCode == RESULT_OK && data != null) {
            try {
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                DoanVien_Edit_Avatar.setBackgroundResource(0);
                DoanVien_Edit_Avatar.setImageBitmap(bitmap);
            } catch (Exception e) {
            }
        } else if (requestCode == App.REQUEST_CODE_FOLDER && resultCode == RESULT_OK && data != null) {
            try {
                Uri uri = data.getData();
                InputStream inputStream = this.getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                DoanVien_Edit_Avatar.setBackgroundResource(0);
                DoanVien_Edit_Avatar.setImageBitmap(bitmap);
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