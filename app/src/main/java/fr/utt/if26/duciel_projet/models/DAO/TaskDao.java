package fr.utt.if26.duciel_projet.models.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import fr.utt.if26.duciel_projet.models.entity.TaskEntity;

public interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(TaskEntity task);

    @Update
    void update(TaskEntity task);

    @Delete
    void delete(TaskEntity task);

    @Query("SELECT * FROM task_table WHERE task_table.name == :name")
    LiveData<TaskEntity> getOne(String name);

    @Query("DELETE FROM task_table")
    void deleteAll();

    @Query("SELECT * FROM task_table ORDER BY name ASC")
    LiveData<List<TaskEntity>> getAllTasks();
}
