package fr.utt.if26.duciel_projet.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import fr.utt.if26.duciel_projet.models.entity.TaskEntity;
import fr.utt.if26.duciel_projet.models.repository.TaskRepository;

public class TasksViewModel extends AndroidViewModel {
    private TaskRepository taskRepository;

    public TasksViewModel(@NonNull Application application) {
        super(application);
        this.taskRepository = new TaskRepository(application);
    }

    public LiveData<List<TaskEntity>> getAllTasks() {
        return this.taskRepository.getAllTasks();
    }

    public void insertTask(TaskEntity taskEntity) {
        taskRepository.insert(taskEntity);
    }

    public void deleteAll() {
        taskRepository.deleteAll();
    }

}
