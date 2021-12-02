package com.example.myfirstnotes.main.ui.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfirstnotes.R;
import com.example.myfirstnotes.main.domain.Note;

import java.util.ArrayList;
import java.util.Collection;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {

    interface OnNoteClicked{
        void onNoteClicked(Note note);
    }

    private final ArrayList<Note> notes = new ArrayList<>();



    private OnNoteClicked noteClicked;

    public void setNotes(Collection<Note> toSet){
        notes.clear();
        notes.addAll(toSet);
    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_note, parent, false);

        return new NotesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {

        Note note = notes.get(position);

        holder.getNoteName().setText(note.getNameNote());
        holder.getNoteCategory().setText(note.getCategoryNote());
        holder.getNoteDate().setText(note.getDateNote());
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public OnNoteClicked getNoteClicked() {
        return noteClicked;
    }

    public void setNoteClicked(OnNoteClicked noteClicked) {
        this.noteClicked = noteClicked;
    }

    class NotesViewHolder extends RecyclerView.ViewHolder {

        private final TextView noteName;
        private final TextView noteCategory;
        private final TextView noteDate;

        public TextView getNoteName() {
            return noteName;
        }

        public TextView getNoteCategory() {
            return noteCategory;
        }

        public TextView getNoteDate() {
            return noteDate;
        }

        public NotesViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (getNoteClicked() != null){
                        getNoteClicked().onNoteClicked(notes.get(getAdapterPosition()));
                    }
                }
            });

            noteName = itemView.findViewById(R.id.note_name);
            noteCategory = itemView.findViewById(R.id.note_category);
            noteDate = itemView.findViewById(R.id.note_date);
        }
    }
}
