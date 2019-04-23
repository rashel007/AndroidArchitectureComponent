package com.work.androidarchitecturecomponent;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteHolder> {

    private List<Note> notes = new ArrayList<>();

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.note_item, viewGroup, false);
        return new NoteHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder noteHolder, int pos) {

        Note currentNote = notes.get(pos);

        noteHolder.tvTitle.setText(currentNote.getTitle());
        noteHolder.tvDescription.setText(currentNote.getDescription());
        noteHolder.tvPriority.setText(String.valueOf(currentNote.getPriority()));
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public void setNotes(List<Note> notes){
        this.notes = notes;
        notifyDataSetChanged();
    }

    class NoteHolder extends RecyclerView.ViewHolder {

        private TextView tvTitle;
        private TextView tvPriority;
        private TextView tvDescription;

        public NoteHolder(@NonNull View itemView) {
            super(itemView);

            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvPriority = itemView.findViewById(R.id.tvPriority);
            tvTitle = itemView.findViewById(R.id.tvTitle);
        }
    }
}
