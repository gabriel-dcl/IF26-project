package fr.utt.if26.duciel_projet.controller;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Window;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.maltaisn.icondialog.pack.IconPack;
import com.maltaisn.icondialog.pack.IconPackLoader;
import com.maltaisn.iconpack.defaultpack.IconPackDefault;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import fr.utt.if26.duciel_projet.R;
import fr.utt.if26.duciel_projet.databinding.ActivityMainBinding;
import fr.utt.if26.duciel_projet.models.DAO.GlobalSettingDao;
import fr.utt.if26.duciel_projet.models.entity.GlobalSettingEntity;
import fr.utt.if26.duciel_projet.viewModel.GlobalSettingViewModel;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private GlobalSettingViewModel globalSettingViewModel;
    private LiveData<GlobalSettingEntity> firstUsage;

    @Nullable
    private IconPack iconPack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        getSupportActionBar().hide(); //hide the title bar

        this.globalSettingViewModel = new GlobalSettingViewModel(this.getApplication());

        firstUsage = this.globalSettingViewModel.getFirstUsageSetting();

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);


        firstUsage.observe(this, (Observer) o -> {
            if(o instanceof GlobalSettingEntity && Boolean.parseBoolean(((GlobalSettingEntity) o).getValue()))
                    startDataConsentActivity();

        });

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications, R.id.settings)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main2);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }

    public void startDataConsentActivity(){
        Intent intent = new Intent(this, DataConsentActivity.class);
        startActivity(intent);
    }

    @Nullable
    public IconPack getIconPack() {
        return iconPack != null ? iconPack : loadIconPack();
    }

    private IconPack loadIconPack() {
        // Create an icon pack loader with application context.
        IconPackLoader loader = new IconPackLoader(this.getApplication().getApplicationContext());

        // Create an icon pack and load all drawables.
        iconPack = IconPackDefault.createDefaultIconPack(loader);
        iconPack.loadDrawables(loader.getDrawableLoader());

        return iconPack;
    }
}