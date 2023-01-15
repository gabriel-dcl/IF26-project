package fr.utt.if26.duciel_projet.controller.settings;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import fr.utt.if26.duciel_projet.R;
import fr.utt.if26.duciel_projet.databinding.FragmentSettingBinding;
import fr.utt.if26.duciel_projet.viewModel.GlobalSettingViewModel;
import fr.utt.if26.duciel_projet.viewModel.RecordViewModel;
import fr.utt.if26.duciel_projet.viewModel.TasksViewModel;

public class SettingFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FragmentSettingBinding binding = FragmentSettingBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setPositiveButton(R.string.reset_app_dialog_validate, (dialog, id) -> {
                    new TasksViewModel(getActivity().getApplication()).deleteAll();
                    new RecordViewModel(getActivity().getApplication()).deleteAll();
                    new GlobalSettingViewModel(getActivity().getApplication()).setFirstUsageSetting(true);
                })
                .setNegativeButton(R.string.cancel, (dialog, id) -> {
                })
                .setTitle(R.string.reset_app_dialog_title);

        final AlertDialog dialog = builder.create();

        Button resetApp = root.findViewById(R.id.reset_app_btn);

        resetApp.setOnClickListener(view ->
                dialog.show()
        );

        return root;
    }
}