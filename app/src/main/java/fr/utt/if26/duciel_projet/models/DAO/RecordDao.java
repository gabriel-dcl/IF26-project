package fr.utt.if26.duciel_projet.models.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import fr.utt.if26.duciel_projet.models.entity.RecordEntity;
import fr.utt.if26.duciel_projet.models.entity.TaskEntity;

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
}
