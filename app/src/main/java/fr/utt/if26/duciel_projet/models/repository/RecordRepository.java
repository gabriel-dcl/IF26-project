package fr.utt.if26.duciel_projet.models.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.time.LocalDateTime;
import java.util.List;

import fr.utt.if26.duciel_projet.models.DAO.RecordDao;
import fr.utt.if26.duciel_projet.models.DAO.TaskDao;
import fr.utt.if26.duciel_projet.models.ProjectRoomDatabase;
import fr.utt.if26.duciel_projet.models.entity.RecordEntity;
import fr.utt.if26.duciel_projet.models.entity.TaskEntity;

public class RecordRepository {
    private RecordDao recordDao;
    private LiveData<List<RecordEntity>> allRecords;

    public RecordRepository(Application application) {
        ProjectRoomDatabase db = ProjectRoomDatabase.getDatabase(application);
        recordDao = db.recordDao();
        allRecords = recordDao.getAllRecords();
    }

    public void updateCurrentlyRecordingRecord(String taskName, LocalDateTime startDate, LocalDateTime stopDate){
        recordDao.updateCurrentlyRecordingRecord(taskName, startDate.toString(), stopDate.toString());
    }

    public RecordEntity getCurrentlyRecordingRecord() { return recordDao.getCurrentlyRecordingRecord();}

    public LiveData<List<RecordEntity>> getAllRecords() {
        return allRecords;
    }


    public LiveData<List<RecordEntity>> getRecordsByTaskName(String taskName) {
        return recordDao.getRecordsByTaskName(taskName);
    }

    public void insert(RecordEntity module) {
        InsertAsyncTask task = new InsertAsyncTask(recordDao);
        task.execute(module);
    }

    private static class InsertAsyncTask extends AsyncTask<RecordEntity, Void, Void> {
        private RecordDao recordDao;

        InsertAsyncTask(RecordDao moduleDAO){
            this.recordDao = moduleDAO;
        }

        @Override
        protected Void doInBackground(RecordEntity... moduleEntities) {
            recordDao.insert(moduleEntities[0]);
            return null;
        }
    }
}
