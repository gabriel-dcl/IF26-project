package fr.utt.if26.duciel_projet.models.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import fr.utt.if26.duciel_projet.models.entity.GlobalSettingEntity;

@Dao
public interface GlobalSettingDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(GlobalSettingEntity globalSettingEntity);

    @Update
    void update(GlobalSettingEntity globalSettingEntity);

    @Delete
    void delete(GlobalSettingEntity globalSettingEntity);

    @Query("DELETE FROM global_setting_table")
    void deleteAll();

    @Query("SELECT * FROM global_setting_table")
    List<GlobalSettingEntity> getAllEntities();

    @Query("SELECT * FROM global_setting_table WHERE name LIKE 'firstUsage'")
    LiveData<GlobalSettingEntity> getFirstUsageSetting();
}
