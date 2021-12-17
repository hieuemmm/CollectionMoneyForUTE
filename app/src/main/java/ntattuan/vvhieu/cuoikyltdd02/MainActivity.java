package ntattuan.vvhieu.cuoikyltdd02;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

import java.util.List;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

import ntattuan.vvhieu.cuoikyltdd02.Fragment.DoanVienFragment;
import ntattuan.vvhieu.cuoikyltdd02.Fragment.DoanPhiFragment;
import ntattuan.vvhieu.cuoikyltdd02.Fragment.HoiPhiFragment;
import ntattuan.vvhieu.cuoikyltdd02.Fragment.TaoDotFragment;
import ntattuan.vvhieu.cuoikyltdd02.Fragment.CaNhanFragment;
import ntattuan.vvhieu.cuoikyltdd02.Model.Candidate;
import ntattuan.vvhieu.cuoikyltdd02.Data.CadidateDAO;

public class MainActivity extends AppCompatActivity {
    private Bundle savedInstanceState;
    private MeowBottomNavigation bnv_Main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bnv_Main = findViewById(R.id.bnv_Main);
        bnv_Main.add(new MeowBottomNavigation.Model(1, R.drawable.doan_vien));
        bnv_Main.add(new MeowBottomNavigation.Model(2, R.drawable.doan_phi));
        bnv_Main.add(new MeowBottomNavigation.Model(3, R.drawable.hoi_phi));
        if(LoginActivity.UserLogined.getRole() == App.ROLE_ADMIN)
            bnv_Main.add(new MeowBottomNavigation.Model(4, R.drawable.tao_dot));
        bnv_Main.add(new MeowBottomNavigation.Model(5, R.drawable.ca_nhan));

        bnv_Main.show(1, true);
        replace(new DoanVienFragment());
        bnv_Main.setOnClickMenuListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                switch (model.getId()) {
                    case 1:
                        replace(new DoanVienFragment());
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
