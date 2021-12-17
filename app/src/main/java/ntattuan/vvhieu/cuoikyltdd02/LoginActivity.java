package ntattuan.vvhieu.cuoikyltdd02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void PressLogin(View view) {
        Intent listItem = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(listItem);
    }
}