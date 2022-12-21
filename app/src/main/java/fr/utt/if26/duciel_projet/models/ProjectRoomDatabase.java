package fr.utt.if26.duciel_projet.models;

import android.content.Context;
import android.icu.text.AlphabeticIndex;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import fr.utt.if26.duciel_projet.models.DAO.GlobalSettingDao;
import fr.utt.if26.duciel_projet.models.DAO.RecordDao;
import fr.utt.if26.duciel_projet.models.DAO.TaskDao;
import fr.utt.if26.duciel_projet.models.entity.GlobalSettingEntity;
import fr.utt.if26.duciel_projet.models.entity.RecordEntity;
import fr.utt.if26.duciel_projet.models.entity.TaskEntity;
import fr.utt.if26.duciel_projet.models.repository.GlobalSettingRepository;


@Database(entities = {GlobalSettingEntity.class, RecordEntity.class, TaskEntity.class}, version = 1)
public abstract class ProjectRoomDatabase extends RoomDatabase {
    public abstract TaskDao taskDao();
    public abstract RecordDao recordDao();
    public abstract GlobalSettingDao globalSettingDao();

    private static volatile ProjectRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static ProjectRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ProjectRoomDatabase.class) {
                if (INSTANCE == null) {

                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    ProjectRoomDatabase.class, "word_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static ProjectRoomDatabase.Callback sRoomDatabaseCallback = new ProjectRoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            databaseWriteExecutor.execute(() -> {
                GlobalSettingDao globalSettingDao = INSTANCE.globalSettingDao();
                globalSettingDao.deleteAll();

                GlobalSettingEntity firstUsageSetting = new GlobalSettingEntity("firstUsage", "true");
                globalSettingDao.insert(firstUsageSetting);
            });
        }
    };
}

