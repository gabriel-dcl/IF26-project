package fr.utt.if26.duciel_projet.models.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "global_setting_table")
public class GlobalSettingEntity {
    @PrimaryKey
    @NonNull
    private String name;
    private String value;

    public GlobalSettingEntity(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
