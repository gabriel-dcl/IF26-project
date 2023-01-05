package fr.utt.if26.duciel_projet.viewModel;

import android.app.Application;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompatSideChannelService;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import fr.utt.if26.duciel_projet.models.entity.GlobalSettingEntity;
import fr.utt.if26.duciel_projet.models.repository.GlobalSettingRepository;

public class GlobalSettingViewModel extends AndroidViewModel {
    private GlobalSettingRepository globalSettingRepository;

    public GlobalSettingViewModel(@NonNull Application application) {
        super(application);
        this.globalSettingRepository = new GlobalSettingRepository(application);
    }

    public boolean getFirstUsageSetting(){
         return Boolean.parseBoolean(globalSettingRepository.getFirstUsageSetting().getValue());
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
