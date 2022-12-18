package fr.utt.if26.duciel_projet.models.entity;

import android.graphics.drawable.Icon;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "task_table")
public class TaskEntity {

    @PrimaryKey
    private String name;
    @NonNull
    private Icon icon;

    public TaskEntity(int id, @NonNull String name, @NonNull Icon icon) {
        this.name = name;
        this.icon = icon;
    }

    @Override
    public String toString() {
        return "TaskEntity{" +
                ", name='" + name + '\'' +
                ", icon=" + icon +
                '}';
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    public Icon getIcon() {
        return icon;
    }

    public void setIcon(@NonNull Icon icon) {
        this.icon = icon;
    }
}
