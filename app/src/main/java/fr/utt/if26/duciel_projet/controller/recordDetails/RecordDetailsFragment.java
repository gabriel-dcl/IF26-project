package fr.utt.if26.duciel_projet.controller.recordDetails;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fr.utt.if26.duciel_projet.R;
import fr.utt.if26.duciel_projet.databinding.FragmentRecordDetailsBinding;
import fr.utt.if26.duciel_projet.models.entity.RecordEntity;
import fr.utt.if26.duciel_projet.models.entity.TaskEntity;
import fr.utt.if26.duciel_projet.viewModel.RecordViewModel;
import fr.utt.if26.duciel_projet.viewModel.TasksViewModel;

public class RecordDetailsFragment extends Fragment {

    private FragmentRecordDetailsBinding binding;
    private RecordViewModel recordViewModel;
    private TasksViewModel tasksViewModel;
    private Spinner dropdown;
    private String chosenTaskName = "";
    LiveData<List<RecordEntity>> records;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentRecordDetailsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        this.recordViewModel = new RecordViewModel(this.getActivity().getApplication());
        this.dropdown = root.findViewById(R.id.taskSpinner);
        tasksViewModel = new TasksViewModel(this.getActivity().getApplication());
        ArrayAdapter adapter = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_spinner_dropdown_item);


        dropdown.setAdapter(adapter);
        adapter.add(String.valueOf(getContext().getResources().getString(R.string.no_selection_dropdown)));
        dropdown.setSelection(0);

        tasksViewModel.getAllTasks().observe(getViewLifecycleOwner(), (Observer<? super List<TaskEntity>>) o -> {
            adapter.clear();

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                adapter.add(String.valueOf(getContext().getResources().getString(R.string.no_selection_dropdown)));

                o.forEach(item -> {
                    adapter.add(item.getName());
                });

            }
        });


        RecordRecyclerAdapter recordRecyclerAdapter = new RecordRecyclerAdapter();

        RecyclerView recyclerView = root.findViewById(R.id.recordRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager( this.getActivity().getApplicationContext() ));
        recyclerView.setAdapter(recordRecyclerAdapter);

        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Object item = adapterView.getItemAtPosition(i);

                if (item instanceof String){
                    chosenTaskName = (String) item;
                    System.out.println(chosenTaskName);
                    recordViewModel.getRecordsByTaskName(chosenTaskName).observe(getViewLifecycleOwner(), (Observer<? super List<RecordEntity>>) o -> {
                        System.out.println(o);
                        recordRecyclerAdapter.submitList(o);
                    });
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}