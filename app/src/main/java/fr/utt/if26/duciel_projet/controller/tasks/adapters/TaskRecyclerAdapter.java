package fr.utt.if26.duciel_projet.controller.tasks.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fr.utt.if26.duciel_projet.R;
import fr.utt.if26.duciel_projet.models.entity.TaskEntity;

public class TaskRecyclerAdapter extends ListAdapter<TaskEntity, TaskRecyclerAdapter.ModuleHolder> {

    public TaskRecyclerAdapter() {
        super(taskEntityItemCallback);
    }

    @NonNull
    @Override
    public ModuleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.single_task, parent, false);

        return new ModuleHolder(view);
    }

    @Override
    public void submitList(@Nullable List<TaskEntity> list) {
        super.submitList(list);
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

    class ModuleHolder extends RecyclerView.ViewHolder{
        private TextView taskName ;

        ModuleHolder(View itemView) {
            super(itemView);

            this.taskName = itemView.findViewById(R.id.recordName);
        }

        void display(TaskEntity task)
        {
            this.taskName.setText(task.getName());
        }

    }
}
