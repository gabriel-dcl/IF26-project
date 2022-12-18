package fr.utt.if26.duciel_projet.models.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import fr.utt.if26.duciel_projet.models.DAO.TaskDao;
import fr.utt.if26.duciel_projet.models.ProjectRoomDatabase;
import fr.utt.if26.duciel_projet.models.entity.TaskEntity;

public class TaskRepository {
    private TaskDao taskDao;
    private LiveData<List<TaskEntity>> allModules;

    public TaskRepository(Application application) {
        ProjectRoomDatabase db = ProjectRoomDatabase.getDatabase(application);
        taskDao = db.taskDao();
        allModules = taskDao.getAllTasks();
    }

    public LiveData<List<TaskEntity>> getAllModules() {
        return allModules;
    }

    public void insert(TaskEntity module) {
        InsertAsyncTask task = new InsertAsyncTask(taskDao);
        task.execute(module);
    }

    public void deleteBySigle(String sigle) {
        DeleteAsyncTask task = new DeleteAsyncTask(taskDao);
        task.execute(sigle);
    }

    private static class InsertAsyncTask extends AsyncTask<TaskEntity, Void, Void> {
        private TaskDao moduleDAO;

        InsertAsyncTask(TaskDao moduleDAO){
            this.moduleDAO = moduleDAO;
        }

        @Override
        protected Void doInBackground(TaskEntity... moduleEntities) {
            moduleDAO.insert(moduleEntities[0]);
            return null;
        }
    }

    private static class DeleteAsyncTask extends AsyncTask<String, Void, Void> {
        private TaskDao moduleDAO;

        DeleteAsyncTask(TaskDao moduleDAO){
            this.moduleDAO = moduleDAO;
        }

        @Override
        protected Void doInBackground(String... strings) {
            // moduleDAO.deleteBySigle(strings[0]);
            return null;
        }
    }

}
