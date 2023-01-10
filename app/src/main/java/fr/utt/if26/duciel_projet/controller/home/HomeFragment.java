package fr.utt.if26.duciel_projet.controller.home;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;

import fr.utt.if26.duciel_projet.R;
import fr.utt.if26.duciel_projet.databinding.FragmentHomeBinding;
import fr.utt.if26.duciel_projet.models.entity.TaskEntity;
import fr.utt.if26.duciel_projet.viewModel.HomeViewModel;
import fr.utt.if26.duciel_projet.viewModel.TasksViewModel;
public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private TasksViewModel tasksViewModel;
    private TaskEntity chosenTaskEntity;
    private ArrayAdapter<String> adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        tasksViewModel = new TasksViewModel( this.getActivity().getApplication() );

        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Spinner dropdown = root.findViewById(R.id.spinner1);
        this.adapter = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_spinner_dropdown_item);
        dropdown.setAdapter(adapter);

        tasksViewModel.getAllTasks().observe(getViewLifecycleOwner(), (Observer<? super List<TaskEntity>>) o -> {
            adapter.clear();

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                o.forEach(item -> {
                    adapter.add(item.getName());
                });
            }
        });

        dropdown.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                chosenTaskEntity = (TaskEntity) adapterView.getItemAtPosition(i);
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