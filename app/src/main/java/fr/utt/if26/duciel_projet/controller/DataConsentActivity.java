package fr.utt.if26.duciel_projet.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import fr.utt.if26.duciel_projet.R;
import fr.utt.if26.duciel_projet.viewModel.GlobalSettingViewModel;

public class DataConsentActivity extends AppCompatActivity {
    Button consentButton;
    GlobalSettingViewModel globalSettingViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_consent);
        consentButton = findViewById( R.id.dataConsent );
        globalSettingViewModel = new GlobalSettingViewModel(this.getApplication());

        consentButton.setOnClickListener(v -> updateFirstUsageValue());
    }

    public void updateFirstUsageValue(){
        this.globalSettingViewModel.setFirstUsageSetting(false);
        finish();
    }
}