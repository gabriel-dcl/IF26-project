package fr.utt.if26.duciel_projet.viewModel;

import android.app.Application;
import android.os.Build;

import androidx.annotation.NonNull;
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
        List<GlobalSettingEntity> firstUsageValue = globalSettingRepository.getAllGlobalSettings();
        if(firstUsageValue == null){
            System.out.println("===== NULL");

        } else{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                firstUsageValue.forEach(item -> System.out.println(item.getName()));
            }
        }
        // return Boolean.parseBoolean(firstUsageValue);
    return true;
    }

    public void insert(){

        GlobalSettingEntity firstUsageSetting = new GlobalSettingEntity("firstUsage", "true");
        globalSettingRepository.insert(firstUsageSetting);
    }
}
