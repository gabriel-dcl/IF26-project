package fr.utt.if26.duciel_projet.controller.tasks.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.maltaisn.icondialog.pack.IconPack;

import java.util.List;

import fr.utt.if26.duciel_projet.R;
import fr.utt.if26.duciel_projet.models.entity.TaskEntity;

public class TaskRecyclerAdapter extends ListAdapter<TaskEntity, TaskRecyclerAdapter.ModuleHolder> {
    private IconPack iconPack;

    public TaskRecyclerAdapter(IconPack iconPack) {
        super(taskEntityItemCallback);
        this.iconPack = iconPack;
    }

    @NonNull
    @Override
    public ModuleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.single_task, parent, false);

        return new ModuleHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ModuleHolder holder, int position) {

        holder.display(getItem(position));
    }


    private static final DiffUtil.ItemCallback<TaskEntity> taskEntityItemCallback
            = new DiffUtil.ItemCallback<TaskEntity>() {

        @Override
        public boolean areItemsTheSame(@NonNull TaskEntity oldItem, @NonNull TaskEntity newItem) {
            return oldItem.getName().equals(newItem.getName());
        }

        @Override
        public boolean areContentsTheSame(@NonNull TaskEntity oldItem, @NonNull TaskEntity newItem) {
            return oldItem.getName().equals(newItem.getName());
        }
    };

    class ModuleHolder extends RecyclerView.ViewHolder {
        private TextView taskName;
        private ImageView imageView;

        ModuleHolder(View itemView) {
            super(itemView);

            this.imageView = itemView.findViewById(R.id.recordIcon);
            this.taskName = itemView.findViewById(R.id.recordName);
        }

        void display(TaskEntity task) {
            this.taskName.setText(task.getName());
            if (iconPack.getIcon(task.getIconId()) != null)
                this.imageView.setImageDrawable(iconPack.getIcon(task.getIconId()).getDrawable());
        }

    }
}
