package fr.utt.if26.duciel_projet.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import fr.utt.if26.duciel_projet.models.entity.GlobalSettingEntity;
import fr.utt.if26.duciel_projet.models.repository.GlobalSettingRepository;

public class GlobalSettingViewModel extends AndroidViewModel {
    private GlobalSettingRepository globalSettingRepository;

    public GlobalSettingViewModel(@NonNull Application application) {
        super(application);
        this.globalSettingRepository = new GlobalSettingRepository(application);
    }

    public LiveData<GlobalSettingEntity> getFirstUsageSetting(){
         return globalSettingRepository.getFirstUsageSetting();
    }

    public void setFirstUsageSetting(boolean value){
        if(value)
            this.globalSettingRepository.setFirtUsageSetting("true");
        else
            this.globalSettingRepository.setFirtUsageSetting("false");

    }

    public void insert(){
        GlobalSettingEntity firstUsageSetting = new GlobalSettingEntity("firstUsage", "true");
        globalSettingRepository.insert(firstUsageSetting);
    }
}
