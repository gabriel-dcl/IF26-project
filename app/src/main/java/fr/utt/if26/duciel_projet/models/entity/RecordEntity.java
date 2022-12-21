package fr.utt.if26.duciel_projet.models.entity;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.TypeConverters;

import java.util.Date;

@TypeConverters(DateConverter.class)
@Entity(primaryKeys = {"taskName","startDate"},tableName = "record_table" )
public class RecordEntity {

    @NonNull
    private String taskName;

    @NonNull
    private Date startDate;
    private Date finalDate;

    public RecordEntity(String taskName, Date startDate, Date finalDate) {
        this.taskName = taskName;
        this.startDate = startDate;
        this.finalDate = finalDate;
    }

    @NonNull
    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(@NonNull String taskName) {
        this.taskName = taskName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(Date finalDate) {
        this.finalDate = finalDate;
    }
}
