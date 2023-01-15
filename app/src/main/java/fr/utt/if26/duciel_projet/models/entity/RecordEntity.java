package fr.utt.if26.duciel_projet.models.entity;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.TypeConverters;

import java.time.LocalDateTime;

@TypeConverters(DateConverter.class)
@Entity(primaryKeys = {"taskName", "startDate"}, tableName = "record_table")
public class RecordEntity {

    @NonNull
    private String taskName;

    @NonNull
    private LocalDateTime startDate;

    @Nullable
    private LocalDateTime finalDate;
    private boolean currentlyRecording;

    public boolean isCurrentlyRecording() {
        return currentlyRecording;
    }

    public void setCurrentlyRecording(boolean currentlyRecording) {
        this.currentlyRecording = currentlyRecording;
    }

    public RecordEntity(String taskName, LocalDateTime startDate, LocalDateTime finalDate, boolean currentlyRecording) {
        this.taskName = taskName;
        this.startDate = startDate;
        this.finalDate = finalDate;
        this.currentlyRecording = currentlyRecording;
    }

    @NonNull
    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(@NonNull String taskName) {
        this.taskName = taskName;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(LocalDateTime finalDate) {
        this.finalDate = finalDate;
    }
}
