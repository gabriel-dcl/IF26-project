package fr.utt.if26.duciel_projet.controller.recordDetails;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.time.Duration;

import fr.utt.if26.duciel_projet.R;
import fr.utt.if26.duciel_projet.models.entity.RecordEntity;
import fr.utt.if26.duciel_projet.utils.DateUtil;

public class RecordRecyclerAdapter  extends ListAdapter<RecordEntity, RecordRecyclerAdapter.RecordHolder> {

    protected RecordRecyclerAdapter() {
        super(recordEntityItemCallback);
    }

    @NonNull
    @Override
    public RecordHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.single_record, parent, false);

        return new RecordRecyclerAdapter.RecordHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecordHolder holder, int position) {
        holder.display(getItem(position));
    }

    private static final DiffUtil.ItemCallback<RecordEntity> recordEntityItemCallback
            = new DiffUtil.ItemCallback<RecordEntity>() {

        @Override
        public boolean areItemsTheSame(@NonNull RecordEntity oldItem, @NonNull RecordEntity newItem) {
            return oldItem.getTaskName().equals(newItem.getTaskName());
        }

        @Override
        public boolean areContentsTheSame(@NonNull RecordEntity oldItem, @NonNull RecordEntity newItem) {
            return oldItem.getTaskName().equals(newItem.getTaskName());
        }
    };

    class RecordHolder extends RecyclerView.ViewHolder{
        private  TextView startDate;
        private TextView duration;

        RecordHolder(View itemView) {
            super(itemView);

            this.startDate = itemView.findViewById(R.id.startDate);
            this.duration = itemView.findViewById(R.id.duration);

        }

        void display(RecordEntity task)
        {
            this.startDate.setText(DateUtil.toFrenchDateFormat(task.getStartDate()));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                this.duration.setText(
                        DateUtil.durationInHoursAndMinutes(Duration.between(task.getStartDate(), task.getFinalDate()))  );
            }

        }

    }
}
