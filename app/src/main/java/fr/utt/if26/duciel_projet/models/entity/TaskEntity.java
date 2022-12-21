package fr.utt.if26.duciel_projet.models.entity;

import android.graphics.drawable.Icon;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "task_table")
public class TaskEntity {

    @PrimaryKey
    @NonNull
    private String name;


    public TaskEntity(@NonNull String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TaskEntity{" +
                ", name='" + name + '\'' +
                '}';
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }
}
