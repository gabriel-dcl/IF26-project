package fr.utt.if26.duciel_projet.controller.tasks;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fr.utt.if26.duciel_projet.R;
import fr.utt.if26.duciel_projet.controller.MainActivity;
import fr.utt.if26.duciel_projet.controller.tasks.adapters.TaskRecyclerAdapter;
import fr.utt.if26.duciel_projet.controller.tasks.addTask.AddTaskDialogFragment;
import fr.utt.if26.duciel_projet.databinding.FragmentTasksBinding;
import fr.utt.if26.duciel_projet.models.entity.TaskEntity;
import fr.utt.if26.duciel_projet.viewModel.TasksViewModel;

public class TasksFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        TasksViewModel tasksViewModel = new TasksViewModel( this.getActivity().getApplication() );
        FragmentTasksBinding binding = FragmentTasksBinding.inflate(inflater, container, false);

        View root = binding.getRoot();

        RecyclerView recyclerView = root.findViewById(R.id.tasksRecyclerView);

        TaskRecyclerAdapter adapter = new TaskRecyclerAdapter( ((MainActivity) getActivity() ).getIconPack());

        tasksViewModel.getAllTasks().observe(getViewLifecycleOwner(), (Observer<? super List<TaskEntity>>) o ->
            adapter.submitList(o)
        );


        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this.getContext(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);

        Button popupButton = root.findViewById(R.id.buttonPopup);
        popupButton.setOnClickListener(v -> {

            AddTaskDialogFragment newFragment = new AddTaskDialogFragment();
            newFragment.show(getParentFragmentManager(), "");

        });

        return root;
    }

}