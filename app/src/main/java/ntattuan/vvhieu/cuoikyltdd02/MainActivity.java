package ntattuan.vvhieu.cuoikyltdd02;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

import ntattuan.vvhieu.cuoikyltdd02.MainFragment.CandidateFragment;
import ntattuan.vvhieu.cuoikyltdd02.MainFragment.RoundFragment;
import ntattuan.vvhieu.cuoikyltdd02.MainFragment.ProfileFragment;
import ntattuan.vvhieu.cuoikyltdd02.MainFragment.StatisticsFragment;
import ntattuan.vvhieu.cuoikyltdd02.MainFragment.UserFragment;

public class MainActivity extends AppCompatActivity {
    private Bundle savedInstanceState;
    private MeowBottomNavigation bnv_Main;
    public static Fragment fragmentDoanVien = new CandidateFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bnv_Main = findViewById(R.id.bnv_Main);
        bnv_Main.add(new MeowBottomNavigation.Model(1, R.drawable.doan_vien));
        if(App.UserLogined.getRole() == App.ROLE_ADMIN) {
            bnv_Main.add(new MeowBottomNavigation.Model(2, R.drawable.tao_dot));
            bnv_Main.add(new MeowBottomNavigation.Model(3, R.drawable.tao_user));
        }
        bnv_Main.add(new MeowBottomNavigation.Model(4, R.drawable.thongke));
        bnv_Main.add(new MeowBottomNavigation.Model(5, R.drawable.ca_nhan));
        bnv_Main.show(1, true);
        replace(new CandidateFragment());
        bnv_Main.setOnClickMenuListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                switch (model.getId()) {
                    case 1:
                        replace(fragmentDoanVien);
                        bnv_Main.show(1, true);
                        break;
                    case 2:
                        replace(new RoundFragment());
                        bnv_Main.show(2, true);
                        break;
                    case 3:
                        replace(new UserFragment());
                        bnv_Main.show(3, true);
                        break;
                    case 4:
                        replace(new StatisticsFragment());
                        bnv_Main.show(4, true);
                        break;
                    case 5:
                        replace(new ProfileFragment());
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
