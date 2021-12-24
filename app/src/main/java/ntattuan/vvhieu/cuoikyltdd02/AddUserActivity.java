package ntattuan.vvhieu.cuoikyltdd02;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.Locale;

import ntattuan.vvhieu.cuoikyltdd02.Adapter.UserAdapter;
import ntattuan.vvhieu.cuoikyltdd02.Data.UserDAO;
import ntattuan.vvhieu.cuoikyltdd02.Model.User;

public class AddUserActivity extends AppCompatActivity {
    private ImageView User_Add_ButtonBack;
    private Button User_Add_ButtonAdd;
    private TextView User_Add_NameUser, User_Add_SDT, User_Add_PassWord, User_Add_RePassWord;
    private TextView User_Add_SDTError, User_Add_RePassWordError,User_Add_UserNameError;
    private RadioButton User_Add_RoleAdmin, User_Add_RoleBithu;
    private UserDAO userDAO;
    private boolean FromErrorSDT = false;
    private boolean FromErrorPass = true;
    private boolean FromErrorUserName = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        userDAO = new UserDAO(this);

        User_Add_ButtonBack = (ImageView) findViewById(R.id.User_Add_ButtonBack);
        User_Add_ButtonAdd = (Button) findViewById(R.id.User_Add_ButtonAdd);
        User_Add_NameUser = (TextView) findViewById(R.id.User_Add_NameUser);
        User_Add_UserNameError = (TextView) findViewById(R.id.User_Add_UserNameError);
        User_Add_SDT = (TextView) findViewById(R.id.User_Add_SDT);
        User_Add_SDTError = (TextView) findViewById(R.id.User_Add_SDTError);
        User_Add_PassWord = (TextView) findViewById(R.id.User_Add_PassWord);
        User_Add_RePassWord = (TextView) findViewById(R.id.User_Add_RePassWord);
        User_Add_RePassWordError = (TextView) findViewById(R.id.User_Add_RePassWordError);
        User_Add_RoleAdmin = (RadioButton) findViewById(R.id.User_Add_RoleAdmin);
        User_Add_RoleBithu = (RadioButton) findViewById(R.id.User_Add_RoleBithu);

        if (!App.CheckIsadmin()) {
            User_Add_RoleAdmin.setVisibility(View.GONE);
            User_Add_RoleBithu.setChecked(true);
        }
        User_Add_ButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        User_Add_NameUser.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                //sự kiện rời forcus
                if (!hasFocus)
                    if (User_Add_NameUser.getText().length() > 0)
                        User_Add_NameUser.setText(String.valueOf(User_Add_NameUser.getText()).replace(" ", "").toLowerCase(Locale.ROOT));
            }
        });
        User_Add_NameUser.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!userDAO.CheckUserExits(String.valueOf(User_Add_NameUser.getText()).replace(" ", "").toLowerCase(Locale.ROOT))) {
                    FromErrorUserName = false;
                    User_Add_UserNameError.setText("");
                    User_Add_UserNameError.setTextColor(Color.BLACK);
                    User_Add_NameUser.setTextColor(Color.BLACK);
                }else{
                    FromErrorUserName = true;
                    User_Add_UserNameError.setText("Đã tồn tại");
                    User_Add_UserNameError.setTextColor(Color.RED);
                    User_Add_NameUser.setTextColor(Color.RED);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        User_Add_SDT.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @SuppressLint("ResourceAsColor")
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                FromErrorSDT = false;
                if (s.length() != 0) {
                    FromErrorSDT = true;
                    if (App.CheckIsSDT(s.toString())) {
                        User_Add_SDTError.setText(s.length() + "/10 kí tự");
                        if (s.length() == 10 || s.length() == 11) {
                            User_Add_SDTError.setText(s.length() + "/" + s.length() + " kí tự");
                            User_Add_SDTError.setTextColor(Color.BLACK);
                            User_Add_SDT.setTextColor(Color.BLACK);
                            if (userDAO.CheckSDTExits(s.toString())) {
                                User_Add_SDTError.setText("[ Đã tồn tại ] " + s.length() + "/" + s.length() + " kí tự");
                                User_Add_SDTError.setTextColor(Color.RED);
                                User_Add_SDT.setTextColor(Color.RED);
                            } else {
                                FromErrorSDT = false;
                            }
                        } else if (s.length() > 11) {
                            User_Add_SDTError.setText(s.length() + "/11 kí tự");
                            User_Add_SDTError.setTextColor(Color.RED);
                            User_Add_SDT.setTextColor(Color.RED);
                        } else {
                            User_Add_SDTError.setTextColor(Color.RED);
                            User_Add_SDT.setTextColor(Color.RED);
                        }
                    } else {
                        User_Add_SDTError.setText("Không phải số điện thoại");
                        User_Add_SDTError.setTextColor(Color.RED);
                        User_Add_SDT.setTextColor(Color.RED);
                    }
                } else {
                    User_Add_SDTError.setText("");
                    User_Add_SDTError.setTextColor(Color.BLACK);
                    User_Add_SDT.setTextColor(Color.BLACK);
                }
            }
        });
        User_Add_RePassWord.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                FromErrorPass = true;
                if (count > 0) {
                    if (!s.toString().equals(String.valueOf(User_Add_PassWord.getText()))) {
                        User_Add_RePassWordError.setText("Mật khẩu không trùng khớp");
                    } else {
                        User_Add_RePassWordError.setText("");
                        FromErrorPass = false;
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        User_Add_ButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (FromErrorUserName) {
                    User_Add_NameUser.requestFocus();
                } else if (FromErrorSDT) {
                    User_Add_SDT.requestFocus();
                } else if (FromErrorPass) {
                    User_Add_RePassWord.requestFocus();
                } else {
                    User user = new User(
                            String.valueOf(User_Add_NameUser.getText()),
                            String.valueOf(User_Add_PassWord.getText()),
                            String.valueOf(User_Add_SDT.getText()),
                            App.ACTIVE,
                            User_Add_RoleAdmin.isChecked() ? App.ROLE_ADMIN : App.ROLE_BITHU);
                    userDAO.addUser(user);
                    App.ToastShow(getBaseContext(),"Thêm tài khoản thành công");
                    UserAdapter.Change();
                    finish();
                }
            }
        });
    }
}