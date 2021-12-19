package ntattuan.vvhieu.cuoikyltdd02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

import java.io.InputStream;

public class AddDoanVienActivity extends AppCompatActivity {
    private ImageView DoanVien_Add_Avatar, DoanVien_Add_ButtonCamera, DoanVien_Add_ButtonLiblary,doanvien_ButtonBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_doan_vien);
        DoanVien_Add_Avatar = (ImageView) findViewById(R.id.DoanVien_Add_Avatar);
        DoanVien_Add_ButtonCamera = (ImageView) findViewById(R.id.DoanVien_Add_ButtonCamera);
        DoanVien_Add_ButtonLiblary = (ImageView) findViewById(R.id.DoanVien_Add_ButtonLiblary);
        doanvien_ButtonBack = (ImageView) findViewById(R.id.doanvien_ButtonBack);

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
        doanvien_ButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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