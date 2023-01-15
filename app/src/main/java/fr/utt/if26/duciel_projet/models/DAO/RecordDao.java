package fr.utt.if26.duciel_projet.models.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import fr.utt.if26.duciel_projet.models.entity.RecordEntity;

@Dao
public interface RecordDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(RecordEntity recordEntity);

    @Update
    void update(RecordEntity recordEntity);

    @Delete
    void delete(RecordEntity recordEntity);

    @Query("DELETE FROM record_table")
    void deleteAll();

    @Query("SELECT * FROM record_table")
    LiveData<List<RecordEntity>> getAllRecords();

    @Query("SELECT * FROM record_table WHERE taskName=:taskName")
    LiveData<List<RecordEntity>> getRecordsByTaskName(String taskName);

    @Query("SELECT * FROM record_table where currentlyRecording = 1")
    RecordEntity getCurrentlyRecordingRecord();


    @Query("UPDATE record_table SET finalDate=:stopDate, currentlyRecording=0 WHERE taskName=:taskName AND startDate=:startDate ")
    void updateCurrentlyRecordingRecord(String taskName, String startDate, String stopDate);
}
