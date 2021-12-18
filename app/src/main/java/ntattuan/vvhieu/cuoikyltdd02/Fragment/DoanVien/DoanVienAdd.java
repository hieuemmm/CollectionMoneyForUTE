package ntattuan.vvhieu.cuoikyltdd02.Fragment.DoanVien;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.InputStream;

import ntattuan.vvhieu.cuoikyltdd02.App;
import ntattuan.vvhieu.cuoikyltdd02.R;

public class DoanVienAdd extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private ImageView DoanVien_Add_Avatar, DoanVien_Add_ButtonCamera, DoanVien_Add_ButtonLiblary;

    public DoanVienAdd() {
    }

    public static DoanVienAdd newInstance(String param1, String param2) {
        DoanVienAdd fragment = new DoanVienAdd();
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
        View view = inflater.inflate(R.layout.fragment_doan_vien_add, container, false);
        DoanVien_Add_Avatar = (ImageView) view.findViewById(R.id.DoanVien_Add_Avatar);
        DoanVien_Add_ButtonCamera = (ImageView) view.findViewById(R.id.DoanVien_Add_ButtonCamera);
        DoanVien_Add_ButtonLiblary = (ImageView) view.findViewById(R.id.DoanVien_Add_ButtonLiblary);

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
        return view;
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
                InputStream inputStream = this.getActivity().getContentResolver().openInputStream(uri);
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