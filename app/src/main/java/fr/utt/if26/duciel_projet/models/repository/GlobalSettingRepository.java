package fr.utt.if26.duciel_projet.models.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import fr.utt.if26.duciel_projet.models.DAO.GlobalSettingDao;
import fr.utt.if26.duciel_projet.models.ProjectRoomDatabase;
import fr.utt.if26.duciel_projet.models.entity.GlobalSettingEntity;

public class GlobalSettingRepository {
    private GlobalSettingDao globalSettingDao;

    public GlobalSettingRepository(Application application) {
        ProjectRoomDatabase db = ProjectRoomDatabase.getDatabase(application);
        globalSettingDao = db.globalSettingDao();
    }

    public List<GlobalSettingEntity> getAllGlobalSettings() {
        return globalSettingDao.getAllEntities();
    }

    public void insert(GlobalSettingEntity globalSetting) {
        InsertAsyncTask task = new InsertAsyncTask(globalSettingDao);
        task.execute(globalSetting);
    }

    public GlobalSettingEntity getFirstUsageSetting() {
        return globalSettingDao.getFirstUsageSetting();
    }

    public void setFirtUsageSetting(String newValue) {
        globalSettingDao.updateFirstUsageSetting(newValue);
    }


    public void update(GlobalSettingEntity updatedEntity){
        this.globalSettingDao.update(updatedEntity);
    }

    private static class InsertAsyncTask extends AsyncTask<GlobalSettingEntity, Void, Void> {
        private GlobalSettingDao globalSettingDao;

        InsertAsyncTask(GlobalSettingDao globalSettingDao){
            this.globalSettingDao = globalSettingDao;
        }

        @Override
        protected Void doInBackground(GlobalSettingEntity... globalSettingEntities) {
            globalSettingDao.insert(globalSettingEntities[0]);
            return null;
        }
    }

}
