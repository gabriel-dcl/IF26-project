package fr.utt.if26.duciel_projet.models.entity;

import android.graphics.drawable.Icon;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "task_table")
public class TaskEntity {

    @PrimaryKey
    @NonNull
    private String name;

    @Nullable
    private int iconId;

    public TaskEntity(@NonNull String name, int iconId) {
        this.name = name;
        this.iconId = iconId;
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

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

}
