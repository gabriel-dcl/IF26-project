package fr.utt.if26.duciel_projet.controller.tasks;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.Layout;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.widget.EditText;

import androidx.fragment.app.DialogFragment;

import fr.utt.if26.duciel_projet.R;
import fr.utt.if26.duciel_projet.models.entity.TaskEntity;
import fr.utt.if26.duciel_projet.viewModel.TasksViewModel;

public class AddTaskDialogFragment extends DialogFragment {
    TasksViewModel tasksViewModel;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        this.tasksViewModel = new TasksViewModel(this.getActivity().getApplication());

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        builder.setView(inflater.inflate(R.layout.add_task_dialog, null))
                .setPositiveButton("Ajouter une task", (dialog, id) -> {
                    final EditText newTaskName = getDialog().findViewById(R.id.newTaskName);
                    TaskEntity taskEntity = new TaskEntity(newTaskName.getText().toString());

                    tasksViewModel.insertTask(taskEntity);
                })
                .setNegativeButton("Annuler", (dialog, id) -> {
                });

        final AlertDialog dialog = builder.create();

        dialog.show();

        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);

        final EditText newTaskName = dialog.findViewById(R.id.newTaskName);

        newTaskName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(TextUtils.isEmpty(editable)){
                    dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
                } else {
                    dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
                }
            }
        });

        return dialog;
    }
}
