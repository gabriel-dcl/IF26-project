package fr.utt.if26.duciel_projet.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import fr.utt.if26.duciel_projet.models.entity.GlobalSettingEntity;
import fr.utt.if26.duciel_projet.models.repository.GlobalSettingRepository;

public class GlobalSettingViewModel extends AndroidViewModel {
    private GlobalSettingRepository globalSettingRepository;

    public GlobalSettingViewModel(@NonNull Application application) {
        super(application);
        this.globalSettingRepository = new GlobalSettingRepository(application);
    }

    public boolean getFirstUsageSetting(){
        String firstUsageValue = globalSettingRepository.getFirstUsageSetting().getValue().getValue();
        return Boolean.parseBoolean(firstUsageValue);
    }
}
