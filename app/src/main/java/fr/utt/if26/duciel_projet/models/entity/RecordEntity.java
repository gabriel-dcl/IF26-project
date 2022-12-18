package fr.utt.if26.duciel_projet.models.entity;


import androidx.room.Entity;

import java.util.Date;

@Entity(primaryKeys = {"task","startDate"},tableName = "record_table" )
public class RecordEntity {

    private TaskEntity task;
    private Date startDate;
    private Date finalDate;

    public RecordEntity(TaskEntity task, Date startDate, Date finalDate) {
        this.task = task;
        this.startDate = startDate;
        this.finalDate = finalDate;
    }

    public TaskEntity getTask() {
        return task;
    }

    public void setTask(TaskEntity task) {
        this.task = task;
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
