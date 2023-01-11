package fr.utt.if26.duciel_projet.viewModel;

import android.app.Application;
import android.icu.text.AlphabeticIndex;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.time.LocalDateTime;

import fr.utt.if26.duciel_projet.models.entity.RecordEntity;
import fr.utt.if26.duciel_projet.models.repository.RecordRepository;

public class RecordViewModel extends ViewModel {
    private RecordRepository recordRepository;

    public RecordViewModel(@NonNull Application application){
        this.recordRepository =  new RecordRepository(application);
    }

    public void insertRecord(RecordEntity recordEntity){
        this.recordRepository.insert(recordEntity);
    }

    public RecordEntity getCurrentlyRecordingRecord(){
        return this.recordRepository.getCurrentlyRecordingRecord();
    }

    public void updateCurrentlyRecordingRecord(String taskName, LocalDateTime startDate, LocalDateTime stopDate){
        recordRepository.updateCurrentlyRecordingRecord(taskName, startDate, stopDate);
    }

}
