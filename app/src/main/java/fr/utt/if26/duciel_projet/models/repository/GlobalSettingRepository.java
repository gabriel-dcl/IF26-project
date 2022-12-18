package fr.utt.if26.duciel_projet.models.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import fr.utt.if26.duciel_projet.models.DAO.GlobalSettingDao;
import fr.utt.if26.duciel_projet.models.DAO.TaskDao;
import fr.utt.if26.duciel_projet.models.ProjectRoomDatabase;
import fr.utt.if26.duciel_projet.models.entity.GlobalSettingEntity;
import fr.utt.if26.duciel_projet.models.entity.TaskEntity;

public class GlobalSettingRepository {
    private GlobalSettingDao globalSettingDao;
    private LiveData<List<GlobalSettingEntity>> allGlobalSettings;

    public GlobalSettingRepository(Application application) {
        ProjectRoomDatabase db = ProjectRoomDatabase.getDatabase(application);
        globalSettingDao = db.globalSettingDao();
        allGlobalSettings = globalSettingDao.getAllEntities();
    }

    public LiveData<List<GlobalSettingEntity>> getAllGlobalSettings() {
        return allGlobalSettings;
    }

    public void insert(GlobalSettingEntity globalSetting) {
        InsertAsyncTask task = new InsertAsyncTask(globalSettingDao);
        task.execute(globalSetting);
    }

    public LiveData<GlobalSettingEntity> getFirstUsageSetting() {
        return globalSettingDao.getFirstUsageSetting();
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
