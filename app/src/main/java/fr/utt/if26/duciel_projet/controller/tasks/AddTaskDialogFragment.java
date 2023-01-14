package fr.utt.if26.duciel_projet.controller.tasks;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.Layout;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.maltaisn.icondialog.IconDialog;
import com.maltaisn.icondialog.IconDialogSettings;
import com.maltaisn.icondialog.data.Icon;
import com.maltaisn.icondialog.pack.IconPack;
import com.maltaisn.icondialog.pack.IconPackLoader;
import com.maltaisn.iconpack.defaultpack.IconPackDefault;

import java.util.List;

import fr.utt.if26.duciel_projet.R;
import fr.utt.if26.duciel_projet.controller.MainActivity;
import fr.utt.if26.duciel_projet.models.entity.TaskEntity;
import fr.utt.if26.duciel_projet.viewModel.TasksViewModel;

public class AddTaskDialogFragment extends DialogFragment  implements IconDialog.Callback {
    private TasksViewModel tasksViewModel;
    private IconDialog iconDialog;
    private ImageView imageView;

    @Nullable
    private IconPack iconPack;

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


        this.imageView = dialog.findViewById(R.id.iconSelected);

            Button iconButton = dialog.findViewById(R.id.iconSelector);
        iconButton.setOnClickListener(view ->
        {
            this.iconDialog =
                    IconDialog.newInstance(new IconDialogSettings.Builder().build());
            if(iconDialog != null)
                show();
        });

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

public void show(){
    this.iconDialog.show(getChildFragmentManager(), "sefsef");

}

    @Nullable
    @Override
    public IconPack getIconDialogIconPack() {
        return getIconPack();
    }


    @Override
    public void onIconDialogCancelled() {}


    @Nullable
    public IconPack getIconPack() {
        return iconPack != null ? iconPack : loadIconPack();
    }

    private IconPack loadIconPack() {
        // Create an icon pack loader with application context.
        IconPackLoader loader = new IconPackLoader(getContext());

        // Create an icon pack and load all drawables.
        iconPack = IconPackDefault.createDefaultIconPack(loader);
        iconPack.loadDrawables(loader.getDrawableLoader());

        return iconPack;
    }

    @Override
    public void onIconDialogIconsSelected(@NonNull IconDialog iconDialog, @NonNull List<Icon> list) {
        if(list.get(0) != null){
            System.out.println(list.get(0).getId());
            this.imageView.setImageDrawable(list.get(0).getDrawable() );

        }

    }


}
