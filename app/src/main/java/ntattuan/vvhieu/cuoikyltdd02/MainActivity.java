package ntattuan.vvhieu.cuoikyltdd02;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

import ntattuan.vvhieu.cuoikyltdd02.MainFragment.DoanVienFragment;
import ntattuan.vvhieu.cuoikyltdd02.MainFragment.DoanPhiFragment;
import ntattuan.vvhieu.cuoikyltdd02.MainFragment.HoiPhiFragment;
import ntattuan.vvhieu.cuoikyltdd02.MainFragment.TaoDotFragment;
import ntattuan.vvhieu.cuoikyltdd02.MainFragment.CaNhanFragment;

public class MainActivity extends AppCompatActivity {
    private Bundle savedInstanceState;
    private MeowBottomNavigation bnv_Main;
    public static Fragment fragmentDoanVien = new DoanVienFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bnv_Main = findViewById(R.id.bnv_Main);
        bnv_Main.add(new MeowBottomNavigation.Model(1, R.drawable.doan_vien));
        bnv_Main.add(new MeowBottomNavigation.Model(2, R.drawable.doan_phi));
        bnv_Main.add(new MeowBottomNavigation.Model(3, R.drawable.hoi_phi));
        if(App.UserLogined.getRole() == App.ROLE_ADMIN)
            bnv_Main.add(new MeowBottomNavigation.Model(4, R.drawable.tao_dot));
        bnv_Main.add(new MeowBottomNavigation.Model(5, R.drawable.ca_nhan));
        bnv_Main.show(1, true);
        replace(new DoanVienFragment());
        bnv_Main.setOnClickMenuListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                switch (model.getId()) {
                    case 1:
                        replace(fragmentDoanVien);
                        bnv_Main.show(1, true);
                        break;
                    case 2:
                        replace(new DoanPhiFragment());
                        bnv_Main.show(2, true);
                        break;
                    case 3:
                        replace(new HoiPhiFragment());
                        bnv_Main.show(3, true);
                        break;
                    case 4:
                        replace(new TaoDotFragment());
                        bnv_Main.show(4, true);
                        break;
                    case 5:
                        replace(new CaNhanFragment());
                        bnv_Main.show(5, true);
                        break;
                }
                return null;
            }
        });
    }
    private void replace(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame, fragment);
        transaction.commit();
    }
}
